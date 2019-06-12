package com.jrx.ydm.springbatchdemo.job.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * ReadTestInfoJob 作业配置
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 9:12
 */
public class ReadTestInfoJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /**
     * 读取csv文件将学生考试信息写入数据库
     * @param readTestInfoJobStep
     * @return
     */
    @Bean
    public Job readTestInfoJob(@Qualifier("readTestInfoJobStep") Step readTestInfoJobStep){
        return jobBuilderFactory.get("readTestInfoJob").start(readTestInfoJobStep).build();
    }
}
