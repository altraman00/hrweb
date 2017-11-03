package com.sunlands.hr;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sunlands.hr.servers.CreateResumeStr;
import com.sunlands.hr.utils.file.Txt;

public class MainTest {
	
	/**
	 * 测试分析效果123456987
	 * 123 789
	 * 
	 * 测试2
	 * 
	 * 测试3
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:hrweb-servlet.xml");
		
		CreateResumeStr createResumeStr = ctx.getBean(CreateResumeStr.class);
		
		String relativelyPath=System.getProperty("user.dir");
		
		System.out.println(relativelyPath);
		
//		ArrayList<String> list = Txt.getFile("C:\\Users\\xiekun\\Desktop\\file\\txt");//文件放在本地
		
		ArrayList<String> list = Txt.getFile(relativelyPath + "\\src\\main\\resumefiles");//文件放在项目中

		for (String file : list) {
			
			System.out.println(file.length());
			
			createResumeStr.getFileContent(file);
		}
	}

	

}
