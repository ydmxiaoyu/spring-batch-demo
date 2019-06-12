package com.jrx.ydm.springbatchdemo.job.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * 作业配置
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 17:32
 */
public class FirstJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /**
     * 读取Json格式的数据将学生信息写入数据库
     * @param firstJobStep
     * @return
     */
    @Bean
    public Job firstJob(@Qualifier("firstJobStep") Step firstJobStep){
        return jobBuilderFactory.get("firstJob").start(firstJobStep).build();
    }

}
