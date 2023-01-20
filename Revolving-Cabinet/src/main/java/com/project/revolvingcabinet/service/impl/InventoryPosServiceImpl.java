package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.InventoryPosMapper;
import com.project.revolvingcabinet.entity.ArchiveBox;
import com.project.revolvingcabinet.entity.DevPos;
import com.project.revolvingcabinet.entity.InventoryPos;
import com.project.revolvingcabinet.service.ArchiveBoxService;
import com.project.revolvingcabinet.service.DevPosService;
import com.project.revolvingcabinet.service.InventoryPosService;
import com.project.revolvingcabinet.service.InventoryService;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.project.revolvingcabinet.utils.HostHolder;
import com.project.revolvingcabinet.utils.RevolvingCabinetConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class InventoryPosServiceImpl implements InventoryPosService, RevolvingCabinetConstants {
    @Resource
    private InventoryPosMapper inventoryPosMapper;

    @Resource
    private HostHolder hostHolder;

    @Resource
    private ArchiveBoxService archiveBoxService;

    @Resource
    private DevPosService devPosService;


    @Override
    public InventoryPos findInventoryPosByPosId(Long devPosId) {
        return inventoryPosMapper.selectInventoryPosByPosId(devPosId);
    }

    /**
     * 根据盘点表外键和盘点储位id初始化储位的盘库信息
     *
     * @param inventoryId
     * @param posId
     * @return
     */
    @Override
    public InventoryPos initInventoryPos(Long inventoryId, Long posId) {
        InventoryPos pos = new InventoryPos();
        pos.setId(CommonUtil.generateId());
        pos.setInventoryId(inventoryId);
        pos.setPosId(posId);
        pos.setStatusFlag(INVENTORY_STATUS_POS_CHECKING);
        pos.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pos.setCreateUser(hostHolder.getUser().getUserId());
        inventoryPosMapper.insertInventoryPosBeforeInventory(pos);
        return pos;
    }


    @Override
    public InventoryPos updateInventoryPos(ArchiveBox archiveBoxBeforeInventory, DevPos devPos, InventoryPos inventoryPosBeforeInventory, int inventorPosState, String rfidInfo) {
        InventoryPos inventoryPosAfterInventory = inventoryPosBeforeInventory;
        inventoryPosAfterInventory.setBoxNo1StatusFlag(archiveBoxBeforeInventory.getStatusFlag());
        inventoryPosAfterInventory.setBoxNo1Location(devPos.getLayerNo() + "-" + devPos.getColumnNo());
        inventoryPosAfterInventory.setStatusFlag(inventorPosState); // 盘点状态
        // 如果天线是坏的，那么当前储位的信息是未知的，因此档案盒只能用原来的档案盒信息
        if (rfidInfo.equals(INVENTORY_POS_STATUS_ANTENNA_BROKEN)) {
            inventoryPosAfterInventory.setBox1Rfid(archiveBoxBeforeInventory.getRfid()); // 这里存放的是档案盒信息中的档案盒RFID,因为天线是坏的只能存原来档案盒的RFID
        } else {
            inventoryPosAfterInventory.setBox1Rfid(devPos.getBoxRfid1()); // 这里存放的是储位中的档案盒编号
        }
        inventoryPosAfterInventory.setBoxNo1(devPos.getBoxNo1()); // 这里存放的是储位信息中的档案盒的编号
        DevPos targetDevPos = devPosService.findPosIdByBoxLocationAndCabinetCode(archiveBoxBeforeInventory.getLayerNo(),
                archiveBoxBeforeInventory.getColumnNo(),
                Long.valueOf(archiveBoxBeforeInventory.getCabinetId())); // 查询档案盒原本的储位信息
        if (targetDevPos != null) {
            inventoryPosAfterInventory.setPosIdTarget1(targetDevPos.getId());
        }
        inventoryPosAfterInventory.setLocationTarget1(archiveBoxBeforeInventory.getLayerNo() + "-" + archiveBoxBeforeInventory.getColumnNo()); // 存放档案盒原本的位置
        inventoryPosAfterInventory.setStatusFlagTarget1(archiveBoxBeforeInventory.getStatusFlag()); // 存放档案盒原本的状态
        inventoryPosAfterInventory.setBoxNo1Old(archiveBoxBeforeInventory.getBoxNo());// 内侧档案盒编号(盘库之前)
        inventoryPosAfterInventory.setBoxNo1StatusFlagOld(archiveBoxBeforeInventory.getStatusFlag()); // 内侧档案盒状态(盘库之前)
        inventoryPosAfterInventory.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        inventoryPosAfterInventory.setUpdateUser(hostHolder.getUser().getUserId());
        int result = inventoryPosMapper.updateInventoryPosBeforeInventory(inventoryPosAfterInventory);
        return inventoryPosAfterInventory;
    }

    @Override
    public InventoryPos updateInventoryPos(ArchiveBox archiveBoxAfterInventory, ArchiveBox archiveBoxBeforeInventory, DevPos devPos, InventoryPos inventoryPosBeforeInventory, int inventoryStatePos) {
        return null;
    }
}
