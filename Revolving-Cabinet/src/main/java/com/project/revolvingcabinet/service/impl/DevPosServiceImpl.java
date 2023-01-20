package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.DevPosMapper;
import com.project.revolvingcabinet.entity.ArchiveBox;
import com.project.revolvingcabinet.entity.DevPos;
import com.project.revolvingcabinet.entity.InventoryPos;
import com.project.revolvingcabinet.service.ArchiveCabinetService;
import com.project.revolvingcabinet.service.DevPosService;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.project.revolvingcabinet.utils.HostHolder;
import com.project.revolvingcabinet.utils.RevolvingCabinetConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class DevPosServiceImpl implements DevPosService, RevolvingCabinetConstants {
    @Resource
    private DevPosMapper devPosMapper;

    @Resource
    private HostHolder hostHolder;

    @Resource
    private ArchiveCabinetService archiveCabinetService;

    @Override
    public DevPos findDevPosByCabinetCode(String cabinetCode, int layer, int column) {
        return devPosMapper.selectDevPosByCabinetCode(cabinetCode, layer, column);
    }


    /**
     * 盘库过后更新储位信息
     * @param devPosInfo
     * @param archiveBox
     * @param devPosStatus
     * @return
     */
    @Override
    public DevPos updateDevPosAfterInventory(DevPos devPosInfo, ArchiveBox archiveBox, int devPosStatus) {
        if (devPosInfo == null) {
            DevPos devPos = new DevPos();
            devPos.setId(CommonUtil.generateId());
            devPos.setLayerNo(archiveBox.getLayerNo());
            devPos.setColumnNo(archiveBox.getColumnNo());
            devPos.setBoxNo1(archiveBox.getBoxNo());
            devPos.setBoxStatus(archiveBox.getStatusFlag());
            devPos.setBoxNo1StatusFlag(archiveBox.getStatusFlag());
            devPos.setStatusFlag(devPosStatus);
            devPos.setBoxRfid1(archiveBox.getRfid());
            devPos.setCreateTime(new Timestamp(System.currentTimeMillis()));
            devPos.setCreateUser(hostHolder.getUser().getUserId());
            devPos.setCabinetCode(CABINET_CODE);
            devPos.setCabinetId(archiveCabinetService.findCabinetIdByCabinetCode(CABINET_CODE));
            devPosMapper.insertDevPos(devPos);
            return devPos;
        } else {
            // 储位信息不为空，那么更新储位信息
            devPosInfo.setStatusFlag(devPosStatus); // 更改储位状态
            devPosInfo.setUpdateTime(new Timestamp(System.currentTimeMillis())); //更改修改时间
            devPosInfo.setUpdateUser(hostHolder.getUser().getUserId()); // 更改修改人id
            // 更改储位的档案盒信息
            devPosInfo.setBoxNo1(archiveBox.getBoxNo());
            devPosInfo.setBoxStatus(archiveBox.getStatusFlag());
            devPosInfo.setBoxNo1StatusFlag(archiveBox.getStatusFlag());
            devPosInfo.setBoxRfid1(archiveBox.getRfid());
            devPosMapper.updateDevPos(devPosInfo);
            return devPosInfo;
        }
    }

    @Override
    public DevPos generateDevPosInfo(String cabinetCode, int layer, int column) {
        DevPos devPos = new DevPos();
        devPos.setId(CommonUtil.generateId());
        devPos.setLayerNo(layer);
        devPos.setColumnNo(column);
        devPos.setStatusFlag(DEV_POS_STATUS_NOT_SCANNED); // 未扫描
        devPos.setCreateTime(new Timestamp(System.currentTimeMillis()));
        devPos.setCreateUser(hostHolder.getUser().getUserId());
        devPos.setCabinetCode(cabinetCode);
        devPos.setCabinetId(archiveCabinetService.findCabinetIdByCabinetCode(cabinetCode));
        devPosMapper.insertDevPos(devPos);
        return devPos;
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
        return devPosMapper.selectPosIdByBoxLocationAndCabinetCode(layerNo, columnNo, cabinetId);
    }
}
