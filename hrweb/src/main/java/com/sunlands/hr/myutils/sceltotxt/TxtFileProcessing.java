package com.sunlands.hr.myutils.sceltotxt;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.util.ArrayList;  
import java.util.HashSet;  
import java.util.List;  
import java.util.logging.Level;  
import java.util.logging.Logger;

public class TxtFileProcessing extends FileProcessing{
	private static final Logger log = Logger.getLogger("TxtFileProcessing");  
    // 文字编码  
    private String encoding = "UTF-8";  
  
    @Override  
    public void parseFile(String filePath, String targetFilePath, boolean isAppend) {  
        if (!targetFilePath.endsWith(".txt")) {  
            throw new IllegalStateException("文件格式错误，后缀必须为.txt，此格式为   " + targetFilePath);  
        }  
        if (!filePath.endsWith(".txt")) {  
            return;  
        }  
        File inputFile = new File(filePath);  
        if (!inputFile.exists()) {  
            log.log(Level.SEVERE,filePath + "   文件不存在");  
        } else {  
            ArrayList<String> content = new ArrayList<>();  
            HashSet<String> set = new HashSet<>();  
            createParentDir(targetFilePath);  
            File outputFile = new File(targetFilePath);  
            if (!isAppend) {  
                // 假如不是附加内容,删除  
                if (outputFile.exists()) {  
                    log.info(outputFile.getAbsolutePath() + "   文件存在，删除...");  
                    outputFile.delete();  
                }  
            } else {  
                // 读取原有的txt文件内容  
                content.addAll(readTargetFile(outputFile));  
            }  
            content.addAll(readSourceFile(inputFile));  
            // 去重  
            for (int i = 0; i < content.size(); i++) {  
                set.add(content.get(i));  
            }  
            // 写入目标文件  
            writeToTargetFile(set, outputFile);  
  
        }  
  
    }  
  
    @Override  
    public void parseFile(String filePath, boolean isAppend) {  
        File file = new File(filePath);  
        if (file.isDirectory()) {  
            File items[] = file.listFiles();  
            for (int i = 0; i < items.length; i++) {  
                if (!items[i].getName().endsWith(".txt")) {  
                    continue;  
                }  
  
                if (targetDir == null) {  
                    parseFile(items[i].getAbsolutePath(), items[i].getAbsolutePath().replace(".txt", "解析.txt"),  
                            isAppend);  
                } else {  
                    parseFile(items[i].getAbsolutePath(), targetDir + "/" + items[i].getName(),  
                            isAppend);  
                }  
  
            }  
        } else {  
            parseFile(filePath, file.getAbsolutePath().replace(".txt", "解析.txt"), isAppend);  
        }  
    }  
      
    @Override  
    public void parseFiles(String fileDirPath, String targetFilePath, boolean isAppend) throws IOException {  
        if (!targetFilePath.endsWith(".txt")) {  
            throw new IllegalStateException("文件格式错误，后缀必须为.txt，此格式为   " + targetFilePath);  
        }  
        File fileDir = new File(fileDirPath);  
        if (!fileDir.isDirectory() || !fileDir.exists()) {  
            throw new IllegalStateException("文件夹路径错误   " + targetFilePath);  
        }  
        File file[] = fileDir.listFiles();  
        ArrayList<String> content = new ArrayList<>();  
        HashSet<String> set = new HashSet<>();  
        createParentDir(targetFilePath);  
        File outputFile = new File(targetFilePath);  
        if (!isAppend) {  
            // 假如不是附加内容,删除  
            if (outputFile.exists()) {  
                log.info(outputFile.getAbsolutePath() + "   文件存在，删除...");  
                outputFile.delete();  
            }  
        } else {  
            // 读取原有的txt文件内容  
            content.addAll(readSourceFile(outputFile));  
        }  
        for (int i = 0; i < file.length; i++) {  
            if (file[i].getName().endsWith(".txt")) {  
                content.addAll(readSourceFile(file[i]));  
            }  
        }  
        // 去重  
        for (int i = 0; i < content.size(); i++) {  
            set.add(content.get(i));  
        }  
        // 写入目标文件  
        writeToTargetFile(set, outputFile);  
  
    }  
  
    /** 
     * 将内容写入目标文件 
     *  
     * @param set 
     *            词库合集 
     * @param outputFile 
     *            目标文件 
     */  
    private void writeToTargetFile(HashSet<String> set, File outputFile) {  
        StringBuffer buff = new StringBuffer();  
        for (String content : set) {  
            buff.append(content);  
            buff.append("\r\n");  
        }  
        String content = buff.toString();  
  
        FileOutputStream out = null;  
        try {  
            out = new FileOutputStream(outputFile);  
            out.write(content.getBytes());  
  
        } catch (IOException e) {  
            log.log(Level.SEVERE, e.getMessage());  
            e.printStackTrace();  
        } finally {  
            try {  
                out.close();  
            } catch (IOException e) {  
                log.log(Level.SEVERE, e.getMessage());  
                e.printStackTrace();  
            }  
        }  
        log.info("生成" + outputFile.getName() + "成功！,总计写入: " + set.size() + " 条数据！");  
    }  
  
      
  
      
  
    /** 
     * 读取源文件，获取中文词库 
     *  
     * @param file 
     *            源文件 
     * @return 中文词库集合 
     */  
    private List<String> readSourceFile(File file) {  
        ArrayList<String> content = new ArrayList<>();  
        try {  
            if (file.isFile() && file.exists()) { // 判断文件是否存在  
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式  
                BufferedReader bufferedReader = new BufferedReader(read);  
                String lineTxt = null;  
  
                while ((lineTxt = bufferedReader.readLine()) != null) {  
                    String newStr = new String(lineTxt.getBytes("UTF-8"));  
                    String split[] = newStr.split(" ");  
                    for (int i = 0; i < split.length; i++) {  
                        if (i % 2 == 0) {  
                            // 拼音字母  
                        } else {  
                            // 中文词库  
                            content.add(split[i]);  
                        }  
                    }  
                }  
                bufferedReader.close();  
                read.close();  
            } else {  
                log.log(Level.SEVERE, "找不到源文件   " + file.getAbsolutePath());  
            }  
        } catch (Exception e) {  
            log.log(Level.SEVERE, e.getMessage());  
            e.printStackTrace();  
        }  
        return content;  
  
    }  
  
    /** 
     * 读取已解析好的的词库文件 
     *  
     * @param file 
     *            词库文件 
     * @return 词库内容 
     */  
    private List<String> readTargetFile(File file) {  
        ArrayList<String> content = new ArrayList<>();  
        try {  
            if (file.isFile() && file.exists()) { // 判断文件是否存在  
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式  
                BufferedReader bufferedReader = new BufferedReader(read);  
                String lineTxt = null;  
  
                while ((lineTxt = bufferedReader.readLine()) != null) {  
                    String newStr = new String(lineTxt.getBytes("UTF-8"));  
                    if (!newStr.trim().isEmpty()) {  
                        content.add(newStr);  
                    }  
                }  
                bufferedReader.close();  
                read.close();  
            } else {  
                System.err.println("找不到目标文件  " + file.getAbsolutePath());  
            }  
        } catch (Exception e) {  
            log.log(Level.SEVERE, e.getMessage());  
            e.printStackTrace();  
        }  
        return content;  
  
    }  
}
