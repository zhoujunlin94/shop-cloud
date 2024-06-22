package io.github.zhoujunlin94.server.xxljob.executor.job;

import cn.hutool.core.date.DateUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhoujunlin
 * @date 2024/6/22 21:35
 */
@Slf4j
@Component
public class SimpleJobHandler {

    @XxlJob("hello")
    public ReturnT<String> hello(String param) {
        XxlJobLogger.log("hello, current time is:{}", DateUtil.now());
        return ReturnT.SUCCESS;
    }

}
