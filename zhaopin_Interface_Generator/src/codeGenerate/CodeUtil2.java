package codeGenerate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import codeGenerate.def.FtlDef;
import codeGenerate.factory.CodeGenerateFactory;
/**
 * 暂时没有使用
 */
public class CodeUtil2 {
	public static void main(String[] args) {
		/** 此处修改成你的 表名 和 中文注释 ***/
		String tableName = "m_arena"; //
		String interfaceName = null;// 接口名称 如果为
		String codeName = "场馆";// 注释
		String keyType = FtlDef.KEY_TYPE_01;// 01 String; 02 int
		CodeGenerateFactory.codeGenerate(tableName, codeName, "", keyType, "pages", true, interfaceName);
	}
	public static void main2(String[] args) {
		readTxtFile("E:/1.txt");
	}

	/**
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static void readTxtFile(String filePath) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int i=1;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
					i++;
					if(i==70){
						System.out.println("---------");
					}
					String[] aa=lineTxt.split(",");
					/** 此处修改成你的 表名 和 中文注释 ***/
					String tableName = aa[0]; //
					String codeName = aa[1];// 注释
					String keyType = FtlDef.KEY_TYPE_01;// 01 String; 02 int
					CodeGenerateFactory.codeGenerate(tableName, codeName, "", keyType, "pages", true, null);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
}