package com.sunlands.hr.myutils.sceltotxt;

import java.io.File;
import java.io.IOException;

public abstract class FileProcessing {
	// 解析后存放的文件夹  
    protected String targetDir;  
  
    /** 
     * 解析单个文件 
     *  
     * @param filePath 
     *            要解析的源文件路径 
     * @param targetFilePath 
     *            解析后的文件路径 
     * @param isAppend 
     *            是否为内容追加，不追加则会覆盖内容 
     */  
    public abstract void parseFile(String filePath, String targetFilePath, boolean isAppend);  
  
    /** 
     * 合并解析多个文件 
     *  
     * @param fileDirPath 
     *            要解析的源文件夹路径 
     * @param targetFilePath 
     *            解析后的文件路径 
     * @param isAppend 
     *            是否为内容追加，不追加则会覆盖内容 
     * @throws IOException  
     */  
    public abstract void parseFiles(String fileDirPath, String targetFilePath, boolean isAppend) throws IOException;  
  
    /** 
     * 解析单个或者多个文件，如果是多个文件则生成对应的txt文件，{@link #setTargetDir(String)}， 
     * 如果targetDir不设置，则在当前文件夹下生成相应的txt文件 
     *  
     * @param filePath 
     *            源文件路径 
     * @param isAppend 
     *            false：覆盖内容 true：附加内容 
     */  
    public abstract void parseFile(String filePath, boolean isAppend);  
      
    /** 
     * 创建文件夹 
     *  
     * @param filePath 
     *            目标文件 
     * @return 
     */  
    protected void createParentDir(String targetFilePath) {  
        if (!targetFilePath.endsWith(".txt")) {  
            throw new IllegalStateException("文件格式错误，后缀必须为.txt，此格式为   " + targetFilePath);  
        }  
        String path = targetFilePath.substring(0, targetFilePath.lastIndexOf("/") + 1);  
        File file = new File(path);  
        if (!file.exists()) {  
            file.mkdirs();  
        }  
    }  
      
    /** 
     * 解析单个文件 
     * @param filePath   文件路径 
     */  
    public void parseFile(String filePath){  
        parseFile(filePath,false);  
    }  
  
    public String getTargetDir() {  
        return targetDir;  
    }  
  
    /** 
     * 解析后的txt文件存放路径 
     *  
     * @param targetDir 
     *            文件夹路径 
     */  
    public void setTargetDir(String targetDir) {  
        this.targetDir = targetDir;  
    }  
  
}
