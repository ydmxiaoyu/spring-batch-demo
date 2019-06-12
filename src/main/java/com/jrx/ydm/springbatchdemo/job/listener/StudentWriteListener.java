package com.jrx.ydm.springbatchdemo.job.listener;

import com.jrx.ydm.springbatchdemo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * 学生信息写入监听器
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 18:34
 */
public class StudentWriteListener implements ItemWriteListener<Student>{

    public static final Logger logger = LoggerFactory.getLogger(StudentWriteListener.class);

    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeWrite(List<? extends Student> list) {

    }

    @Override
    public void afterWrite(List<? extends Student> list) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends Student> list) {
        try {
            errorWriter.write(String.format("%s%n", exception.getMessage()));
            for (Student student:list){
                errorWriter.write(String.format("Failed writing message id: %s", student.getStudentId()));
            }
        } catch (IOException e){
            logger.error("在写入Student信息的时候发生IO异常:" + e.getMessage());
        }
    }
}
