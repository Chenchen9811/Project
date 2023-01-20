package com.project.revolvingcabinet.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class ArchiveBox {

    private Long boxId; // 档案盒Id
    private String boxNo; // 档案盒编号
    private String boxName; // 档案盒名称
    private String boxLocation; // 档案盒所在的储位号
    private String rfid; // 档案盒的RFID
    private Date boxCreateTime; // 档案盒建档日期
    private Long outOfDate; // 档案盒保存年限
    private String content; // 档案盒主要内容
    private Long classId; // 档案盒分类Id
    private Integer statusFlag; // 档案盒状态
    private Integer unitNo; // 档案盒所处单元号
    private Integer layerNo; // 档案盒所处层号
    private Integer columnNo; // 档案盒所处列号
    private Integer posNo; // 档案盒所处位置号
    private String cabinetId; // 档案柜外键
    private String cabinetCode; // 档案柜编号
    private Timestamp createTime; // 创建时间
    private Long createUser; // 创建人
    private Timestamp updateTime; // 更新时间
    private Long updateUser; // 更新人
    private String delFlag; // 是否删除
    private Timestamp delTime; // 删除时间
    private Long delUser; // 删除人

    @Override
    public String toString() {
        return "ArchiveBox{" +
                "boxId=" + boxId +
                ", boxNo='" + boxNo + '\'' +
                ", boxName='" + boxName + '\'' +
                ", boxLocation='" + boxLocation + '\'' +
                ", rfid='" + rfid + '\'' +
                ", boxCreateTime=" + boxCreateTime +
                ", outOfDate=" + outOfDate +
                ", content='" + content + '\'' +
                ", classId=" + classId +
                ", statusFlag=" + statusFlag +
                ", unitNo=" + unitNo +
                ", layerNo=" + layerNo +
                ", columnNo=" + columnNo +
                ", posNo=" + posNo +
                ", cabinetId='" + cabinetId + '\'' +
                ", cabinetCode='" + cabinetCode + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", delFlag='" + delFlag + '\'' +
                ", delTime=" + delTime +
                ", delUser=" + delUser +
                '}';
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getBoxLocation() {
        return boxLocation;
    }

    public void setBoxLocation(String boxLocation) {
        this.boxLocation = boxLocation;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Date getBoxCreateTime() {
        return boxCreateTime;
    }

    public void setBoxCreateTime(Date boxCreateTime) {
        this.boxCreateTime = boxCreateTime;
    }

    public Long getOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(Long outOfDate) {
        this.outOfDate = outOfDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Integer getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo;
    }

    public Integer getLayerNo() {
        return layerNo;
    }

    public void setLayerNo(Integer layerNo) {
        this.layerNo = layerNo;
    }

    public Integer getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(Integer columnNo) {
        this.columnNo = columnNo;
    }

    public Integer getPosNo() {
        return posNo;
    }

    public void setPosNo(Integer posNo) {
        this.posNo = posNo;
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
