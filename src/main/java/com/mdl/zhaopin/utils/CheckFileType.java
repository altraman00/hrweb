package com.mdl.zhaopin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CheckFileType {

    private final static Logger logger = LoggerFactory.getLogger(CheckFileType.class);

    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    private CheckFileType() {
    }

    static {
        getAllFileType(); // 初始化文件类型信息
    }

    /**
     * Discription:[getAllFileType,常见文件头信息]
     */
    private static void getAllFileType() {
        FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); // JPEG (jpg)
        FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); // PNG (png)
        FILE_TYPE_MAP.put("47494638396126026f01", "gif"); // GIF (gif)
        FILE_TYPE_MAP.put("49492a00227105008037", "tif"); // TIFF (tif)
        FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); // 16色位图(bmp)
        FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); // 24位位图(bmp)
        FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); // 256色位图(bmp)
        FILE_TYPE_MAP.put("41433130313500000000", "dwg");  // CAD (dwg)
        FILE_TYPE_MAP.put("3c21444f435459504520", "html"); // HTML (html)
        FILE_TYPE_MAP.put("3c21646f637479706520", "htm");  // HTM (htm)
        FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css");  // css
        FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js");   // js
        FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf");  // Rich Text Format
        // (rtf)
        FILE_TYPE_MAP.put("38425053000100000000", "psd");  // Photoshop (psd)
        FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml");  // Email [Outlook
        // Express 6] (eml)
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc");  // MS Excel
        // 注意：word、msi 和
        // excel的文件头一样
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "vsd");   // Visio 绘图
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb");   // MS Access (mdb)
        FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
        FILE_TYPE_MAP.put("255044462d312e350d0a", "pdf");  // Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("255044462d312e340a25", "pdf");  // (经过其他格式转换成的pdf)
        FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); // rmvb/rm相同
        FILE_TYPE_MAP.put("464c5601050000000900", "flv");  // flv与f4v相同
        FILE_TYPE_MAP.put("00000020667479706d70", "mp4");
        FILE_TYPE_MAP.put("49443303000000002176", "mp3");
        FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //
        FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); // wmv与asf相同
        FILE_TYPE_MAP.put("52494646e27807005741", "wav"); // Wave (wav)
        FILE_TYPE_MAP.put("52494646d07d60074156", "avi");
        FILE_TYPE_MAP.put("4d546864000000060001", "mid"); // MIDI (mid)
        FILE_TYPE_MAP.put("504b0304140000000800", "zip");
        FILE_TYPE_MAP.put("526172211a0700cf9073", "rar");
        FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
        FILE_TYPE_MAP.put("504b03040a0000000000", "jar");
        FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");// 可执行文件
        FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");// jsp文件
        FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");// MF文件
        FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");// xml文件
        FILE_TYPE_MAP.put("494e5345525420494e54", "sql");// xml文件
        FILE_TYPE_MAP.put("7061636b616765207765", "java");// java文件
        FILE_TYPE_MAP.put("406563686f206f66660d", "bat");// bat文件
        FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");// gz文件
        FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");// bat文件
        FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");// bat文件
        FILE_TYPE_MAP.put("49545346030000006000", "chm");// bat文件
        FILE_TYPE_MAP.put("04000000010000001300", "mxp");// bat文件
        FILE_TYPE_MAP.put("504b0304140006000800", "docx");// docx文件
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");// WPS文字wps、表格et、演示dps都是一样的
        FILE_TYPE_MAP.put("6431303a637265617465", "torrent");

        FILE_TYPE_MAP.put("3c68746d6c20786d6c6e", "htm");// 猎聘、智联简历。htm
        FILE_TYPE_MAP.put("46726f6d3a3cd3c920cd", "mht");// 51job简历。mht

        FILE_TYPE_MAP.put("6D6F6F76", "mov"); // Quicktime (mov)
        FILE_TYPE_MAP.put("FF575043", "wpd"); // WordPerfect (wpd)
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); // Outlook Express (dbx)
        FILE_TYPE_MAP.put("2142444E", "pst"); // Outlook (pst)
        FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); // Quicken (qdf)
        FILE_TYPE_MAP.put("E3828596", "pwl"); // Windows Password (pwl)
        FILE_TYPE_MAP.put("2E7261FD", "ram"); // Real Audio (ram)
    }

    /**
     * 得到上传文件的文件头
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据制定文件的文件头判断其文件类型
     *
     * @param filePaht
     * @return
     */
    public static String getFileType(String filePaht) {
        String res = null;
        logger.info("简历真实路径2：{}",filePaht);
        try {
            FileInputStream is = new FileInputStream(filePaht);
            byte[] b10 = new byte[10];
            is.read(b10, 0, b10.length);
            String fileCode10 = bytesToHexString(b10);
            //取10位可能取不到，备用4位
            byte[] b4 = Arrays.copyOfRange(b10, 0, 4);
            String fileCode4 = bytesToHexString(b4);

            if (logger.isDebugEnabled()) {
                logger.debug("上传文件的文件头 str10:{}, str4:{}, code：{}", new String(b10), new String(b4), fileCode10);
            }

            // 这种方法在字典的头代码不够位数的时候可以用但是速度相对慢一点
            Iterator<String> keyIter = FILE_TYPE_MAP.keySet().iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                if (key.toLowerCase().startsWith(fileCode10.toLowerCase())
                        || fileCode10.toLowerCase().startsWith(key.toLowerCase())
                        || key.toUpperCase().startsWith(fileCode4.toLowerCase())) {
                    res = FILE_TYPE_MAP.get(key);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
           logger.error("getFileType",e);
        } catch (IOException e) {
            logger.error("getFileType",e);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {

//		String type = getFileType("C:\\Users\\xiekun\\Desktop\\file\\pdf\\1514460703492_l5R0p_赵权（简历）.pdf");
        String type = getFileType("C:\\Users\\xiekun\\Desktop\\file\\doc\\51job_白雪(899259695).doc");
//        System.out.println("Except : " + type);
//        System.out.println();

        /*
         * type = getFileType("C:/test/350996.wav");
         * System.out.println("350996.wav : "+type); System.out.println();
         */

    }
}
