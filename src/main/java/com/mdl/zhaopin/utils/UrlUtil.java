package com.mdl.zhaopin.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.utils
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月26日 11:48
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class UrlUtil {

    public static class UrlEntity {
        /**
         * 基础url
         */
        public String baseUrl;
        /**
         * url参数
         */
        public Map<String, String> params;
    }

    /**
     * 解析url
     *
     * @param url
     * @return
     */
    public static UrlEntity parse(String url) {
        UrlEntity entity = new UrlEntity();
        if (url == null) {
            return entity;
        }
        url = url.trim();
        if (url.equals("")) {
            return entity;
        }
        String[] urlParts = url.split("\\?");
        entity.baseUrl = urlParts[0];
        //没有参数
        if (urlParts.length == 1) {
            return entity;
        }
        //有参数
        String[] params = urlParts[1].split("&");
        entity.params = new HashMap<>();
        for (String param : params) {
            String[] keyValue = param.split("=");
            entity.params.put(keyValue[0], keyValue[1]);
        }

        return entity;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
//        UrlEntity entity = parse(null);
//        System.out.println(entity.baseUrl + "\n" + entity.params);
//        entity = parse("http://www.123.com");
//        System.out.println(entity.baseUrl + "\n" + entity.params);
//        entity = parse("http://www.123.com?id=1");
//        System.out.println(entity.baseUrl + "\n" + entity.params);
//        entity = parse("http://www.123.com?id=1&name=小明");
//        System.out.println(entity.baseUrl + "\n" + entity.params);

//        //51job搜索未付费简历的url
//        String resumeUrl = "https://ehire.51job.com/Candidate/ResumeView.aspx?hidUserID=829239949&hidEvents=23&pageCode=3&hidKey=c5995cb202a0fa31a0a4140782773e7b";
//        //51job搜索已付费简历的url
//        String resumeUrl = "https://ehire.51job.com/Candidate/ResumeView.aspx?hidUserID=829239949&hidEvents=23&pageCode=3&hidKey=c5995cb202a0fa31a0a4140782773e7b";
//        //51job主动投递的简历url:
//        String resumeUrl = "https://ehire.51job.com/Candidate/ResumeViewFolderV2.aspx?hidSeqID=13363549255&hidFolder=EMP&pageCode=24";


        //智联主动投递的简历url:
//        String resumeUrl = "https://rd5.zhaopin.com/resume/detail?p=4017&resumeNo=525677452310_1_1&openFrom=5";

//        //智联搜索未付费简历的url（王先生）：
//        String resumeUrl = "https://rd5.zhaopin.com/resume/detail?keyword=java&z=402101&sceneValue=%7B%22S_KEYWORD%22%3A%22java%22%2C%22S_CV_ACTIVE_TIME_LAST%22%3A%221582698617%2C1587882617%22%2C%22S_DESIRED_CITY%22%3A%22530%22%7D&actionId=15878826203911053022543&traceId=027162ec66434d3393a60a77164569c3&p=4021&resumeNo=wPh6VdfogTWjWA2hQpvYU2Oxif8NuhG%28_1_1%3BFF6BEC9EF143FC2C69226567F6A86B00%3B1587882621376&openFrom=1";

//        //智联搜索已付费简历的url（王先生-王海涛）：
//        String resumeUrl = "https://rd5.zhaopin.com/resume/detail?keyword=java&z=402101&sceneValue=%7B%22S_KEYWORD%22%3A%22java%22%2C%22S_CV_ACTIVE_TIME_LAST%22%3A%221582698617%2C1587882617%22%2C%22S_DESIRED_CITY%22%3A%22530%22%7D&actionId=15878826203911053022543&traceId=027162ec66434d3393a60a77164569c3&p=4021&resumeNo=wPh6VdfogTWjWA2hQpvYU2Oxif8NuhG%28_1_1%3BFF6BEC9EF143FC2C69226567F6A86B00%3B1587882621376&openFrom=1";

        String resumeUrl = "https://rd5.zhaopin.com/resume/detail?p=4017&resumeNo=525665801610_1_1&openFrom=5";

        UrlEntity parse = parse(resumeUrl);
        System.out.println(parse.baseUrl + "\n" + JSONUtils.objectToJson(parse.params));
        String openFrom = parse.params.get("openFrom");
        System.out.println(openFrom);


    }

}
