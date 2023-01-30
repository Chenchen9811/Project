package com.project.revolvingcabinet.service;

import com.serotonin.modbus4j.exception.ModbusTransportException;

public interface OperationService {

    /**
     * 开门操作
     * @return
     */
    String openDoor() throws ModbusTransportException;

    /**
     * 关门操作
     * @return
     */
    String closeDoor() throws ModbusTransportException;

    /**
     * 获取当前层
     * @return
     * @throws ModbusTransportException
     */
    int getCurrentLayer() throws ModbusTransportException;

    /**
     * 移层
     * @param targetLayer 目标层
     * @return
     */
    String moveLayer(int targetLayer) throws ModbusTransportException;


    /**
     * 停止
     * @return
     */
    String stop() throws ModbusTransportException;

}
