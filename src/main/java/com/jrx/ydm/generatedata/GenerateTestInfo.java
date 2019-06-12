package com.jrx.ydm.generatedata;

import com.jrx.ydm.generatedata.testenum.SubjectEnum;
import com.jrx.ydm.generatedata.testenum.TestTimeEnum;
import com.jrx.ydm.springbatchdemo.model.TestInfo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生成考试信息
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/5 23:34
 */
public class GenerateTestInfo {

    /*public static final String TESTTIME_CHINESE = "2019-05-01 14:00:00";
    public static final String TESTTIME_MATH = "2019-05-01 08:00:00";
    public static final String TESTTIME_ENGLISH = "2019-05-02 08:00:00";*/

    /**
     * 成绩不及格部分的占比： ( 3-1 ) * 10%
     */
    public static final int FAIL = 3;
    /**
     * 成绩在 [60,80) 之间的人数占比： ( 9-3 ) * 10%
     */
    public static final int WELL = 9;
    /**
     * 序号后缀部分的长度
     */
    public static final int LEN_SUBFIX = 6;

    /**
     * 考试序号前缀
     */
    public static final String PREFIX = "2019";

    /**
     * list 集合初始容量
     */
    public static final int RECODE_NUMBER = 30000;

    /**
     * 随机对象
     */
    private Random random = new Random();

    /**
     * 存放 TestInfo 对象
     */
    private List<TestInfo> testInfoList = new ArrayList<>(RECODE_NUMBER);

    /**
     * 考试科目数组
     */
    private static final String[] SUBJECTS = {
            SubjectEnum.SUBJECT_CHINESE.getSubject(),SubjectEnum.SUBJECT_MATH.getSubject(),SubjectEnum.SUBJECT_ENGLISH.getSubject()};

    /**
     * 考试时间数组
     */
    private static final Timestamp[] TESTTIMES = {
            TestTimeEnum.TEST_TIME_CHINESE.getTestTime(),TestTimeEnum.TEST_TIME_MATH.getTestTime(),TestTimeEnum.TEST_TIME_ENGLISH.getTestTime()
    };

    /**
     * 生成考试信息对象
     * @param list 学生 id 集合
     * @return 所有考试信息的 List 集合
     */
    public List<TestInfo> generateTestInfoObject(List<String> list){
        // 遍历 list 集合中的所有学生 id，一个学生生成 3 条考试信息
        for (String stuId:list) {
            // 生成考试序号
            String xh = generateTestXH(stuId);

            // 每个学生对应的每门科目都生成考试成绩
            for (int i = 0,n = SUBJECTS.length; i < n; i++){
                TestInfo testInfo = new TestInfo();
                // 设置学生id
                testInfo.setStudentId(stuId);
                // 设置考试序号
                testInfo.setXh(xh);
                // 设置考试科目
                testInfo.setSubject(SUBJECTS[i]);
                // 设置考试时间
                testInfo.setTestTime(TESTTIMES[i].toString());
                // 设置考试成绩
                testInfo.setScore(generateScore());
                // 把对象添加到 list 集合中
                testInfoList.add(testInfo);
                System.out.println("创建的testInfo对象：" + testInfo.toString());
            }

        }

        return testInfoList;
    }


    /**
     * 生成考试成绩
     * @return
     */
    private BigDecimal generateScore(){
        int k = random.nextInt(10) + 1;

        if(k < FAIL){
            // 0-59分之间的占比为 20%
            return BigDecimal.valueOf(random.nextInt(60));
        } else if (k < WELL){
            // 60-80分之间的占比为 60%
            return BigDecimal.valueOf(random.nextInt(20) + 60);
        } else {
            // 80-100分之间的占比为20%
            return BigDecimal.valueOf(random.nextInt(21) + 80);
        }
//        return BigDecimal.valueOf(0);
    }


    /**
     * 生成唯一的考试序号
     * @param stuId 学生 id
     * @return 考试序号
     */
    private String generateTestXH(String stuId){
        int length = stuId.length();
        int n;
        StringBuilder sb = new StringBuilder();
        // 不足 6 位的自动在前面补 0
        if ( (n = LEN_SUBFIX - length) > 0){
            for (int i = 1; i <= n ; i++) {
                sb.append("0");
            }
        }

        return PREFIX + sb.toString() + stuId;
    }


}
