package codeGenerate;

import codeGenerate.factory.CodeGenerateFactory;

public class CodeUtil {
	public static void main(String[] args) {
		/**
		 * 尚德人力资源招聘系统
		 */
		String interfaceName = "resume";//简历
		String codeName = "简历列表";// 注释
		CodeGenerateFactory.codeGenerate(codeName,interfaceName);
	}
}


