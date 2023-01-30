package com.project.revolvingcabinet.service;


import com.project.revolvingcabinet.entity.InventoryLayer;

public interface InventoryLayerService {

    /**
     * 初始化层盘库信息
     * @param inventoryId
     * @param startLayer
     * @param inventoryStatusLayer
     * @return
     */
    InventoryLayer initInventoryLayerInfo(Long inventoryId, int startLayer, int inventoryStatusLayer);


    /**
     * 更新层盘库信息
     * @param vacancyNum
     * @param endLayer
     * @param inventoryStatusLayer
     * @param inventoryLayer
     * @return
     */
    int updateInventoryLayerInfo(int vacancyNum, int endLayer, int inventoryStatusLayer, InventoryLayer inventoryLayer);
}
