package io.github.zhoujunlin94.server.product.controller.internal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujunlin
 * @date 2024/6/11 22:42
 */
@RefreshScope
@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {

    @Value("${app.config.name:}")
    private String appConfigName;

    @GetMapping("echoAppConfigName")
    public String echoAppConfigName() {
        return appConfigName;
    }

}
