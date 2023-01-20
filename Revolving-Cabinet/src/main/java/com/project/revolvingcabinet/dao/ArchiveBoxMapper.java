package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.ArchiveBox;
import com.project.revolvingcabinet.entity.DevPos;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArchiveBoxMapper {


    /**
     * 通过档案柜编号、层号和列号查找档案盒信息
     * @param cabinetCode 档案柜编号
     * @param layer 层号
     * @param column 列号
     * @return
     */
    ArchiveBox selectArchiveBoxByCabinetCodeAndPosInfo(String cabinetCode, int layer, int column);


    /**
     * 通过档案盒位置和档案柜外键来查询档案盒的储位信息
     * @param layerNo
     * @param columnNo
     * @param cabinetId
     * @return
     */
    DevPos selectPosIdByBoxLocationAndCabinetCode(Integer layerNo, Integer columnNo, Long cabinetId);

    /**
     * 盘点后更新档案盒信息
     * @param archiveBoxStatusAfterInventory
     * @param layer
     * @param column
     * @param archiveBoxId
     * @param boxLocation
     */
     int updateArchiveBoxAfterInventory(int archiveBoxStatusAfterInventory, Long archiveBoxId, int layer, int column, String boxLocation);
}
