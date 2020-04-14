package com.mdl.zhaopin.handler.hanlp.parse;

import com.mdl.zhaopin.handler.hanlp.parse.HanlpPraseResume;
import com.mdl.zhaopin.handler.hanlp.read.ReadFile;
import com.mdl.zhaopin.handler.hanlp.read.impl.ReadWord;

public class TurnWordToText extends HanlpPraseResume {

	private String filePath;

	public TurnWordToText(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String readFile() {

		ReadFile readWord = new ReadWord();

		String read = readWord.read(filePath);

		super.separateStr = read;

		return read;
	}

}
