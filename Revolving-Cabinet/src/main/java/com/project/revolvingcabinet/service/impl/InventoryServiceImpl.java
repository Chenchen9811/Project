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
        // ????????????
        ModbusMaster com1 = ModbusUtils.getSerialPortRtuMaster(ModbusUtils.getAvailablePortName(), 115200, 8, 1, 0);
        // ????????????????????????RFID??????????????????
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


        // ????????????????????????RFID????????????
        if (curLayer != Integer.MIN_VALUE && maxRfidNums != Integer.MIN_VALUE && totalLayers != Integer.MIN_VALUE) {
            // ?????????????????????
            ArchiveCabinet archiveCabinet = archiveCabinetService.getArchiveCabinet();
            // RFID????????????
            int address = ADDRESS_OFFSET_READ_RFID_START;
            int count = 0;

            // ??????????????????
            int vacancyNum = 0;
            // ??????????????????
            Timestamp inventoryStartTime = new Timestamp(System.currentTimeMillis());
            // ????????????
            Integer storageNoStart = curLayer * 10 + 1; // curLayer * 10 + 1
            // ????????????
            int inventoryType = INVENTORY_TYPE_STOCK; // ????????????
            // ????????????
            int inventoryMethod = INVENTORY_METHOD_SINGLE; // ????????????
            // ????????????????????????
            Inventory inventoryBefore = this.initInventoryInfo(storageNoStart, inventoryType, inventoryMethod, INVENTORY_STATUS_NOT_CHECK);

            Long inventoryId = inventoryBefore.getInventoryId();
            // ???????????????????????????????????????
            int startLayer = curLayer;
            InventoryLayer inventoryLayer = inventoryLayerService.initInventoryLayerInfo(inventoryId, startLayer, INVENTORY_STATUS_LAYER_NOT_INVENTORY);
            while (count < totalLayers) {
                // ????????????
                ModbusUtils.writeOutputState05(com1, SLAVE_ID, ADDRESS_OFFSET_START_INVENTORY, true);
                // ????????????????????????????????????????????????true????????????????????????
                boolean inventoryStartSignal = false;
                do {
                    try {
                        inventoryStartSignal = ModbusUtils.readOutputState01(com1, SLAVE_ID, ADDRESS_OFFSET_START_INVENTORY);
                    } catch (ModbusTransportException e) {
                        logger.error(Messages.MSG_E_LOG_029);
                        throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_029));
                    }
                } while (!inventoryStartSignal);
                // ????????????500ms????????????????????????
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // ????????????
                address = ADDRESS_OFFSET_READ_RFID_START;
                // ???????????????
                for (int curColumn = 1; curColumn <= maxRfidNums; curColumn ++) {
                    // ??????????????????RFID??????
                    short[] results = new short[2];
                    try {
                        results = ModbusUtils.readMultiInputRegistersValue04(com1, SLAVE_ID, address, 2);
                    } catch (ModbusTransportException e) {
                        logger.error(Messages.MSG_E_LOG_012);
                        throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_012));
                    }
                    // ???????????????????????????????????????????????????
                    DevPos devPosInfoBeforeInventory = devPosService.findDevPosByCabinetCode(archiveCabinet.getCabinetCode(), curLayer, curColumn);
                    // ????????????????????????????????????????????????????????????????????????
                    if (devPosInfoBeforeInventory == null) {
                        devPosInfoBeforeInventory = devPosService.generateDevPosInfo(archiveCabinet.getCabinetCode(), curLayer, curColumn);
                    }
                    // ???????????????????????????????????????????????????????????????????????????
                    InventoryPos inventoryPosBeforeInventory = inventoryPosService.initInventoryPos(inventoryId, devPosInfoBeforeInventory.getId());
                    // ????????????????????????????????????????????????????????????
                    ArchiveBox archiveBoxBeforeInventory = archiveBoxService.findArchiveBoxByCabinetCodeAndPosInfo(archiveCabinet.getCabinetCode(), curLayer, curColumn);

                    // ?????????????????????RFID????????????????????????
                    switch (this.judgeInventoryResult(results)) {
                        // ?????????????????????
                        case INVENTORY_POS_STATUS_ANTENNA_BROKEN: {
                            // ??????????????????????????????????????????????????????????????????????????????????????????RFID???????????????????????????????????????????????????????????????????????????
                            // ??????????????????????????????????????????????????????????????????????????????
                            DevPos devPosInfoAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_EXCEPTION);
                            // ??????????????????????????????????????????????????????????????????????????????
                            // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                            InventoryPos inventoryPos = inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                    devPosInfoAfterInventory,
                                    inventoryPosBeforeInventory,
                                    INVENTORY_STATUS_POS_EXCEPTION,
                                    INVENTORY_POS_STATUS_ANTENNA_BROKEN);
                            break;
                        }
                        // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                        case INVENTORY_POS_STATUS_EXCEPTION: {
                            // ???????????????????????????????????????
                            if (archiveBoxBeforeInventory != null) {
                                // ??????????????????????????????
                                int archiveBoxStatus = archiveBoxBeforeInventory.getStatusFlag();
                                // ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                                if (archiveBoxStatus == ARCHIVE_BOX_STATUS_IN_CABINET) {
                                    // ?????????????????????????????????????????????????????????????????????????????????????????????
                                    DevPos devPosInfoAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_NORMAL);
                                    // ???????????????????????????????????????
                                    archiveBoxService.updateArchiveBoxAfterInventory(ARCHIVE_BOX_STATUS_RFID_BROKEN, archiveBoxBeforeInventory.getBoxId(), 0, 0, null);
                                    // ??????????????????????????????????????????????????????????????????????????????
                                    inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                            devPosInfoAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_EXCEPTION,
                                            INVENTORY_POS_STATUS_EXCEPTION);
                                }
                                // ????????????????????????????????????????????????????????????????????????????????????????????????
