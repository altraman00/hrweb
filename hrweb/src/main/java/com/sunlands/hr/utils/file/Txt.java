package com.sunlands.hr.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Txt {

	public static ArrayList<String> getFile(String pathname) {
		File file = new File(pathname);
		File[] arr = file.listFiles();
		ArrayList<String> list = new ArrayList<>();
		
		for (File f : arr) {
//			System.out.println(f.toString());
			list.add(f.toString());
//			read(f.toString());
		}
		return list;
	}

	public static String read(String filePath) {
		String result = null;
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				result = "";
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					result += lineTxt;
				}
				read.close();

			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

//		System.out.println(result);
		
		return result;
	}

}
