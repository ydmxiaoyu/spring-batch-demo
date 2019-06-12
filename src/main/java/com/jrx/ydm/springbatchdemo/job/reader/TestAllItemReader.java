package com.jrx.ydm.springbatchdemo.job.reader;

import com.jrx.ydm.springbatchdemo.model.TestAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 考试汇总表读取类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 14:07
 */
@Configuration
public class TestAllItemReader {

//    public static final String PATH = "C:\\Users\\Administrator.SKY-20181103GSC\\Desktop\\";
    public static final String FILE_NAME = "/testall.csv";
    public static final String []NAMES = {"studentId","xh","scoreAll","scoreAvg"};


    public static final Logger logger = LoggerFactory.getLogger(TestAllItemReader.class);

    @Bean
    public JdbcCursorItemReader<TestAll> jdbcCursorItemReader(){
        JdbcCursorItemReader<TestAll> reader = new JdbcCursorItemReader<>();

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/batch?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
        DataSource dataSource = dataSourceBuilder.build();

        // 设置数据源
        reader.setDataSource(dataSource);

        reader.setSql("SELECT student_id,xh,score_all,score_avg FROM test_all");
        reader.setRowMapper(new RowMapper<TestAll>() {
            @Nullable
            @Override
            public TestAll mapRow(ResultSet resultSet, int i) throws SQLException {
                TestAll testAll = new TestAll();
                testAll.setStudentId(resultSet.getString(1));
                testAll.setXh(resultSet.getString(2));
                testAll.setScoreAll(resultSet.getBigDecimal(3));
                testAll.setScoreAvg(resultSet.getBigDecimal(4));
                return testAll;
            }
        });
        return reader;
    }

    /**
     * 使用分隔符从文件读取数据
     * @return
     */
    @Bean
    public FlatFileItemReader<TestAll> testAllFlatFileItemReader(){

        FlatFileItemReader<TestAll> reader = new FlatFileItemReader<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setNames(NAMES);

        BeanWrapperFieldSetMapper<TestAll> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(TestAll.class);

        DefaultLineMapper<TestAll> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        //获取资源路径
        String path = this.getClass().getResource("/").getPath();
        File file = new File(path + FILE_NAME);
//        File file = new File(PATH + FILE_NAME);
        Resource resource = new FileSystemResource(file);
        reader.setResource(resource);

        reader.setLineMapper(defaultLineMapper);
        reader.setSkippedLinesCallback((String s) -> logger.error("skip line:" + s));
        return reader;
    }

}
