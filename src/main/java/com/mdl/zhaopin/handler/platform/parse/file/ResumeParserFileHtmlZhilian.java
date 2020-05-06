package com.mdl.zhaopin.handler.platform.parse.file;//package com.mdl.zhaopin.handler.platform.parse;
//
//import com.mdl.zhaopin.handler.platform.resume.Resume;
//import com.mdl.zhaopin.handler.platform.resume.ResumeZhilian;
//import com.mdl.zhaopin.utils.JsonTools;
//import org.apache.commons.io.FileUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @Project : reusme-parse
// * @Package Name : com.mdl.zhaopin.handler.platform.parse
// * @Description : TODO
// * @Author : xiekun
// * @Create Date : 2020年04月15日 15:36
// * @ModificationHistory Who   When     What
// * ------------    --------------    ---------------------------------
// */
//public class ResumeParserHtmlZhilian extends AbstractResumeParser implements ResumeFileParser{
//
//    @Override
//    public String getName() {
//        return "智联招聘";
//    }
//
//
//    @Override
//    public Resume parse(File file) throws Exception {
//
//        Document doc = parse2Html(file);
//
//        String filename = file.getName();
//        String[] params = filename.split("_");
//        ResumeZhilian resume = new ResumeZhilian();
//        resume.setName(params[1]);
//        resume.setJob(params[2]);
//
//        Element resumeDetail = doc.getElementById("resumeDetail");
//
//        String userName = resumeDetail.getElementsByClass("main-title-fl").text();
//
//        /** resumeDetail > div > div > div.summary > div.summary-top > span **/
//        String str_1 = doc.getElementsByClass("summary-top").select("span").text();
//        String[] arr = str_1.split("    ");
//        /** 性别 **/
//        String sex = arr[0];
//        /** 年龄 **/
//        String age = arr[1];
//        /** 学历 **/
//        String degree = arr[2];
//        /** 是否结婚 **/
//        String isMarry = arr[3];
//
//        /** resumeDetail > div > div > div.summary > div.summary-top > br  **/
//        String summaryTop = doc.getElementsByClass("summary-top").html();
//        String reg = "<br[^>]*>((?:(?!<br[^>]*>)[\\s\\S])*)";
//        Pattern p = Pattern.compile(reg);
//        Matcher m = p.matcher(summaryTop);
//        String[] topArr = null;
//        while (m.find()) {
//            topArr = m.group(1).split("：");
//        }
//
//        //现居住地
//        String address = topArr[1].replace(" ", "").split("\\|")[0].trim();
//        //户口
//        String userAccount = topArr[2].trim().split("\\|")[1].trim();
//        //政治面貌
//        String politicalStatus = topArr[2].trim().split("\\|")[1].trim();
//
//        //#resumeDetail > div > div > div:nth-child(4)
//        Elements select = resumeDetail.select("div").select("div").select("div").select("div:nth-child(4)");
//        String h3 = select.select("h3").text();
//
//        Elements allItems = resumeDetail.getElementsByClass("resume-preview-left").get(0).getElementsByClass("resume-preview-all");
//        for(Element item : allItems){
//            String itemTitle = item.select("h3").text();
//            String itemContent = item.select("h2").text();
//            System.out.println(itemTitle);
//            System.out.println(itemContent + "\n");
//        }
//
////        while(iterator.hasNext()){
////            Element next = iterator.next();
////            Elements elementsByClass = next.getElementsByClass("resume-preview-all");
////            String itemTitle = elementsByClass.select("h3").text();
////            String itemContent = elementsByClass.select("h2").text();
////            System.out.println(itemTitle);
////            System.out.println(itemContent + "\n");
////        }
//
//
//
//
//
//
//
//
////        // 解析其他数据
////        Elements trs = doc.select("table").get(1).select("tr");
////        for (Element tr : trs) {
////            String text = tr.text();
////            if (text.contains("工作经验")) {
////                // 男    38岁(1978年12月)    13年工作经验    本科 现居住地：北京 | 户口：保定
////                parseBasicInfo(resume, text);
////            } else if (text.startsWith("手机：")) {
////                // 手机：18600904162 E-mail：18600904162@163.com
////                parseContactMethod(resume, text);
////            }
////            // 其他信息忽略
////        }
//
//        return resume;
//    }
//
//    /**
//     * 男 38岁(1978年12月)  13年工作经验 本科 现居住地：北京 | 户口：保定
//     **/
//    protected void parseBasicInfo(ResumeZhilian resume, String basicInfo) {
//        final String seprator = "    ";
//        String[] msg = basicInfo.split(seprator);
//        for (String info : msg) {
//            if (info.contains("男")) {
//                resume.setSex("男");
//            } else if (info.contains("女")) {
//                resume.setSex("女");
//            } else if (info.contains("岁")) {
//                int index = info.indexOf("岁");
//                resume.setAge(info.substring(0, index));
//                resume.setBirthday(trim(info.substring(index + 1), "(", ")", "（", "）"));
//            } else if (info.contains("工作经验")) {
//                int index = info.indexOf("年");
//                if (index > 0) {
//                    resume.setWorkDuration(info.substring(0, index));
//                } else {
//                    resume.setWorkDuration(info.substring(0, info.indexOf("工作经验")));
//                }
//            } else if (info.contains("现居住地：")) {
//                resume.setEducation(intercept(info, null, "现居住地："));
//                resume.setCity(intercept(info, "现居住地：", "|"));
//            }
//        }
//    }
//
//    /**
//     * 手机：18600904162 E-mail：18600904162@163.com
//     **/
//    protected void parseContactMethod(ResumeZhilian resume, String contact) {
//        resume.setPhone(intercept(contact, "手机：", "E-mail："));
//        resume.setMail(intercept(contact, "E-mail：", null));
//    }
//
//
//    /**
//     * file转string
//     * @param file
//     * @return
//     * @throws Exception
//     */
//    protected Document parse2Html(File file) throws Exception {
//        String html = FileUtils.readFileToString(file, "UTF-8");
//        return Jsoup.parse(html);
//    }
//
//
//    public static void main(String[] args) throws Exception {
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘_底文娟_人力资源专员-BP方向_中文_20200415_1586922516067.html";
//        File file = new File(filePath);
//        ResumeFileParser resumeParser = new ResumeParserHtmlZhilian();
//        ResumeZhilian resume = (ResumeZhilian) resumeParser.parse(file);
//        System.out.println(JsonTools.obj2String(resume));
//    }
//
//
//}
