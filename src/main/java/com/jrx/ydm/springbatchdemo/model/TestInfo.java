package com.jrx.ydm.springbatchdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 考试明细类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/5 22:17
 */
@Entity
@Table(name = "test_info")
public class TestInfo {

    /**
     * 主键自增 id
     */
    @Id
    @Column(name = "id")
    private int id;
    /**
     * 学生学号
     */
    @Column(name = "student_id")
    private String studentId;
    /**
     * 科目
     */
    @Column(name = "subject")
    private String subject;
    /**
     * 考试序号
     */
    @Column(name = "xh")
    private String xh;
    /**
     * 考试分数
     */
    @Column(name = "score")
    private BigDecimal score;
    /**
     * 考试时间
     */
    @Column(name = "test_time")
    private Timestamp testTime;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append(studentId).append(',')
                .append(subject).append(',')
                .append(xh).append(',')
                .append(score.toString()).append(',')
                .append(testTime.toString()).append("\r\n").toString();
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Timestamp getTestTime() {
        return testTime;
    }

    /**
     *
     * @param testTime 当从文件读入的时候传入参数是 String 类型
     */
    public void setTestTime(String testTime) {
        this.testTime = Timestamp.valueOf(testTime);
    }

    /*public void setTestTime(Timestamp testTime) {
        this.testTime = testTime;
    }*/
}
