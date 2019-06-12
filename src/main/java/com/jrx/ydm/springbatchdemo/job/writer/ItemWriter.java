package com.jrx.ydm.springbatchdemo.job.writer;

import com.jrx.ydm.springbatchdemo.model.Student;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * 把对象写入数据库
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 21:35
 */
@Configuration
public class ItemWriter {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaItemWriter<Student> studentItemWriter(){
        JpaItemWriter<Student> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

}
