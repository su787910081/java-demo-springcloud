package com.suyh.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "temp-controller", tags = "temp-controller")
public class TempController {
    @RequestMapping("/suyh")
    @ApiOperation(value = "添加一个客户、供应商、承运商等",
            notes = "增加客户、供应商、承运商等记录")
    public String get() {
        return "OK";
    }
}
