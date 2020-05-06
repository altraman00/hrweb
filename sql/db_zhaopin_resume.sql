/*
 Navicat Premium Data Transfer

 Source Server         : test_zhaopin
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : 111.230.70.125:3389
 Source Schema         : db_feo_zhaopin_resume

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 06/05/2020 10:36:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bi_resume
-- ----------------------------
DROP TABLE IF EXISTS `bi_resume`;
CREATE TABLE `bi_resume` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `job_seeker_name` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `degree` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '教育背景ids 参考constant表',
  `current_state` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '求职状态  ',
  `in_way` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '简历来源方式：190001：主动投递，190002：搜索后存入，190003：从简历管理上传后存入，190004：从人才库上传后存入',
  `phone` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号码',
  `plat_id` varchar(11) CHARACTER SET utf8 DEFAULT '10007' COMMENT '简历来源 10001：智联，10002:58同城，10003：猎聘，10004：Boss直聘，10005:前程无忧，10006：拉钩',
  `state` int(10) DEFAULT NULL COMMENT '状态',
  `age` int(20) DEFAULT NULL COMMENT '年龄',
  `email` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '电子邮箱',
  `sex` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '性别，70001表示男，70002表示女',
  `birthday` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '生日',
  `work_length_max` int(3) DEFAULT '0' COMMENT '工作年限最高',
  `work_length_min` int(3) DEFAULT '0' COMMENT '工作年限最低',
  `work_length` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '工作年限，有的简历可能存的是1-3年，有的可能直接存的3年',
  `is_marry` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '已婚，未婚',
  `job_title` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '应聘职位  Java工程师',
  `reside_address` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '住址',
  `expect_salary_max` int(20) DEFAULT NULL COMMENT '期望最高薪资',
  `expect_salary_min` int(20) DEFAULT NULL COMMENT '期望最低薪资',
  `expect_place` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '期望工作地点',
  `expect_job` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '期望从事职业',
  `expect_industry` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '期待行业 互联网/电子商务、计算机软件、其他',
  `photo_path` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像路径',
  `expect_job_property` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '20001全职，20002兼职，20003实习',
  `political_status` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '团员，党员等',
  `university` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '专业',
  `nation` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '民族',
  `bitrh_place` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '籍贯',
  `certificate_type` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '证件类型',
  `certificate_num` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '证件号码',
  `wechat_num` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '微信号',
  `health` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '健康状况',
  `hobbies` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '兴趣爱好',
  `housing_status` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '居住状况',
  `house_area` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '居住所在区',
  `house_address` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '目前住址',
  `trace_id` mediumtext COMMENT '插件获取的html文件',
  `deleted` int(11) DEFAULT '0',
  `create_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `version` varchar(255) DEFAULT '0',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  `domicile_place` varchar(255) DEFAULT NULL COMMENT '户口所在地',
  `self_evaluation` varchar(1000) DEFAULT NULL COMMENT '自我评价',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `seekerid` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='简历表\r\n';

-- ----------------------------
-- Table structure for bi_resume_education
-- ----------------------------
DROP TABLE IF EXISTS `bi_resume_education`;
CREATE TABLE `bi_resume_education` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '教育背景id',
  `resume_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '从属简历id',
  `university` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '学校名称',
  `education` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '学历code',
  `education_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '学历',
  `degree` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '学位',
  `education_type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '学历性质',
  `major` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '专业名称',
  `education_from` varchar(32) DEFAULT NULL COMMENT '学习时间起点',
  `education_to` varchar(32) DEFAULT NULL COMMENT '学习时间终点',
  `comment` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `deleted` int(11) DEFAULT '0',
  `create_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `version` varchar(255) DEFAULT '0',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教育背景表';

-- ----------------------------
-- Table structure for bi_resume_extend
-- ----------------------------
DROP TABLE IF EXISTS `bi_resume_extend`;
CREATE TABLE `bi_resume_extend` (
  `id` char(32) NOT NULL,
  `resume_id` char(32) DEFAULT NULL COMMENT '简历id',
  `interviewed_before` varchar(255) DEFAULT NULL COMMENT '是否参加过尚德机构的面试',
  `division_before` varchar(255) DEFAULT NULL COMMENT '曾经面试过的事业部',
  `position_before` varchar(255) DEFAULT NULL COMMENT '面试的职务',
  `interview_date_before` timestamp NULL DEFAULT NULL COMMENT '面试的时间',
  `choose_reason` varchar(255) DEFAULT NULL COMMENT '应聘本岗位的原因（多选）',
  `care_point` varchar(255) DEFAULT NULL COMMENT '选择工作机会时看重的项目（多选）',
  `career_plan_short` varchar(255) DEFAULT NULL COMMENT '短期职业规划',
  `plat_name` varchar(255) DEFAULT NULL COMMENT '应聘渠道',
  `introducer` varchar(255) DEFAULT NULL COMMENT '推荐人',
  `salary_expect` varchar(255) DEFAULT NULL COMMENT '期望月薪（税前）',
  `entry_date_lately` timestamp NULL DEFAULT NULL COMMENT '最快入职时间',
  `deleted` int(11) DEFAULT '0',
  `create_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `version` varchar(255) DEFAULT '0',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for bi_resume_parse_log
-- ----------------------------
DROP TABLE IF EXISTS `bi_resume_parse_log`;
CREATE TABLE `bi_resume_parse_log` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `resume_id` varchar(32) DEFAULT NULL COMMENT '简历id',
  `platform` varchar(32) DEFAULT NULL COMMENT '平台',
  `resume_detail` mediumtext COMMENT '简历详情',
  `plug_html` mediumtext COMMENT '插件获取的html文件',
  `platform_resume_url` varchar(5000) DEFAULT NULL COMMENT '平台的简历url',
  `status` varchar(32) DEFAULT NULL COMMENT '解析是否成功',
  `trace_id` varchar(32) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `create_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `version` varchar(255) DEFAULT '0',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `seekerid` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='简历表\r\n';

-- ----------------------------
-- Table structure for bi_resume_skills
-- ----------------------------
DROP TABLE IF EXISTS `bi_resume_skills`;
CREATE TABLE `bi_resume_skills` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '技能id',
  `resume_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '从属简历id',
  `skill_name` varchar(500) CHARACTER SET utf8 NOT NULL COMMENT '技能名称',
  `level` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '掌握程度',
  `comment` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `deleted` int(11) DEFAULT '0',
  `create_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `version` varchar(255) DEFAULT '0',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='技能表';

-- ----------------------------
-- Table structure for bi_resume_work_exp
-- ----------------------------
DROP TABLE IF EXISTS `bi_resume_work_exp`;
CREATE TABLE `bi_resume_work_exp` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '工作经历id',
  `resume_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '从属简历id',
  `on_job_from` varchar(32) DEFAULT NULL COMMENT '在职时间起点',
  `on_job_to` varchar(32) DEFAULT NULL COMMENT '在职时间终点',
  `work_length` int(11) DEFAULT NULL COMMENT '工作年限',
  `company_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '公司名称',
  `position_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '职位名称',
  `salary` varchar(32) DEFAULT NULL COMMENT '离职薪资待遇(税前)',
  `certifier_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '证明人名字',
  `certifier_phone` char(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '证明人电话',
  `work_content` mediumtext CHARACTER SET utf8 COMMENT '工作内容',
  `comment` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `deleted` int(11) DEFAULT '0',
  `create_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_dt` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `version` varchar(255) DEFAULT '0',
  `sort_no` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作经历表';

SET FOREIGN_KEY_CHECKS = 1;
