package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Resume;
import com.mdl.zhaopin.handler.platform.resume.ResumeZhilian;
import com.mdl.zhaopin.utils.JsonTools;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : 针对通过插件在智联上获取的 搜索 的简历的html文件解析
 * @Author : xiekun
 * @Create Date : 2020年04月15日 15:36
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class ResumeParserPlugHtmlZhilian extends AbstractResumeParser implements ResumeStrParser {

    @Override
    public String getName() {
        return "智联招聘";
    }

    @Override
    public Resume parse(String htmlStr) {

        ResumeZhilian resume = new ResumeZhilian();

        //1、获取所有模块的html
        Map<String, String> resumeModuleMap = getResumeMoudelMap(htmlStr);
        System.out.println("moduleMap:" + JsonTools.obj2String(resumeModuleMap));

//        //2、各个模块进行解析获取数据
//        parseResumeMap(resumeMap);

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
        String 基本信息_element = doc.getElementsByClass("resume-content__candidate-basic").html();
        resumeMap.put("基本信息", 基本信息_element);

        //简历信息 源头 "resumeDetail"
        //------------------------------------------------求职意向------------------------------------------------
        String 求职意向_element = doc.getElementById("resumeDetail").select("[data-bind = visible: isShowIntent]").html();
        resumeMap.put("求职意向", 求职意向_element);

        //------------------------------------------------教育经历------------------------------------------------
        String 教育经历_element = doc.getElementById("resumeDetail").select("[data-bind = if: educationExperience().length]").html();
        resumeMap.put("教育经历", 教育经历_element);

        //------------------------------------------------工作经历------------------------------------------------
        String 工作经历_element = doc.getElementById("resumeDetail").select("[data-bind = if: workExperience().length]").html();
        resumeMap.put("工作经历", 工作经历_element);

        //------------------------------------------------项目经验------------------------------------------------
        String 项目经验_element = doc.getElementById("resumeDetail").select("[data-bind = if: projectExperience().length]").html();
        resumeMap.put("项目经验", 项目经验_element);

        //------------------------------------------------专业技能------------------------------------------------
        String 专业技能_element = doc.getElementById("resumeDetail").select("[data-bind = if: professionalSkill().length]").html();
        resumeMap.put("专业技能", 专业技能_element);

        //------------------------------------------------自我评价------------------------------------------------
        String 自我评价_element = doc.getElementById("resumeDetail").select("[data-bind = if: evaluate.content]").html();
        resumeMap.put("自我评价", 自我评价_element);

        //------------------------------------------------培训经历------------------------------------------------
        String 培训经历_element = doc.getElementById("resumeDetail").select("[data-bind = if: trainingExperience().length]").html();
        resumeMap.put("培训经历", 培训经历_element);

        //------------------------------------------------所获证书------------------------------------------------
        String 所获证书_element = doc.getElementById("resumeDetail").select("[data-bind = if: achieveCertificate().length]").html();
        resumeMap.put("所获证书", 所获证书_element);

        //------------------------------------------------在校情况------------------------------------------------
        String 在校情况_element = doc.getElementById("resumeDetail").select("[data-bind = if: achieveScholarship().length || achieveAward().length || studyInfomation()]").html();
        resumeMap.put("在校情况", 在校情况_element);

        //------------------------------------------------语言能力------------------------------------------------
        String 语言能力_element = doc.getElementById("resumeDetail").select("[data-bind = if: languageSkill().length]").html();
        resumeMap.put("语言能力", 语言能力_element);

        return resumeMap;
    }


    /**
     * 解析简历map
     * @param next
     */
//    private void parseResumeMap(Element next) {
//
//        System.out.println("\n------------------------------------------------基本信息------------------------------------------------");
//        getSeekerBaseInfo(resume, doc, elementsByClass);
//
//
//        System.out.println("\n------------------------------------------------求职意向------------------------------------------------");
//        getJobHuntBaseInfo(resumeDetail);
//
//        System.out.println("\n------------------------------------------------教育经历------------------------------------------------");
//        if(next.attr("data-bind").equals("if: educationExperience().length")){
//            getEduBaseInfo(next);
//        }
//
//        System.out.println("\n------------------------------------------------工作经历------------------------------------------------");
//        if(next.attr("data-bind").equals("if: workExperience().length")){
//            getWorkExpBaseInfo(next);
//        }
//
//        System.out.println("\n------------------------------------------------项目经验------------------------------------------------");
//        if(next.attr("data-bind").equals("if: projectExperience().length")) {
//            getProjectExpBaseInfo(next);
//
//        }
//
//        System.out.println("\n------------------------------------------------专业技能------------------------------------------------");
//        if(next.attr("data-bind").equals("if: professionalSkill().length")) {
//            getSkillBaseInfo(next);
//        }
//
//        System.out.println("\n------------------------------------------------自我评价------------------------------------------------");
//        if(next.attr("data-bind").equals("if: evaluate.content")) {
//            getSelfEvalBaseInfo(next);
//        }
//
//        System.out.println("\n------------------------------------------------培训经历------------------------------------------------");
//        if(next.attr("data-bind").equals("if: trainingExperience().length")) {
//            getTrainBaseInfo(next);
//        }
//
//        System.out.println("\n------------------------------------------------所获证书------------------------------------------------");
//        if(next.attr("data-bind").equals("if: achieveCertificate().length")) {
//            getCertificateBaseInfo(next);
//
//        }
//
//        System.out.println("\n------------------------------------------------在校情况------------------------------------------------");
//        if(next.attr("data-bind").equals("if: achieveScholarship().length || achieveAward().length || studyInfomation()")) {
//            getUniversityBaseInfo(next);
//        }
//
//        System.out.println("\n------------------------------------------------语言能力------------------------------------------------");
//        if(next.attr("data-bind").equals("if: languageSkill().length")) {
//            getLanguageBaseInfo(next);
//        }
//    }


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
//            System.out.println("\n语言能力:" + 语言能力);
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
//            System.out.println("\n证书名字及时间：" + 证书名字及时间);
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
            System.out.println("\n培训时间：" + 培训时间 + "\n培训机构：" + 培训机构);
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
        System.out.println("\n自我评价:" + 自我评价);
    }


