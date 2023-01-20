package com.project.revolvingcabinet.service;


import com.project.revolvingcabinet.entity.ArchiveBox;
import com.project.revolvingcabinet.entity.DevPos;
import com.project.revolvingcabinet.entity.InventoryPos;

public interface InventoryPosService {
    /**
     * 通过储位id查询储位盘点信息
     * @param devPosId 储位id
     * @return 储位盘点信息
     */
    InventoryPos findInventoryPosByPosId(Long devPosId);


    /**
     * 根据档案盒信息与扫描后的RFID信息修改该储位的盘库信息
     * @param archiveBoxAfterInventory
     * @param devPos
     * @param inventoryPosBeforeInventory
     * @param inventoryPosState
     */
    InventoryPos updateInventoryPos(ArchiveBox archiveBoxAfterInventory, DevPos devPos, InventoryPos inventoryPosBeforeInventory, int inventoryPosState, String rfidInfo);

    /**
     * 同上，这是一个重写方法
     * @param archiveBoxAfterInventory
     * @param archiveBoxBeforeInventory
     * @param devPos
     * @param inventoryPosBeforeInventory
     * @param inventoryPosState
     * @return
     */
    InventoryPos updateInventoryPos(ArchiveBox archiveBoxAfterInventory, ArchiveBox archiveBoxBeforeInventory, DevPos devPos, InventoryPos inventoryPosBeforeInventory, int inventoryPosState);

    /**
     * 根据盘点表外键和盘点储位id初始化储位的盘库信息
     * @param inventoryId
     * @param id
     * @return
     */
    InventoryPos initInventoryPos(Long inventoryId, Long posId);
}
