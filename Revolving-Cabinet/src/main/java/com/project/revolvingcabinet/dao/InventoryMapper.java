package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryMapper {

    /**
     * 初始化总盘库信息
     * @param inventory
     * @return
     */
    int initInventoryInfo(Inventory inventory);

    /**
     * 盘库完成后更新总盘库信息
     * @param inventory
     * @return
     */
    int updateInventoryInfo(Inventory inventory);
}
