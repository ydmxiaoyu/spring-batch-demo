package com.jrx.ydm.springbatchdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 考试汇总信息表映射的类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 11:02
 */
@Entity
@Table(name = "test_all")
public class TestAll {

    /**
     * 自增主键
     */
    @Id
    @Column(name = "id")
    private int id;
    /**
     * 学号
     */
    @Column(name = "student_id")
    private String studentId;
    /**
     * 考试序号
     */
    @Column(name = "xh")
    private String xh;
    /**
     * 总成绩
     */
    @Column(name = "score_all")
    private BigDecimal scoreAll;
    /**
     * 平均成绩
     */
    @Column(name = "score_avg")
    private BigDecimal scoreAvg;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(studentId).append(',')
                .append(xh).append(',')
                .append(scoreAll).append(',')
                .append(scoreAvg).append(',')
                .append("\r\n").toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public BigDecimal getScoreAll() {
        return scoreAll;
    }

    public void setScoreAll(BigDecimal scoreAll) {
        this.scoreAll = scoreAll;
    }

    public BigDecimal getScoreAvg() {
        return scoreAvg;
    }

    public void setScoreAvg(BigDecimal scoreAvg) {
        this.scoreAvg = scoreAvg;
    }
}
