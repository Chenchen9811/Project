package com.project.revolvingcabinet.config;

import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.dao.CabinetDataMonitorMapper;
import com.project.revolvingcabinet.entity.CabinetDataMonitor;
import com.project.revolvingcabinet.modbus.ModBusConstants;
import com.project.revolvingcabinet.modbus.ModbusUtils;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class SchedulerTaskConfig implements ModBusConstants {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SchedulerTaskConfig.class.getName());

    @Resource
    private CabinetDataMonitorMapper cabinetDataMonitorMapper;

    //@Scheduled(cron = "*/5 * * * * ?")
    public void updateCabinetMonitorData() throws ModbusTransportException {
        String availablePortName = ModbusUtils.getAvailablePortName();
        ModbusMaster master = ModbusUtils.getSerialPortRtuMaster(availablePortName, BAUD_RATE, DATAd_BITS, STOP_BIT, PARITY);
        int temperature = 0, humidity = 0;
        BigDecimal tem = new BigDecimal("0.0");
        BigDecimal h = new BigDecimal("0.0");
        boolean plcSignal = true, rfidSignal = true, lightCurtainStatus = false;
        // 获取温度信息
        try {
            temperature = ModbusUtils.readSingleInputRegisterValue04(master, SLAVE_ID, ADDRESS_OFFSET_TEMPERATURE);
        } catch (ModbusTransportException e) {
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_018));
        }
        if (temperature != 0) {
            tem = this.intToBigDecimal(temperature);
        }
        // 获取湿度信息
        try {
            humidity = ModbusUtils.readSingleInputRegisterValue04(master, SLAVE_ID, ADDRESS_OFFSET_HUMIDITY);
        } catch (ModbusTransportException e) {
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_019));
        }
        if (humidity != 0) {
            h = this.intToBigDecimal(humidity);
        }

        // 获取plc通讯信号
        try {
            plcSignal = ModbusUtils.readInputState02(master, SLAVE_ID, ADDRESS_OFFSET_PLC_SIGNAL);
        } catch (ModbusTransportException e) {
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_020));
        }
        // 获取rfid通讯信号
        try {
            rfidSignal = ModbusUtils.readInputState02(master, SLAVE_ID, ADDRESS_OFFSET_RFID_SIGNAL);
        } catch (ModbusTransportException e) {
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_021));
        }

        // 获取光幕状态
        try {
            lightCurtainStatus = ModbusUtils.readInputState02(master, SLAVE_ID, ADDRESS_OFFSET_LIGHT_CURTAIN_SIGNAL);
        } catch (ModbusTransportException e) {
            throw new ModbusTransportException(Messages.getErrorMsg(Messages.MSG_E_LOG_022));
        }

        CabinetDataMonitor cabinetDataMonitor = new CabinetDataMonitor();
        cabinetDataMonitor.setId(CommonUtil.generateId());
        cabinetDataMonitor.setTemperature(tem);
        cabinetDataMonitor.setHumidity(h);
        cabinetDataMonitor.setPlcSignal(plcSignal? 1 : 0);
        cabinetDataMonitor.setRfidSignal(rfidSignal? 1 : 0);
        cabinetDataMonitor.setLightCurtainStatus(lightCurtainStatus? 1 : 0);
        cabinetDataMonitor.setCreateTime(new Timestamp(System.currentTimeMillis()));
        try {
            cabinetDataMonitorMapper.insertMonitorData(cabinetDataMonitor);
        } catch (Exception e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_006));
        }
        logger.info("更新档案柜检测数据成功！");
    }

    public BigDecimal intToBigDecimal(int temperature) {
        int ones = temperature % 10;
        temperature /= 10;
        int tens = temperature % 10;
        temperature /= 10;
        return new BigDecimal(temperature + "." + (tens * 10 + ones));
    }
}
