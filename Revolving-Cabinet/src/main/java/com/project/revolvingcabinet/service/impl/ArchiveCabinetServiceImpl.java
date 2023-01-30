package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.ArchiveCabinetMapper;
import com.project.revolvingcabinet.dao.CabinetDataMonitorMapper;
import com.project.revolvingcabinet.entity.ArchiveCabinet;
import com.project.revolvingcabinet.entity.CabinetDataMonitor;
import com.project.revolvingcabinet.modbus.ModBusConstants;
import com.project.revolvingcabinet.modbus.ModbusUtils;
import com.project.revolvingcabinet.service.ArchiveCabinetService;
import com.serotonin.modbus4j.ModbusMaster;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class ArchiveCabinetServiceImpl implements ArchiveCabinetService, ModBusConstants {

    @Resource
    private ArchiveCabinetMapper archiveCabinetMapper;

    @Resource
    private CabinetDataMonitorMapper cabinetDataMonitorMapper;


    /**
     * 通过档案柜编号查询档案柜id
     * @param cabinetCode 档案柜编号
     * @return 档案柜id
     */
    @Override
    public String findCabinetIdByCabinetCode(String cabinetCode) {
        return String.valueOf(archiveCabinetMapper.selectCabinetIdByCabinetCode(cabinetCode));
    }


    /**
     * 查找档案柜信息，一个下位机就只对应一个档案柜
     * @return
     */
    @Override
    public ArchiveCabinet getArchiveCabinet() {
        return archiveCabinetMapper.selectCabinetByAll();
    }


    /**
     * 获取档案柜的温度
     * @return
     */
    @Override
    public BigDecimal getArchiveCabinetTemperature() {
        return cabinetDataMonitorMapper.getLatestMonitorData().getTemperature();
    }
}
