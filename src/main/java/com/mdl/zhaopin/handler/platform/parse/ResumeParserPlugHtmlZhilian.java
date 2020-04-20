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


    /**
     * 在校情况
     *
     * @param next
     */
    private void getUniversityBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(10) > div.resume-content__section-body.resume-content--timeline.is-mt-10.is-mb-20 > dl > dd
        Elements select = next.select("div.resume-content__section-body.resume-content--timeline.is-mt-10.is-mb-20");
        for (Element element : select) {
            String 在校情况 = element.text();
            System.out.println("\n在校情况:" + 在校情况);
        }
    }


    /**
     * 语言能力
     *
     * @param next
     */
    private void getLanguageBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(12) > ul > li
        Elements select = next.select("ul > li");
        for (Element element : select) {
            String 语言能力 = element.text();
            System.out.println("\n语言能力:" + 语言能力);
        }
    }


    /**
     * 证书名字及时间
     *
     * @param next
     */
    private void getCertificateBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(9) > ul
        Elements ul = next.select("ul > li");
        for (Element element : ul) {
            //#resumeDetail > div:nth-child(9) > ul > li:nth-child(1)
            String 证书名字及时间 = element.text();
            System.out.println("\n证书名字及时间：" + 证书名字及时间);
        }
    }


    /**
     * 培训经历
     *
     * @param next
     */
    private void getTrainBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(7) > ul
        Elements ul = next.select("ul > li");
        for (Element element : ul) {
            //#resumeDetail > div:nth-child(7) > ul > li > p > span:nth-child(1)
            String 培训时间 = element.select("p > span").text();
            //#resumeDetail > div:nth-child(7) > ul > li > dl > dd
            String 培训机构 = element.select("dl > dd").text();
            System.out.println("\n培训时间：" + 培训时间 + "\n培训机构：" + 培训机构);
        }
    }


    /**
     * 自我评价
     *
     * @param next
     */
    private void getSelfEvalBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(7) > div
        String 自我评价 = next.select("div").get(1).text();
        System.out.println("\n自我评价:" + 自我评价);
    }

    /**
     * 专业技能
     *
     * @param next
     */
    private void getSkillBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(5) > ul
        Elements select = next.select("ul > li");
        for (Element element : select) {
            //#resumeDetail > div:nth-child(5) > ul > li:nth-child(1) > p.is-weight-bold
            String 技能名称 = element.select("p.is-weight-bold").text();
            //#resumeDetail > div:nth-child(5) > ul > li:nth-child(1) > p.resume-content__labels--sub > span:nth-child(1)
            String 技能掌握程度 = element.select("p.resume-content__labels--sub > span:nth-child(1)").text();
            //#resumeDetail > div:nth-child(5) > ul > li:nth-child(1) > p.resume-content__labels--sub > span:nth-child(2)
            String 技能掌握时长 = element.select("p.resume-content__labels--sub > span:nth-child(2)").text();

            System.out.println("\n技能名称:" + 技能名称 + "\n技能掌握程度:" + 技能掌握程度 + "\n技能掌握时长:" + 技能掌握时长);
        }
    }


    /**
     * 项目经验
     *
     * @param next
     */
    private void getProjectExpBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(4) > ul
        Elements ul = next.select("ul > li");
        for (Element element : ul) {
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p > span:nth-child(2)
            String 项目名字 = element.select("p > span:nth-child(2)").text();
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p > span.timeline__time-range
            String 项目时长 = element.select("p > span.timeline__time-range").text();
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > dl > dd
            String 项目描述 = element.select("dl > dd").text();

            System.out.println("\n项目名字:" + 项目名字 + "\n项目时长:" + 项目时长 + "\n项目描述:" + 项目描述);
        }
    }


    /**
     * 工作经历
     *
     * @param next
     */
    private void getWorkExpBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p.timeline__header
        Elements select = next.select("ul > li");
        for (Element element : select) {
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p.timeline__header > span.timeline__time--wrapper
            String 起止时间 = element.select("p > span.timeline__time--wrapper").text();
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p.timeline__header > span:nth-child(2)
            String 公司 = element.select("p > span:nth-child(2)").text();
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(2) > p.timeline__header > span.timeline__time-range
            String 就职时长 = element.select("p > span:nth-child(3)").text();
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(2) > p.resume-content__skill-tags
            String 技能标签 = element.select("p.resume-content__skill-tags").text();
            //#resumeDetail > div:nth-child(4) > ul > li:nth-child(2) > dl > dd
            String 工作描述 = element.select("dl > dd").text();

            System.out.println("\n起止时间:" + 起止时间 + "\n公司:" + 公司 + "\n就职时长:" + 就职时长 + "\n技能标签:" + 技能标签 + "\n工作描述:" + 工作描述);
        }
    }


    /**
     * 教育经历
     *
     * @param next
     */
    private void getEduBaseInfo(Element next) {
        //#resumeDetail > div:nth-child(3) > ul > li > p > span.timeline__time--wrapper
        Elements select = next.select("ul > li");
        for (Element element : select) {
            String 上学时间 = element.select("p > span.timeline__time--wrapper").text();
            //#resumeDetail > div:nth-child(3) > ul > li > p > span:nth-child(2)
            String 学校 = element.select("p > span:nth-child(2)").text();
            //#resumeDetail > div:nth-child(3) > ul > li > p > span:nth-child(3)
            String 专业 = element.select("p > span:nth-child(3)").text();

            System.out.println("\n上学时间:" + 上学时间 + "\n学校：" + 学校 + "\n专业" + 专业);
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

        System.out.println(keyList.size() + "---" + valList.size());

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

        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-任女士/求职意向.html";
        File file = new File(filePath);
        String html = FileUtils.readFileToString(file, "UTF-8");
        ResumeParserPlugHtmlZhilian resumeParser = new ResumeParserPlugHtmlZhilian();
        resumeParser.getJobHuntBaseInfo(html);

    }


}
