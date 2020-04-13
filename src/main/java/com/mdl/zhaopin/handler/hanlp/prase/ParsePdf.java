package com.mdl.zhaopin.handler.hanlp.prase;

import com.mdl.zhaopin.handler.hanlp.factory.ParseFileFactory;
import com.mdl.zhaopin.handler.read.ReadFile;
import com.mdl.zhaopin.handler.read.impl.ReadPDF;

public class ParsePdf extends ParseFileFactory {

	private String filePath;

	public ParsePdf(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String readFile() {

		ReadFile readPDF = new ReadPDF();

		String read = readPDF.read(filePath);

		super.separateStr = read;

		return read;
	}

}
