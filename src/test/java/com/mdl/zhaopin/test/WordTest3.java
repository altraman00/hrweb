package com.mdl.zhaopin.test;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.suggest.Suggester;
import com.mdl.zhaopin.utils.TxtUtils;

import java.util.List;

public class WordTest3 {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String str = TxtUtils.read(path + "/src/main/resumefiles/1-夏小为.txt");

        System.out.println("1-->" + HanLP.extractKeyword(str, 5));
//        System.out.println("2-->" + HanLP.newSegment().enableNameRecognize(true).seg(str));

        //自动摘要
        List<String> sentenceList = HanLP.extractSummary(str, 3);
        System.out.println("3-->" + sentenceList);

        //短语提取
        List<String> phraseList = HanLP.extractPhrase(str, 10);
        System.out.println("4-->" + phraseList);

        //关键字提取
        List<String> keywordList = HanLP.extractKeyword(str, 25);
        System.out.println("5-->" +keywordList);

//        //智能推荐部分
//        Suggester suggester = new Suggester();
//        String[] titleArray = (str).split("\\n");
//        for (String title : titleArray) {
//            suggester.addSentence(title);
//        }
//        System.out.println("3-->" +suggester.suggest("自我评价", 1) + "\n");   // 语义
//        System.out.println("4-->" +suggester.suggest("工作经验", 1) + "\n");   // 字符
//        System.out.println("5-->" +suggester.suggest("工作描述", 1) + "\n");     // 拼音

    }



}
