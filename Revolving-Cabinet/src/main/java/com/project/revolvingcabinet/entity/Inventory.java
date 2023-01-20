package com.project.revolvingcabinet.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class Inventory {
    private Long inventoryId; // 主键
    private String inventoryName; // 名称
    private Integer inventoryNum; // 入库数量
    private Integer storageNoStart; // 开始储位
    private Integer storageNoEnd; // 结束储位
    private Integer inventoryType; // 盘点类型 1-入库盘点，2-库存盘点
    private Integer inventoryMethods; // 盘点方法： 1 - 单本盘点， 2 - 双本盘点
    private Integer statusFlag; // 状态：1-未盘点，2-部分盘点，3-已盘点
    private Timestamp startTime; // 开始时间
    private Timestamp endTime; // 结束时间
    private String remark; // 备注
    private Timestamp createTime; // 创建时间
    private Long createUser; // 创建人
    private Timestamp updateTime; // 修改时间
    private Long updateUser; // 修改人
    private String delFlag; // 是否删除：Y-已删除，N-未删除
    private Timestamp delTime; // 删除时间
    private Long delUser; // 删除人
    private String cabinetId; // 档案柜外键
    private String cabinetCode; // 档案柜编号

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", inventoryName='" + inventoryName + '\'' +
                ", inventoryNum=" + inventoryNum +
                ", storageNoStart=" + storageNoStart +
                ", storageNoEnd=" + storageNoEnd +
                ", inventoryType=" + inventoryType +
                ", inventoryMethods=" + inventoryMethods +
                ", statusFlag=" + statusFlag +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", delFlag='" + delFlag + '\'' +
                ", delTime=" + delTime +
                ", delUser=" + delUser +
                ", cabinetId='" + cabinetId + '\'' +
                ", cabinetCode='" + cabinetCode + '\'' +
                '}';
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public Integer getStorageNoStart() {
        return storageNoStart;
    }

    public void setStorageNoStart(Integer storageNoStart) {
        this.storageNoStart = storageNoStart;
    }

    public Integer getStorageNoEnd() {
        return storageNoEnd;
    }

    public void setStorageNoEnd(Integer storageNoEnd) {
        this.storageNoEnd = storageNoEnd;
    }

    public Integer getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(Integer inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Integer getInventoryMethods() {
        return inventoryMethods;
    }

    public void setInventoryMethods(Integer inventoryMethods) {
        this.inventoryMethods = inventoryMethods;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
}
