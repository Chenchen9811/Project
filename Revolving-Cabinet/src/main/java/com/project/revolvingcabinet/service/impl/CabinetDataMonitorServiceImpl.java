package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.CabinetDataMonitorMapper;
import com.project.revolvingcabinet.entity.CabinetDataMonitor;
import com.project.revolvingcabinet.service.CabinetDataMonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CabinetDataMonitorServiceImpl implements CabinetDataMonitorService {
    @Resource
    private CabinetDataMonitorMapper cabinetDataMonitorMapper;


    @Override
    public CabinetDataMonitor getLatestMonitorData() {
        return cabinetDataMonitorMapper.getLatestMonitorData();
    }
}
