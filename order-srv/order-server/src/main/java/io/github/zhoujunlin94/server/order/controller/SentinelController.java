package io.github.zhoujunlin94.server.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.github.zhoujunlin94.server.order.app.sentinel.SentinelResourceHandler;
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
        // 测试服务雪崩
        return "测试高并发情况下请求此接口";
    }

    @GetMapping("createOrder")
    public String createOrder() {
        goodService.queryGood();
        return "创建订单";
    }

    @GetMapping("queryGood")
    public String queryGood() {
        // 测试 流控
        goodService.queryGood();
        return "查询商品";
    }

    @SneakyThrows
    @GetMapping("fallback1")
    public String fallback1() {
        // 测试熔断  RT响应时间  慢调用比例
        TimeUnit.SECONDS.sleep(2L);
        return "fallback1";
    }

    int i = 0;

    @GetMapping("fallback2")
    public String fallback2() {
        // 测试熔断  异常比例
        if (++i % 3 == 0) {
            throw new RuntimeException("故意抛出一个异常");
        }
        return "fallback2";
    }

    @GetMapping("fallback3")
    public String fallback3(String flg) {
        // 测试熔断  异常数
        if ("error".equals(flg)) {
            throw new RuntimeException("故意抛出一个异常");
        }
        return "fallback3";
    }

    @GetMapping("hotSpot1")
    @SentinelResource("hotSpot1")
    public String hotSpot1(Integer productId) {
        // 测试热点规则  配置热点商品id进行限流  其他商品不受控制
        // @SentinelResource 会直接返回异常 500  需要进行处理
        return "hotSpot1";
    }

    // 系统规则:  监控应用服务器的 Load  RT   QPS  线程数  CPU等指标判断是否需要拒绝服务


    @GetMapping("auth1")
    public String auth1(String deviceName) {
        // 测试授权规则   配置黑、白名单  例如当deviceName为ios时可以访问（白名单），其他设备无法访问
        return "auth1";
    }


    @GetMapping("anno1")
    @SentinelResource(value = "anno1",
            blockHandlerClass = SentinelResourceHandler.class, blockHandler = "annoBlockHandler")
    public String anno1(String flg) {
        if ("error".equals(flg)) {
            throw new RuntimeException("故意抛出一个异常");
        }
        return "anno1";
    }

}
