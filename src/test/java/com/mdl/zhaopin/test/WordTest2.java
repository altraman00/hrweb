package com.mdl.zhaopin.test;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.util.List;

public class WordTest2 {

    public static void main(String[] args) {

        System.out.println("1-->" + HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
        System.out.println("2-->" + HanLP.extractKeyword("程序员(英文Programmer)是从事程序开发、" +
                "维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，" +
                "特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类", 5));
        System.out.println("3-->" + HanLP.convertToTraditionalChinese("用笔记本电脑写程序"));
        System.out.println("4-->" + HanLP.convertToSimplifiedChinese("「以後等妳當上皇后，就能買士多啤梨慶祝了」"));
        System.out.println("5-->" + HanLP.newSegment().enableNameRecognize(true).seg("签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。"));
        Suggester suggester = new Suggester();
        //下面文档中\n换行符去掉后，文本智能推荐suggest部分失效
        String[] titleArray =
                (
                        "威廉王子发表演说 呼吁保护野生动物\n" +
                                "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
                                "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
                                "日本保密法将正式生效 日媒指其损害国民知情权\n" +
                                "人工智能如今是非常火热的一门技术”"
                ).split("\\n");
        for (String title : titleArray) {
            suggester.addSentence(title);
        }
        System.out.println("6-->" +suggester.suggest("机器学习", 1));   // 语义
        System.out.println("7-->" +suggester.suggest("危机公共", 1));   // 字符
        System.out.println("8-->" +suggester.suggest("mayun", 1));     // 拼音

    }



}
