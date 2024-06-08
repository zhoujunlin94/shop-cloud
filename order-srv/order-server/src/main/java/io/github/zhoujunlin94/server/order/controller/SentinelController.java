package io.github.zhoujunlin94.server.order.controller;

import io.github.zhoujunlin94.server.order.service.GoodService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoujunlin
 * @date 2024/6/8 10:29
 * 模拟大量请求一个耗时的接口(test1)，并且tomcat线程数不足（这里只配置10），
 * 当再有一个请求这个不耗时的接口也会没有线程响应  （所有接口无法响应）
 * <p>
 * 服务雪崩： 当服务A不可用时，服务B有大量请求到服务A，会导致服务B也不可用，最后整个雪崩（服务群中有一个服务不可用，导致其他服务不可用）
 * 解决方案：
 * 1. 隔离机制：对某个服务的请求，限制请求资源（比如对A，最多只能有5个线程请求）
 * 2. 超时机制：调用下游时，设置超时时间，超过此时间，断开连接
 * 3. 限流机制：预估服务的资源阈值。 超过则拒绝服务
 * 4. 熔断机制：下游服务压力大时，上游断开请求，过一段时间重试 （熔断一段时间）
 * 熔断关闭：不限制调用
 * 熔断开启：不调用，使用本地fallback方法
 * 半熔断：尝试恢复调用，允许有限流量请求并监控成功率（成功率达到预期，熔断器关闭，否则开启）
 * 5. 降级机制：服务不可用时的兜底方案
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sentinel")
public class SentinelController {

    private final GoodService goodService;

    @SneakyThrows
    @GetMapping("test1")
    public String test1() {
        TimeUnit.SECONDS.sleep(1L);
        return "test1";
    }

    @GetMapping("test2")
    public String test2() {
        return "测试高并发情况下请求此接口";
    }

    @GetMapping("createOrder")
    public String createOrder() {
        goodService.queryGood();
        return "创建订单";
    }

    @GetMapping("queryGood")
    public String queryGood() {
        goodService.queryGood();
        return "查询商品";
    }

}
