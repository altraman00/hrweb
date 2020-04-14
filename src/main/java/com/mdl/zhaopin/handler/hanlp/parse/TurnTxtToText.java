package com.mdl.zhaopin.handler.hanlp.parse;


import com.mdl.zhaopin.handler.hanlp.read.ReadFile;
import com.mdl.zhaopin.handler.hanlp.read.impl.ReadTxt;
import com.mdl.zhaopin.utils.RegexUtils;

public class TurnTxtToText extends HanlpPraseResume {

	private String filePath = null;

	public TurnTxtToText(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String readFile() {

		ReadFile readTxt = new ReadTxt();

		String str = readTxt.read(this.filePath);

//		List<String> list = SentencesUtil.toSentenceList(str);
//		for (String string : list) {
//			System.out.println("----------------->" + string);
//		}

//		System.out.println(str + "\n");

		String res = RegexUtils.replaceBlank(str);

//		System.out.println(res + "\n");

		super.separateStr = res;

		return separateStr;
	}

}
