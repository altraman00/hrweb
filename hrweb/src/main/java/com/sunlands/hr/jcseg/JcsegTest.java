package com.sunlands.hr.jcseg;

import java.io.IOException;
import java.io.StringReader;

import org.lionsoul.jcseg.tokenizer.core.ADictionary;
import org.lionsoul.jcseg.tokenizer.core.DictionaryFactory;
import org.lionsoul.jcseg.tokenizer.core.ISegment;
import org.lionsoul.jcseg.tokenizer.core.IWord;
import org.lionsoul.jcseg.tokenizer.core.JcsegException;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.lionsoul.jcseg.tokenizer.core.SegmentFactory;

public class JcsegTest {

	public static void main(String[] args) throws JcsegException, IOException {
		//设置要被分词的文本
		String str = "姓名：谢军	QQ:8693324213 性别：男	手机：13394558186	住址：武汉市洪山区雄楚大道金地中心城";
		
		//创建JcsegTaskConfig分词配置实例，自动查找加载jcseg.properties配置项来初始化
		JcsegTaskConfig config = new JcsegTaskConfig(true);

		//创建默认单例词库实现，并且按照config配置加载词库
		ADictionary dic = DictionaryFactory.createSingletonDictionary(config);

		//依据给定的ADictionary和JcsegTaskConfig来创建ISegment
		//为了Api往后兼容，建议使用SegmentFactory来创建ISegment对象
		ISegment seg = SegmentFactory.createJcseg(
		    JcsegTaskConfig.COMPLEX_MODE, 
		    new Object[]{new StringReader(str), config, dic}
		);


		//备注：以下代码可以反复调用，seg为非线程安全

		seg.reset(new StringReader(str));

		//获取分词结果
		IWord word = null;
		while ( (word = seg.next()) != null ) {
		    System.out.println(word.getValue());
		}
	}
	
}
