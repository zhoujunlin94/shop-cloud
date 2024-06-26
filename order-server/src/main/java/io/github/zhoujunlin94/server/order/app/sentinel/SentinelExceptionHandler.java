package io.github.zhoujunlin94.server.order.app.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.meet.common.util.ServletUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoujunlin
 * @date 2024/6/9 20:39
 */
@Component
public class SentinelExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        JsonResponse<Object> ret = JsonResponse.fail("sentinel blocked");
        if (e instanceof FlowException) {
            ret = JsonResponse.fail(-1, "接口被限流了");
        } else if (e instanceof DegradeException) {
            ret = JsonResponse.fail(-2, "接口被熔断了");
        } else if (e instanceof ParamFlowException) {
            ret = JsonResponse.fail(-3, "热点规则触发");
        } else if (e instanceof AuthorityException) {
            ret = JsonResponse.fail(-4, "授权规则触发");
        } else if (e instanceof SystemBlockException) {
            ret = JsonResponse.fail(-5, "系统规则触发");
        }
        ServletUtils.writeJSON(httpServletResponse, ret);
    }

}
