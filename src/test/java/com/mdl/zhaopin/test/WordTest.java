package com.mdl.zhaopin.test;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.corpus.occurrence.TermFrequency;
import com.hankcs.hanlp.mining.word.TermFrequencyCounter;
import com.hankcs.hanlp.mining.word.WordInfo;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.mdl.zhaopin.utils.TxtUtils;

import java.util.List;

public class WordTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String testCase = TxtUtils.read(path + "/src/main/resumefiles/1-夏小为.txt");

//        System.out.println(str);
//        Segment segment = HanLP.newSegment().enableNameRecognize(true);
//        List<Term> termList = segment.seg(str);
//        System.out.println(termList);

//        String testCase = "简历——智联招聘夏小为,女 | 已婚 | 1980年5月生 | 户口：江苏 | 现居住于上海 7年工作经验 | 无党派人士|  身份证： 320326198088888888 现住在浦东临港新城 邮编200433 手机号13397158111，email：2733492193@qq.com";

//        //1、标准分词（名字被拆开了）
//        List<Term> termList1 = StandardTokenizer.segment(testCase);
//        System.out.println("\n0--->" + termList1);


        //2、NLP分词NLPTokenizer会执行词性标注和命名实体识别，由结构化感知机序列标注框架支撑。
        System.out.println("\n1--->" + NLPTokenizer.segment(testCase));
        // 注意观察下面两个“希望”的词性、两个“晚霞”的词性
//        System.out.println("2--->" + NLPTokenizer.analyze(testCase).translateLabels() );
        System.out.println("\n3--->" + NLPTokenizer.analyze(testCase));


//        //5、N-最短路径分词 (分的太细，名字被拆开了)
//        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//        System.out.println("\n4--->N-最短分词：" + nShortSegment.seg(testCase));
//        System.out.println("\n5--->最短路分词：" + shortestSegment.seg(testCase));

//        //9、中国人名识别（名字被拆开了）
//        Segment segment = HanLP.newSegment().enableNameRecognize(true);
//        List<Term> termList2 = segment.seg(testCase);
//        System.out.println("\n4--->" + termList2);

//        //7、极速词典分词（稀烂）
//        System.out.println("\n7--->" + SpeedTokenizer.segment(testCase));

        //14、关键词提取
        List<String> keywordList = HanLP.extractKeyword(testCase, 5);
        System.out.println("\n14--->" + keywordList);


        //15、自动摘要
        List<String> sentenceList = HanLP.extractSummary(testCase, 3);
        System.out.println("\n15--->" + sentenceList);


        //16、短语提取
        List<String> phraseList = HanLP.extractPhrase(testCase, 10);
        System.out.println("\n16--->" + phraseList);


        //17、文本推荐
        Suggester suggester = new Suggester();
        suggester.addSentence(testCase);
        System.out.println("\n17--->" + suggester.suggest("培训描述", 1));       // 语义


//        nlpFrequency();
//        nlpExtractWords(str);
//        nlpSegment(str);
//        nlpAnalyze(str);

    }


    /**
     * 计算词语出现的频率
     */
    private static void nlpFrequency() {
        TermFrequencyCounter counter = new TermFrequencyCounter();
//        counter.getSegment().enableIndexMode(true);
//        counter.setSegment(new PerceptronLexicalAnalyzer().enableIndexMode(true));
        counter.add("加油加油中国队!"); // 第一个文档
        counter.add("中国观众高呼加油中国"); // 第二个文档
        // 遍历每个词与词频
        for (TermFrequency termFrequency : counter) {
            System.out.printf("%s=%d\n", termFrequency.getTerm(), termFrequency.getFrequency());
        }
        System.out.println(counter.top(2)); // 取top N
    }

    /**
     * 短语关键词提取
     * @param str
     */
    private static void nlpExtractWords(String str) {
        List<WordInfo> wordInfoList = HanLP.extractWords(str, 100);
        System.out.println(wordInfoList);
    }


    /**
     * 分词
     * @param str
     */
    private static void nlpSegment(String str) {

        List<Term> segment = NLPTokenizer.segment(str);
        System.out.println(segment.toString());

        String address = "";
        String phone = "";
        String name = "";

        for(Term term : segment){
            if(term.nature.startsWith("nr")){
                //nr代表人名
                name = term.word;
                System.out.println("name: " + name);
            }

            if(term.nature.startsWith("m") && term.word.length() == 11){
                //m代表数字
                phone = term.word;
                System.out.println("phone: " + phone);
            }
        }

//        //由于地址包含了数字，解析的时候数字成为单独的个体，与实际不符，所以通过差集求出地址
//        address = str.replace(phone, "").replace(name, "").trim();
//        System.out.println("address: " + address);
    }


    /**
     * 语法分析
     * @param str
     */
    private static void nlpAnalyze(String str) {
        Sentence analyze = NLPTokenizer.analyze(str);
        List<IWord> list = analyze.wordList;
        System.out.println(analyze.toString());
        list.stream().forEach(t -> {
            String lable = t.getLabel();
            String value = t.getValue();
            System.out.println(lable + "---" + value);
        });
    }

}
