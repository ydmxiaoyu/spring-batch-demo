package com.jrx.ydm.springbatchdemo.job.step;

import com.jrx.ydm.springbatchdemo.model.TestAll;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TestAllJob 的步骤配置
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 14:50
 */
@Configuration
public class TestAllJobStepConfig {

    public static final int CHUNK_SIZE = 1000;

    public static final int SKIP_LIMIT = 5;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 将数据从数据库导出到csv文件
     * @param reader
     * @param writer
     * @return
     */
    @Bean
    public Step testAllJobStep(@Qualifier("jdbcCursorItemReader")JdbcCursorItemReader<TestAll> reader,
                               @Qualifier("flatFileItemWriter")FlatFileItemWriter<TestAll> writer){
        return stepBuilderFactory.get("testAllJobStep").<TestAll,TestAll>chunk(CHUNK_SIZE)
                .reader(reader).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .writer(writer).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .build();
    }


    /**
     * 数据从文件存储到数据库
     * @return
     */
    @Bean
    public Step testAllJobStepDataFromFile(@Qualifier("testAllFlatFileItemReader")FlatFileItemReader<TestAll> reader,
                                           @Qualifier("testAllJpaItemWriter")JpaItemWriter<TestAll> writer){
        return stepBuilderFactory.get("testAllJobStepDataFromFile").<TestAll,TestAll>chunk(CHUNK_SIZE)
                .reader(reader).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .writer(writer).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .build();
    }


}
