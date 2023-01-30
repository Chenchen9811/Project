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
    private Timestamp createTime;

    @Override
    public String toString() {
        return "CabinetDataMonitor{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", plcSignal=" + plcSignal +
                ", rfidSignal=" + rfidSignal +
                ", lightCurtainStatus=" + lightCurtainStatus +
                ", createTime=" + createTime +
                '}';
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
