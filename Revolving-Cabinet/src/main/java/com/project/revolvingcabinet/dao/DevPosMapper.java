package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.DevPos;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DevPosMapper {


    /**
     * 通过档案柜编号，层号和列号查找储位信息
     * @param cabinetCode
     * @return
     */
    DevPos selectDevPosByCabinetCode(String cabinetCode, int layer, int column);

    /**
     * 插入储位信息
     * @param devPos
     */
    void insertDevPos(DevPos devPos);

    /**
     * 更新储位信息
     * @param devPosInfo
     */
    void updateDevPos(DevPos devPosInfo);

    /** 通过档案盒位置和档案柜外键来查询档案盒的储位信息
     * @param layerNo
     * @param columnNo
     * @param cabinetId
     * @return
     */
    DevPos selectPosIdByBoxLocationAndCabinetCode(Integer layerNo, Integer columnNo, Long cabinetId);
}
