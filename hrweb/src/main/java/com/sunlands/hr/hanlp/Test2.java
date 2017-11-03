package com.sunlands.hr.hanlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.sunlands.hr.hanlp.factory.MatcherAssist;

public class Test2 {

//	public static void main(String[] args) {
//
//		// String str = "我是一名产品经理攻城狮Java工程师IOS工程师Android工程师硬质合金烧结工 中山火炬职业技术学院";
//		//
//		// Segment segment = HanLP.newSegment()
//		// .enableCustomDictionary(true)
//		// .enablePartOfSpeechTagging(true);// 开启词性标注
//		//
//		// List<Term> termList = segment.seg(str);
//		//
//		// System.out.println("\n标准分词器A：--->" + termList);
//
//		String str = "哈哈,13899,321,ff222";
//
//		getSalary(str);
//
//		// // 提取数字
//		// // 1
//		// Pattern pattern = Pattern.compile("[^0-9]");
//		// Matcher matcher = pattern.matcher(phoneString);
//		// String all = matcher.replaceAll("");
//		// System.out.println("phone:" + all);
//		// // 2
//		// Pattern.compile("[^0-9]").matcher(phoneString).replaceAll("");
//
//	}

	public static String getSalary(String str) {
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(str);
		String string = matcher.replaceAll("#").trim();
		String[] strArr = string.split("#");
		List<String> list = new ArrayList<>();
		System.out.println(list.toString());
		for (String s : strArr) {
			if (!s.isEmpty()) {
				list.add(s);
			}
		}
		return list.toString();
	}

	

	public static void main(String[] args) {
		String str = "&nbsp;&nbsp;发布时间：2016.06 2013-03-03 2016#08#08 15:51:03 ";
//		String date = MatcherAssist.getFirstDateYM(str, ".");
		
		List<String> list = new ArrayList<>();
		
		String[] split = {".","-","#"};
		for (String sp : split) {
			List<String> dates = MatcherAssist.getDatesYM(str, sp);
			for (String d : dates) {
				String res = d.replace(sp, "");
				list.add(res);
			}
		}
		System.out.println(list.toString());
		
		Collections.sort(list);
		
		System.out.println(list.toString());
		
		
		ArrayList<Integer> mList = new ArrayList<>();
		
		for (String ss : list) {
			mList.add(Integer.valueOf(ss));
		}
		
		Collections.sort(mList,new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		System.out.println(mList.toString());
		
	}

}
