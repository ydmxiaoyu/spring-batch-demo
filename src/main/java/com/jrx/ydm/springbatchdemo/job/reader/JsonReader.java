package com.jrx.ydm.springbatchdemo.job.reader;

import com.jrx.ydm.springbatchdemo.model.Student;
import com.jrx.ydm.springbatchdemo.job.mapper.StudentLineMapper;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * 文件读取类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 20:44
 */
@Configuration
public class JsonReader {

    /**
     * 文件地址
     */
    public static final String STUDENT_FILE = "C:\\Users\\Administrator.SKY-20181103GSC\\Desktop\\test.txt";

    @Bean
    public FlatFileItemReader<Student> studentJsonReader(){
        FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(new File(STUDENT_FILE)));
        reader.setLineMapper(new StudentLineMapper());
        return reader;
    }

}
