package io.github.zhoujunlin94.server.xxljob.executor.job;

import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhoujunlin
 * @date 2024/6/23 16:22
 * 测试XxlJob的Glue模式
 */
@Slf4j
@Service
public class GlueService {

    public void helloA() {
        log.warn("执行helloA");
        XxlJobLogger.log("执行helloA");
    }

    public void helloB() {
        log.warn("执行helloB");
        XxlJobLogger.log("执行helloB");
    }

}
