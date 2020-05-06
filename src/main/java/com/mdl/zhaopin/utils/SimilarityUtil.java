package com.mdl.zhaopin.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.utils
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月13日 13:38
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class SimilarityUtil {

    static {
        CustomDictionary.add("子类");
        CustomDictionary.add("父类");
    }

    private SimilarityUtil() {
    }


    /**
     * 计算相似度（两个向量的余弦值）
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static double getSimilarity(String sentence1, String sentence2) {
        //处理html标签和标点符号
        List<String> sent1Words = getSplitWords(sentence1);
        List<String> sent2Words = getSplitWords(sentence2);

        //合并分词结果，列出所有的词
        List<String> allWords = mergeList(sent1Words, sent2Words);

        //统计词频，得到词频构成的向量
        int[] statistic1 = statistic(allWords, sent1Words);
        int[] statistic2 = statistic(allWords, sent2Words);

        double dividend = 0;
        double divisor1 = 0;
        double divisor2 = 0;
        for (int i = 0; i < statistic1.length; i++) {
            dividend += statistic1[i] * statistic2[i];
            divisor1 += Math.pow(statistic1[i], 2);
            divisor2 += Math.pow(statistic2[i], 2);
        }

        return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));

    }


    /**
     * 统计词频，得到词频构成的向量
     * @param allWords
     * @param sentWords
     * @return
     */
    private static int[] statistic(List<String> allWords, List<String> sentWords) {
        int[] result = new int[allWords.size()];
        for (int i = 0; i < allWords.size(); i++) {
            result[i] = Collections.frequency(sentWords, allWords.get(i));
        }
        return result;
    }


    /**
     * 合并分词结果，列出所有的词
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> mergeList(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return result.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 处理html标签和标点符号
     * jsoup是为了处理题干中的html标签，去除html标签得到纯文本的题干内容
     * @param sentence
     * @return
     */
    private static List<String> getSplitWords(String sentence) {
        // 去除掉html标签
        sentence = Jsoup.parse(sentence.replace("&nbsp;","")).body().text();
        // 标点符号会被单独分为一个Term，去除之
        return HanLP.segment(sentence).stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？".contains(s)).collect(Collectors.toList());
    }

}
