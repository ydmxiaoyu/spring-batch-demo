package com.jrx.ydm.springbatchdemo.job.writer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintWriter;
import java.io.Writer;

/**
 * 创建 errorWriter
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/5 0:14
 */
@Configuration
public class ErrorWriterFactory {

    @Bean
    public Writer errorWriter(){
        return new PrintWriter(System.out);
    }
}
