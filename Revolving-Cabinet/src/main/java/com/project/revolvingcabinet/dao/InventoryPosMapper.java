package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.InventoryPos;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryPosMapper {
    /**
     * 通过储位id查询储位盘点信息
     * @param devPosId 储位id
     * @return 储位盘点信息
     */
    InventoryPos selectInventoryPosByPosId(Long devPosId);

    /**
     * 插入一条储位盘点信息
     * @param pos
     */
    //int insertInventoryPos(InventoryPos pos);

    /**
     * 插入一条储位盘点信息
     * @param pos
     * @return
     */
    int insertInventoryPosBeforeInventory(InventoryPos pos);

    /**
     * 盘库后更新储位的盘库信息
     * @param inventoryPosAfterInventory
     * @return
     */
    int updateInventoryPosBeforeInventory(InventoryPos inventoryPosAfterInventory);
}
