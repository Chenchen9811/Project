package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.InventoryLayerMapper;
import com.project.revolvingcabinet.entity.InventoryLayer;
import com.project.revolvingcabinet.service.InventoryLayerService;
import com.project.revolvingcabinet.service.InventoryPosService;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.project.revolvingcabinet.utils.HostHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class InventoryLayerServiceImpl implements InventoryLayerService {

    @Resource
    private HostHolder hostHolder;

    @Resource
    private InventoryLayerMapper inventoryLayerMapper;

    /**
     * 初始化层盘库信息
     * @param inventoryId
     * @param startLayer
     * @param inventoryStatusLayer
     * @return
     */
    @Override
    public InventoryLayer initInventoryLayerInfo(Long inventoryId, int startLayer, int inventoryStatusLayer) {
        InventoryLayer inventoryLayer = new InventoryLayer();
        inventoryLayer.setId(CommonUtil.generateId());
        inventoryLayer.setInventoryId(inventoryId);
        inventoryLayer.setStartLayerNo(startLayer);
        inventoryLayer.setCreateUser(hostHolder.getUser().getUserId());
        inventoryLayer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        inventoryLayer.setStatusFlag(inventoryStatusLayer);
        inventoryLayerMapper.initInventoryLayerInfo(inventoryLayer);
        return inventoryLayer;
    }

    /**
     * 更新层盘库信息
     * @param vacancyNum
     * @param endLayer
     * @param inventoryStatusLayer
     * @param inventoryLayer
     * @return
     */
    @Override
    public int updateInventoryLayerInfo(int vacancyNum, int endLayer, int inventoryStatusLayer, InventoryLayer inventoryLayer) {
        inventoryLayer.setVacancyNum(vacancyNum);
        inventoryLayer.setEndLayerNo(endLayer);
        inventoryLayer.setStatusFlag(inventoryStatusLayer);
        inventoryLayer.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        inventoryLayer.setUpdateUser(hostHolder.getUser().getUserId());
        return inventoryLayerMapper.updateInventoryLayerInfo(inventoryLayer);
    }
}
