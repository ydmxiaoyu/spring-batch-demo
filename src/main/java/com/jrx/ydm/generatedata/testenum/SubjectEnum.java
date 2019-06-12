package com.jrx.ydm.generatedata.testenum;

/**
 * 考试科目枚举
 * @author yudaoming
 */
public enum SubjectEnum {

    /**
     * 语文科目
     */
    SUBJECT_CHINESE("语文"),
    /**
     * 数学科目
     */
    SUBJECT_MATH("数学"),
    /**
     * 英语科目
     */
    SUBJECT_ENGLISH("英语");

    /**
     * 考试科目
     */
    private String subject;

    private SubjectEnum(){
    }

    private SubjectEnum(String s){
        this.subject = s;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
