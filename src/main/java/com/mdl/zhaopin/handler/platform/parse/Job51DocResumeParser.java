package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Job51Resume;
import com.mdl.zhaopin.handler.platform.resume.Resume;
import com.mdl.zhaopin.utils.JsonTools;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : 前程无忧简历doc 解析器
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:43
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class Job51DocResumeParser extends AbstractResumeParser {

    @Override
    public String getName() {
        return "前程无忧";
    }

    @Override
    public boolean canParse(File file) {
        if (file == null) {
            return false;
        } else {
            String name = file.getName();
            return name.contains("51job") || name.startsWith("前程无忧-");
        }
    }

    @Override
    public Resume parse(File file) throws Exception {

        Document doc = parse2HtmlAsMail(file);

        if (doc == null) {
            doc = parse2Html(file);
        }
        Job51Resume job51Resume = new Job51Resume();

        // 应聘人姓名
        String fileName = file.getName();
        if (fileName.startsWith("51job_")) {
            Element nameElement = doc.select(".name").first();
            if (nameElement != null) {
                String userName = nameElement.text();
                if (userName.contains("流程状态")) {
                    userName = userName.substring(0, userName.indexOf("流程状态"));
                } else if (userName.contains("标签")) {
                    userName = userName.substring(0, userName.indexOf("标签"));
                }
                job51Resume.setName(userName);
            }
        } else {
            job51Resume.setName(fileName.substring(fileName.lastIndexOf("－") + 1, fileName.indexOf(".eml")));
        }


        // 个人信息
        // 男 |22 岁 (1997/01/10)|现居住深圳-龙华新区|暂无经验男
        Elements detailEles = doc.select("[style=\"height: 28px;\"]");
        if (detailEles.size() > 0) {
            String detail = detailEles.text();
            String[] ds = detail.replaceAll("\\|", " ~!~ ").split("~!~");
            for (String str : ds) {
                if (str.contains("工作经验")) {
                    int index = str.indexOf("年");
                    if (index > 0) {
                        job51Resume.setWorkDuration(str.substring(0, index).trim());
                    }
                } else if (str.contains("岁")) {
                    int index = str.indexOf("岁");
                    job51Resume.setAge(str.substring(0, index).trim());
                } else if (str.contains("男")) {
                    job51Resume.setSex("男");
                } else if (str.contains("女")) {
                    job51Resume.setSex("女");
                }
            }
        }

        return job51Resume;
    }

    protected Document parse2Html(File file) throws Exception {
        // 读取51job的简历文件
        List<String> lines = FileUtils.readLines(file);

        // 开始解析
        String state = "";
        String charset = null;
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            if (line.startsWith("Content-Type:text/html;charset=")) {
                charset = line.substring("Content-Type:text/html;charset=".length()).replaceAll("\"", "");
                state = "ready";
            } else if (line.startsWith("Content-Type: text/html;charset=")) {
                charset = line.substring("Content-Type: text/html;charset=".length()).replaceAll("\"", "");
                state = "ready";
            } else if (state.equals("ready") && line.length() == 76) {
                state = "go";
                sb.append(line);
            } else if (state.equals("go") && line.length() > 0) {
                sb.append(line);
            } else if (state.equals("go") && line.length() == 0) {
                state = "over";
                break;
            }
        }

        // 未成功解析则返回null
        if (!"over".equals(state)) {
            return null;
        }

        // 解析成功, 开始进行数据读取
        String html = new String(Base64.decodeBase64(sb.toString()), charset);
        return Jsoup.parse(html);
    }

    protected Document parse2HtmlAsMail(File file) throws Exception {
        InputStream in = new FileInputStream(file);

        Session mailSession = Session.getDefaultInstance(System.getProperties(), null);

        MimeMessage msg = new MimeMessage(mailSession, in);

        Multipart part = (Multipart) msg.getContent();
        String html = null;
        for (int i = 0; i < part.getCount(); i++) {
            html = parseHtml(part.getBodyPart(i));
            if (html != null) {
                break;
            }
        }
        in.close();
        return html == null ? null : Jsoup.parse(html);
    }

    private String parseHtml(BodyPart body) throws Exception {
        if (body.getContentType().startsWith("text/html")) {
            Object content = body.getContent();
            return content == null ? null : content.toString();
        } else if (body.getContentType().startsWith("multipart")) {
            Multipart subpart = (Multipart) body.getContent();
            for (int j = 0; j < subpart.getCount(); j++) {
                BodyPart subbody = subpart.getBodyPart(j);
                String html = parseHtml(subbody);
                if (html != null) {
                    return html;
                }
            }
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        String filePath = "/Users/admin/Desktop/简历解析/51job_付必新_i销售经理(828854605).doc";
        File file = new File(filePath);

        AbstractResumeParser resumeParser = new Job51DocResumeParser();
        Job51Resume resume = (Job51Resume) resumeParser.parse(file);

        System.out.println(JsonTools.obj2String(resume));

    }


}
