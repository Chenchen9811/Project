package com.project.revolvingcabinet.entity;

import java.sql.Timestamp;

public class ArchiveCabinet {
    private Long cabinetId; // 档案柜ID
    private String cabinetName; // 档案柜名称
    private String cabinetCode; // 档案柜编号
    private String cabinetType; // 档案柜类型：1 - 机器人; 2 - 回转柜; 3 - RFID盒定位
    private String agreement; // 接口协议
    private String ip; // 接口ip
    private Integer port; // 接口端口
    private String virtualDirectory; // 接口虚拟目录
    private Integer webSocketPort; // webSocket端口
    private String dbConnection; // 数据库连接串
    private Integer statusFlag; // 状态： 1 - 在线; 2 - 离线; 3 - 停用
    private String remark; // 备注
    private Timestamp createTime; // 创建时间
    private Long createUser; // 创建人
    private Timestamp updateTime; // 更新时间
    private Long updateUser; // 更新人
    private String delFlag; // 是否删除
    private Timestamp delTime; // 删除时间
    private Long delUser; // 删除人
    private Integer layerTotal; // 总层数设置2~255
    private Integer layerCurrent; // 当前层修改值1~255
    private Integer layerTarget; // 目标层修改值~255
    private Integer ledTotal; // 数码管最大数量~255
    private Integer rfidTotal; // RFID最大数量2~255
    private String lightCurtainFlag; // 光幕启用信号；Y - 启用，N - 禁用
    private String aiFlag; // 指纹启用信号；Y - 启用，N - 禁用
    private String rfidFlag; // RFID启用信号；Y - 启用，N - 禁用
    private Integer layerProtectTime; // 层运行保护时间(默认4000ms)
    private Integer doorProtectTime;  // 开关门保护时间(默认4000ms)
    private Integer singleUpDelayedTime; // 单层上行延时时间(默认4000ms)
    private Integer singleDownDelayedTime; // 单层下行延时时间(默认4000ms)
    private Integer multipleTupDelayedTime; // 多层上行延时时间(默认4000ms)
    private Integer multipleDownDelayedTime; // 多层下行延时时间(默认4000ms)
    private Integer upDelayTime; // 上行延时停时间(默认4000ms)
    private Integer downDelayedTime; // 下行延时停时间(默认4000ms)


    @Override
    public String toString() {
        return "ArchiveCabinet{" +
                "cabinetId=" + cabinetId +
                ", cabinetName='" + cabinetName + '\'' +
                ", cabinetCode='" + cabinetCode + '\'' +
                ", cabinetType='" + cabinetType + '\'' +
                ", agreement='" + agreement + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", virtualDirectory='" + virtualDirectory + '\'' +
                ", webSocketPort=" + webSocketPort +
                ", dbConnection='" + dbConnection + '\'' +
                ", statusFlag=" + statusFlag +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", delFlag='" + delFlag + '\'' +
                ", delTime=" + delTime +
                ", delUser=" + delUser +
                ", layerTotal=" + layerTotal +
                ", layerCurrent=" + layerCurrent +
                ", layerTarget=" + layerTarget +
                ", ledTotal=" + ledTotal +
                ", rfidTotal=" + rfidTotal +
                ", lightCurtainFlag='" + lightCurtainFlag + '\'' +
                ", aiFlag='" + aiFlag + '\'' +
                ", rfidFlag='" + rfidFlag + '\'' +
                ", layerProtectTime=" + layerProtectTime +
                ", doorProtectTime=" + doorProtectTime +
                ", singleUpDelayedTime=" + singleUpDelayedTime +
                ", singleDownDelayedTime=" + singleDownDelayedTime +
                ", multipleTupDelayedTime=" + multipleTupDelayedTime +
                ", multipleDownDelayedTime=" + multipleDownDelayedTime +
                ", upDelayTime=" + upDelayTime +
                ", downDelayedTime=" + downDelayedTime +
                '}';
    }

    public Integer getLayerTotal() {
        return layerTotal;
    }

