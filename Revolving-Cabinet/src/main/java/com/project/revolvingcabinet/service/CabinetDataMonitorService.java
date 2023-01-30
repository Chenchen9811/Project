package com.project.revolvingcabinet.service;

import com.project.revolvingcabinet.entity.CabinetDataMonitor;

public interface CabinetDataMonitorService {
    /**
     * 获取最新的档案柜监测数据
     * @return
     */
    CabinetDataMonitor getLatestMonitorData();
}
