package com.mdl.zhaopin.handler.platform.parse.file;//package com.mdl.zhaopin.handler.platform.parse.file;
//
//import com.mdl.zhaopin.handler.platform.parse.AbstractResumeParser;
//import com.mdl.zhaopin.handler.platform.parse.IResumeParser;
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
//public class ResumeParserFileDocZhilian extends AbstractResumeParser implements IResumeParser {
//
//    @Override
//    public String getName() {
//        return "智联招聘";
//    }
//
//    @Override
//    public Resume parse(String path) throws Exception {
//        File file = new File(path);
//        Document doc = parse2Html(file);
//
//        String filename = file.getName();
//        String[] params = filename.split("_");
//        ResumeZhilian resume = new ResumeZhilian();
//        resume.setName(params[1]);
//        resume.setJob(params[2]);
//
//        // 解析其他数据
//        Elements trs = doc.select("table").get(1).select("tr");
//        for (Element tr : trs) {
//            String text = tr.text();
//            if (text.contains("工作经验")) {
//                // 男    38岁(1978年12月)    13年工作经验    本科 现居住地：北京 | 户口：保定
//                parseBasicInfo(resume, text);
//            } else if (text.startsWith("手机：")) {
//                // 手机：18600904162 E-mail：18600904162@163.com
//                parseContactMethod(resume, text);
//            }
//            // 其他信息忽略
//        }
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
//     *
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
//        String filePath = "/Users/admin/Desktop/简历解析/智联招聘_底文娟_人力资源专员-BP方向_中文_20200415_1586922507275.doc";
//        ResumeParserFileDocZhilian resumeParser = new ResumeParserFileDocZhilian();
//        ResumeZhilian resume = (ResumeZhilian) resumeParser.parse(filePath);
//        System.out.println(JsonTools.obj2String(resume));
//
//    }
//
//
//}
