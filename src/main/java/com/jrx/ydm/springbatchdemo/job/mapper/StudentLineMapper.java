package com.jrx.ydm.springbatchdemo.job.mapper;

import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrx.ydm.springbatchdemo.model.Student;
import org.springframework.batch.item.file.LineMapper;

/**
 * 把 Json 格式的文本转换为对象
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 21:04
 */
public class StudentLineMapper implements LineMapper<Student> {

    private MappingJsonFactory factory = new MappingJsonFactory();

    @Override
    public Student mapLine(String line, int lineNumber) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Student student = objectMapper.readValue(line,Student.class);

        return student;
    }
}
