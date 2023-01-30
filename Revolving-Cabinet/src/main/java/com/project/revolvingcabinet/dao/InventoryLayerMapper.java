package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.InventoryLayer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryLayerMapper {
    /**
     * 插入一条
     * @param inventoryLayer
     * @return
     */
    int initInventoryLayerInfo(InventoryLayer inventoryLayer);

    /**
     * 更新层盘库信息
     * @param inventoryLayer
     * @return
     */
    int updateInventoryLayerInfo(InventoryLayer inventoryLayer);
}
