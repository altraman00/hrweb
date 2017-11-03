package com.sunlands.hr.spider;

import java.util.List;

import com.sunlands.hr.spider.bean.LinkTypeData;
import com.sunlands.hr.spider.rule.Rule;

public class SpiderTest {

	public static void main(String[] args) {
		three();

	}

	private static void one() {
		Rule rule = new Rule("http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",
				new String[] { "query.enterprisename", "query.registationnumber" }, new String[] { "兴网", "" },
				"cont_right", Rule.CLASS, Rule.POST);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);

	}

	private static void two() {
		Rule rule = new Rule("http://www.11315.com/search", new String[] { "name" }, new String[] { "兴网" },
				"div.g-mn div.con-model", Rule.SELECTION, Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);

	}
	
	private static void three() {
		Rule rule = new Rule("http://news.baidu.com/ns", new String[] { "word" }, new String[] { "支付宝" }, null, -1,
				Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		System.out.println(extracts);

	}

	public static void printf(List<LinkTypeData> datas) {
		for (LinkTypeData data : datas) {
			System.out.println(data.getLinkText());
			System.out.println(data.getLinkHref());
			System.out.println("***********************************");
		}

	}

}
