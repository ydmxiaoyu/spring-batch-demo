package com.jrx.ydm.springbatchdemo.config;

import com.jrx.ydm.springbatchdemo.job.config.FirstJobConfig;
import com.jrx.ydm.springbatchdemo.job.config.ReadTestInfoJobConfig;
import com.jrx.ydm.springbatchdemo.job.config.SecondJobConfig;
import com.jrx.ydm.springbatchdemo.job.config.TestAllJobConfig;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置全局 configuration 对象来加载作业
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 17:15
 */
@Configuration
@EnableBatchProcessing(modular = true)
public class JobConfig {


    @Bean
    public ApplicationContextFactory firstJobContext(){
        return new GenericApplicationContextFactory(FirstJobConfig.class);
    }

    @Bean
    public ApplicationContextFactory secondJobContext(){
        return new GenericApplicationContextFactory(SecondJobConfig.class);
    }

    @Bean
    public ApplicationContextFactory readTestInfoJobContext(){
        return new GenericApplicationContextFactory(ReadTestInfoJobConfig.class);
    }

    /**
     * Test_All 表相关的 Job 上下文
     * @return
     */
    @Bean
    public ApplicationContextFactory testAllJobContext(){
        return new GenericApplicationContextFactory(TestAllJobConfig.class);
    }

}
