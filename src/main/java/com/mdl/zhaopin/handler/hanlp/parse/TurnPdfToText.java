package com.mdl.zhaopin.handler.hanlp.parse;

import com.mdl.zhaopin.handler.hanlp.read.ReadFile;
import com.mdl.zhaopin.handler.hanlp.read.impl.ReadPDF;

public class TurnPdfToText extends HanlpPraseResume {

	private String filePath;

	public TurnPdfToText(String filePath) {
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