    public void setLayerTotal(Integer layerTotal) {
        this.layerTotal = layerTotal;
    }

    public Integer getLayerCurrent() {
        return layerCurrent;
    }

    public void setLayerCurrent(Integer layerCurrent) {
        this.layerCurrent = layerCurrent;
    }

    public Integer getLayerTarget() {
        return layerTarget;
    }

    public void setLayerTarget(Integer layerTarget) {
        this.layerTarget = layerTarget;
    }

    public Integer getLedTotal() {
        return ledTotal;
    }

    public void setLedTotal(Integer ledTotal) {
        this.ledTotal = ledTotal;
    }

    public Integer getRfidTotal() {
        return rfidTotal;
    }

    public void setRfidTotal(Integer rfidTotal) {
        this.rfidTotal = rfidTotal;
    }

    public String getLightCurtainFlag() {
        return lightCurtainFlag;
    }

    public void setLightCurtainFlag(String lightCurtainFlag) {
        this.lightCurtainFlag = lightCurtainFlag;
    }

    public String getAiFlag() {
        return aiFlag;
    }

    public void setAiFlag(String aiFlag) {
        this.aiFlag = aiFlag;
    }

    public String getRfidFlag() {
        return rfidFlag;
    }

    public void setRfidFlag(String rfidFlag) {
        this.rfidFlag = rfidFlag;
    }

    public Integer getLayerProtectTime() {
        return layerProtectTime;
    }

    public void setLayerProtectTime(Integer layerProtectTime) {
        this.layerProtectTime = layerProtectTime;
    }

    public Integer getDoorProtectTime() {
        return doorProtectTime;
    }

    public void setDoorProtectTime(Integer doorProtectTime) {
        this.doorProtectTime = doorProtectTime;
    }

    public Integer getSingleUpDelayedTime() {
        return singleUpDelayedTime;
    }

    public void setSingleUpDelayedTime(Integer singleUpDelayedTime) {
        this.singleUpDelayedTime = singleUpDelayedTime;
    }

    public Integer getSingleDownDelayedTime() {
        return singleDownDelayedTime;
    }

    public void setSingleDownDelayedTime(Integer singleDownDelayedTime) {
        this.singleDownDelayedTime = singleDownDelayedTime;
    }

    public Integer getMultipleTupDelayedTime() {
        return multipleTupDelayedTime;
    }

    public void setMultipleTupDelayedTime(Integer multipleTupDelayedTime) {
        this.multipleTupDelayedTime = multipleTupDelayedTime;
    }

    public Integer getMultipleDownDelayedTime() {
        return multipleDownDelayedTime;
    }

    public void setMultipleDownDelayedTime(Integer multipleDownDelayedTime) {
        this.multipleDownDelayedTime = multipleDownDelayedTime;
    }

    public Integer getUpDelayTime() {
        return upDelayTime;
    }

    public void setUpDelayTime(Integer upDelayTime) {
        this.upDelayTime = upDelayTime;
    }

    public Integer getDownDelayedTime() {
        return downDelayedTime;
    }

    public void setDownDelayedTime(Integer downDelayedTime) {
        this.downDelayedTime = downDelayedTime;
    }

    public Long getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(Long cabinetId) {
        this.cabinetId = cabinetId;
    }

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public String getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(String cabinetType) {
        this.cabinetType = cabinetType;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getVirtualDirectory() {
        return virtualDirectory;
    }

    public void setVirtualDirectory(String virtualDirectory) {
        this.virtualDirectory = virtualDirectory;
    }

    public Integer getWebSocketPort() {
        return webSocketPort;
    }

    public void setWebSocketPort(Integer webSocketPort) {
        this.webSocketPort = webSocketPort;
    }

    public String getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(String dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Timestamp getDelTime() {
        return delTime;
    }

    public void setDelTime(Timestamp delTime) {
        this.delTime = delTime;
    }

    public Long getDelUser() {
        return delUser;
    }

    public void setDelUser(Long delUser) {
        this.delUser = delUser;
    }
}
