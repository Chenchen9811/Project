package com.project.revolvingcabinet.entity;

import java.sql.Timestamp;

public class InventoryPos {
    private Long id; // 主键
    private Long inventoryId; // 盘库信息外键
    private Long posId; // 储位id
    // 目标信息以外的存放的都是当前的储位信息，盘库就是比较目标信息和当前信息是否一致
    private Integer boxNo1StatusFlag; // 档案盒1状态
    private Integer boxNo2StatusFlag; // 档案盒2状态
    private String boxNo1Location; // 档案盒1位置
    private String boxNo2Location; // 档案盒2位置
    private Integer statusFlag; // 状态：1 - 待盘点，2 - 盘点中， 3 - 盘点成功， 4 - 盘点异常
    private String box1Rfid; // 内侧档案盒RFID;
    private String boxNo1; // 内侧档案盒编号
    // 目标信息是档案盒原来的状态信息
    private Long posIdTarget1; // 目标储位1id，通过档案盒表中的储位信息然后在储位表中查找储位id
    private String locationTarget1; // 目标位置1，存放档案盒表中的储位信息
    private Integer statusFlagTarget1; // 目标档案盒1状态，存放档案盒中的信息
    private Integer boxNo1ReadNum; // 内侧档案盒读卡次数

    private String box2Rfid; // 外侧档案盒RFID;
    private String boxNo2; // 外侧档案盒编号
    private Long posIdTarget2; // 目标储位2id
    private String locationTarget2; // 目标位置2
    private Integer statusFlagTarget2; // 目标档案盒2状态
    private Integer boxNo2ReadNum; // 外侧档案盒读卡次数

    private String message; // 过程
    private Timestamp createTime; // 创建时间
    private Long createUser; // 创建人
    private Timestamp updateTime; // 修改时间
    private Long updateUser; // 修改人
    private String delFlag; // 是否删除：Y-已删除，N-未删除
    private Timestamp delTime; // 删除时间
    private Long delUser; // 删除人

    private String boxNo1Old; // 内侧档案盒编号(盘库之前)
    private String boxNo2Old; // 外侧档案盒编号(盘库之前)
    private Integer boxNo1StatusFlagOld; // 内侧档案盒状态(盘库之前)
    private Integer boxNo2StatusFlagOld; // 外侧档案盒状态(盘库之前)
    private String taskId; // 流水号
    private Integer inventoryNum; // 盘库次数

