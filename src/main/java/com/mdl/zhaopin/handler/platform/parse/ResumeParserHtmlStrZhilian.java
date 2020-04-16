package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Resume;
import com.mdl.zhaopin.handler.platform.resume.ResumeZhilian;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.List;
import java.util.ListIterator;
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
public class ResumeParserHtmlStrZhilian extends AbstractResumeParser implements ResumeStrParser{

    @Override
    public String getName() {
        return "智联招聘";
    }

    @Override
    public boolean canParse(String file) {
        if (file == null) {
            return false;
        } else {
//            String name = file.getName();
//            return name.startsWith("智联招聘") && name.endsWith(".doc");
            return true;
        }
    }

    @Override
    public Resume parse(String htmlStr) {

        ResumeZhilian resume = new ResumeZhilian();
        Document doc = parse2Html(htmlStr);

        //简历主要核心部件
        Element elementsByClass = doc.getElementsByClass("resume-content__section").get(0);

        System.out.println("\n------------------------------------------------基本信息------------------------------------------------");

        //名字
        String 名字 = elementsByClass.getElementsByClass("resume-content__candidate-header").select("span").text().trim();
        resume.setName(名字);

        //性别，工作年限等基本信息
        Elements spans1 = elementsByClass.getElementsByClass("resume-content__labels").select("span");
        List<String> collect = spans1.stream().map(Element::text).collect(Collectors.toList());

        /** 性别 **/
        String 性别 = collect.get(0);
        /** 年龄,出生年月日 **/
        String 年龄 = collect.get(1);
        /** 工作年限 **/
        String 工作年限 = collect.get(2);
        /** 学历 **/
        String 学历 = collect.get(3);
        resume.setSex(性别);
        resume.setAge(年龄);
        resume.setWorkDuration(工作年限);
        resume.setDegree(学历);

        System.out.println("\n名字：" + 名字  + "\n性别:" + 性别 + "\n年龄：" + 年龄 + "\n工作年限：" + 工作年限 + "\n学历" + 学历);

        //居住城市
        String liveCity = elementsByClass.getElementsByClass("resume-content__labels--sub").select("span").select("span").get(2).text();
        resume.setCity(liveCity);

        //简历信息 源头 "resumeDetail"
        Element resumeDetail = doc.getElementById("resumeDetail");

        //简历亮点 #resumeDetail > div:nth-child(1)
        String 简历亮点 = resumeDetail.getElementsByClass("resume-recommend__section-body").text();
        System.out.println("\n简历亮点" + 简历亮点);

        //求职意向
        Elements dl = resumeDetail.select("div.resume-content__section.is-career-objective > div > dl > dd");
        String 期望工作地点 = dl.get(0).text().trim();
        String 期望月薪 = dl.get(1).text().trim();
        String 目前状况 = dl.get(2).text().trim();
        String 期望工作性质 = dl.get(3).text().trim();
        String 期望从事职业 = dl.get(4).text().trim();
        String 期望从事行业 = dl.get(5).text().trim();

        System.out.println("\n期望工作地点：" + 期望工作地点  + "\n期望月薪:" + 期望月薪 + "\n目前状况："
                + 目前状况 + "\n期望工作性质:" + 期望工作性质 + "\n期望从事职业:" + 期望从事职业 + "\n期望从事行业:" + 期望从事行业);

        Elements elementsByClass1 = doc.getElementsByClass("resumeDetail > div:nth-child(5)");
        String text5 = resumeDetail.select("div:nth-child(5)").text();

        ListIterator<Element> div = resumeDetail.select("div").listIterator();

        while(div.hasNext()){
            Element next = div.next();
            String text = next.text();
//            System.out.println("\ntext--->" + text);

            /** 教育经历 **/
            if(next.attr("data-bind").equals("if: educationExperience().length")){
                System.out.println("\n------------------------------------------------教育经历------------------------------------------------");
                //#resumeDetail > div:nth-child(3) > ul > li > p > span.timeline__time--wrapper
                Elements select = next.select("ul > li");
                for(Element element : select){
                    String 上学时间 = element.select("p > span.timeline__time--wrapper").text();
                    //#resumeDetail > div:nth-child(3) > ul > li > p > span:nth-child(2)
                    String 学校 = element.select("p > span:nth-child(2)").text();
                    //#resumeDetail > div:nth-child(3) > ul > li > p > span:nth-child(3)
                    String 专业 = element.select("p > span:nth-child(3)").text();

                    System.out.println("\n上学时间:" + 上学时间 + "\n学校：" + 学校 + "\n专业" + 专业);
                }
            }

            /** 工作经历 **/
            if(next.attr("data-bind").equals("if: workExperience().length")){
                System.out.println("\n------------------------------------------------工作经历------------------------------------------------");
                //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p.timeline__header
                Elements select = next.select("ul > li");
                for(Element element : select){
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

            /** 项目经验 **/
            if(next.attr("data-bind").equals("if: projectExperience().length")) {
                System.out.println("\n------------------------------------------------项目经验------------------------------------------------");

                //#resumeDetail > div:nth-child(4) > ul
                Elements ul = next.select("ul > li");
                for(Element element : ul){
                    //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p > span:nth-child(2)
                    String 项目名字 = element.select("p > span:nth-child(2)").text();
                    //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > p > span.timeline__time-range
                    String 项目时长 = element.select("p > span.timeline__time-range").text();
                    //#resumeDetail > div:nth-child(4) > ul > li:nth-child(1) > dl > dd
                    String 项目描述 = element.select("dl > dd").text();

                    System.out.println("\n项目名字:" + 项目名字 + "\n项目时长:" + 项目时长 + "\n项目描述:" + 项目描述);
                }

            }

            /** 专业技能 **/
            if(next.attr("data-bind").equals("if: professionalSkill().length")) {
                System.out.println("\n------------------------------------------------专业技能------------------------------------------------");

                //#resumeDetail > div:nth-child(5) > ul
                Elements select = next.select("ul > li");
                for(Element element : select){
                    //#resumeDetail > div:nth-child(5) > ul > li:nth-child(1) > p.is-weight-bold
                    String 技能名称 = element.select("p.is-weight-bold").text();
                    //#resumeDetail > div:nth-child(5) > ul > li:nth-child(1) > p.resume-content__labels--sub > span:nth-child(1)
                    String 技能掌握程度 = element.select("p.resume-content__labels--sub > span:nth-child(1)").text();
                    //#resumeDetail > div:nth-child(5) > ul > li:nth-child(1) > p.resume-content__labels--sub > span:nth-child(2)
                    String 技能掌握时长 = element.select("p.resume-content__labels--sub > span:nth-child(2)").text();

                    System.out.println("\n技能名称:" + 技能名称 + "\n技能掌握程度:" + 技能掌握程度 + "\n技能掌握时长:" + 技能掌握时长);
                }

            }

            /** 自我评价 **/
            if(next.attr("data-bind").equals("if: evaluate.content")) {
                System.out.println("\n------------------------------------------------自我评价------------------------------------------------");
                //#resumeDetail > div:nth-child(7) > div
                String 自我评价 = next.select("div").get(1).text();
                System.out.println("\n自我评价:" + 自我评价);
            }

            /** 培训经历 **/
            if(next.attr("data-bind").equals("if: trainingExperience().length")) {
                System.out.println("\n------------------------------------------------培训经历------------------------------------------------");
            }

            /** 所获证书 **/
            if(next.attr("data-bind").equals("if: achieveCertificate().length")) {
                System.out.println("\n------------------------------------------------所获证书------------------------------------------------");

                //#resumeDetail > div:nth-child(9) > ul
                Elements ul = next.select("ul > li");
                for(Element element : ul){
                    //#resumeDetail > div:nth-child(9) > ul > li:nth-child(1)
                    String 证书名字及时间 = element.text();
                    System.out.println("\n证书名字及时间：" + 证书名字及时间);
                }

            }

            /** 在校情况 **/
            if(next.attr("data-bind").equals("if: achieveScholarship().length || achieveAward().length || studyInfomation()")) {
                System.out.println("\n------------------------------------------------在校情况------------------------------------------------");

                //#resumeDetail > div:nth-child(10) > div.resume-content__section-body.resume-content--timeline.is-mt-10.is-mb-20 > dl > dd
                Elements select = next.select("div.resume-content__section-body.resume-content--timeline.is-mt-10.is-mb-20");
                for(Element element : select){
                    String 在校情况 = element.text();
                    System.out.println("\n在校情况:" + 在校情况);
                }
            }

            /** 在校实践经验 **/
            if(next.attr("data-bind").equals("if: socialEvents().length")) {
                System.out.println("\n-----------------------------------------------在校实践经验----------------------------------------------");
            }


            /** 语言能力 **/
            if(next.attr("data-bind").equals("if: languageSkill().length")) {
                System.out.println("\n------------------------------------------------语言能力------------------------------------------------");

                //#resumeDetail > div:nth-child(12) > ul > li
                Elements select = next.select("ul > li");
                for(Element element : select){
                    String 语言能力 = element.text();
                    System.out.println("\n语言能力:" + 语言能力);
                }

            }


        }


        return resume;
    }

    /**
     * 手机：18600904162 E-mail：18600904162@163.com
     **/
    protected void parseContactMethod(ResumeZhilian resume, String contact) {
        resume.setPhone(intercept(contact, "手机：", "E-mail："));
        resume.setMail(intercept(contact, "E-mail：", null));
    }


    /**
     * html转Document
     * @param html
     * @return
     * @throws Exception
     */
    protected Document parse2Html(String html) {
        return Jsoup.parse(html);
    }


    public static void main(String[] args) throws Exception {
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-插件-html.html";
        String filePath = "/Users/admin/Desktop/简历解析/智联招聘-李先生-插件.html";
        File file = new File(filePath);
        String html = FileUtils.readFileToString(file, "UTF-8");
        ResumeParserHtmlStrZhilian resumeParser = new ResumeParserHtmlStrZhilian();
        ResumeZhilian resume = (ResumeZhilian) resumeParser.parse(html);
//        System.out.println(JsonTools.obj2String(resume));
    }


}