//    /**
//     * 专业技能
//     *
//     * @param skillBaseInfoHtml
//     */
//    private void getSkillBaseInfo(String skillBaseInfoHtml) {
//        Document document = parse2Html(skillBaseInfoHtml);
//        Elements select = document.select("ul > li");
//        for (Element element : select) {
//            String 技能名称 = element.select("p.is-weight-bold").text();
//            String 技能掌握程度 = element.select("p.resume-content__labels--sub > span:nth-child(1)").text();
//            String 技能掌握时长 = element.select("p.resume-content__labels--sub > span:nth-child(2)").text();
//            System.out.println("\n技能名称:" + 技能名称 + "\n技能掌握程度:" + 技能掌握程度 + "\n技能掌握时长:" + 技能掌握时长);
//        }
//    }


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
            System.out.println("\n起止时间:" + 起止时间 + "\n项目名字:" + 项目名字 + "\n项目时长:" + 项目时长 + "\n项目描述:" + 项目描述);
        }
    }


    /**
     * 工作经历
     *
     * @param workExpBaseInfoHtml
     */
    private void getWorkExpBaseInfo(String workExpBaseInfoHtml) {
        Document document = parse2Html(workExpBaseInfoHtml);
        Elements select = document.select("body > ul > li");
        for (Element element : select) {
            String 开始时间 = element.select("[data-bind = text: data.workDate.beginDate]").text();
            String 结束时间 = element.select("[data-bind = text: data.workDate.endDate]").text();
            String 公司名字 = element.select("[data-bind = textHighlight: {originalValue: data.companyName, highlightMark: data.companyNameHighlightWords}]").text();
            String 就职时长 = element.select("[data-bind = text: data.workDate.range]").text();
            String 岗位 = element.select("[data-bind = textHighlight: {originalValue: data.jobTitle, highlightMark: data.jobTitleHighlightWords}]").text();
            String 薪资 = element.select("[data-bind = text: data.salaray]").text();
            String 工作描述 = element.select("[data-bind = textHighlight: {originalValue: data.workDescription, highlightMark: data.workDescriptionHighlightWords}]").text();

            System.out.println("\n开始时间:" + 开始时间 + "\n结束时间:" + 结束时间 + "\n公司名字:" + 公司名字
                    + "\n就职时长:" + 就职时长 + "\n岗位:" + 岗位 + "\n薪资:" + 薪资 + "\n工作描述:" + 工作描述);
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
        for (Element element : select) {
            String 上学开始时间 = element.select("[data-bind = text: data.eduDate.beginDate]").text();
            String 上学结束时间 = element.select("[data-bind = text: data.eduDate.endDate]").text();
            String 学校 = element.select("[data-bind = textHighlight: {originalValue: data.schoolName, highlightMark: data.schoolNameHighlightWords}]").text();
            String 专业 = element.select("[data-bind = textHighlight: {originalValue: data.majorName, highlightMark: data.majorNameHighlightWords}]").text();
            System.out.println("\n上学开始时间：" + 上学开始时间 + "\n上学结束时间：" + 上学结束时间 + "\n学校：" + 学校 + "\n专业：" + 专业);
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

        System.out.println("\n期望工作地点：" + 期望工作地点 + "\n期望月薪：" + 期望月薪 + "\n目前状况："
                + 目前状况 + "\n期望工作性质：" + 期望工作性质 + "\n期望从事职业：" + 期望从事职业 + "\n期望从事行业：" + 期望从事行业);
    }


    /**
     * 基本信息
     *
     * @param seekerBaseInfoHtml
     */
    private void getSeekerBaseInfo(String seekerBaseInfoHtml) {
        Element seekerBaseInfo = parse2Html(seekerBaseInfoHtml);
        /** 名字 **/
        String 名字 = seekerBaseInfo.select("[data-bind = textQ: candidateName]").text();
        /** 性别 **/
        String 性别 = seekerBaseInfo.select("[data-bind = $key: genderDesc]").text();
        /** 年龄,出生年月日 **/
        String 年龄 = seekerBaseInfo.select("[data-bind = text: age]").text();
        /** 工作年限 **/
        String 工作年限 = seekerBaseInfo.select("[data-bind = text: workYears]").text();
        /** 学历 **/
        String 学历 = seekerBaseInfo.select("[data-bind = text: eduLevel()]").text();
        /** 居住城市 **/
        String 居住城市 = seekerBaseInfo.select("[data-bind =textQ: currentCity()]").text();

        System.out.println("\n名字：" + 名字 + "\n性别：" + 性别 + "\n年龄：" + 年龄 + "\n工作年限：" + 工作年限 + "\n学历：" + 学历 + "\n居住城市：" + 居住城市);

    }

//    /**
//     * 手机：18600904162 E-mail：18600904162@163.com
//     **/
//    protected void parseContactMethod(ResumeZhilian resume, String contact) {
//        resume.setPhone(intercept(contact, "手机：", "E-mail："));
//        resume.setMail(intercept(contact, "E-mail：", null));
//    }


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


    public static void main(String[] args) throws Exception {
////        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-html.html";
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-任女士-插件.html";
//        File file = new File(filePath);
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
//        ResumeZhilian resume = (ResumeZhilian) resumeParser.parse(html);


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


//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/工作经历.html";
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


        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/培训经历.html";
        File file = new File(filePath);
        String html = FileUtils.readFileToString(file, "UTF-8");
        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
        resumeParser.getTrainBaseInfo(html);


    }


}
