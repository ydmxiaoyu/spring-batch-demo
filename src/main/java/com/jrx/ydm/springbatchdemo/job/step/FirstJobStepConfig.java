package com.jrx.ydm.springbatchdemo.job.step;

import com.fasterxml.jackson.core.JsonParseException;
import com.jrx.ydm.springbatchdemo.model.Student;
import com.jrx.ydm.springbatchdemo.job.listener.StudentReadListener;
import com.jrx.ydm.springbatchdemo.job.listener.StudentWriteListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Writer;

/**
 * 配置 Step
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 18:00
 */
@Configuration
public class FirstJobStepConfig {

    public static final int CHUNK_SIZE = 1000;

    public static final int SKIP_LIMIT = 5;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step firstJobStep(@Qualifier("studentJsonReader")FlatFileItemReader<Student> studentJsonReader,
                             @Qualifier("studentItemWriter")JpaItemWriter<Student> studentItemWriter,
                             @Qualifier("errorWriter")Writer errorWriter){
        return stepBuilderFactory.get("firstJobStep").<Student,Student>chunk(CHUNK_SIZE).reader(studentJsonReader)
                .faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
                .listener(new StudentReadListener(errorWriter))
                .writer(studentItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new StudentWriteListener())
                .build();

    }

}
