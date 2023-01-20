package com.project.revolvingcabinet.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

// 储位信息
public class DevPos {

    private Long id; // 主键
    private Integer unitNo; // 单元号
    private Integer layerNo; // 层号
    private Integer columnNo; // 列号
    private BigDecimal xPos; // x坐标
    private BigDecimal yPos; // y坐标
    private BigDecimal zPos; // z坐标
    private String boxNo1; // 内侧档案盒编号
    private String boxNo2; // 外侧档案盒编号
    private Integer storageNo; // 仓位编号
    private Integer boxStatus; // 档案盒状态
    private Integer statusFlag; // 状态：1 - 正常; 2 - 未扫锚; 3 - 异常; 4 - 锁定; 5 - 保留
    private Integer boxNo1StatusFlag; // 档案盒1状态
    private Integer boxNo2StatusFlag; // 档案盒2状态
    private String boxRfid1; // 内侧档案盒RFID
    private String boxRfid2; // 外侧档案盒RFID

    private Timestamp createTime; // 创建时间
    private Long createUser; // 创建人
    private Timestamp updateTime; // 更新时间
    private Long updateUser; // 更新人
    private String delFlag; // 是否删除
    private Timestamp delTime; // 删除时间
    private Long delUser; // 删除人
    private String cabinetId; // 档案柜外键
    private String cabinetCode; // 档案柜编号


    @Override
    public String toString() {
        return "DevPos{" +
                "id=" + id +
                ", unitNo=" + unitNo +
                ", layerNo=" + layerNo +
                ", columnNo=" + columnNo +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", zPos=" + zPos +
                ", boxNo1='" + boxNo1 + '\'' +
                ", boxNo2='" + boxNo2 + '\'' +
                ", storageNo=" + storageNo +
                ", boxStatus=" + boxStatus +
                ", statusFlag=" + statusFlag +
                ", boxNo1StatusFlag=" + boxNo1StatusFlag +
                ", boxNo2StatusFlag=" + boxNo2StatusFlag +
                ", boxRfid1='" + boxRfid1 + '\'' +
                ", boxRfid2='" + boxRfid2 + '\'' +
                ", cabinetId='" + cabinetId + '\'' +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", delFlag='" + delFlag + '\'' +
                ", delTime=" + delTime +
                ", delUser=" + delUser +
                ", cabinetCode='" + cabinetCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getxPos() {
        return xPos;
    }

    public void setxPos(BigDecimal xPos) {
        this.xPos = xPos;
    }

    public BigDecimal getyPos() {
        return yPos;
    }

    public void setyPos(BigDecimal yPos) {
        this.yPos = yPos;
    }

    public BigDecimal getzPos() {
        return zPos;
    }

    public void setzPos(BigDecimal zPos) {
        this.zPos = zPos;
    }

    public String getBoxNo1() {
        return boxNo1;
    }

    public void setBoxNo1(String boxNo1) {
        this.boxNo1 = boxNo1;
    }

    public String getBoxNo2() {
        return boxNo2;
    }

    public void setBoxNo2(String boxNo2) {
        this.boxNo2 = boxNo2;
    }

    public Integer getStorageNo() {
        return storageNo;
    }

    public void setStorageNo(Integer storageNo) {
        this.storageNo = storageNo;
    }

    public Integer getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Integer boxStatus) {
        this.boxStatus = boxStatus;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
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

    public String getBoxRfid1() {
        return boxRfid1;
    }

    public void setBoxRfid1(String boxRfid1) {
        this.boxRfid1 = boxRfid1;
    }

    public String getBoxRfid2() {
        return boxRfid2;
    }

    public void setBoxRfid2(String boxRfid2) {
        this.boxRfid2 = boxRfid2;
    }

    public String getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(String cabinetId) {
        this.cabinetId = cabinetId;
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
}
