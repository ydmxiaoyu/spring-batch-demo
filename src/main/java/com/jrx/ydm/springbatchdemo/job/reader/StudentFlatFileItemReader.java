package com.jrx.ydm.springbatchdemo.job.reader;

import com.jrx.ydm.springbatchdemo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * 学生信息读取类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/5 15:10
 */
public class StudentFlatFileItemReader extends FlatFileItemReader<Student> {

    public static final Logger logger = LoggerFactory.getLogger(StudentFlatFileItemReader.class);


    public StudentFlatFileItemReader() {
        this.readByDelimitedLine();
    }


    /**
     * 使用分隔符的方式对文件进行读取的配置
     */
    private void readByDelimitedLine(){

        String []names = {"studentId","classId","name","sex","age"};

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames(names);

        DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        BeanWrapperFieldSetMapper<Student> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Student.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        this.setLineMapper(defaultLineMapper);
        this.setLinesToSkip(1);
        this.setSkippedLinesCallback((String s) -> logger.error("skip line:" + s));

    }

    @Override
    protected void doOpen() throws Exception {
        // 文件路径
        String path = this.getClass().getResource("/").getPath() + "/studentinfo.csv";

        File pathFile = new File(path);

        setResource(new FileSystemResource(pathFile));

        super.doOpen();
    }
}
