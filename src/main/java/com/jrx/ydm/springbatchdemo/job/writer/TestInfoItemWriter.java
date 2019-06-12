package com.jrx.ydm.springbatchdemo.job.writer;

import com.jrx.ydm.springbatchdemo.model.TestInfo;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * 考试明细表写入类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 9:22
 */
@Configuration
public class TestInfoItemWriter {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaItemWriter<TestInfo> testInfoJpaItemWriter(){
        JpaItemWriter<TestInfo> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

}
