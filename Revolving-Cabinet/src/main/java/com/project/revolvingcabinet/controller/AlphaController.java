package com.project.revolvingcabinet.controller;

import com.project.revolvingcabinet.common.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlphaController {

    @RequestMapping(value = "/manage", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult manage() {
        return CommonResult.success("", "hello");
    }
}
