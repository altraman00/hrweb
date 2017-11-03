package com.sunlands.hr.myutils.sceltotxt;

import java.io.IOException;

public class Demo {

	public static void main(String[] args) {
//		//单个scel文件转化，转化之后的格式为 pinying 拼音  , SougouScelFileProcessing 解析的源文件必须为.scel文件类型，解析后的文件必须为.txt文件类型，参数以绝对路径进行传递
//        FileProcessing scel_to_txt = new SougouScelFileProcessing();  
//        scel_to_txt.parseFile("C:\\Users\\xiekun\\Desktop\\file\\scel_to_txt\\scel\\university\\211院校名单.scel", "C:\\Users\\xiekun\\Desktop\\file\\scel_to_txt\\txt\\中国高等院校大全.txt", true);  
        

		
//		//多个scel文件转化为一个txt (格式：拼音字母 词) 
//		FileProcessing scel_to_txt = new SougouScelFileProcessing();  
//        try {  
//        	scel_to_txt.parseFiles("C:\\Users\\xiekun\\Desktop\\file\\scel_to_txt\\scel\\university", "C:\\Users\\xiekun\\Desktop\\file\\scel_to_txt\\txt\\中国高等院校大全.txt", false);  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
		
        
		
		//将txt中的拼音去除，xtFileProcessing , 解析的源文件必须为.txt，解析后的文件必须为.txt文件类型，参数以绝对路径进行传递
		FileProcessing txt_kill_pingyin = new TxtFileProcessing();
		txt_kill_pingyin.parseFile("C:\\Users\\xiekun\\Desktop\\file\\scel_to_txt\\txt\\中国高等院校大全.txt", "C:\\Users\\xiekun\\Desktop\\file\\scel_to_txt\\txt\\zw\\中国高等院校大全_zw.txt", true);  
  
        
		
//        //多个scel文件转化为多个txt文件  
//        scel.setTargetDir("/Users/ST_iOS/Desktop/test/ciku/多对多");//转化后文件的存储位置  
//        scel.parseFile("/Users/ST_iOS/Desktop/test/ciku",false); 

	}
	

}
