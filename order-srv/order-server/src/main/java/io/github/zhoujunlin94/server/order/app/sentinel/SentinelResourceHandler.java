package io.github.zhoujunlin94.server.order.app.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoujunlin
 * @date 2024/6/9 21:45
 */
@Slf4j
public class SentinelResourceHandler {

    public static String annoBlockHandler(String flg, BlockException exp) {
        log.error("参数:{}", flg, exp);
        return "接口被限流或者降级了";
    }

    public static String annoFallbackHandler(String flg, BlockException exp) {
        log.error("参数:{}", flg, exp);
        return "接口发生异常了";
    }

}
