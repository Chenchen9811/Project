package com.project.revolvingcabinet.service;


import com.project.revolvingcabinet.common.CommonResult;
import com.serotonin.modbus4j.exception.ModbusTransportException;

import java.util.Map;

public interface InventoryService {

    /**
     * 盘库
     * @throws ModbusTransportException
     */
    CommonResult inventory() throws ModbusTransportException;

    /**
     * 判断储位返回值,
     * 0表示储位是空的 ，
     * F表示有档案盒没有标签或者标签是坏的
     * 有数字读出来表示正常，
     * 空的表示天线是坏的
     * @param results 储位返回值
     * @return 判断结果
     */
    String judgeInventoryResult(short[] results);
}
