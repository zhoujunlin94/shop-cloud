package io.github.zhoujunlin94.server.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author zhoujunlin
 * @date 2024/6/8 14:57
 */
@Service
public class GoodService {

    @SentinelResource(value = "queryGood")
    public String queryGood() {
        // 当前接口  创建订单和查询商品接口都会使用 在sentinel中配置这个资源，qps大于3时 拒绝查询接口的访问  保证创建订单接口能使用
        return "查询商品";
    }

}
