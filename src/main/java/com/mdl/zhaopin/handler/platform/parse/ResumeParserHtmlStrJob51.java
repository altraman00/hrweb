package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Resume;
import com.mdl.zhaopin.handler.platform.resume.ResumeJob51;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : 针对通过插件在 51job 上获取的 搜索 的简历的html文件解析
 * @Author : xiekun
 * @Create Date : 2020年04月15日 15:36
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class ResumeParserHtmlStrJob51 extends AbstractResumeParser implements ResumeStrParser {

    @Override
    public String getName() {
        return "51Job";
    }

    @Override
    public boolean canParse(String file) {
        if (file == null) {
            return false;
        } else {
//            String name = file.getName();
//            return name.startsWith("51Job") && name.endsWith(".插件");
            return true;
        }
    }

    @Override
    public Resume parse(String htmlStr) {

        ResumeJob51 resume = new ResumeJob51();
        Document doc = parse2Html(htmlStr);

        System.out.println("\n------------------------------------------------基本信息------------------------------------------------");


        //body > table > tbody > tr > td > table.bottom_border
        /** 最外层的所有tables **/
        Elements tables = doc.select("body > table > tbody > tr > td > table");

        //body > table > tbody > tr > td > table.bottom_border > tbody
        /** 第1层，基本信息姓名性别相关的table **/
        Element table0 = tables.get(0);

        if (table0 != null) {
            //body > table > tbody > tr > td > table.bottom_border > tbody > tr > tds
            Elements table0_tbody0_tr0_tds = table0.select("tbody > tr > td");

            /** 姓名在职状况等信息 **/
            //body > table > tbody > tr > td > table.bottom_border > tbody > tr > td:nth-child(2)
            //基本信息的最外层
            Element table0_tbody0_tr0_td1 = table0_tbody0_tr0_tds.get(1);
            Elements table0_tbody0_tr0_td1_trs = table0_tbody0_tr0_td1.select("table > tbody > tr");

            if (table0_tbody0_tr0_td1_trs.size() > 0) {
                /** 孙超 **/
                //body > table > tbody > tr > td > table.bottom_border > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1)
                Element table0_tbody0_tr0_td1_tr0 = table0_tbody0_tr0_td1_trs.get(0);
                String tdseekname = table0_tbody0_tr0_td1_tr0.text().trim().split("和Ta聊聊")[0].trim();
                System.out.println("\n名字：" + tdseekname);

                //body > table > tbody > tr > td > table.bottom_border > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2)
                /** 目前正在找工作  13581583885  ww333ee@163.com  **/
                Element table0_tbody0_tr0_td1_tr1 = table0_tbody0_tr0_td1_trs.get(1);
                //body > table > tbody > tr > td > table.bottom_border > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td:nth-child(2)
                Elements table0_tbody0_tr1_td = table0_tbody0_tr0_td1_tr1.select("td > table > tbody > tr > td");
                String 在职状况 = table0_tbody0_tr1_td.get(1).text();
                String 手机号 = table0_tbody0_tr1_td.get(3).text();
                String 邮箱 = table0_tbody0_tr1_td.get(5).text();
                System.out.println("在职状况：" + 在职状况 + "\n手机号：" + 手机号 + "\n邮箱：" + 邮箱);

                //body > table > tbody > tr > td > table.bottom_border > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(3)
                //body > table > tbody > tr > td > table.bottom_border > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(3)
                /** 男 41岁（1978年7月1日） 现居住 北京 20年工作经验 **/
                Element table0_tbody0_tr0_td1_tr2 = table0_tbody0_tr0_td1_trs.get(3);
                Elements table0_tbody0_tr0_td1_tr2_tds = table0_tbody0_tr0_td1_tr2.select("td");
                String text = table0_tbody0_tr0_td1_tr2_tds.text();
                String[] split = text.split("\\|");
                String 性别 = split[0].trim();
                String 年龄 = split[1].trim();
                String 住址 = split[2].replace("现居住","").trim();
                String 工作经验 = split[3].trim();
                System.out.println("\n性别：" +性别 + "\n年龄：" + 年龄 + "\n住址：" + 住址 + "\n工作经验：" + 工作经验);
            }
        }

        /** 第2层，基本信息学历，学校等相关的table **/
        Element table1 = tables.get(1);
        if(table1 != null){
            /** table1最外层 **/
            //body > table > tbody > tr > td > table.box2 > tbody > tr > td > table > tbody > tr > td:nth-child(1)
            Elements table1_tds = table1.select("tbody > tr > td > table > tbody > tr > td");

//            Elements table1_tds = table1.select("tbody > tr").get(0)
//                    .select("td").get(0)
//                    .select("table > tbody > tr").get(0)
//                    .select("td");

            Element table1_td0_trs = table1_tds.get(0);
            if(table1_td0_trs != null){
                Elements table1_td0_trs_tds = table1_td0_trs.select("table > tbody > tr");
                String 工作年限 = table1_td0_trs_tds.get(0).select("td > span").text().trim();
                String 职位 = table1_td0_trs_tds.get(1).select("td").get(1).text().trim();
                String 行业 = table1_td0_trs_tds.get(2).select("td").get(1).text().trim();
                System.out.println("\n最近工作年限：" + 工作年限 + "\n职位:" + 职位 + "\n行业:" + 行业);
            }

            Element table1_td1_trs = table1_tds.get(1);
            if(table1_td1_trs != null){
                Elements table1_td1_trs_tds = table1_td1_trs.select("table > tbody > tr");

                String 专业 = table1_td1_trs_tds.get(1).select("td").get(1).text().trim();
                String 学校 = table1_td1_trs_tds.get(2).select("td").get(1).text().trim();
                String 学历 = table1_td1_trs_tds.get(3).select("td").get(1).text().trim();

                System.out.println("\n专业：" + 专业 + "\n学校:" + 学校 + "\n学历:" + 学历);

            }



        }

        /** 第3层，政治面貌等相关的table **/
        Element table2 = tables.get(2);
        if(table2 != null){

        }

        return resume;
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


    public static void main(String[] args) throws Exception {
        String filePath = "/Users/admin/Desktop/简历解析/51job-插件-2.html";
        File file = new File(filePath);
        String html = FileUtils.readFileToString(file, "UTF-8");
        ResumeParserHtmlStrJob51 resumeParser = new ResumeParserHtmlStrJob51();
        ResumeJob51 resume = (ResumeJob51) resumeParser.parse(html);
//        System.out.println(JsonTools.obj2String(resume));
    }


}
