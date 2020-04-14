package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Job51Resume;
import com.mdl.zhaopin.handler.platform.resume.Resume;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Enumeration;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : 前程无忧简历doc 解析器
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:43
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class Job51DocParser extends AbstractParser {

    @Override
    public Resume parse(File file) throws Exception {

        Job51Resume job51Resume = new Job51Resume();

        String doc = mht2html(file);

        // 前程无忧的html文件 编码都是gbk
        Document document = Jsoup.parse(doc);

        //姓名

        Element nameElement = document.select(".name").first();
        if (nameElement != null) {
            String userName = nameElement.text();
            if (userName.contains("流程状态")) {
                userName = userName.substring(0, userName.indexOf("流程状态"));
            } else if (userName.contains("标签")) {
                userName = userName.substring(0, userName.indexOf("标签"));
            }
            job51Resume.setName(userName);
        }

        // 个人信息
        // 男 |22 岁 (1997/01/10)|现居住深圳-龙华新区|暂无经验男
        Elements detailEles = document.select("[style=\"height: 28px;\"]");
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

        return null;
    }

    /**
     * 将 mht文件转换成 String
     *
     * @param file // mht 文件
     */
    public static String mht2html(File file) {
        try {
            InputStream fis = new FileInputStream("C:\\Users\\12558\\Desktop\\人才库\\51job_李勇(458339121).doc");
            Session mailSession = Session.getDefaultInstance(
                    System.getProperties(), null);
            MimeMessage msg = new MimeMessage(mailSession, fis);
            Object content = msg.getContent();
            if (content instanceof Multipart) {
                MimeMultipart mp = (MimeMultipart) content;
                MimeBodyPart bp1 = (MimeBodyPart) mp.getBodyPart(0);

                // 获取mht文件内容代码的编码
                String strEncodng = getEncoding(bp1);

                // 获取mht文件的内容
                String strText = getHtmlText(bp1, strEncodng);

                //if (strText == null)
                return strText;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取mht文件中的内容代码
     *
     * @param bp
     * @param strEncoding 该mht文件的编码
     * @return
     */
    private static String getHtmlText(MimeBodyPart bp, String strEncoding) {
        InputStream textStream = null;
        BufferedInputStream buff = null;
        BufferedReader br = null;
        Reader r = null;
        try {
            textStream = bp.getInputStream();
            buff = new BufferedInputStream(textStream);
            r = new InputStreamReader(buff, strEncoding);
            br = new BufferedReader(r);
            StringBuffer strHtml = new StringBuffer("");
            String strLine = null;
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
                strHtml.append(strLine + "\r\n");
            }
            br.close();
            r.close();
            textStream.close();
            return strHtml.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (buff != null) {
                    buff.close();
                }
                if (textStream != null) {
                    textStream.close();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 获取mht网页文件中内容代码的编码
     *
     * @param bp
     * @return
     */
    private static String getEncoding(MimeBodyPart bp) {
        if (bp == null) {
            return null;
        }
        try {
            Enumeration list = bp.getAllHeaders();
            while (list.hasMoreElements()) {
                javax.mail.Header head = (javax.mail.Header) list.nextElement();
                if (head.getName().equalsIgnoreCase("Content-Type")) {
                    String strType = head.getValue();
                    int pos = strType.indexOf("charset=");
                    if (pos >= 0) {
                        String strEncoding = strType.substring(pos + 8,
                                strType.length());
                        if (strEncoding.startsWith("\"")
                                || strEncoding.startsWith("\'")) {
                            strEncoding = strEncoding.substring(1,
                                    strEncoding.length());
                        }
                        if (strEncoding.endsWith("\"")
                                || strEncoding.endsWith("\'")) {
                            strEncoding = strEncoding.substring(0,
                                    strEncoding.length() - 1);
                        }
                        if (strEncoding.toLowerCase().compareTo("gb2312") == 0) {
                            strEncoding = "gbk";
                        }
                        return strEncoding;
                    }
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
