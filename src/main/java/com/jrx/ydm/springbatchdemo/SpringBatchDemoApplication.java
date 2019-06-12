package com.jrx.ydm.springbatchdemo;

import com.jrx.ydm.generatedata.ExtraData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

/**
 * 项目入口
 * @author yudaoming
 */
@SpringBootApplication
public class SpringBatchDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBatchDemoApplication.class);

	public static void main(String[] args) {
//		String jobName = "testAllJobDataFromFile";
		String jobName = "secondJob";

		try {
			ConfigurableApplicationContext context = SpringApplication.run(SpringBatchDemoApplication.class, args);
			// 获得一个作业注册器
			JobRegistry jobRegistry = context.getBean(JobRegistry.class);
			// 进行 Job 的注册
			Job job = jobRegistry.getJob(jobName);
			JobLauncher jobLauncher = context.getBean(JobLauncher.class);
			JobExecution jobExecution = jobLauncher.run(job,createJobParams());

			if (!jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
				throw new RuntimeException(String.format("%s Job execution failed.", jobName));
			}

			logger.info("-------------------------------作业执行完成-------------------------------");
		} catch (Exception e){
			logger.error(String.format("%s 作业执行失败.",jobName));
			e.printStackTrace();
		}

	}

	private static JobParameters createJobParams() {
		return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
	}

}
