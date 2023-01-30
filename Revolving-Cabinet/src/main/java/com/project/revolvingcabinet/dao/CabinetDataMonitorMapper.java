package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.CabinetDataMonitor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CabinetDataMonitorMapper {
    /**
     * 获取最新的档案柜监测数据
     * @return
     */
    CabinetDataMonitor getLatestMonitorData();

    /**
     * 插入一条数据
     * @param cabinetDataMonitor
     */
    void insertMonitorData(CabinetDataMonitor cabinetDataMonitor);
}
