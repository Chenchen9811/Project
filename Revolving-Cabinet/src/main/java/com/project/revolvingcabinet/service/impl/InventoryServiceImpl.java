package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.common.CommonResult;
import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.controller.UserController;
import com.project.revolvingcabinet.dao.DevPosMapper;
import com.project.revolvingcabinet.dao.InventoryMapper;
import com.project.revolvingcabinet.entity.*;
import com.project.revolvingcabinet.modbus.ModBusConstants;
import com.project.revolvingcabinet.modbus.ModbusUtils;
import com.project.revolvingcabinet.service.*;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.project.revolvingcabinet.utils.HostHolder;
import com.project.revolvingcabinet.utils.RevolvingCabinetConstants;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("LanguageDetectionInspection")
@Service
public class InventoryServiceImpl implements InventoryService, ModBusConstants, RevolvingCabinetConstants {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Resource
    private HostHolder hostHolder;

    @Resource
    private DevPosService devPosService;

    @Resource
    private ArchiveCabinetService archiveCabinetService;

    @Resource
    private ArchiveBoxService archiveBoxService;

    @Resource
    private InventoryPosService inventoryPosService;

    @Resource
    private InventoryLayerService inventoryLayerService;

    @Resource
    private InventoryMapper inventoryMapper;



    @Override
    public CommonResult inventory() throws ModbusTransportException{
        // 连接串口
        ModbusMaster com1 = ModbusUtils.getSerialPortRtuMaster(ModbusUtils.getAvailablePortName(), 115200, 8, 1, 0);
        // 获取当前层、最大RFID数量、总层数
        int curLayer = Integer.MIN_VALUE, maxRfidNums = Integer.MIN_VALUE, totalLayers = Integer.MIN_VALUE;
        try {
            curLayer = ModbusUtils.readSingleHoldingRegisterValue03(com1, SLAVE_ID, ADDRESS_OFFSET_CUR_LAYER);
        } catch (ModbusTransportException e) {
            logger.error(Messages.MSG_E_LOG_010);
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_010));
        }
        try {
            maxRfidNums = ModbusUtils.readSingleHoldingRegisterValue03(com1, SLAVE_ID, ADDRESS_OFFSET_MAX_RFID);
        } catch (ModbusTransportException e) {
            logger.error(Messages.MSG_E_LOG_011);
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_011));
        }
        try {
            totalLayers = ModbusUtils.readSingleHoldingRegisterValue03(com1, SLAVE_ID, ADDRESS_OFFSET_TOTAL_LAYER);
        } catch (ModbusTransportException e) {
            logger.error(Messages.MSG_E_LOG_012);
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_012));
        }


        // 获取当前层和最大RFID数量成功
        if (curLayer != Integer.MIN_VALUE && maxRfidNums != Integer.MIN_VALUE && totalLayers != Integer.MIN_VALUE) {
            // 获取档案柜信息
            ArchiveCabinet archiveCabinet = archiveCabinetService.getArchiveCabinet();
            // RFID开始地址
            int address = ADDRESS_OFFSET_READ_RFID_START;
            int count = 0;

            // 初始化空位数
            int vacancyNum = 0;
            // 盘库开始时间
            Timestamp inventoryStartTime = new Timestamp(System.currentTimeMillis());
            // 开始储位
            Integer storageNoStart = curLayer * 10 + 1; // curLayer * 10 + 1
            // 盘点类型
            int inventoryType = INVENTORY_TYPE_STOCK; // 库存盘点
            // 盘点方法
            int inventoryMethod = INVENTORY_METHOD_SINGLE; // 单本盘点
            // 初始化总盘库信息
            Inventory inventoryBefore = this.initInventoryInfo(storageNoStart, inventoryType, inventoryMethod, INVENTORY_STATUS_NOT_CHECK);

            Long inventoryId = inventoryBefore.getInventoryId();
            // 初始化本次盘库的层盘点信息
            int startLayer = curLayer;
            InventoryLayer inventoryLayer = inventoryLayerService.initInventoryLayerInfo(inventoryId, startLayer, INVENTORY_STATUS_LAYER_NOT_INVENTORY);
            while (count < totalLayers) {
                // 开始盘点
                ModbusUtils.writeOutputState05(com1, SLAVE_ID, ADDRESS_OFFSET_START_INVENTORY, true);
                // 读取盘点开始盘点信号，读到信号为true时才往下开始盘点
                boolean inventoryStartSignal = false;
                do {
                    try {
                        inventoryStartSignal = ModbusUtils.readOutputState01(com1, SLAVE_ID, ADDRESS_OFFSET_START_INVENTORY);
                    } catch (ModbusTransportException e) {
                        logger.error(Messages.MSG_E_LOG_029);
                        throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_029));
                    }
                } while (!inventoryStartSignal);
                // 暂停线程500ms等待硬件执行盘库
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 重置地址
                address = ADDRESS_OFFSET_READ_RFID_START;
                // 盘点当前层
                for (int curColumn = 1; curColumn <= maxRfidNums; curColumn ++) {
                    // 获取当前列的RFID信息
                    short[] results = new short[2];
                    try {
                        results = ModbusUtils.readMultiInputRegistersValue04(com1, SLAVE_ID, address, 2);
                    } catch (ModbusTransportException e) {
                        logger.error(Messages.MSG_E_LOG_012);
                        throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_012));
                    }
                    // 获取当前储位的储位信息（盘库之前）
                    DevPos devPosInfoBeforeInventory = devPosService.findDevPosByCabinetCode(archiveCabinet.getCabinetCode(), curLayer, curColumn);
                    // 如果储位信息为空，那么就生成一个初始版的储位信息
                    if (devPosInfoBeforeInventory == null) {
                        devPosInfoBeforeInventory = devPosService.generateDevPosInfo(archiveCabinet.getCabinetCode(), curLayer, curColumn);
                    }
                    // 生成一个初始版的储位盘库信息，盘库状态设置为盘点中
                    InventoryPos inventoryPosBeforeInventory = inventoryPosService.initInventoryPos(inventoryId, devPosInfoBeforeInventory.getId());
                    // 获取当前储位原来的档案盒信息（盘库之前）
                    ArchiveBox archiveBoxBeforeInventory = archiveBoxService.findArchiveBoxByCabinetCodeAndPosInfo(archiveCabinet.getCabinetCode(), curLayer, curColumn);

                    // 直接判断储位的RFID状态，即返回信息
                    switch (this.judgeInventoryResult(results)) {
                        // 储位天线是坏的
                        case INVENTORY_POS_STATUS_ANTENNA_BROKEN: {
                            // 直接更新该储位的储位信息，因为天线已坏，因此无法根据该储位的RFID信息更新档案盒信息，因此这种情况无需更新档案盒信息
                            // 更新储位的信息，天线是坏的情况主要更新储位的状态信息
                            DevPos devPosInfoAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_EXCEPTION);
                            // 更新该储位本次的盘库信息，储位盘库状态设置为盘库异常
                            // 储位盘库信息中有关档案盒的信息暂定用原来的，因为天线是坏的，无法得知该储位现在的档案盒信息
                            InventoryPos inventoryPos = inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                    devPosInfoAfterInventory,
                                    inventoryPosBeforeInventory,
                                    INVENTORY_STATUS_POS_EXCEPTION,
                                    INVENTORY_POS_STATUS_ANTENNA_BROKEN);
                            break;
                        }
                        // 扫描到了有档案盒但是档案盒没有标签或者是标签是坏的，因此获取不了该储位当前存放的档案盒信息
                        case INVENTORY_POS_STATUS_EXCEPTION: {
                            // 如果该储位原来有档案盒信息
                            if (archiveBoxBeforeInventory != null) {
                                // 判断原来档案盒的状态
                                int archiveBoxStatus = archiveBoxBeforeInventory.getStatusFlag();
                                // 如果原来档案盒的状态为在库，那么就可以判断现在该储位中的档案盒是原来的档案盒
                                if (archiveBoxStatus == ARCHIVE_BOX_STATUS_IN_CABINET) {
                                    // 这种情况直接更新储位状态即可，更新为正常状态。因为天线是好的。
                                    DevPos devPosInfoAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_NORMAL);
                                    // 更新档案盒的状态为标签损坏
                                    archiveBoxService.updateArchiveBoxAfterInventory(ARCHIVE_BOX_STATUS_RFID_BROKEN, archiveBoxBeforeInventory.getBoxId(), 0, 0, null);
                                    // 更新该储位本次的盘库信息，储位盘库状态设置为盘库异常
                                    inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                            devPosInfoAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_EXCEPTION,
                                            INVENTORY_POS_STATUS_EXCEPTION);
                                }
                                // 如果原来档案盒的状态为借出，那么该储位上存储的必定是错误的档案盒
