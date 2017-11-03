package com.sunlands.hr.hanlp;

import java.util.Map;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;

public class CustomDictionaryTest {

	public static void main(String[] args) {
		// 动态增加
		CustomDictionary.add("攻城师");
		// 强行插入
		CustomDictionary.insert("白富美", "nz 1024");
		// 删除词语（注释掉试试）
		// CustomDictionary.remove("攻城狮");
		System.out.println(CustomDictionary.add("单身狗", "nz 1024 n 1"));
		System.out.println(CustomDictionary.get("单身狗"));

		String text = "华中科技大学攻城师逆袭单身狗，迎娶白富美，走上人生巅峰";

		// DoubleArrayTrie分词
		final char[] charArray = text.toCharArray();
		CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
			@Override
			public void hit(int begin, int end, CoreDictionary.Attribute value) {
				System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(charArray, begin, end - begin), value);
			}
		});
		// 首字哈希之后二分的trie树分词
		BaseSearcher searcher = CustomDictionary.getSearcher(text);
		Map.Entry entry;
		while ((entry = searcher.next()) != null) {
			System.out.println(entry);
		}

		// 标准分词
		System.out.println(HanLP.segment(text));

		// Note:动态增删不会影响词典文件
		// 目前CustomDictionary使用DAT储存词典文件中的词语，用BinTrie储存动态加入的词语，前者性能高，后者性能低
		// 之所以保留动态增删功能，一方面是历史遗留特性，另一方面是调试用；未来可能会去掉动态增删特性。
	}

}
