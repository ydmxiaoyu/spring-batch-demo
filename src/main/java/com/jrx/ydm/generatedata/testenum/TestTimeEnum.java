package com.jrx.ydm.generatedata.testenum;

import java.sql.Timestamp;

/**
 * 考试时间枚举
 * @author yudaoming
 */
public enum TestTimeEnum {

    /**
     * 语文考试时间
     */
    TEST_TIME_CHINESE("2019-05-01 14:00:00"),
    /**
     * 数学考试时间
     */
    TEST_TIME_MATH("2019-05-01 08:00:00"),
    /**
     * 英语考试时间
     */
    TEST_TIME_ENGLISH("2019-05-02 08:00:00");

    private Timestamp testTime;

    private TestTimeEnum(String time){
        testTime = Timestamp.valueOf(time);
    }

    public Timestamp getTestTime() {
        return testTime;
    }

    public void setTestTime(Timestamp testTime) {
        this.testTime = testTime;
    }
}