//                                else if (archiveBoxStatus == ARCHIVE_BOX_STATUS_BORROWED) {
//                                   证明多档案了
//                                }
                            }
                            // 如果该储位
                            break;
                        }
                        // 该储位有档案盒并且能扫描到档案盒的标签
                        case INVENTORY_POS_STATUS_NORMAL: {
                            // 查询该储位有没有档案盒记录
                            // 如果该储位有档案盒记录
                            if (archiveBoxBeforeInventory != null) {
                                // 档案盒的状态为在库，并且比对比对读出的标签与档案盒表中的标签是否一致
                                if (archiveBoxBeforeInventory.getStatusFlag() == ARCHIVE_BOX_STATUS_IN_CABINET &&
                                        Integer.valueOf(archiveBoxBeforeInventory.getRfid()) == CommonUtil.getRfidThoughModbus(results)) {
                                    // 一致证明盘点成功，更新档案盒信息（更为在库）
                                    ArchiveBox archiveBoxAfterInventory = archiveBoxService.updateArchiveBoxAfterInventory(ARCHIVE_BOX_STATUS_IN_CABINET,
                                            archiveBoxBeforeInventory.getBoxId(), curLayer, curColumn,
                                            curLayer + "-" + curColumn);
                                    // 更新储位状态
                                    DevPos devPosAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxAfterInventory, DEV_POS_STATUS_NORMAL);
                                    // 更新该储位本次的盘库记录（更新盘库状态为盘库成功）
                                    inventoryPosService.updateInventoryPos(archiveBoxAfterInventory, devPosAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_SUCCESS,
                                            INVENTORY_POS_STATUS_NORMAL);
                                }
                                // 标签不一致证明该储位放了错误的档案盒，暂定更新储位的状态为异常，盘库的状态也为异常
//                                else {
//
//                                }
                            }
                            // 如果该储位没有档案盒记录，那么就证明多放了档案盒，暂定更新储位的状态为异常，盘库的状态也为异常
