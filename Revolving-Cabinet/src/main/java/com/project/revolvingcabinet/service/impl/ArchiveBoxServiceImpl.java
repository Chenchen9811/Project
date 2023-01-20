package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.ArchiveBoxMapper;
import com.project.revolvingcabinet.entity.ArchiveBox;
import com.project.revolvingcabinet.entity.DevPos;
import com.project.revolvingcabinet.service.ArchiveBoxService;
import com.project.revolvingcabinet.utils.RevolvingCabinetConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArchiveBoxServiceImpl implements ArchiveBoxService, RevolvingCabinetConstants {

    @Resource
    private ArchiveBoxMapper archiveBoxMapper;

    @Override
    public ArchiveBox findArchiveBoxByCabinetCodeAndPosInfo(String cabinetCode, int layer, int column) {
        return archiveBoxMapper.selectArchiveBoxByCabinetCodeAndPosInfo(cabinetCode, layer, column);
    }

    /**
     * 通过档案盒位置和档案柜外键来查询档案盒的储位信息
     * @param layerNo
     * @param columnNo
     * @param cabinetId
     * @return
     */
    @Override
    public DevPos findPosIdByBoxLocationAndCabinetCode(Integer layerNo, Integer columnNo, Long cabinetId) {
        return archiveBoxMapper.selectPosIdByBoxLocationAndCabinetCode(layerNo, columnNo, cabinetId);
    }

    /**
     * 盘点后更新档案盒信息
     * @param archiveBoxStatusAfterInventory
     * @param layer
     * @param column
     * @param archiveBoxId
     * @param boxLocation
     */
    @Override
    public ArchiveBox updateArchiveBoxAfterInventory(int archiveBoxStatusAfterInventory, Long archiveBoxId, int layer, int column, String boxLocation) {
        int result = archiveBoxMapper.updateArchiveBoxAfterInventory(archiveBoxStatusAfterInventory, archiveBoxId, layer, column, boxLocation);
        if (result == 1) {
            return archiveBoxMapper.selectArchiveBoxByCabinetCodeAndPosInfo(CABINET_CODE, layer, column);
        } else return null;
    }
}
