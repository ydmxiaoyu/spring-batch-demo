package com.jrx.ydm.springbatchdemo.job.step;

import com.fasterxml.jackson.core.JsonParseException;
import com.jrx.ydm.springbatchdemo.model.TestInfo;
import com.jrx.ydm.springbatchdemo.job.listener.TestInfoReadListener;
import com.jrx.ydm.springbatchdemo.job.listener.TestInfoWriteListener;
import com.jrx.ydm.springbatchdemo.job.reader.TestInfoFlatFileItemReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Writer;

/**
 * describe
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 9:15
 */
@Configuration
public class ReadTestInfoJobStepConfig {


    public static final int CHUNK_SIZE = 1000;

    public static final int SKIP_LIMIT = 5;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step readTestInfoJobStep(@Qualifier("testInfoJpaItemWriter")JpaItemWriter<TestInfo> testInfoJpaItemWriter,
                             @Qualifier("errorWriter")Writer errorWriter){
        return stepBuilderFactory.get("readTestInfoJobStep").<TestInfo,TestInfo>chunk(CHUNK_SIZE)
                .reader(new TestInfoFlatFileItemReader()).faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
                .listener(new TestInfoReadListener(errorWriter))
                .writer(testInfoJpaItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new TestInfoWriteListener())
                .build();

    }
}