//                            else {
//
//                            }
                            break;
                        }
                        // 该储位是空的
                        case INVENTORY_POS_STATUS_IS_EMPTY: {
                            // 判断该储位原来有没有档案盒记录
                            // 如果该储位原来有档案盒记录
                            if (archiveBoxBeforeInventory != null) {
                                // 判断档案盒状态
                                int archiveBoxStatus = archiveBoxBeforeInventory.getStatusFlag();
                                // 如果档案盒是已借出状态，那么盘点正常。
                                if (archiveBoxStatus == ARCHIVE_BOX_STATUS_BORROWED) {
                                    // 更新储位状态，无需更新档案盒状态，设置为正常状态
                                    DevPos devPosAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_NORMAL);
                                    // 更新该储位的盘库信息，盘库状态为盘库成功
                                    inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                            devPosAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_SUCCESS,
                                            INVENTORY_POS_STATUS_IS_EMPTY);
                                }
                                // 如果档案盒的状态是已移交状态，那么也是盘点正常，此时空位数量 + 1
                                else if (archiveBoxStatus == ARCHIVE_BOX_STATUS_REMOVED) {
                                    // 空位数 + 1
                                    vacancyNum ++;
                                    // 更新储位状态，无需更新档案盒状态，设置为正常状态
                                    DevPos devPosAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_NORMAL);
                                    // 更新该储位的盘库信息，盘库状态为盘库成功
                                    inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                            devPosAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_SUCCESS,
                                            INVENTORY_POS_STATUS_IS_EMPTY);
                                }
                                // 如果档案盒是在库状态证明档案盒的记录是错误的

                            }
                            break;
                        }
                        default: break;
                    };
                    // 当前列盘点完毕，继续盘点下一列，地址+2.
                    address += 2;
                }
                // 当前层盘点完毕
                // 获取目标层
                int targetLayer = curLayer + 1;
                if (targetLayer > totalLayers) targetLayer = 1;
                // 修改目标层
                try {
                    ModbusUtils.writeSingleRegister06(com1, SLAVE_ID, ADDRESS_OFFSET_TARGET_LAYER, targetLayer);
                } catch (ModbusTransportException e) {
                    logger.error(Messages.MSG_E_LOG_013);
                    throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_013));
                }
                // 移层
                try {
                    ModbusUtils.writeOutputState05(com1, SLAVE_ID, ADDRESS_OFFSET_MOVE_LAYER, true);
                } catch (ModbusTransportException e) {
                    logger.error(Messages.MSG_E_LOG_014);
                    throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_014));
                }
                // 暂停当前线程，等待移层
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获取当前层，看移层是否到位
                do {
                    try {
                        curLayer = ModbusUtils.readSingleHoldingRegisterValue03(com1, SLAVE_ID, ADDRESS_OFFSET_CUR_LAYER);
                    } catch (ModbusTransportException e) {
                        logger.error(Messages.MSG_E_LOG_016);
                        throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_016));
                    }
                } while (curLayer != targetLayer);
                count ++;
            }
            // 所有层盘库完毕
            int endLayer = startLayer - 1 == 0? totalLayers : curLayer;
            // 更新层的盘库信息
            inventoryLayerService.updateInventoryLayerInfo(vacancyNum, endLayer, INVENTORY_STATUS_LAYER_FINISHED, inventoryLayer);
            // 更新本次盘库的总盘库信息
            Timestamp inventoryEndTime = new Timestamp(System.currentTimeMillis());
            // 结束储位
            int storageNoEnd = endLayer * 100 + maxRfidNums;
            // 盘库状态：已盘点
            this.updateInventoryInfo(storageNoEnd, INVENTORY_STATUS_ALL, inventoryBefore);
        } else {
            // 获取最大RFID和当前层失败
        }


        return null;
    }




    /**
     * 判断储位返回值
     * 0表示储位是空的，
     * F表示有档案盒没有标签或者标签是坏的，
     * 有数字读出来表示正常，
     * 空的表示天线是坏的
     * @param results 储位返回值
     * @return 判断结果
     */
    @Override
    public String judgeInventoryResult(short[] results) {
        if (results == null) {
            return INVENTORY_POS_STATUS_ANTENNA_BROKEN;
         } else if (results[0] == 0xff || results[1] == 0xff) {
            return INVENTORY_POS_STATUS_EXCEPTION;
        } else if (results[0] == 0 && results[1] == 0) {
            return INVENTORY_POS_STATUS_IS_EMPTY;
        } else return INVENTORY_POS_STATUS_NORMAL;
    }

    /**
     * 初始化总盘库信息
     * @param storageNoStart
     * @param inventoryType
     * @param inventoryMethod
     * @param statusFlag
     * @return
     */
    @Override
    public Inventory initInventoryInfo(Integer storageNoStart, int inventoryType, int inventoryMethod, int statusFlag) {
        ArchiveCabinet archiveCabinet = archiveCabinetService.getArchiveCabinet();
        Inventory inventory = new Inventory();
        inventory.setInventoryId(CommonUtil.generateId());
        inventory.setStorageNoStart(storageNoStart);
        inventory.setInventoryType(inventoryType);
        inventory.setInventoryMethods(inventoryMethod);
        inventory.setStatusFlag(statusFlag);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        inventory.setStartTime(startTime);
        inventory.setCreateTime(startTime);
        inventory.setCreateUser(hostHolder.getUser().getUserId());
        inventory.setCabinetId(String.valueOf(archiveCabinet.getCabinetId()));
        inventory.setCabinetCode(archiveCabinet.getCabinetCode());
        return inventoryMapper.initInventoryInfo(inventory) == 1? inventory : null;
    }


    /**
     * 盘库完成后更新总盘库信息
     * @param storageNoEnd
     * @param statusFlag
     * @param inventory
     * @return
     */
    @Override
    public int updateInventoryInfo(int storageNoEnd, int statusFlag, Inventory inventory) {
        inventory.setStorageNoEnd(storageNoEnd);
        inventory.setStatusFlag(statusFlag);
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        inventory.setEndTime(endTime);
        inventory.setUpdateTime(endTime);
        inventory.setUpdateUser(hostHolder.getUser().getUserId());
        return inventoryMapper.updateInventoryInfo(inventory);
    }
}
