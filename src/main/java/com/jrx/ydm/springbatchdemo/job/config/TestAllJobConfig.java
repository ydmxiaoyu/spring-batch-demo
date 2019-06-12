package com.jrx.ydm.springbatchdemo.job.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * 考试汇总表的 Job
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 15:03
 */
public class TestAllJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /**
     * 数据从数据库导出到文件
     * @param testAllJobStep
     * @return
     */
    @Bean
    public Job testAllJob(@Qualifier("testAllJobStep")Step testAllJobStep){
        return jobBuilderFactory.get("testAllJob").start(testAllJobStep).build();
    }


    /**
     * 数据从文件写入数据库
     * @param step
     * @return
     */
    @Bean
    public Job testAllJobDataFromFile(@Qualifier("testAllJobStepDataFromFile")Step step){
        return jobBuilderFactory.get("testAllJobDataFromFile").start(step).build();
    }

}
