package io.github.zhoujunlin94.server.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhoujunlin
 * @date 2024/7/18 21:16
 */
@Validated
@RestController
@Api(tags = {"S-服务信息"})
@RequestMapping("/api/v1/server-info")
public class ServerInfoController {

    @ApiOperation(value = "服务器时间")
    @GetMapping("/date")
    public Date serverDate() {
        return new Date();
    }

}
