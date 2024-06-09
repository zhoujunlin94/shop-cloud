package io.github.zhoujunlin94.server.order.app.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujunlin
 * @date 2024/6/9 20:33
 */
@Component
public class DeviceNameRequestOriginParseDefinition implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        return request.getParameter("deviceName");
    }

}
