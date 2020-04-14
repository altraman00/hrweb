package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Job51Resume;
import com.mdl.zhaopin.handler.platform.resume.Resume;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;


/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:49
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class Job51HtmlParser extends AbstractParser {

    @Value("${file.hardPath}")
    String hardPath;

    /**
     * 解析 前程无忧的html简历
     *
     * @param file
     * @return
     */
    @Override
    public Resume parse(File file) throws IOException {

        Job51Resume job51HtmlResume = new Job51Resume();

        // 前程无忧的html文件 编码都是gbk
        Document document = Jsoup.parse(file, "gbk");

        //姓名
        Element nameElement = document.select(".name").first();
        if (nameElement != null) {
            String userName = nameElement.text();
            job51HtmlResume.setName(userName);
        }

        // 个人信息
        // 22 岁 (1997/01/10)|现居住深圳-龙华新区|暂无经验男
        Elements detailEle = document.select("table table table tr:nth-child(3) p");
        if (detailEle.size() > 0) {
            String detail = detailEle.text();
            String[] ds = detail.replaceAll("\\|", " ~!~ ").split("~!~");
            for (String str : ds) {
                /*if (str.contains("工作经验")) {
                    int index = str.indexOf("年");
                    if (index > 0) {
                        job51HtmlResume.set(KEY_WORK_DURATION, str.substring(0, index));
                    } else {
                        resume.set(KEY_WORK_DURATION, str.substring(0, str.indexOf("工作经验")));
                    }
                }else if (){
                }*/
            }
        }

        return new Job51Resume();
    }

}
