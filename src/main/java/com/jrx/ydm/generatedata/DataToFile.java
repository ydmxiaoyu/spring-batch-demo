package com.jrx.ydm.generatedata;

import com.jrx.ydm.springbatchdemo.model.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * 把考试信息数据存入文件
 *
 * @author yudaoming
 * @version 1.0
 * @date 2019/6/6 1:33
 */
public class DataToFile {

    /**
     * 文件路径
     */
    public static final String PATH = "C:\\Users\\Administrator.SKY-20181103GSC\\Desktop\\";
    /**
     * 文件名
     */
    public static final String FILE_NAME = "testinfo.csv";

    private static final Logger logger = LoggerFactory.getLogger(DataToFile.class);

    /*public static void main(String[] args) {
        new DataToFile().writeToCSVFile();
    }*/

    /**
     * 把数据写入到 csv 文件当中
     */
    public void writeToCSVFile(){
        try {
            File file = new File(PATH + FILE_NAME);
            boolean isCreate = false;
            if(!file.exists()){
                isCreate = file.createNewFile();
            }

            // 如果文件创建成功
            if(isCreate || file.exists()){
                OutputStream outputStream = new FileOutputStream(file);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                // 考试明细 list
                List<TestInfo> list = getTestInfoList();
//                logger.error("list:" + list.size());

                for (int i = 0, n = list.size(); i < n; i++) {
//                    logger.error("第" + i + "个对象：" + list.get(i).toString());
                    bufferedWriter.append(list.get(i).toString());
                    // 每 1000 条数据就刷出到文件中
                    if ((i % 1000 == 0) || (i == n - 1)){
                        bufferedWriter.flush();
                    }
                }

                bufferedWriter.close();
                writer.close();
                outputStream.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取考试明细的 list 集合
     * @return
     */
    private List<TestInfo> getTestInfoList(){
        ExtraData extraData = new ExtraData();
        List<String> stuIds = extraData.extraStudentId();
        return new GenerateTestInfo().generateTestInfoObject(stuIds);
    }


}
