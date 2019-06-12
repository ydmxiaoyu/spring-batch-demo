package com.jrx.ydm.springbatchdemo.job.listener;

import com.jrx.ydm.springbatchdemo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import java.io.IOException;
import java.io.Writer;

/**
 * 数据读取监听器
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 21:54
 */
public class StudentReadListener implements ItemReadListener<Student> {

    public static final Logger logger = LoggerFactory.getLogger(StudentReadListener.class);

    private Writer errorWriter;

    public StudentReadListener(Writer errorWriter) {
        this.errorWriter = errorWriter;
    }

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Student student) {

    }

    @Override
    public void onReadError(Exception ex) {
        try {
            errorWriter.write(String.format("%s%n", ex.getMessage()));
        } catch (IOException e){
            logger.error("读取日志输出异常:" + e.getMessage());
        }
    }
}
