package com.sunlands.hr.myutils.sceltotxt;

import java.io.ByteArrayOutputStream;  
import java.io.DataInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.LinkedHashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Set;  
import java.util.logging.Level;  
import java.util.logging.Logger; 

public class SougouScelFileProcessing extends FileProcessing{
	private static final Logger log = Logger.getLogger("SougouScelFileProcessing");  
    protected static String encoding = "UTF-16LE";  
    protected ByteArrayOutputStream output = new ByteArrayOutputStream();  
  
    /** 
     * 解析单个或者多个文件，如果是多个文件则生成对应的txt文件，{@link #setTargetDir(String)}， 
     * 如果targetDir不设置，则在当前文件夹下生成相应的txt文件 
     *  
     * @param filePath 
     *            源文件路径 
     * @param isAppend 
     *            false：覆盖内容 true：附加内容 
     */  
    public void parseFile(String filePath, boolean isAppend) {  
        File file = new File(filePath);  
        if (file.isDirectory()) {  
            File items[] = file.listFiles();  
            for (int i = 0; i < items.length; i++) {  
                if (!items[i].getName().endsWith(".scel")) {  
                    continue;  
                }  
  
                if (targetDir == null) {  
                    parseFile(items[i].getAbsolutePath(), items[i].getAbsolutePath().replace(".scel", ".txt"),  
                            isAppend);  
                } else {  
                    parseFile(items[i].getAbsolutePath(), targetDir + "/" + items[i].getName().replace(".scel", ".txt"),  
                            isAppend);  
                }  
  
            }  
        } else {  
            parseFile(filePath, file.getAbsolutePath().replace(".scel", ".txt"), isAppend);  
        }  
  
    }  
  
    /** 
     * 解析单个scel文件 
     *  
     * @param filePath 
     *            源文件路径 
     * @param targetFilePath目标文件路径 
     * @param isAppend 
     *            false：覆盖内容 true：附加内容 
     */  
    public void parseFile(String filePath, String targetFilePath, boolean isAppend) {  
        if (!targetFilePath.endsWith(".txt")) {  
            throw new IllegalStateException("文件格式错误，后缀必须为.txt，此格式为   " + targetFilePath);  
        }  
        if (!filePath.endsWith(".scel")) {  
            return;  
        }  
        File input = new File(filePath);  
        if (input.length() < 8) {  
            // 假如文件小于8字节，不去考虑它  
            return;  
        }  
        FileInputStream in = null;  
        SougouScelModel model = null;  
        try {  
            in = new FileInputStream(input);  
            model = read(in);  
            if (model == null) {  
                return;  
            }  
            writeToTargetFile(model, targetFilePath, isAppend);  
        } catch (IOException e) {  
            log.log(Level.SEVERE, e.getMessage());  
            e.printStackTrace();  
        }  
  
    }  
  
    /** 
     * 解析多个文件夹，将解析后的内容放到一个文件里 
     *  
     * @param fileDirPath 
     *            源文件夹路径 
     * @param targetFilePath 
     *            目标文件路径 
     * @param isAppend 
     *            false：覆盖内容 true：附加内容 
     * @throws FileNotFoundException 
     */  
    public void parseFiles(String fileDirPath, String targetFilePath, boolean isAppend) throws IOException {  
        if (!targetFilePath.endsWith(".txt")) {  
            throw new IllegalStateException("文件格式错误，后缀必须为.txt，此格式为   " + targetFilePath);  
        }  
        File dir = new File(fileDirPath);  
        if (!dir.exists() || !dir.isDirectory()) {  
            throw new IllegalStateException("scel文件夹路径错误   " + targetFilePath);  
        }  
        File scels[] = dir.listFiles();  
        ArrayList<SougouScelModel> models = new ArrayList<>();  
        for (int i = 0; i < scels.length; i++) {  
            if (!scels[i].getName().endsWith(".scel")) {  
                continue;  
            }  
            FileInputStream in = null;  
            SougouScelModel model = null;  
            in = new FileInputStream(scels[i]);  
            model = read(in);  
            if (model != null) {  
                models.add(model);  
            }  
        }  
        writeToTargetFile(models, targetFilePath, isAppend);  
    }  
  
    private void writeToTargetFile(SougouScelModel model, String targetFilePath, boolean isAppend) throws IOException {  
        List<SougouScelModel> models = new ArrayList<>();  
        models.add(model);  
        writeToTargetFile(models, targetFilePath, isAppend);  
  
    }  
  
