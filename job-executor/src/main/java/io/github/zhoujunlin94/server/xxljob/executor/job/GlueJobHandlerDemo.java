package io.github.zhoujunlin94.server.xxljob.executor.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import javax.annotation.Resource;

/**
 * @author zhoujunlin
 * @date 2024/6/23 16:28
 * 这段代码 放在XXl-JOB 的Glue-IDE中
 */
public class GlueJobHandlerDemo extends IJobHandler {

    @Resource
    private GlueService glueService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, 执行之前.");
        glueService.helloA();
        XxlJobLogger.log("XXL-JOB, 执行之后.");
        return ReturnT.SUCCESS;
    }

}

