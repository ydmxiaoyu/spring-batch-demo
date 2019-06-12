package com.jrx.ydm.springbatchdemo.job.listener;

import com.jrx.ydm.springbatchdemo.model.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * 考试明细作业写入数据的监听器
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 9:27
 */
public class TestInfoWriteListener implements ItemWriteListener<TestInfo> {

    public static final Logger logger = LoggerFactory.getLogger(TestInfoWriteListener.class);

    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeWrite(List<? extends TestInfo> list) {

    }

    @Override
    public void afterWrite(List<? extends TestInfo> list) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends TestInfo> list) {
        try {
            errorWriter.write(String.format("%s%n", exception.getMessage()));
            for (TestInfo testInfo:list){
                errorWriter.write(String.format("Failed writing message id: %s", testInfo.getStudentId()));
            }
        } catch (IOException e){
            logger.error("在写入Student信息的时候发生IO异常:" + e.getMessage());
        }
    }
}
