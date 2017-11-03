package com.sunlands.hr.word;

import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PartOfSpeechTagging;

public class WordTest {

	public static void main(String[] args) throws Exception {

		String str = "我的名字叫谢昆，今年18岁，性别男，手机号是13397158181，邮箱是869118563@qq.com,备用邮箱是xie_kun_cloud@163.com，在武汉市洪山区关山大道软件园上班，公司名字叫尚德机构有限公司";
		
//		List<Word> words = WordSegmenter.seg(str);
		List<Word> words = WordSegmenter.segWithStopWords(str);
		
		PartOfSpeechTagging.process(words);//添加词性标注
		
		for (Word word : words) {
			System.out.println("text" + word.getText());
			System.out.println("AcronymPinYin" + word.getAcronymPinYin());
			System.out.println("FullPinYin" + word.getFullPinYin());
			System.out.println("weight" + word.getWeight());
		}
		
		System.out.println(words.toString());

	}

}
