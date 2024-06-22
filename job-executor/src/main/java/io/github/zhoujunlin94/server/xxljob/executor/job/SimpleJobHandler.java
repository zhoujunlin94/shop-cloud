package io.github.zhoujunlin94.server.xxljob.executor.job;

import cn.hutool.core.date.DateUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhoujunlin
 * @date 2024/6/22 21:35
 * 任务Handler示例（Bean模式）
 * *
 * * 开发步骤：
 * * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 */
@Slf4j
@Component
@JobHandler("simpleJobHandler")
public class SimpleJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("hello, current time is:{}", DateUtil.now());
        return ReturnT.SUCCESS;
    }

}
