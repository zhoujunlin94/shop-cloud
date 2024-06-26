package io.github.zhoujunlin94.server.xxljob.executor.job;

import cn.hutool.core.date.StopWatch;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.util.ShardingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoujunlin
 * @date 2024/6/23 17:00
 */
@Slf4j
@Component
@JobHandler("shardingJobHandler")
public class ShardingJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String params) throws Exception {
        ShardingUtil.ShardingVO shardingVo = ShardingUtil.getShardingVo();
        int total = shardingVo.getTotal();
        int index = shardingVo.getIndex();
        log.warn("执行器集群总数:{}, 当前机器序号:{}", total, index);

        int count = 0;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 2000; i++) {
            if (i % total == 0) {
                count++;
                log.warn("分片处理任务,id:{}", i);
                TimeUnit.MILLISECONDS.sleep(10);
            } else {
                log.warn("由其他机器处理此任务,id:{}", i);
            }
        }
        stopWatch.stop();
        log.warn("当前机器处理任务数:{}, 耗时:{}", count, stopWatch.getTotalTimeMillis());
        return ReturnT.SUCCESS;
    }

}
