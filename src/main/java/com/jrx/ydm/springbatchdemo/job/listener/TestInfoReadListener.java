package com.jrx.ydm.springbatchdemo.job.listener;

import com.jrx.ydm.springbatchdemo.model.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import java.io.IOException;
import java.io.Writer;

/**
 * 考试明细作业读取数据的监听器
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 9:27
 */
public class TestInfoReadListener implements ItemReadListener<TestInfo> {

    public static final Logger logger = LoggerFactory.getLogger(TestInfoReadListener.class);

    private Writer errorWriter;

    public TestInfoReadListener(Writer errorWriter) {
        this.errorWriter = errorWriter;
    }

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(TestInfo testInfo) {

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
