package com.project.revolvingcabinet.service;

import com.project.revolvingcabinet.entity.ArchiveBox;
import com.project.revolvingcabinet.entity.DevPos;
import com.project.revolvingcabinet.entity.InventoryPos;

public interface DevPosService {

    /**
     * 通过档案柜编号，层号和列号查找储位信息
     * @param cabinetCode
     * @return
     */
    DevPos findDevPosByCabinetCode(String cabinetCode, int layer, int column);


    /**
     * 盘库过后更新储位信息
     * @param devPosBeforeInventory
     * @param archiveBoxBeforeInventory
     * @param devPosStatus
     * @return
     */
    DevPos updateDevPosAfterInventory(DevPos devPosBeforeInventory, ArchiveBox archiveBoxBeforeInventory, int devPosStatus);

    /**
     * 根据档案柜编号、层号和列号生成储位信息
     * @param cabinetCode 档案柜编号
     * @param layer 层号
     * @param column 列号
     * @return
     */
    DevPos generateDevPosInfo(String cabinetCode, int layer, int column);


    /**
     * 通过档案盒位置和档案柜外键来查询档案盒的储位信息
     * @param layerNo
     * @param columnNo
     * @param cabinetId
     * @return
     */
    DevPos findPosIdByBoxLocationAndCabinetCode(Integer layerNo, Integer columnNo, Long cabinetId);
}
