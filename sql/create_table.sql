
# 学生信息表
CREATE TABLE student_info(
  student_id VARCHAR (16) NOT NULL COMMENT '学生ID',
  class_id VARCHAR(10) NOT NULL COMMENT '班级ID',
  name VARCHAR(20) NOT NULL COMMENT '姓名',
  sex CHAR(1) NOT NULL COMMENT '性别',
  age INT NOT NULL COMMENT '年龄',
  PRIMARY KEY(student_id)
)ENGINE = Innodb DEFAULT CHARSET = utf8;

# 考试明细表
CREATE TABLE test_info(
  id INT AUTO_INCREMENT COMMENT '主键',
  student_id VARCHAR (16) NOT NULL COMMENT '学生ID',
  subject VARCHAR(10) NOT NULL COMMENT '科目',
  xh VARCHAR(16) NOT NULL COMMENT '考试序号',
  score DECIMAL(4,1) DEFAULT 0 COMMENT '成绩',
  test_time DATETIME DEFAULT now() COMMENT '考试时间',
  PRIMARY KEY (id),
  CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES student_info(student_id)
)ENGINE = Innodb DEFAULT CHARSET = utf8;

# 考试汇总表
CREATE TABLE test_all(
  id INT AUTO_INCREMENT COMMENT '主键',
  student_id VARCHAR (16) NOT NULL COMMENT '学生ID',
  xh VARCHAR(16) NOT NULL COMMENT '考试序号',
  score_all DECIMAL(5,1) DEFAULT 0 COMMENT '总成绩',
  score_avg DECIMAL(4,1) DEFAULT 0 COMMENT '平均成绩',
  PRIMARY KEY (id),
  CONSTRAINT student_id_fk1 FOREIGN KEY (student_id) REFERENCES student_info(student_id)
)ENGINE = Innodb DEFAULT CHARSET = utf8;


