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
import com.mdl.zhaopin.handler.platform.resume.ResumeJob51;
import com.mdl.zhaopin.utils.DateUtil;
import com.mdl.zhaopin.utils.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : 针对通过插件在 51job 上获取的 搜索 的简历的html文件解析
 *
 * 前端插件获取html脚本 ： document.querySelector('#divResume').outerHTML
 *
 * @Author : xiekun
 * @Create Date : 2020年04月15日 15:36
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class ResumeParserPlugHtmlJob51Search extends AbstractResumeParser implements IResumeParser {

    private static final Logger logger = LoggerFactory.getLogger(ResumeParserPlugHtmlJob51Search.class);

    private ResumeBaseDTO resumeBase;
    private List<ResumeParseEducationDTO> eduList;
    private List<ResumeParseSkillsDTO> skillList;
    private List<ResumeParseWorkExpDTO> workExpList;

    @Override
    public String getName() {
        return "51Job";
    }

    @Override
    public Resume parse(String resumeHtml) {

//        logger.debug("【parsePlugHtml】-【ResumeParserPlugHtmlJob51Search】:{}",resumeHtml);

        ResumeJob51 resume = new ResumeJob51();
        resumeBase = new ResumeBaseDTO();
        eduList = new ArrayList<>();
        skillList = new ArrayList<>();
        workExpList = new ArrayList<>();

        //1、获取所有模块的html
        Map<String, String> moduleMap = getResumeMoudelMap(resumeHtml);

        //2、各个模块进行解析获取数据
        parseResumeMap(moduleMap);

        resumeBase.setInWay(InWayEnums.SEARCH_DOWNLOAD.getCode()+"");
        resumeBase.setPlatId(PlatformEnums.JOBS.getCode()+"");
        resume.setResumeBase(resumeBase);
        resume.setEduList(eduList);
        resume.setSkillList(skillList);
        resume.setWorkExpList(workExpList);

        logger.debug("【parsePlugHtml】-【ResumeParserPlugHtmlJob51Search】resume:{}",JSONUtils.objectToJson(resume));

        return resume;
    }

    /**
     * 1、获取所有模块的html
     *
     * @param resumeHtml
     * @return
     */
    private Map<String, String> getResumeMoudelMap(String resumeHtml) {
        Map<String, String> moduleMap = new HashMap<>();

        //构建整个resumeHtml的Document对象
        Document doc = parse2Html(resumeHtml);

        //#divResume > table.column
        //#divResume > table.column > tbody > tr > td
        /** 最外层的所有tables **/
        Elements tables = doc.getElementById("divResume").select("table.column > tbody > tr > td > table");

        if (tables != null) {
            //body > table > tbody > tr > td > table.bottom_border > tbody
            /** 第1层，基本信息姓名性别相关的table **/
            //------------------------------------------------基本信息------------------------------------------------
            try {
                //#divResume > table.column > tbody > tr > td > table.box1
                Element table0 = tables.get(0);
                moduleMap.put("基本信息", table0.html());
            } catch (Exception e) {
                logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlJob51Search】基本信息 html获取失败");
            }

            //------------------------------------------------学历信息------------------------------------------------
            /** 第2层，基本信息学历，学校等相关的table **/
            try {
                Element table1 = tables.get(1);
                moduleMap.put("学历信息", table1.html());
            } catch (Exception e) {
                logger.error("【parsePlugHtml】-【getModuleHtml】-【ResumeParserPlugHtmlJob51Search】学历信息 html获取失败");
            }

            /** 第3层，政治面貌等相关的table **/
            Element table2 = tables.get(2);
            if (table2 != null) {

                Elements divInfo1 = table2.getElementById("divInfo").select("td > table.box");
                //#divInfo > td > table:nth-child(3) > tbody > tr:nth-child(1) > td
                for(Element element : divInfo1){
                    element.select("tbody > tr > td.plate1 > span").remove();
                    String item_name = element.select("tbody > tr > td.plate1").text();
                    logger.debug("item_name:{}",item_name);
                    moduleMap.put(item_name, element.html());
                }
            }
        }
//        logger.debug("moduleMap:{}",JSONUtils.objectToJson(moduleMap));
        return moduleMap;
    }


    /**
     * 2、各个模块进行接续获取数据
     *
     * @param map
     */
    public void parseResumeMap(Map<String, String> map) {

        logger.debug("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】所有的模块:{}"
                , StringUtils.join((map.keySet().toArray()), "--->"));

        logger.debug("\n------------------------------------------------基本信息------------------------------------------------");
        try {
            String nameBaseInfoHtml = map.get("基本信息");
            getNameBaseInfo(nameBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】基本信息 html获取失败");
        }

        logger.debug("\n------------------------------------------------个人信息------------------------------------------------");
        try {
            String seekerBaseInfoHtml = map.get("个人信息");
            getSeekerBaseInfo(seekerBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】个人信息 html获取失败");
        }

        logger.debug("\n------------------------------------------------学历信息------------------------------------------------");
        try {
            String degreeBaseInfoHtml = map.get("学历信息");
            getDegreeBaseInfo(degreeBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】学历信息 html获取失败");
        }

        logger.debug("\n------------------------------------------------求职意向------------------------------------------------");
        try {
            String jobHuntBaseInfoHtml = map.get("求职意向");
            getJobHuntBaseInfo(jobHuntBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】求职意向 html获取失败");
        }

        logger.debug("\n------------------------------------------------工作经验------------------------------------------------");
        try {
            String workExpBaseInfoHtml = map.get("工作经验");
            getWorkExpBaseInfo(workExpBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】工作经验 html获取失败");
        }

        logger.debug("\n------------------------------------------------教育经历------------------------------------------------");
        try {
            String eduBaseInfoHtml = map.get("教育经历");
            getEduBaseInfo(eduBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】教育经历 html获取失败");
        }

        logger.debug("\n------------------------------------------------技能特长------------------------------------------------");
        try {
            String skillsBaseInfoHtml = map.get("技能特长");
            getSkillsBaseInfo(skillsBaseInfoHtml);
        } catch (Exception e) {
            logger.error("【parsePlugHtml】-【getModuleData】-【ResumeParserPlugHtmlJob51Search】技能特长 html获取失败");
        }

    }

    /**
     * 技能特长
     * @param skillsBaseInfoHtml
     */
    private void getSkillsBaseInfo(String skillsBaseInfoHtml) {
        //#divInfo > td > table:nth-child(8) > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td.skill
        //body > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr > td > table > tbody > tr > td.skill
        Element divInfo = parse2Html(skillsBaseInfoHtml);
        List<String> skillNames = divInfo.select("body > table > tbody > tr > td .skill").eachText();
        List<String> skillLevels = divInfo.select("body > table > tbody > tr > td .skbg").eachText();

        ResumeParseSkillsDTO skillDTO;
        for (int i = 0; i < skillNames.size(); i++) {
            skillDTO = new ResumeParseSkillsDTO();
            String skillName = skillNames.get(i);
            String skillLevel = skillLevels.get(i);
            //技能名称
            skillDTO.setSkillName(skillName);
            //掌握程度
            skillDTO.setLevel(skillLevel);
            skillList.add(skillDTO);
            logger.debug("\n技能名称:{}\n技能掌握程度:{}",skillName,skillLevel);
        }
    }


    /**
     * 6、教育经历
     *
     * @param eduBaseInfoHtml
     */
    private void getEduBaseInfo(String eduBaseInfoHtml) {
        ResumeParseEducationDTO eduDTO;
        Element divInfo = parse2Html(eduBaseInfoHtml);
        Elements divInfo_edu = divInfo.select("body > table > tbody > tr > td.p15");
        for (Element element : divInfo_edu) {
            eduDTO = new ResumeParseEducationDTO();
            Elements deu = element.select("td[valign] , td.phd");
            //在校时间：2012/9-2016/6
            String 在校时间 = deu.get(0).text().trim();
            String 学校 = deu.get(1).text().trim();
            String 学历 = deu.get(2).text().split("\\|")[0];
            String 专业 = deu.get(2).text().split("\\|")[1];

            String[] eduDateSplit = 在校时间.split("-");
            if (eduDateSplit != null && eduDateSplit.length == 2) {
                String eduStartDate = eduDateSplit[0];
                String eduEndDate = eduDateSplit[1];

                eduStartDate = DateUtil.stringToString(eduStartDate, DateUtil.DATETIME_PATTERN_YYYY_PATTERN_MM, DateUtil.DATETIME_PATTERN_YYYY_MM);
                if (!eduEndDate.contains("至今")) {
                    eduEndDate = DateUtil.stringToString(eduEndDate, DateUtil.DATETIME_PATTERN_YYYY_PATTERN_MM, DateUtil.DATETIME_PATTERN_YYYY_MM);
                }

                //开始时间
                eduDTO.setEducationFrom(eduStartDate);
                //结束时间
                eduDTO.setEducationTo(eduEndDate);
            }

            //学校名字
            eduDTO.setUniversity(学校);
            //专业
            eduDTO.setMajor(专业);
            //学位
            eduDTO.setDegree(学历);
            eduList.add(eduDTO);

            logger.debug("\n在校时间：{}\n学校:{}\n学历:{}\n专业:{}",在校时间,学校,学历,专业);
        }
    }

    /**
     * 5、工作经验
     *
     * @param workExpBaseInfoHtml
     */
    private void getWorkExpBaseInfo(String workExpBaseInfoHtml) {

        Element divInfo = parse2Html(workExpBaseInfoHtml);
        ResumeParseWorkExpDTO workExpDTO;
        //body > table > tbody
        Elements divInfo_worx_exps = divInfo.select("body > table > tbody > tr > td.p15");

        for (Element elements : divInfo_worx_exps) {
            workExpDTO = new ResumeParseWorkExpDTO();
            Elements select = elements.select(" td > table > tbody > tr > td[valign] , td.rtbox");
            String 在职时间 = select.get(0).text().trim();
            String 公司名字 = select.get(1).text().trim();
            String 部门 = select.get(4).text().trim();
            String 岗位 = select.get(5).text().trim();
            String 工作描述 = select.get(7).text().trim();

            String[] workDateSplit = 在职时间.split("-");
//            String currentDateStr = DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN_YYYY_MM);
            if (workDateSplit != null && workDateSplit.length == 2) {
                String workStartDate = workDateSplit[0];
                String workEndDate = workDateSplit[1];

                workStartDate = DateUtil.stringToString(workStartDate, DateUtil.DATETIME_PATTERN_YYYY_PATTERN_MM, DateUtil.DATETIME_PATTERN_YYYY_MM);
                if (!workEndDate.contains("至今")) {
                    workEndDate = DateUtil.stringToString(workEndDate, DateUtil.DATETIME_PATTERN_YYYY_PATTERN_MM, DateUtil.DATETIME_PATTERN_YYYY_MM);
                }
                //工作开始时间
                workExpDTO.setOnJobFrom(workStartDate);
                //工作结束时间
                workExpDTO.setOnJobTo(workEndDate);
            }

            //工作年限
            workExpDTO.setWorkLength(null);
            //公司名字
            workExpDTO.setCompanyName(公司名字);
            //岗位
            workExpDTO.setPositionName(岗位);
            //工作描述
            workExpDTO.setWorkContent(工作描述);
            //离职薪酬
            workExpDTO.setSalary(null);
            //证明人名字
            workExpDTO.setCertifierName(null);
            //证明人电话
            workExpDTO.setCertifierPhone(null);
            workExpList.add(workExpDTO);

            logger.debug("\n在职时间：{}\n公司名字:{}\n部门:{}\n岗位:{}\n工作描述:{}",在职时间,公司名字,部门,岗位,工作描述);
        }
    }


    /**
     * 3、个人信息
     *
     * @param seekerBaseInfoHtml
     */
    private void getSeekerBaseInfo(String seekerBaseInfoHtml) {

        Element divInfo = parse2Html(seekerBaseInfoHtml);

        //body > table > tbody > tr:nth-child(1) > td > table > tbody > tr
        List<String> keys = divInfo.select("body > table > tbody > tr > td.tb2 > table > tbody > tr > td.keys").eachText();
        List<String> values = divInfo.select("body > table > tbody > tr > td.tb2 > table > tbody > tr > td.txt2").eachText();

        Map<String, String> resMap = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i).replace("　", "").replace("：","").trim();
            int valLength = values.size();
            if (i <= valLength) {
                String val = values.get(i).trim();
                resMap.put(key, val);
            }
        }

        String 户口 = resMap.get("户口/国籍");
        String 政治面貌 = resMap.get("政治面貌");
        String 家庭地址 = resMap.get("家庭地址");
        String 身高 = resMap.get("身高");
        String 婚姻状况 = resMap.get("婚姻状况");
        String 职能职位 = resMap.get("职能/职位");

        //民族
        resumeBase.setNation(null);
        //户口所在地
        resumeBase.setDomicilePlace(户口);
        //身份证号
        resumeBase.setCertificateNum(null);
        //婚姻状况
        resumeBase.setIsMarry(isMarry(婚姻状况));
        //当前住址
        resumeBase.setHouseAddress(家庭地址);
        //健康状况
        resumeBase.setHealth(null);
        //政治面貌
        resumeBase.setPoliticalStatus(政治面貌);
        //兴趣爱好
        resumeBase.setHobbies(null);

        logger.debug("\n户口:{}\n身高:{}\n婚姻状况:{}\n政治面貌:{}\n家庭地址:{}\n职能职位:{}",户口,身高,婚姻状况,政治面貌,家庭地址,职能职位);
    }


    /**
     * 3、求职意向
     *
     * @param jobHuntBaseInfoHtml
     */
    private void getJobHuntBaseInfo(String jobHuntBaseInfoHtml) {

        Element divInfo = parse2Html(jobHuntBaseInfoHtml);

        List<String> keys = divInfo.select("body > table > tbody > tr > td.tb2 table > tbody > tr > td.keys").eachText();
        List<String> values = divInfo.select("body > table > tbody > tr > td.tb2 table > tbody > tr > td.txt2").eachText();

        Map<String, String> resMap = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i).replace("　", "").replace("：","").trim();
            int valLength = values.size();
            if (i <= valLength) {
                String val = values.get(i).trim();
                resMap.put(key, val);
            }
        }

        String 自我评价 = divInfo.select("body > table > tbody > tr > td.tb1 table > tbody > tr > td.txt1").text().trim();
        String 工作类型 = resMap.get("工作类型");
        String 到岗时间 = resMap.get("到岗时间");
        String 期望薪资 = resMap.get("期望薪资");
        String 期望地点 = resMap.get("地点");
        String 职能职位 = resMap.get("职能/职位");

        //期望薪资
        Integer expectSalaryMin = 0;
        Integer expectSalaryMax = 0;
        List<Integer> numbers = getSalaryArr(期望薪资);
        if (numbers != null && numbers.size() != 0) {
            if (numbers.size() == 2) {
                expectSalaryMin = numbers.get(0);
                expectSalaryMax = numbers.get(1);
            }
            if (numbers.size() == 1) {
                expectSalaryMax = numbers.get(0);
            }
        }
        resumeBase.setExpectSalaryMin(expectSalaryMin);
        resumeBase.setExpectSalaryMax(expectSalaryMax);

        //期望工作地点
        resumeBase.setExpectPlace(期望地点);
        //自我评价
        resumeBase.setSelfEvaluation(自我评价);

        logger.debug("\n工作类型：{}\n到岗时间：{}\n期望薪资：{}\n期望地点：{}\n职能职位：{}\n自我评价：{}",工作类型,到岗时间,期望薪资,期望地点,职能职位,自我评价);

    }


    /**
     * 2、学历信息
     *
     * @param introBaseInfoHtml
     */
    private void getDegreeBaseInfo(String introBaseInfoHtml) {

        Element table1 = parse2Html(introBaseInfoHtml);

        //body > table > tbody > tr > td:nth-child(1) > table > tbody > tr:nth-child(2) > td.keys
        String[] keys = table1.select("body > table > tbody > tr > td.tb2 > table > tbody > tr > td.keys").text().split("：");
        String[] values = table1.select("body > table > tbody > tr > td.tb2 > table > tbody > tr > td.txt1,td.txt2").text().split(" ");

        Map<String, String> resMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i].replace("　", "").trim();
            int valLength = values.length;
            if (i <= valLength) {
                String val = values[i].trim();
                resMap.put(key, val);
            }
        }

        String 公司 = resMap.get("公司");
        String 职位 = resMap.get("职位");
        String 行业 = resMap.get("行业");

        logger.debug("\n公司：{}\n职位：{}\n行业：{}",公司,职位,行业);

        String 专业 = resMap.get("专业");
        String 学校 = resMap.get("学校");
        String 学历 = resMap.get("学历/学位");
        logger.debug("\n专业：{}\n学校：{}\n学历：{}",专业,学校,学历);

    }


    /**
     * 1、基本信息
     *
     * @param nameBaseInfoHtml
     */
    private void getNameBaseInfo(String nameBaseInfoHtml) {
        Element table0 = parse2Html(nameBaseInfoHtml);

        if (table0 != null) {
            /** 姓名在职状况等信息 **/
            //#tdseekname
            /** 孙超 **/
            //先去除td下的span内容，然后安安静静的取出td的文本.<td>名字<span>xxxx</span><span>xxxx</span></td>
            table0.getElementById("tdseekname").select("span").remove();
            String 名字 = table0.getElementById("tdseekname").text().trim();
            logger.debug("\n名字：{}",名字);

            //body > table > tbody > tr:nth-child(2)
            /** 目前正在找工作  13581583885  ww333ee@163.com  **/
            String text1 = table0.select("body > table > tbody > tr:nth-child(2)").text();
            String 手机号 = getTelnum(text1);
            String 邮箱 = getEmailnum(text1);
            String 在职状况 = table0.select("body > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td:nth-child(2)").text().trim();

            logger.debug("在职状况：{}\n手机号：{}\n邮箱：{}",在职状况,手机号,邮箱);

            //body > table > tbody > tr:nth-child(3) > td
            /** 男 41岁（1978年7月1日） 现居住 北京 20年工作经验 **/
            Elements select = table0.select("body > table > tbody > tr:nth-child(3) > td");
            String text = select.text().trim();
            String[] split = text.split("\\|");
            String 性别 = split[0].trim();
            String 年龄 = split[1].trim();
            String 住址 = split[2].replace("现居住", "").trim();
            String 工作经验 = split[3].trim();

            logger.debug("\n性别：{}\n年龄：{}\n住址：{}\n工作经验：{}",性别,年龄,住址,工作经验);

            String[] ageArr = 年龄.split("岁");

            Integer age = null;
            String birthday = null;
            if(ageArr != null & ageArr.length == 2){
                age = Integer.valueOf(ageArr[0]);
                birthday = ageArr[1].replaceAll("（","").replaceAll("）","").trim();
                //yyyy年MM月dd日 转化为 yyyy-MM-dd 格式
                birthday = DateUtil.stringToString(birthday,DateUtil.EXPANDED_DATE_FORMAT_WORD_1,DateUtil.ISO_EXPANDED_DATE_FORMAT);
            }

            //姓名
            resumeBase.setJobSeekerName(名字);
            //年龄
            resumeBase.setAge(age);
            //性别
            resumeBase.setSex(parseSex(性别)+"");
            //手机号
            resumeBase.setPhone(null);
            //邮箱地址
            resumeBase.setEmail(null);
            //出生年月
            resumeBase.setBirthday(birthday);
            //目前在职状态
            resumeBase.setCurrentState(在职状况);
        }
    }


    /**
     * html转Document
     *
     * @param html
     * @return
     * @throws Exception
     */
    protected Document parse2Html(String html) {
        return Jsoup.parse(html);
    }



    /**
     * 51job中搜索的已付费和未付费简历
     * @param args
     */
    public static void main(String[] args) {
//        String filePath = "/Users/admin/Desktop/简历解析/51job-插件-2.html";
//        String filePath = "/Users/admin/Desktop/简历解析/51job-插件-陈安安.html";
//        String filePath = "/Users/admin/Desktop/简历解析/51job-插件-杨义刚.html";

//        String filePath = "/Users/admin/Desktop/简历解析/51job/51job-插件-收件箱-孙超.html";
//        String filePath = "/Users/admin/Desktop/简历解析/51job/51job-插件-搜索未付费-彭毅.html";
//        String filePath = "/Users/admin/Desktop/简历解析/51job/51job-插件-搜索已付费-彭毅.html";
//        String filePath = "/Users/admin/Desktop/简历解析/51job/123.html";

//        String filePath = "/Users/admin/Desktop/简历解析/51job/51job-插件-猜你喜欢模块.html";
//        String filePath = "/Users/admin/Desktop/简历解析/51job/51job-插件-期望薪资期望地点有误.html";
        String filePath = "/Users/admin/Desktop/简历解析/51job/51job-插件-薪资万元年.html";


        File file = new File(filePath);
        String html = null;
        try {
            html = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResumeParserPlugHtmlJob51Search resumeParser = new ResumeParserPlugHtmlJob51Search();
        Resume resume = resumeParser.parse(html);
        logger.debug("resume:{}",JSONUtils.objectToJson(resume));


//        /**基本信息**/
//        ResumeParserHtmlStrMoudelsJob51 resumeParser = new ResumeParserHtmlStrMoudelsJob51();
//        String nameBaseInfo = "/Users/admin/Desktop/简历解析/51job-插件-2/基本信息.html";
//        File file = new File(nameBaseInfo);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        resumeParser.getNameBaseInfo(html);


//        /**学历信息**/
//        ResumeParserHtmlStrMoudelsJob51 resumeParser = new ResumeParserHtmlStrMoudelsJob51();
//        String nameBaseInfo = "/Users/admin/Desktop/简历解析/51job-插件-2/学历信息.html";
//        File file = new File(nameBaseInfo);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        resumeParser.getDegreeBaseInfo(html);

//        /**求职意向**/
//        ResumeParserHtmlStrMoudelsJob51 resumeParser = new ResumeParserHtmlStrMoudelsJob51();
//        String nameBaseInfo = "/Users/admin/Desktop/简历解析/51job-插件-2/求职意向.html";
//        File file = new File(nameBaseInfo);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        resumeParser.getJobHuntBaseInfo(html);

//        /**个人信息**/
//        ResumeParserHtmlStrMoudelsJob51 resumeParser = new ResumeParserHtmlStrMoudelsJob51();
//        String nameBaseInfo = "/Users/admin/Desktop/简历解析/51job-插件-2/个人信息.html";
//        File file = new File(nameBaseInfo);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        resumeParser.getSeekerBaseInfo(html);


//        /**工作经验**/
//        ResumeParserHtmlStrMoudelsJob51 resumeParser = new ResumeParserHtmlStrMoudelsJob51();
//        String nameBaseInfo = "/Users/admin/Desktop/简历解析/51job-插件-2/工作经验.html";
//        File file = new File(nameBaseInfo);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        resumeParser.getWorkExpBaseInfo(html);


//        /**教育经历**/
//        ResumeParserHtmlStrMoudelsJob51 resumeParser = new ResumeParserHtmlStrMoudelsJob51();
//        String nameBaseInfo = "/Users/admin/Desktop/简历解析/51job-插件-2/教育经历.html";
//        File file = new File(nameBaseInfo);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        resumeParser.getEduBaseInfo(html);

//        /**教育经历**/
//        ResumeParserPlugHtmlJob51Search resumeParser = new ResumeParserPlugHtmlJob51Search();
//        String nameBaseInfo = "/Users/admin/Desktop/简历解析/51job/51job-插件-技能.html";
//        File file = new File(nameBaseInfo);
//        String html = null;
//        try {
//            html = FileUtils.readFileToString(file, "UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        resumeParser.getSkillsBaseInfo(html);


    }


}