    @Override
    public String toString() {
        return "InventoryPos{" +
                "id=" + id +
                ", inventoryId=" + inventoryId +
                ", posId=" + posId +
                ", boxNo1StatusFlag=" + boxNo1StatusFlag +
                ", boxNo2StatusFlag=" + boxNo2StatusFlag +
                ", boxNo1Location='" + boxNo1Location + '\'' +
                ", boxNo2Location='" + boxNo2Location + '\'' +
                ", statusFlag=" + statusFlag +
                ", box1Rfid='" + box1Rfid + '\'' +
                ", boxNo1='" + boxNo1 + '\'' +
                ", posIdTarget1=" + posIdTarget1 +
                ", locationTarget1='" + locationTarget1 + '\'' +
                ", statusFlagTarget1=" + statusFlagTarget1 +
                ", boxNo1ReadNum=" + boxNo1ReadNum +
                ", box2Rfid='" + box2Rfid + '\'' +
                ", boxNo2='" + boxNo2 + '\'' +
                ", posIdTarget2=" + posIdTarget2 +
                ", locationTarget2='" + locationTarget2 + '\'' +
                ", statusFlagTarget2=" + statusFlagTarget2 +
                ", boxNo2ReadNum=" + boxNo2ReadNum +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", delFlag='" + delFlag + '\'' +
                ", delTime=" + delTime +
                ", delUser=" + delUser +
                ", boxNo1Old='" + boxNo1Old + '\'' +
                ", boxNo2Old='" + boxNo2Old + '\'' +
                ", boxNo1StatusFlagOld=" + boxNo1StatusFlagOld +
                ", boxNo2StatusFlagOld=" + boxNo2StatusFlagOld +
                ", taskId='" + taskId + '\'' +
                ", inventoryNum=" + inventoryNum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getPosId() {
        return posId;
    }

    public void setPosId(Long posId) {
        this.posId = posId;
    }

    public Integer getBoxNo1StatusFlag() {
        return boxNo1StatusFlag;
    }

    public void setBoxNo1StatusFlag(Integer boxNo1StatusFlag) {
        this.boxNo1StatusFlag = boxNo1StatusFlag;
    }

    public Integer getBoxNo2StatusFlag() {
        return boxNo2StatusFlag;
    }

    public void setBoxNo2StatusFlag(Integer boxNo2StatusFlag) {
        this.boxNo2StatusFlag = boxNo2StatusFlag;
    }

    public String getBoxNo1Location() {
        return boxNo1Location;
    }

    public void setBoxNo1Location(String boxNo1Location) {
        this.boxNo1Location = boxNo1Location;
    }

    public String getBoxNo2Location() {
        return boxNo2Location;
    }

    public void setBoxNo2Location(String boxNo2Location) {
        this.boxNo2Location = boxNo2Location;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getBox1Rfid() {
        return box1Rfid;
    }

    public void setBox1Rfid(String box1Rfid) {
        this.box1Rfid = box1Rfid;
    }

    public String getBoxNo1() {
        return boxNo1;
    }

    public void setBoxNo1(String boxNo1) {
        this.boxNo1 = boxNo1;
    }

    public Long getPosIdTarget1() {
        return posIdTarget1;
    }

    public void setPosIdTarget1(Long posIdTarget1) {
        this.posIdTarget1 = posIdTarget1;
    }

    public String getLocationTarget1() {
        return locationTarget1;
    }

    public void setLocationTarget1(String locationTarget1) {
        this.locationTarget1 = locationTarget1;
    }

    public Integer getStatusFlagTarget1() {
        return statusFlagTarget1;
    }

    public void setStatusFlagTarget1(Integer statusFlagTarget1) {
        this.statusFlagTarget1 = statusFlagTarget1;
    }

    public Integer getBoxNo1ReadNum() {
        return boxNo1ReadNum;
    }

    public void setBoxNo1ReadNum(Integer boxNo1ReadNum) {
        this.boxNo1ReadNum = boxNo1ReadNum;
    }

    public String getBox2Rfid() {
        return box2Rfid;
    }

    public void setBox2Rfid(String box2Rfid) {
        this.box2Rfid = box2Rfid;
    }

    public String getBoxNo2() {
        return boxNo2;
    }

    public void setBoxNo2(String boxNo2) {
        this.boxNo2 = boxNo2;
    }

    public Long getPosIdTarget2() {
        return posIdTarget2;
    }

    public void setPosIdTarget2(Long posIdTarget2) {
        this.posIdTarget2 = posIdTarget2;
    }

    public String getLocationTarget2() {
        return locationTarget2;
    }

    public void setLocationTarget2(String locationTarget2) {
        this.locationTarget2 = locationTarget2;
    }

    public Integer getStatusFlagTarget2() {
        return statusFlagTarget2;
    }

    public void setStatusFlagTarget2(Integer statusFlagTarget2) {
        this.statusFlagTarget2 = statusFlagTarget2;
    }

    public Integer getBoxNo2ReadNum() {
        return boxNo2ReadNum;
    }

    public void setBoxNo2ReadNum(Integer boxNo2ReadNum) {
        this.boxNo2ReadNum = boxNo2ReadNum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getBoxNo1Old() {
        return boxNo1Old;
    }

    public void setBoxNo1Old(String boxNo1Old) {
        this.boxNo1Old = boxNo1Old;
    }

    public String getBoxNo2Old() {
        return boxNo2Old;
    }

    public void setBoxNo2Old(String boxNo2Old) {
        this.boxNo2Old = boxNo2Old;
    }

    public Integer getBoxNo1StatusFlagOld() {
        return boxNo1StatusFlagOld;
    }

    public void setBoxNo1StatusFlagOld(Integer boxNo1StatusFlagOld) {
        this.boxNo1StatusFlagOld = boxNo1StatusFlagOld;
    }

    public Integer getBoxNo2StatusFlagOld() {
        return boxNo2StatusFlagOld;
    }

    public void setBoxNo2StatusFlagOld(Integer boxNo2StatusFlagOld) {
        this.boxNo2StatusFlagOld = boxNo2StatusFlagOld;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }
}
