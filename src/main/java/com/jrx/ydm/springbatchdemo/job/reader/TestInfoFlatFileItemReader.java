package com.jrx.ydm.springbatchdemo.job.reader;

import com.jrx.ydm.springbatchdemo.model.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * 考试明细表读取类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 9:04
 */
public class TestInfoFlatFileItemReader extends FlatFileItemReader<TestInfo> {

    public static final Logger logger = LoggerFactory.getLogger(TestInfoFlatFileItemReader.class);


    public TestInfoFlatFileItemReader() {
        readByDelimitedLine();
    }

    /**
     * 使用分隔符的方式对文件进行读取的配置
     */
    private void readByDelimitedLine(){

        String []names = {"studentId","subject","xh","score","testTime"};

        // 读取数据之后转换为 bean
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames(names);

        DefaultLineMapper<TestInfo> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        BeanWrapperFieldSetMapper<TestInfo> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(TestInfo.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        this.setLineMapper(defaultLineMapper);
//        this.setComments(new String[]{"==","##"});
//        this.setLinesToSkip(0);
        this.setSkippedLinesCallback((String s) -> logger.error("skip line:" + s));

    }

    @Override
    protected void doOpen() throws Exception {
        // 文件路径
        String path = this.getClass().getResource("/").getPath() + "/testinfo.csv";

        File pathFile = new File(path);

        setResource(new FileSystemResource(pathFile));

        super.doOpen();
    }
}
