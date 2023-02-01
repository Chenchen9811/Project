package com.project.revolvingcabinet.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CabinetDataMonitor {
    private Long id;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private Integer plcSignal;
    private Integer rfidSignal;
    private Integer lightCurtainStatus;
    private String upperComputerNetworkState; // 上位机通讯信号
    private String cabinetId; // 档案柜外键
    private String cabinetCode; // 档案柜编号
    private Timestamp createTime;

    public String getUpperComputerNetworkState() {
        return upperComputerNetworkState;
    }

    public void setUpperComputerNetworkState(String upperComputerNetworkState) {
        this.upperComputerNetworkState = upperComputerNetworkState;
    }

    @Override
    public String toString() {
        return "CabinetDataMonitor{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", plcSignal=" + plcSignal +
                ", rfidSignal=" + rfidSignal +
                ", lightCurtainStatus=" + lightCurtainStatus +
                ", upperComputerNetworkState='" + upperComputerNetworkState + '\'' +
                ", cabinetId='" + cabinetId + '\'' +
                ", cabinetCode='" + cabinetCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(String cabinetId) {
        this.cabinetId = cabinetId;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public Integer getPlcSignal() {
        return plcSignal;
    }

    public void setPlcSignal(Integer plcSignal) {
        this.plcSignal = plcSignal;
    }

    public Integer getRfidSignal() {
        return rfidSignal;
    }

    public void setRfidSignal(Integer rfidSignal) {
        this.rfidSignal = rfidSignal;
    }

    public Integer getLightCurtainStatus() {
        return lightCurtainStatus;
    }

    public void setLightCurtainStatus(Integer lightCurtainStatus) {
        this.lightCurtainStatus = lightCurtainStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
