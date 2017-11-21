package codeGenerate.util;

import codeGenerate.factory.CodeGenerateFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class CommonPageParser {
	
	private static VelocityEngine ve;
	private static final String CONTENT_ENCODING = "UTF-8";
	private static boolean isReplace = true;
	
	static {
		try {
			String templateBasePath = CodeGenerateFactory.getProjectPath() + "/resources/mdl/template";
			Properties properties = new Properties();
			properties.setProperty("resource.loader", "file");
			properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
			properties.setProperty("file.resource.loader.path", templateBasePath);
			properties.setProperty("file.resource.loader.cache", "true");
			properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
			properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
			properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
			properties.setProperty("directive.set.null.allowed", "true");
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init(properties);
			ve = velocityEngine;
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void WriterPage(VelocityContext context, String templateName, String fileDirPath, String targetFile) {
	
		File file;
		try {
			file = new File(fileDirPath + targetFile);
			if (!(file.exists())) {
				new File(file.getParent()).mkdirs();
			}
			else if (isReplace) {
				System.out.println("替换文件:" + file.getAbsolutePath());
			}
			
			Template template = ve.getTemplate(templateName, "UTF-8");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			template.merge(context, writer);
			writer.flush();
			writer.close();
			fos.close();
			System.out.println("生成文件：" + file.getAbsolutePath());
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}