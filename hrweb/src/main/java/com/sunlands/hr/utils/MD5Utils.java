package com.sunlands.hr.utils;

import java.security.MessageDigest;

public class MD5Utils {
	public static String toMD5(String inStr) {

		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(inStr.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(i));
			}
		} catch (Exception ex) {
		}
		return sb.toString();

	}

	public static void main(String[] args) throws Exception {
//		System.out.println(toMD5("das34").equals("76218bca4041f85db438054b87268301"));
		System.out.println(toMD5("123456"));
		
	}

}
