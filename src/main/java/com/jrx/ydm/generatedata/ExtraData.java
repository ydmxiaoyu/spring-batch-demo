package com.jrx.ydm.generatedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 从数据库中抽取学生信息
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/5 22:16
 */
public class ExtraData {

    private JdbcTemplate jdbcTemplate;

    public ExtraData() {
        // 构造一个 JdbcTemplate 并注入 dataSource
        this.jdbcTemplate = new JdbcTemplate();
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/batch?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");

        DataSource dataSource = dataSourceBuilder.build();
        this.jdbcTemplate.setDataSource(dataSource);
    }

    /**
     * 抽取学生 ID
     * @return list
     */
    public List<String> extraStudentId(){
        final String sql = "SELECT student_id FROM student_info";

        return jdbcTemplate.query(sql, new RowMapper<String>() {
            @Nullable
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString(1);
            }
        });
    }

}