    /** 
     * 将搜狗scel文件解析后的内容写入txt文件 
     *  
     * @param models 
     * @param targetFilePath 
     * @param isAppend 
     * @throws IOException 
     */  
    private void writeToTargetFile(List<SougouScelModel> models, String targetFilePath, boolean isAppend)  
            throws IOException {  
        createParentDir(targetFilePath);  
        FileOutputStream out = new FileOutputStream(targetFilePath, isAppend);  
        int count = 0;  
        for (int k = 0; k < models.size(); k++) {  
            Map<String, List<String>> words = models.get(k).getWordMap(); // 词<拼音,词>  
            Set<Entry<String, List<String>>> set = words.entrySet();  
            Iterator<Entry<String, List<String>>> iter = set.iterator();  
            if (isAppend) {  
                out.write("\r\n".getBytes());  
            }  
            while (iter.hasNext()) {  
                Entry<String, List<String>> entry = iter.next();  
                List<String> list = entry.getValue();  
  
                int size = list.size();  
                for (int i = 0; i < size; i++) {  
                    String word = list.get(i);  
                    out.write((entry.getKey() + " ").getBytes());  
                    out.write((word + "\n").getBytes());// 写入txt文件  
                    count++;  
  
                }  
            }  
  
        }  
        out.close();  
        log.info("生成" + targetFilePath.substring(targetFilePath.lastIndexOf("/") + 1) + "成功！,总计写入: " + count + " 条数据！");  
  
    }  
  
    private SougouScelModel read(InputStream in) {  
        SougouScelModel model = new SougouScelModel();  
        DataInputStream input = new DataInputStream(in);  
        int read;  
        try {  
            byte[] bytes = new byte[4];  
            input.readFully(bytes);  
            assert (bytes[0] == 0x40 && bytes[1] == 0x15 && bytes[2] == 0 && bytes[3] == 0);  
            input.readFully(bytes);  
            int flag1 = bytes[0];  
            assert (bytes[1] == 0x43 && bytes[2] == 0x53 && bytes[3] == 0x01);  
            int[] reads = new int[] { 8 };  
            model.setName(readString(input, 0x130, reads));  
            model.setType(readString(input, 0x338, reads));  
            model.setDescription(readString(input, 0x540, reads));  
            model.setSample(readString(input, 0xd40, reads));  
            read = reads[0];  
            input.skip(0x1540 - read);  
            read = 0x1540;  
            input.readFully(bytes);  
            read += 4;  
            assert (bytes[0] == (byte) 0x9D && bytes[1] == 0x01 && bytes[2] == 0 && bytes[3] == 0);  
            bytes = new byte[128];  
            Map<Integer, String> pyMap = new LinkedHashMap<Integer, String>();  
            while (true) {  
                int mark = readUnsignedShort(input);  
                int size = input.readUnsignedByte();  
                input.skip(1);  
                read += 4;  
                assert (size > 0 && (size % 2) == 0);  
                input.readFully(bytes, 0, size);  
                read += size;  
                String py = new String(bytes, 0, size, encoding);  
                // System.out.println(py);  
                pyMap.put(mark, py);  
                if ("zuo".equals(py)) {  
                    break;  
                }  
            }  
            if (flag1 == 0x44) {  
                input.skip(0x2628 - read);  
            } else if (flag1 == 0x45) {  
                input.skip(0x26C4 - read);  
            } else {  
                throw new RuntimeException("出现意外，联系作者");  
            }  
            StringBuffer buffer = new StringBuffer();  
            Map<String, List<String>> wordMap = new LinkedHashMap<String, List<String>>();  
            while (true) {  
                int size = readUnsignedShort(input);  
                if (size < 0) {  
                    break;  
                }  
                int count = readUnsignedShort(input);  
                int len = count / 2;  
                assert (len * 2 == count);  
                buffer.setLength(0);  
                for (int i = 0; i < len; i++) {  
                    int key = readUnsignedShort(input);  
                    buffer.append(pyMap.get(key)).append("'");  
                }  
                buffer.setLength(buffer.length() - 1);  
                String py = buffer.toString();  
                List<String> list = wordMap.get(py);  
                if (list == null) {  
                    list = new ArrayList<String>();  
                    wordMap.put(py, list);  
                }  
                for (int i = 0; i < size; i++) {  
                    count = readUnsignedShort(input);  
                    if (count > bytes.length) {  
                        bytes = new byte[count];  
                    }  
                    input.readFully(bytes, 0, count);  
                    String word = new String(bytes, 0, count, encoding);  
                    // 接下来12个字节可能是词频或者类似信息  
                    input.skip(12);  
                    list.add(word);  
                }  
            }  
            model.setWordMap(wordMap);  
            return model;  
        } catch (IOException e) {  
            log.log(Level.SEVERE, e.getMessage());  
            e.printStackTrace();  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
  
    protected String readString(DataInputStream input, int pos, int[] reads) throws IOException {  
        int read = reads[0];  
        input.skip(pos - read);  
        read = pos;  
        output.reset();  
        while (true) {  
            int c1 = input.read();  
            int c2 = input.read();  
            read += 2;  
            if (c1 == 0 && c2 == 0) {  
                break;  
            } else {  
                output.write(c1);  
                output.write(c2);  
            }  
        }  
        reads[0] = read;  
        return new String(output.toByteArray(), encoding);  
    }  
  
    protected final int readUnsignedShort(InputStream in) throws IOException {  
        int ch1 = in.read();  
        int ch2 = in.read();  
        if ((ch1 | ch2) < 0) {  
            return Integer.MIN_VALUE;  
        }  
        return (ch2 << 8) + (ch1 << 0);  
    }  
}
