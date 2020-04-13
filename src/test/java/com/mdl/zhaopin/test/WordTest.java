package com.mdl.zhaopin.test;


import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.corpus.document.sentence.word.IWord;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.mdl.zhaopin.utils.TxtUtils;

import java.util.List;

public class WordTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String str = TxtUtils.read(path + "/src/main/resumefiles/1-夏小为.txt");

        nlpSegment(str);
//        nlpAnalyze(str);

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
