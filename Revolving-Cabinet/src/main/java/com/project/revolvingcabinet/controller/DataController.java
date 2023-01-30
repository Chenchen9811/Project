package com.project.revolvingcabinet.controller;

import com.project.revolvingcabinet.common.CommonResult;
import com.project.revolvingcabinet.entity.CabinetDataMonitor;
import com.project.revolvingcabinet.service.ArchiveCabinetService;
import com.project.revolvingcabinet.service.CabinetDataMonitorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/param")
public class DataController {

    @Resource
    private CabinetDataMonitorService cabinetDataMonitorService;

    /**
     * 获取档案柜的温度
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getArchiveCabinetTemperature() {
        CabinetDataMonitor latestMonitorData = cabinetDataMonitorService.getLatestMonitorData();
        return CommonResult.success(latestMonitorData);
    }
}
