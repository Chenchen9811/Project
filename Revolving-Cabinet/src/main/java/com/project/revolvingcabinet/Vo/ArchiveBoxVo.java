package com.project.revolvingcabinet.Vo;

public class ArchiveBoxVo {
    String boxNo; // 档案盒编号
    String boxName; // 档案盒名称
    Integer layerNo; // 层号
    Integer columnNo; // 列号

    @Override
    public String toString() {
        return "ArchiveBoxVo{" +
                "boxNo='" + boxNo + '\'' +
                ", boxName='" + boxName + '\'' +
                ", layerNo=" + layerNo +
                ", columnNo=" + columnNo +
                '}';
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
}
