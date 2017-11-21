package codeGenerate.factory;

import java.util.Map;

import org.apache.velocity.VelocityContext;

import codeGenerate.def.CodeResourceUtil;
import codeGenerate.util.CommonPageParser;
import codeGenerate.util.CreateBean;

public class CodeGenerateFactory {
	private static String url = CodeResourceUtil.URL;
	private static String username = CodeResourceUtil.USERNAME;
	private static String passWord = CodeResourceUtil.PASSWORD;
	private static String buss_package = CodeResourceUtil.bussiPackage;
	private static String projectPath = getProjectPath();
	
	public static void codeGenerate(String codeName, String interfaceName) {
		String srcPath = projectPath + CodeResourceUtil.source_root_package + "\\";
		String pckPath = srcPath + CodeResourceUtil.bussiPackageUrl + "\\";
		VelocityContext context = new VelocityContext();
		context.put("codeName", codeName);
		context.put("bussPackage", buss_package);
		context.put("codeName", codeName);
		if(interfaceName!=null && interfaceName.length()>0){
			context.put("servicePackage", buss_package+"."+interfaceName);
			context.put("interfaceName", interfaceName);
			String interfaceNames=interfaceName;
			String a3 = interfaceName.substring(0,1);
			interfaceName = a3.toUpperCase()+interfaceName.substring(1);
			context.put("interfaceName1", interfaceName);
			String inPath = interfaceNames+"\\bean\\" + interfaceName + "In.java";
			CommonPageParser.WriterPage(context, "inTemplate.ftl", pckPath, inPath);
			
			String outPath = interfaceNames+"\\bean\\" + interfaceName + "Out.java";
			CommonPageParser.WriterPage(context, "outTemplate.ftl", pckPath, outPath);
			
			String daoPath = interfaceNames+"\\dao\\" + interfaceName + "Dao.java";
			CommonPageParser.WriterPage(context, "DaoTTemplate.ftl", pckPath, daoPath);
			
			String xmlPath = interfaceNames+"\\dao\\" + interfaceName + "Mapper.xml";
			CommonPageParser.WriterPage(context, "xmlTemplate.xml", pckPath, xmlPath);
			
			String serviceImplPath = interfaceNames+"\\service\\impl\\" + interfaceName + "ServiceImpl.java";
			CommonPageParser.WriterPage(context, "ServiceImplTemplate.ftl", pckPath, serviceImplPath);
			
			String servicePath = interfaceNames+"\\service\\" + interfaceName + "Service.java";
			CommonPageParser.WriterPage(context, "ServiceTemplate.ftl", pckPath, servicePath);
			
			String controllerPath = interfaceNames+"\\controller\\" + interfaceName + "Controller.java";
			CommonPageParser.WriterPage(context, "ControllerTemplate.ftl", pckPath, controllerPath);
		}
		
		//CommonPageParser.WriterPage(context, "ControllerTemplate.ftl", pckPath, controllerPath);
		
//		CommonPageParser.WriterPage(context, isFtl ? "ftlTemplate.ftl" : "jspTemplate.ftl", webPath, jspPath);
//		CommonPageParser.WriterPage(context, "jsTemplate.ftl", webPath, jsPath);
		
		System.out.println("----------------------------代码生成完毕---------------------------");
	}
	public static void codeGenerate(String tableName, String codeName, String entityPackage, String keyType, String pageFolder,
			boolean isFtl, String interfaceName) {
	
		if (null == pageFolder || "".equals(pageFolder)) {
			pageFolder = "view";
		}
		
		CreateBean createBean = new CreateBean();
		createBean.setMysqlInfo(url, username, passWord);
		
		String className = createBean.getTablesNameToClassName(tableName);
		String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());
		
		String srcPath = projectPath + CodeResourceUtil.source_root_package + "\\";
		
		String pckPath = srcPath + CodeResourceUtil.bussiPackageUrl + "\\";
		
		String webPath = projectPath + CodeResourceUtil.web_root_package + "\\" + pageFolder + "\\" + CodeResourceUtil.bussiPackageUrl
				+ "\\";
		if(entityPackage!=null && !entityPackage.equals("")){
			entityPackage=entityPackage + "\\";
		}else{
			entityPackage="";
		}
		String modelPath = "entity\\" + entityPackage  + className + "Page.java";
		String beanPath ="entity\\" + entityPackage + className + ".java";
		String mapperPath = "dao\\" + entityPackage + className + "Dao.java";
		//founder.mobile.aisports.phone.rest.in.login.rest
		String sqlMapperPath = "dao\\" + entityPackage + className + "Mapper.xml";
//		webPath = webPath + entityPackage + "\\";
//		
//		String jspPath = lowerName + (isFtl ? ".ftl" : ".jsp");
//		String jsPath = "page-" + lowerName + ".js";
		
		VelocityContext context = new VelocityContext();
		context.put("className", className);
		context.put("lowerName", lowerName);
		context.put("codeName", codeName);
		context.put("tableName", tableName);
		context.put("bussPackage", buss_package);
		context.put("entityPackage", entityPackage);
		context.put("keyType", keyType);
		context.put("codeName", codeName);
		
		try {
			context.put("feilds", createBean.getBeanFeilds(tableName));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Map sqlMap = createBean.getAutoCreateSql(tableName);
			context.put("columnDatas", createBean.getColumnDatas(tableName));
			context.put("SQL", sqlMap);
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		CommonPageParser.WriterPage(context, "MapperTemplate.xml", pckPath, sqlMapperPath);
		CommonPageParser.WriterPage(context, "EntityTemplate.ftl", pckPath, beanPath);
		CommonPageParser.WriterPage(context, "PageTemplate.ftl", pckPath, modelPath);
		CommonPageParser.WriterPage(context, "DaoTemplate.ftl", pckPath, mapperPath);
		if(interfaceName!=null && interfaceName.length()>0){
			context.put("servicePackage", buss_package+"."+interfaceName);
			context.put("interfaceName", interfaceName);
			String interfaceNames=interfaceName;
			String a3 = interfaceName.substring(0,1);
			interfaceName = a3.toUpperCase()+interfaceName.substring(1);
			context.put("interfaceName1", interfaceName);
			String servicePath = interfaceNames+"\\service\\In" + entityPackage  + interfaceName + "Service.java";
			CommonPageParser.WriterPage(context, "ServiceTemplate.ftl", pckPath, servicePath);
			String controllerPath = interfaceNames+"\\rest\\In" + interfaceName + "Controller.java";
			CommonPageParser.WriterPage(context, "ControllerTemplate.ftl", pckPath, controllerPath);
		}
		//CommonPageParser.WriterPage(context, "ControllerTemplate.ftl", pckPath, controllerPath);
		
//		CommonPageParser.WriterPage(context, isFtl ? "ftlTemplate.ftl" : "jspTemplate.ftl", webPath, jspPath);
//		CommonPageParser.WriterPage(context, "jsTemplate.ftl", webPath, jsPath);
		
		System.out.println("----------------------------代码生成完毕---------------------------");
	}
	
	public static String getProjectPath() {
	
		String path = System.getProperty("user.dir").replace("\\", "/") + "/";
		return path;
	}
}