package com.project.revolvingcabinet.entity;

import java.sql.Timestamp;

public class InventoryLayer {

    private Long id; // 主键
    private Long inventoryId; // 盘库信息外键
    private Integer unitNo; // 单元号，这里可以看作档案柜编号
    private Integer startLayerNo; // 层开始号
    private Integer endLayerNo; // 层结束号
    private Integer vacancyNum; // 空位数
    private Integer statusFlag; // 状态：1 - 待盘点; 2 - 盘点中; 3 - 已盘点
    private String remark; // 备注
    private Timestamp createTime; // 创建时间
    private Long createUser; // 创建人
    private Timestamp updateTime; // 修改时间
    private Long updateUser; // 修改人
    private String delFlag; // 是否删除：Y-已删除，N-未删除
    private Timestamp delTime; // 删除时间
    private Long delUser; // 删除人


    @Override
    public String toString() {
        return "InventoryLayer{" +
                "id=" + id +
                ", inventoryId=" + inventoryId +
                ", unitNo=" + unitNo +
                ", startLayerNo=" + startLayerNo +
                ", endLayerNo=" + endLayerNo +
                ", vacancyNum=" + vacancyNum +
                ", statusFlag=" + statusFlag +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", delFlag='" + delFlag + '\'' +
                ", delTime=" + delTime +
                ", delUser=" + delUser +
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

    public Integer getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(Integer unitNo) {
        this.unitNo = unitNo;
    }

    public Integer getStartLayerNo() {
        return startLayerNo;
    }

    public void setStartLayerNo(Integer startLayerNo) {
        this.startLayerNo = startLayerNo;
    }

    public Integer getEndLayerNo() {
        return endLayerNo;
    }

    public void setEndLayerNo(Integer endLayerNo) {
        this.endLayerNo = endLayerNo;
    }

    public Integer getVacancyNum() {
        return vacancyNum;
    }

    public void setVacancyNum(Integer vacancyNum) {
        this.vacancyNum = vacancyNum;
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
