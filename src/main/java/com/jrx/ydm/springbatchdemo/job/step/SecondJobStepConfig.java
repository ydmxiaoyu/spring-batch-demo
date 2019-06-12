package com.jrx.ydm.springbatchdemo.job.step;

import com.fasterxml.jackson.core.JsonParseException;
import com.jrx.ydm.springbatchdemo.model.Student;
import com.jrx.ydm.springbatchdemo.job.listener.StudentReadListener;
import com.jrx.ydm.springbatchdemo.job.listener.StudentWriteListener;
import com.jrx.ydm.springbatchdemo.job.reader.StudentFlatFileItemReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
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
 * @date 2019/6/5 16:03
 */
@Configuration
public class SecondJobStepConfig {


    public static final int CHUNK_SIZE = 1000;

    public static final int SKIP_LIMIT = 1;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step secondJobStep(@Qualifier("studentItemWriter")JpaItemWriter<Student> studentItemWriter,
                             @Qualifier("errorWriter")Writer errorWriter){
        return stepBuilderFactory.get("secondJobStep").<Student,Student>chunk(CHUNK_SIZE).reader(new StudentFlatFileItemReader())
                .faultTolerant().skip(JsonParseException.class).skipLimit(SKIP_LIMIT)
                .listener(new StudentReadListener(errorWriter))
                .writer(studentItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new StudentWriteListener())
                .build();

    }
}