//                                else if (archiveBoxStatus == ARCHIVE_BOX_STATUS_BORROWED) {
//                                   ??????????????????
//                                }
                            }
                            // ???????????????
                            break;
                        }
                        // ?????????????????????????????????????????????????????????
                        case INVENTORY_POS_STATUS_NORMAL: {
                            // ???????????????????????????????????????
                            // ?????????????????????????????????
                            if (archiveBoxBeforeInventory != null) {
                                // ??????????????????????????????????????????????????????????????????????????????????????????????????????
                                if (archiveBoxBeforeInventory.getStatusFlag() == ARCHIVE_BOX_STATUS_IN_CABINET &&
                                        Integer.valueOf(archiveBoxBeforeInventory.getRfid()) == CommonUtil.getRfidThoughModbus(results)) {
                                    // ??????????????????????????????????????????????????????????????????
                                    ArchiveBox archiveBoxAfterInventory = archiveBoxService.updateArchiveBoxAfterInventory(ARCHIVE_BOX_STATUS_IN_CABINET,
                                            archiveBoxBeforeInventory.getBoxId(), curLayer, curColumn,
                                            curLayer + "-" + curColumn);
                                    // ??????????????????
                                    DevPos devPosAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxAfterInventory, DEV_POS_STATUS_NORMAL);
                                    // ???????????????????????????????????????????????????????????????????????????
                                    inventoryPosService.updateInventoryPos(archiveBoxAfterInventory, devPosAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_SUCCESS,
                                            INVENTORY_POS_STATUS_NORMAL);
                                }
                                // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//                                else {
//
//                                }
                            }
                            // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
//                            else {
//
//                            }
                            break;
                        }
                        // ??????????????????
                        case INVENTORY_POS_STATUS_IS_EMPTY: {
                            // ?????????????????????????????????????????????
                            // ???????????????????????????????????????
                            if (archiveBoxBeforeInventory != null) {
                                // ?????????????????????
                                int archiveBoxStatus = archiveBoxBeforeInventory.getStatusFlag();
                                // ?????????????????????????????????????????????????????????
                                if (archiveBoxStatus == ARCHIVE_BOX_STATUS_BORROWED) {
                                    // ????????????????????????????????????????????????????????????????????????
                                    DevPos devPosAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_NORMAL);
                                    // ????????????????????????????????????????????????????????????
                                    inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                            devPosAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_SUCCESS,
                                            INVENTORY_POS_STATUS_IS_EMPTY);
                                }
                                // ?????????????????????????????????????????????????????????????????????????????????????????? + 1
                                else if (archiveBoxStatus == ARCHIVE_BOX_STATUS_REMOVED) {
                                    // ????????? + 1
                                    vacancyNum ++;
                                    // ????????????????????????????????????????????????????????????????????????
                                    DevPos devPosAfterInventory = devPosService.updateDevPosAfterInventory(devPosInfoBeforeInventory, archiveBoxBeforeInventory, DEV_POS_STATUS_NORMAL);
                                    // ????????????????????????????????????????????????????????????
                                    inventoryPosService.updateInventoryPos(archiveBoxBeforeInventory,
                                            devPosAfterInventory,
                                            inventoryPosBeforeInventory,
                                            INVENTORY_STATUS_POS_SUCCESS,
                                            INVENTORY_POS_STATUS_IS_EMPTY);
                                }
                                // ??????????????????????????????????????????????????????????????????

                            }
                            break;
                        }
                        default: break;
                    };
                    // ??????????????????????????????????????????????????????+2.
                    address += 2;
                }
                // ?????????????????????
                // ???????????????
                int targetLayer = curLayer + 1;
                if (targetLayer > totalLayers) targetLayer = 1;
                // ???????????????
                try {
                    ModbusUtils.writeSingleRegister06(com1, SLAVE_ID, ADDRESS_OFFSET_TARGET_LAYER, targetLayer);
                } catch (ModbusTransportException e) {
                    logger.error(Messages.MSG_E_LOG_013);
                    throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_013));
                }
                // ??????
                try {
                    ModbusUtils.writeOutputState05(com1, SLAVE_ID, ADDRESS_OFFSET_MOVE_LAYER, true);
                } catch (ModbusTransportException e) {
                    logger.error(Messages.MSG_E_LOG_014);
                    throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_014));
                }
                // ?????????????????????????????????
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // ???????????????????????????????????????
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
            // ?????????????????????
            int endLayer = startLayer - 1 == 0? totalLayers : curLayer;
            // ????????????????????????
            inventoryLayerService.updateInventoryLayerInfo(vacancyNum, endLayer, INVENTORY_STATUS_LAYER_FINISHED, inventoryLayer);
            // ????????????????????????????????????
            Timestamp inventoryEndTime = new Timestamp(System.currentTimeMillis());
            // ????????????
            int storageNoEnd = endLayer * 100 + maxRfidNums;
            // ????????????????????????
            this.updateInventoryInfo(storageNoEnd, INVENTORY_STATUS_ALL, inventoryBefore);
        } else {
            // ????????????RFID??????????????????
        }


        return null;
    }




    /**
     * ?????????????????????
     * 0????????????????????????
     * F??????????????????????????????????????????????????????
     * ?????????????????????????????????
     * ???????????????????????????
     * @param results ???????????????
     * @return ????????????
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
     * ????????????????????????
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
     * ????????????????????????????????????
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
