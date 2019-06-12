package com.jrx.ydm.springbatchdemo.job.writer;

import com.jrx.ydm.springbatchdemo.model.TestAll;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.persistence.EntityManagerFactory;

/**
 * 考试汇总表写入类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 14:23
 */
@Configuration
public class TestAllItemWriter {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * 将考试汇总信息写入文件
     * @return
     */
    @Bean
    public FlatFileItemWriter<TestAll> flatFileItemWriter(){
        FlatFileItemWriter<TestAll> writer = new FlatFileItemWriter<>();
        String path = "C:\\Users\\Administrator.SKY-20181103GSC\\Desktop\\";
        String fileName = "testall.csv";
        Resource outputResource = new FileSystemResource(path + fileName);
        writer.setResource(outputResource);

        BeanWrapperFieldExtractor<TestAll> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[]{"studentId","xh","scoreAll","scoreAvg"});

        DelimitedLineAggregator<TestAll> delimitedLineAggregator = new DelimitedLineAggregator<>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);

        writer.setLineAggregator(delimitedLineAggregator);
        return writer;
    }

    /**
     * 将从文件读过来的数据持久化到数据库
     * @return
     */
    @Bean
    public JpaItemWriter<TestAll> testAllJpaItemWriter(){
        JpaItemWriter<TestAll> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }

}
