package com.project.revolvingcabinet.controller;

import com.project.revolvingcabinet.common.CommonResult;
import com.project.revolvingcabinet.common.Messages;
import com.project.revolvingcabinet.service.OperationService;
import com.project.revolvingcabinet.service.impl.OperationServiceImpl;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OperateController {
    private static final Logger logger = LoggerFactory.getLogger(OperateController.class);


    @Resource
    private OperationService operationService;

    /**
     * 开门
     * @return
     */
    @RequestMapping(path = "/operate/openDoor", method = RequestMethod.GET)
    @ResponseBody
    public String openDoor() {
        String message = null;
        try {
            message = operationService.openDoor();
        } catch (ModbusTransportException e) {
            return CommonUtil.getJSONString(500, Messages.getErrorMsg(Messages.MSG_E_LOG_023));
        }
        if (StringUtils.isBlank(message)) {
            return CommonUtil.getJSONString(500, Messages.getErrorMsg(Messages.MSG_E_LOG_023));
        }
        return CommonUtil.getJSONString(200, Messages.getSuccessMsg(Messages.MSG_S_LOG_012));
    }


    /**
     * 关门
     * @return
     */
    @RequestMapping(path = "/operate/closeDoor", method = RequestMethod.GET)
    @ResponseBody
    public String closeDoor() {
        String message = null;
        try {
            message = operationService.closeDoor();
        } catch (ModbusTransportException e) {
            return CommonUtil.getJSONString(500, Messages.getErrorMsg(Messages.MSG_E_LOG_024));
        }
        if (StringUtils.isBlank(message)) {
            return CommonUtil.getJSONString(500, Messages.getErrorMsg(Messages.MSG_E_LOG_024));
        }
        return CommonUtil.getJSONString(200, Messages.getSuccessMsg(Messages.MSG_S_LOG_013));
    }

    @RequestMapping(path = "/operate", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult operatePage() {
        int currentLayer = 0;
        // 获取当前层
        try {
            currentLayer = operationService.getCurrentLayer();
        } catch (ModbusTransportException e) {
            return CommonResult.failed(Messages.getErrorMsg(Messages.MSG_E_LOG_016));
        }
        // 获取档案柜操作日志
        // 获取档案盒存储日志
        Map<String, Object> map = new HashMap<>();
        map.put("currentLayer", currentLayer);
        return CommonResult.success(map);
    }

    /**
     * 移层
     * @param targetLayer 目标层
     * @return
     * @throws ModbusTransportException
     */
    @RequestMapping(path = "/operate/moveLayer", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult moveLayer(@RequestParam(name = "targetLayer") int targetLayer) {

        String message = null;
        try {
            message = operationService.moveLayer(targetLayer);
        } catch (ModbusTransportException e) {
            return CommonResult.failed(Messages.getErrorMsg(Messages.MSG_E_LOG_014));
        }
        if (message.equals(Messages.getErrorMsg(Messages.MSG_E_LOG_016))) {
            return CommonResult.failed(Messages.getErrorMsg(Messages.MSG_E_LOG_016));
        }
        logger.info("移层成功，移动到{}层", targetLayer);
        return CommonResult.success("", Messages.getSuccessMsg(Messages.MSG_S_LOG_014));
    }


    @RequestMapping(path = "/operate/stop", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult stop() {
        String message = null;
        try {
            message = operationService.stop();
        } catch (ModbusTransportException e) {
            return CommonResult.failed(Messages.getErrorMsg(Messages.MSG_E_LOG_028));
        }

        return CommonResult.success("", message);
    }

}
