package com.jrx.ydm.springbatchdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生信息类
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/4 18:06
 */
@Entity
@Table(name = "student_info")
public class Student {

    public Student() {
    }

    /**
     * 主键  学号
     */
    @Id
    @Column(name = "student_id")
    private String studentId;
    /**
     * 班级号
     */
    @Column(name = "class_id")
    private String classId;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 性别
     */
    @Column(name = "sex")
    private char sex;

    /**
     * 年龄
     */
    @Column(name = "age")
    private int age;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{")
                .append("studentId='").append(studentId).append('\'')
                .append(", classId='").append(classId).append('\'')
                .append(", name='").append(name).append('\'')
                .append(", sex='").append(sex).append('\'')
                .append(", age=").append(age)
                .append('}');
        return  sb.toString();
    }
}
