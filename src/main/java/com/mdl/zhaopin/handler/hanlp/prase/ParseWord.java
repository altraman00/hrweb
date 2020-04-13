package com.mdl.zhaopin.handler.hanlp.prase;

import com.mdl.zhaopin.handler.hanlp.factory.ParseFileFactory;
import com.mdl.zhaopin.handler.read.ReadFile;
import com.mdl.zhaopin.handler.read.impl.ReadWord;

public class ParseWord extends ParseFileFactory {

	private String filePath;

	public ParseWord(String filePath) {
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
