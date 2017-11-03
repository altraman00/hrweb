package com.sunlands.hr.word;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apdplat.word.WordFrequencyStatistics;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PartOfSpeechTagging;

public class WordTest3 {

	public static void main(String[] args) throws Exception {

		String str = "我的名字叫谢昆，今年18岁，性别男，手机号是13397158181，邮箱是869118563@qq.com,备用邮箱是xie_kun_cloud@163.com，在武汉市洪山区关山大道软件园上班，公司名字叫尚德机构有限公司";
		
//		List<Word> words = WordSegmenter.seg(str);
//		List<Word> words = WordSegmenter.segWithStopWords(str);
		
		String file = "C:\\Users\\xiekun\\Desktop\\file\\txt\\谢昆.txt";
		
		List<Word> words = WordSegmenter.segWithStopWords(file);
		
		PartOfSpeechTagging.process(words);//添加词性标注
		
		System.out.println(words.toString());
		
		for (Word word : words) {
			String wordStr = word.toString();
			if(wordStr.endsWith("/i")) {
				System.out.println("名字:" + word.getText());
			}else if(wordStr.endsWith("/m") && wordStr.length() == 11) {
				System.out.println("手机号：:" + word.getText());
			}else if(wordStr.endsWith("/m") && wordStr.length() < 11) {
				System.out.println("邮箱：:" + word.getText());
			}
		}
		

	}

}
