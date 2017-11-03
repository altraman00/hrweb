package com.sunlands.hr.hanlp;

import java.util.List;

import com.sunlands.hr.hanlp.factory.AnalysisFileFactory;
import com.sunlands.hr.utils.file.Txt;

public class AnalysisDoc extends AnalysisFileFactory{

	String filePath = null;
	
	public AnalysisDoc(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String readFile() {
		String str = Txt.read(this.filePath);
		super.separateStr = str;
		return separateStr;
	}

	@Override
	public List<String> getProjectList() {
		// TODO Auto-generated method stub
		return null;
	}

}
