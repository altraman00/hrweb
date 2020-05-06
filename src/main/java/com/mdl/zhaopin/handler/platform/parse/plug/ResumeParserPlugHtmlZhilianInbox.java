package com.mdl.zhaopin.handler.platform.parse.plug;

import com.mdl.zhaopin.DTO.ResumeBaseDTO;
import com.mdl.zhaopin.DTO.ResumeParseEducationDTO;
import com.mdl.zhaopin.DTO.ResumeParseSkillsDTO;
import com.mdl.zhaopin.DTO.ResumeParseWorkExpDTO;
import com.mdl.zhaopin.enums.InWayEnums;
import com.mdl.zhaopin.enums.PlatformEnums;
import com.mdl.zhaopin.handler.platform.parse.AbstractResumeParser;
import com.mdl.zhaopin.handler.platform.parse.IResumeParser;
import com.mdl.zhaopin.handler.platform.resume.Resume;
import com.mdl.zhaopin.handler.platform.resume.ResumeZhilian;
import com.mdl.zhaopin.utils.DateUtil;
import com.mdl.zhaopin.utils.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : 针对通过插件在智联上获取的 收件箱 的简历的html文件解析
 *
 * 前端插件获取html脚本 ： document.getElementById('resume-detail-wrapper').outerHTML
 *
 * @Author : xiekun
 * @Create Date : 2020年04月15日 15:36
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class ResumeParserPlugHtmlZhilianInbox extends AbstractResumeParser implements IResumeParser {

    private static final Logger logger = LoggerFactory.getLogger(ResumeParserPlugHtmlZhilianInbox.class);

    private ResumeBaseDTO resumeBase;
    private List<ResumeParseEducationDTO> eduList;
    private List<ResumeParseSkillsDTO> skillList;
    private List<ResumeParseWorkExpDTO> workExpList;

    @Override
    public String getName() {
        return "智联招聘-收件箱的简历";
    }

    @Override
    public Resume parse(String resumeHtml) {

//        logger.debug("【parsePlugHtml】-【ResumeParserPlugHtmlZhilianInbox】:{}",resumeHtml);

        ResumeZhilian resume = new ResumeZhilian();
        resumeBase = new ResumeBaseDTO();
        eduList = new ArrayList<>();
        skillList = new ArrayList<>();
        workExpList = new ArrayList<>();

        //1、获取所有模块的html
        Map<String, String> resumeModuleMap = getResumeMoudelMap(resumeHtml);

        //2、各个模块进行解析获取数据
        parseResumeMap(resumeModuleMap);
        resumeBase.setInWay(InWayEnums.RESUME_DELIVERY.getCode()+"");
        resumeBase.setPlatId(PlatformEnums.ZHI_LIAN.getCode()+"");
        resume.setResumeBase(resumeBase);
        resume.setEduList(eduList);
        resume.setSkillList(skillList);
        resume.setWorkExpList(workExpList);

        return resume;
    }


    /**
     * 1、获取所有模块的html
     *
     * @param htmlStr
     * @return
     */
    private Map<String, String> getResumeMoudelMap(String htmlStr) {

        Map<String, String> resumeMap = new HashMap<>();
        Document doc = parse2Html(htmlStr);

        //------------------------------------------------基本信息------------------------------------------------
        try {
            String 基本信息_element = doc.getElementsByClass("resume-content__candidate-basic").html();
            resumeMap.put("基本信息", 基本信息_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】基本信息 html获取失败");
        }

        //简历信息 源头 "resumeDetail"
        //------------------------------------------------求职意向------------------------------------------------
        try {
            String 求职意向_element = doc.getElementById("resumeDetail").select("[data-bind = visible: isShowIntent]").html();
            resumeMap.put("求职意向", 求职意向_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】求职意向 html获取失败");
        }

        //------------------------------------------------教育经历------------------------------------------------
        try {
            String 教育经历_element = doc.getElementById("resumeDetail").select("[data-bind = if: educationExperience().length]").html();
            resumeMap.put("教育经历", 教育经历_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】教育经历 html获取失败");
        }

        //------------------------------------------------工作经验------------------------------------------------
        try {
            String 工作经验_element = doc.getElementById("resumeDetail").select("[data-bind = if: workExperience().length]").html();
            resumeMap.put("工作经验", 工作经验_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】工作经验 html获取失败");
        }

        //------------------------------------------------项目经验------------------------------------------------
        try {
            String 项目经验_element = doc.getElementById("resumeDetail").select("[data-bind = if: projectExperience().length]").html();
            resumeMap.put("项目经验", 项目经验_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】项目经验 html获取失败");
        }

        //------------------------------------------------专业技能------------------------------------------------
        try {
            String 专业技能_element = doc.getElementById("resumeDetail").select("[data-bind = if: professionalSkill().length]").html();
            resumeMap.put("专业技能", 专业技能_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】专业技能 html获取失败");
        }

        //------------------------------------------------自我评价------------------------------------------------
        try {
            String 自我评价_element = doc.getElementById("resumeDetail").select("[data-bind = if: evaluate.content]").html();
            resumeMap.put("自我评价", 自我评价_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】自我评价 html获取失败");
        }

        //------------------------------------------------培训经历------------------------------------------------
        try {
            String 培训经历_element = doc.getElementById("resumeDetail").select("[data-bind = if: trainingExperience().length]").html();
            resumeMap.put("培训经历", 培训经历_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】培训经历 html获取失败");
        }

        //------------------------------------------------所获证书------------------------------------------------
        try {
            String 所获证书_element = doc.getElementById("resumeDetail").select("[data-bind = if: achieveCertificate().length]").html();
            resumeMap.put("所获证书", 所获证书_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】所获证书 html获取失败");
        }

        //------------------------------------------------在校情况------------------------------------------------
        try {
            String 在校情况_element = doc.getElementById("resumeDetail").select("[data-bind = if: achieveScholarship().length || achieveAward().length || studyInfomation()]").html();
            resumeMap.put("在校情况", 在校情况_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】在校情况 html获取失败");
        }

        //------------------------------------------------语言能力------------------------------------------------
        try {
            String 语言能力_element = doc.getElementById("resumeDetail").select("[data-bind = if: languageSkill().length]").html();
            resumeMap.put("语言能力", 语言能力_element);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】语言能力 html获取失败");
        }
        logger.debug("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlZhilianInbox】resumeMap:{}",JSONUtils.objectToJson(resumeMap));
        return resumeMap;
    }


    /**
     * 解析简历map
     * @param map
     */
    private void parseResumeMap(Map<String,String> map) {

        logger.debug("\n------------------------------------------------基本信息------------------------------------------------");
        try {
            String seekerBaseInfoHtml = map.get("基本信息");
            getSeekerBaseInfo(seekerBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】基本信息 html获取失败");
        }


        logger.debug("\n------------------------------------------------求职意向------------------------------------------------");
        try {
            String JobHuntBaseInfoHtml = map.get("求职意向");
            getJobHuntBaseInfo(JobHuntBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】求职意向 html获取失败");
        }

        logger.debug("\n------------------------------------------------教育经历------------------------------------------------");
        try {
            String eduBaseInfoHtml = map.get("教育经历");
            getEduBaseInfo(eduBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】教育经历 html获取失败");
        }

        logger.debug("\n------------------------------------------------工作经验------------------------------------------------");
        try {
            String workExpBaseInfoHtml = map.get("工作经验");
            getWorkExpBaseInfo(workExpBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】工作经验 html获取失败");
        }

        logger.debug("\n------------------------------------------------项目经验------------------------------------------------");
        try {
            String projectExpBaseInfoHtml = map.get("项目经验");
            getProjectExpBaseInfo(projectExpBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】项目经验 html获取失败");
        }

        logger.debug("\n------------------------------------------------专业技能------------------------------------------------");
        try {
            String skillBaseInfoHtml = map.get("专业技能");
            getSkillBaseInfo(skillBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】专业技能 html获取失败");
        }

        logger.debug("\n------------------------------------------------自我评价------------------------------------------------");
        try {
            String selfEvalBaseInfoHtml = map.get("自我评价");
            getSelfEvalBaseInfo(selfEvalBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】自我评价 html获取失败");
        }

        logger.debug("\n------------------------------------------------培训经历------------------------------------------------");
        try {
            String trainBaseInfoHtml = map.get("培训经历");
            getTrainBaseInfo(trainBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlZhilianInbox】培训经历 html获取失败");
        }

//        logger.debug("\n------------------------------------------------所获证书------------------------------------------------");
//        String certificateBaseInfoHtml = map.get("所获证书");
//        getCertificateBaseInfo(certificateBaseInfoHtml);

//        logger.debug("\n------------------------------------------------语言能力------------------------------------------------");
//        String languageBaseInfoHtml = map.get("语言能力");
//        getLanguageBaseInfo(languageBaseInfoHtml);
    }


//    /**
//     * 语言能力
//     *
//     * @param languageBaseInfoHtml
//     */
//    private void getLanguageBaseInfo(String languageBaseInfoHtml) {
//        Document document = parse2Html(languageBaseInfoHtml);
//        Elements select = document.select("ul > li");
//        for (Element element : select) {
//            String 语言能力 = element.text();
//            logger.debug("\n语言能力:{}",语言能力);
//        }
//    }


//    /**
//     * 证书名字及时间
//     *
//     * @param certificateBaseInfoHtml
//     */
//    private void getCertificateBaseInfo(String certificateBaseInfoHtml) {
//        Document document = parse2Html(certificateBaseInfoHtml);
//        Elements ul = document.select("ul > li");
//        for (Element element : ul) {
//            //#resumeDetail > div:nth-child(9) > ul > li:nth-child(1)
//            String 证书名字及时间 = element.text();
//            logger.debug("\n证书名字及时间：{}",证书名字及时间);
//        }
//    }


    /**
     * 培训经历
     *
     * @param trainBaseInfoHtml
     */
    private void getTrainBaseInfo(String trainBaseInfoHtml) {
        Document document = parse2Html(trainBaseInfoHtml);
        Elements ul = document.select("ul > li");
        for (Element element : ul) {
            String 培训时间 = element.select("p > span").text();
            String 培训机构 = element.select("dl > dd").text();
            logger.debug("\n培训时间：{}\n培训机构：{}",培训时间,培训机构);
        }
    }


    /**
     * 自我评价
     *
     * @param selfEvalBaseInfoHtml
     */
    private void getSelfEvalBaseInfo(String selfEvalBaseInfoHtml) {
        Document document = parse2Html(selfEvalBaseInfoHtml);
        String 自我评价 = document.select("div").text().trim();
        //自我评价
        resumeBase.setSelfEvaluation(自我评价);
        logger.debug("\n自我评价:{}",自我评价);
    }


    /**
     * 专业技能
     *
     * @param skillBaseInfoHtml
     */
    private void getSkillBaseInfo(String skillBaseInfoHtml) {
        Document document = parse2Html(skillBaseInfoHtml);
        Elements select = document.select("ul > li");
        ResumeParseSkillsDTO skillDTO;
        for (Element element : select) {
            skillDTO = new ResumeParseSkillsDTO();
            String 技能名称 = element.select("p.is-weight-bold").text();
            String 技能掌握程度 = element.select("p.resume-content__labels--sub > span:nth-child(1)").text();
            String 技能掌握时长 = element.select("p.resume-content__labels--sub > span:nth-child(2)").text();

            //技能名称
            skillDTO.setSkillName(技能名称);
            //掌握程度
            skillDTO.setLevel(技能掌握程度);
            skillList.add(skillDTO);

            logger.debug("\n技能名称:{}\n技能掌握程度:{}\n技能掌握时长:{}",技能名称,技能掌握程度,技能掌握时长);
        }
    }


    /**
     * 项目经验
     *
     * @param projectExpBaseInfoHtml
     */
    private void getProjectExpBaseInfo(String projectExpBaseInfoHtml) {
        Document document = parse2Html(projectExpBaseInfoHtml);
        Elements ul = document.select("ul > li");
        for (Element element : ul) {
            String 起止时间 = element.select("p > span").get(0).text();
            String 项目名字 = element.select("p > span:nth-child(2)").text();
            String 项目时长 = element.select("p > span.timeline__time-range").text();
            String 项目描述 = element.select("dl > dd").text();

            logger.debug("\n起止时间:{}\n项目名字:{}\n项目时长:{}\n项目描述:{}",起止时间,项目名字,项目时长,项目描述);
        }
    }


    /**
     * 工作经验
     *
     * @param workExpBaseInfoHtml
     */
    private void getWorkExpBaseInfo(String workExpBaseInfoHtml) {
        Document document = parse2Html(workExpBaseInfoHtml);
        Elements select = document.select("body > ul > li");
        ResumeParseWorkExpDTO workExpDTO;
        for (Element element : select) {
            workExpDTO = new ResumeParseWorkExpDTO();
            String 开始时间 = element.select("[data-bind = textQ: data.workDate.beginDate]").text();
            String 结束时间 = element.select("[data-bind = textQ: data.workDate.endDate]").text();
            String 公司名字 = element.select("[data-bind = textQ: data.companyName]").text();
            String 就职时长 = element.select("[data-bind = textQ: data.workDate.range]").text();
            String 岗位 = element.select("[data-bind = textQ: data.jobTitle]").text();
            String 离职薪资 = element.select("[data-bind = textQ: data.salaray]").text();
            String 工作描述 = element.select("[data-bind = textQ: data.workDescription]").text();

            开始时间 = DateUtil.stringToString(开始时间, DateUtil.DATETIME_PATTERN_YYYY_POINT_MM, DateUtil.DATETIME_PATTERN_YYYY_MM);
            if (!结束时间.contains("至今")) {
                结束时间 = DateUtil.stringToString(结束时间, DateUtil.DATETIME_PATTERN_YYYY_POINT_MM, DateUtil.DATETIME_PATTERN_YYYY_MM);
            }
            //工作开始时间
            workExpDTO.setOnJobFrom(开始时间);
            //工作结束时间
            workExpDTO.setOnJobTo(结束时间);

            //工作年限
//            String currentDateStr = DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN_YYYY_MM);
            workExpDTO.setWorkLength(null);
            //公司名字
            workExpDTO.setCompanyName(公司名字);
            //岗位
            workExpDTO.setPositionName(岗位);
            //工作描述
            workExpDTO.setWorkContent(工作描述);

            //离职薪资
            String leaveSalary = "";
            List<Integer> numbers = getSalaryArr(离职薪资);
            if (numbers != null && numbers.size() != 0) {
                if (numbers.size() == 2) {
                    leaveSalary = numbers.get(0) + "-" + numbers.get(1);
                }
                if (numbers.size() == 1) {
                    leaveSalary = numbers.get(0) + "";
                }
            }
            //离职薪酬
            workExpDTO.setSalary(leaveSalary);
            //证明人名字
            workExpDTO.setCertifierName(null);
            //证明人电话
            workExpDTO.setCertifierPhone(null);
            workExpList.add(workExpDTO);

            logger.debug("\n开始时间:{}\n结束时间:{}\n公司名字:{}\n就职时长:{}\n岗位:{}\n离职薪资:{}\n工作描述:{}"
                    ,开始时间,结束时间,公司名字,就职时长,岗位,离职薪资,工作描述);
        }
    }


    /**
     * 教育经历
     *
     * @param eduBaseInfoHtml
     */
    private void getEduBaseInfo(String eduBaseInfoHtml) {
        Document document = parse2Html(eduBaseInfoHtml);
        Elements select = document.select("body > ul > li");
        ResumeParseEducationDTO eduDTO;
        for (Element element : select) {
            eduDTO = new ResumeParseEducationDTO();
            String 上学开始时间 = element.select("[data-bind = textQ: data.eduDate.beginDate]").text();
            String 上学结束时间 = element.select("[data-bind = textQ: data.eduDate.endDate]").text();
            String 学校 = element.select("[data-bind = textQ: data.schoolName]").text();
            String 专业 = element.select("[data-bind = textQ: data.majorName]").text();

            //开始时间
            上学开始时间 = DateUtil.stringToString(上学开始时间,DateUtil.DATETIME_PATTERN_YYYY_POINT_MM,DateUtil.DATETIME_PATTERN_YYYY_MM);
            eduDTO.setEducationFrom(上学开始时间);

            //结束时间
            if(!上学结束时间.contains("至今")){
                上学结束时间 = DateUtil.stringToString(上学结束时间,DateUtil.DATETIME_PATTERN_YYYY_POINT_MM,DateUtil.DATETIME_PATTERN_YYYY_MM);
            }
            eduDTO.setEducationTo(上学结束时间);
            //学校名字
            eduDTO.setUniversity(学校);
            //专业
            eduDTO.setMajor(专业);
            //学位
            eduDTO.setDegree(null);
            eduList.add(eduDTO);

            logger.debug("\n上学开始时间：{}\n上学结束时间：{}\n学校：{}\n专业：{}",上学开始时间,上学结束时间,学校,专业);
        }

    }

    /**
     * 求职意向
     *
     * @param JobHuntBaseInfoHtml
     */
    private void getJobHuntBaseInfo(String JobHuntBaseInfoHtml) {
        Element resumeDetail = parse2Html(JobHuntBaseInfoHtml);

        List<String> keyList = resumeDetail.select("[data-bind = foreach: { data: intents, as: 'intent' }]")
                .select("dt").stream().map(Element::text).collect(Collectors.toList());

        List<String> valList = resumeDetail.select("[data-bind = foreach: { data: intents, as: 'intent' }]")
                .select("dd").stream().map(Element::text).collect(Collectors.toList());

        Map<String, String> resMap = new HashMap<>();
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i).replace("　", "").trim();
            int valSize = valList.size();
            if (i <= valSize) {
                String val = valList.get(i).trim();
                resMap.put(key, val);
            }
        }

        String 期望工作地点 = resMap.get("期望工作地点");
        String 期望月薪 = resMap.get("期望月薪");
        String 目前状况 = resMap.get("目前状况");
        String 期望工作性质 = resMap.get("期望工作性质");
        String 期望从事职业 = resMap.get("期望从事职业");
        String 期望从事行业 = resMap.get("期望从事行业");

        //期望薪资
        Integer expectSalaryMin = 0;
        Integer expectSalaryMax = 0;
        List<Integer> numbers = getSalaryArr(期望月薪);
        if(numbers != null){
            if(numbers.size() == 2){
                expectSalaryMin = numbers.get(0);
                expectSalaryMax = numbers.get(1);
            }
            if(numbers.size() == 1){
                expectSalaryMax = numbers.get(0);
            }
        }
        resumeBase.setExpectSalaryMin(expectSalaryMin);
        resumeBase.setExpectSalaryMax(expectSalaryMax);
        //期望工作地点
        resumeBase.setExpectPlace(期望工作地点);
        //目前在职状态
        resumeBase.setCurrentState(目前状况);

        logger.debug("\n期望工作地点：{}\n期望月薪：{}\n目前状况：{}\n期望工作性质：{}\n期望从事职业：{}\n期望从事行业：{}"
                ,期望工作地点,期望月薪,目前状况,期望工作性质,期望从事职业,期望从事行业);

    }


    /**
     * 基本信息
     *
     * @param seekerBaseInfoHtml
     */
    private void getSeekerBaseInfo(String seekerBaseInfoHtml) {
        Element seekerBaseInfo = parse2Html(seekerBaseInfoHtml);
        /** 名字 **/
        String 名字 = seekerBaseInfo.select("span.resume-tomb").select("[data-bind = textQ: candidateName]").text();
        /** 性别 **/
        String 性别 = seekerBaseInfo.select("[data-bind = $key: genderDesc]").text();
        /** 年龄,出生年月日 **/
        String 年龄 = seekerBaseInfo.select("[data-bind = text: age]").text();
        /** 工作年限 **/
        String 工作年限 = seekerBaseInfo.select("[data-bind = text: workYears]").text();
        /** 学历 **/
        String 学历 = seekerBaseInfo.select("[data-bind = text: eduLevel()]").text();
        /** 居住城市 **/
        String 居住城市 = seekerBaseInfo.select("[data-bind = textQ: currentCity()]").text();
        /**户口**/
        String 户口 = seekerBaseInfo.select("[data-bind = textQ: hukou()]").text();

        /** 手机号 **/
//        String 手机号 = getTelnum(seekerBaseInfoHtml);
        String 手机号 = seekerBaseInfo.select("[data-bind = textQ: mobilePhone]").text();
        /** 邮箱 **/
//        String 邮箱 = getEmailnum(seekerBaseInfoHtml);
        String 邮箱 = seekerBaseInfo.select("[data-bind = textQ: email]").text();

        String[] ageArr = 年龄.replaceAll(" ","").split("岁");
        Integer age = null;
        String birthday = null;
        if(ageArr != null & ageArr.length == 2){
            age = Integer.valueOf(ageArr[0]);
            birthday = ageArr[1].replaceAll("（","").replaceAll("）","").trim();
            //yyyy年MM月dd日 转化为 yyyy-MM-dd 格式
            birthday = DateUtil.stringToString(birthday,DateUtil.EXPANDED_DATE_FORMAT_WORD_2,DateUtil.ISO_EXPANDED_DATE_FORMAT);
        }

        //姓名
        resumeBase.setJobSeekerName(名字);
        //年龄
        resumeBase.setAge(age);
        //性别
        if(名字.contains("女士")){
            性别 = "女";
        }else if(名字.contains("先生")){
            性别 = "男";
        }
        resumeBase.setSex(parseSex(性别)+"");
        //手机号
        resumeBase.setPhone(手机号);
        //邮箱地址
        resumeBase.setEmail(邮箱);
        //出生年月
        resumeBase.setBirthday(birthday);
        //民族
        resumeBase.setNation(null);
        //身份证号
        resumeBase.setCertificateNum(null);
        //户口所在地
        resumeBase.setDomicilePlace(户口);
        //婚姻状况
        resumeBase.setIsMarry(null);
        //当前住址
        resumeBase.setHouseAddress(居住城市);
        //健康状况
        resumeBase.setHealth(null);
        //政治面貌
        resumeBase.setPoliticalStatus(null);
        //兴趣爱好
        resumeBase.setHobbies(null);

        logger.debug("\n名字：{}\n性别：{}\n年龄：{}\n出生年月:{}\n手机号:{}\n邮箱:{}\n户口:{}\n工作年限：{}\n学历：{}\n居住城市：{}",名字,性别,年龄,birthday,手机号,邮箱,户口,工作年限,学历,居住城市);
    }


    /**
     * html转Document
     *
     * @param html
     * @return
     * @throws Exception
     */
    private Document parse2Html(String html) {
        return Jsoup.parse(html);
    }


    public static void main(String[] args) {
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-html.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-任女士-插件.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-2.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-html.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-李先生-插件.html";

//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/智联招聘-插件-收件箱-徐建萍.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/智联招聘-插件-收件箱-徐建萍-智联改版后.html";

//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/智联招聘-插件-搜索未付费-王先生.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/智联招聘-插件-搜索已付费-王先生（王海涛）.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/222.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/智联招聘-插件-罗阳-名字重复.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/智联招聘-插件-精英人才-报错-朱先生.html";
        String filePath = "/Users/admin/Desktop/简历解析/智联招聘/智联招聘-插件-已下载模块-openFrom-2-李宁-直接报错.html";

        File file = new File(filePath);
        String html = null;
        try {
            html = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResumeParserPlugHtmlZhilianInbox resumeParser = new ResumeParserPlugHtmlZhilianInbox();
        Resume resume = resumeParser.parse(html);

        logger.debug("resume:{}",JSONUtils.objectToJson(resume));


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/基本信息.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getSeekerBaseInfo(html);


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/求职意向.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getJobHuntBaseInfo(html);


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/教育经历.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getEduBaseInfo(html);


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/工作经验.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getWorkExpBaseInfo(html);


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/项目经验.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getProjectExpBaseInfo(html);


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/自我评价.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getSelfEvalBaseInfo(html);


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/培训经历.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getTrainBaseInfo(html);


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/培训经历.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        resumeParser.getTrainBaseInfo(html);


    }


}
