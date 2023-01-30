package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.common.CommonResult;
import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.controller.UserController;
import com.project.revolvingcabinet.modbus.ModBusConstants;
import com.project.revolvingcabinet.modbus.ModbusUtils;
import com.project.revolvingcabinet.service.OperationService;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OperationServiceImpl implements OperationService, ModBusConstants {
    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);


    /**
     * 开门
     * @return
     */
    @Override
    public String openDoor() throws ModbusTransportException {
        // 连接串口
        ModbusMaster master = ModbusUtils.getSerialPortRtuMaster(ModbusUtils.getAvailablePortName(), BAUD_RATE, DATAd_BITS, STOP_BIT, PARITY);
        // 执行开门指令
        try {
            ModbusUtils.writeOutputState05(master, SLAVE_ID, ADDRESS_OFFSET_OPEN_DOOR, true);
        } catch (ModbusTransportException e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_023));
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_023));
        }
        // 暂停线程2s
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isOpen = false;
        do {
            isOpen = ModbusUtils.readInputState02(master, SLAVE_ID, ADDRESS_OFFSET_DOOR_OPENED);
        } while (!isOpen);
        return Messages.getSuccessMsg(Messages.MSG_S_LOG_012);
    }

    /**
     * 关门
     * @return
     * @throws ModbusTransportException
     */
    @Override
    public String closeDoor() throws ModbusTransportException {
        // 连接串口
        ModbusMaster master = ModbusUtils.getSerialPortRtuMaster(ModbusUtils.getAvailablePortName(), BAUD_RATE, DATAd_BITS, STOP_BIT, PARITY);
        // 执行开门指令
        try {
            ModbusUtils.writeOutputState05(master, SLAVE_ID, ADDRESS_OFFSET_CLOSE_DOOR, true);
        } catch (ModbusTransportException e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_025));
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_025));
        }
        // 暂停线程2s
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isClose = false;
        do {
            isClose = ModbusUtils.readInputState02(master, SLAVE_ID, ADDRESS_OFFSET_DOOR_CLOSED);
        } while (!isClose);
        return Messages.getSuccessMsg(Messages.MSG_S_LOG_013);
    }

    /**
     * 获取当前层
     * @return
     * @throws ModbusTransportException
     */
    @Override
    public int getCurrentLayer() throws ModbusTransportException {
        // 连接端口
        ModbusMaster master = ModbusUtils.getSerialPortRtuMaster(ModbusUtils.getAvailablePortName(), BAUD_RATE, DATAd_BITS, STOP_BIT, PARITY);
        // 获取当前层
        int curLayer = 0;
        do {
            curLayer = ModbusUtils.readSingleHoldingRegisterValue03(master, SLAVE_ID, ADDRESS_OFFSET_CUR_LAYER);
        } while (curLayer == 0);
        return curLayer;
    }

    /**
     * 移层
     * @param targetLayer 目标层
     * @return
     */
    @Override
    public String moveLayer(int targetLayer) throws ModbusTransportException {
        // 获取当前层
        int currentLayer = 0;
        try {
            currentLayer = this.getCurrentLayer();
        } catch (ModbusTransportException e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_016));
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_016));
        }
        // 判断当前层和目标层是否一样
        if (targetLayer == currentLayer) {
            return Messages.getErrorMsg(Messages.MSG_E_LOG_027);
        }
        // 连接串口
        ModbusMaster master = ModbusUtils.getSerialPortRtuMaster(ModbusUtils.getAvailablePortName(), BAUD_RATE, DATAd_BITS, STOP_BIT, PARITY);
        // 发送移层指令
        ModbusUtils.writeOutputState05(master, SLAVE_ID, ADDRESS_OFFSET_MOVE_LAYER, true);
        // 线程暂停2s
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                currentLayer = ModbusUtils.readSingleHoldingRegisterValue03(master, SLAVE_ID, ADDRESS_OFFSET_CUR_LAYER);
            } catch (ModbusTransportException e) {
                logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_016));
                throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_016));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (currentLayer != targetLayer);
        return Messages.getSuccessMsg(Messages.MSG_S_LOG_014);
    }

    /**
     * 停止
     * @return
     */
    @Override
    public String stop() throws ModbusTransportException {
        // 连接串口
        ModbusMaster master = ModbusUtils.getSerialPortRtuMaster(ModbusUtils.getAvailablePortName(), BAUD_RATE, DATAd_BITS, STOP_BIT, PARITY);
        // 发送停止命令
        try {
            ModbusUtils.writeOutputState05(master, SLAVE_ID, ADDRESS_OFFSET_STOP, true);
        } catch (ModbusTransportException e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_028));
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_028));
        }
        return Messages.getSuccessMsg(Messages.MSG_S_LOG_015);
    }
}
