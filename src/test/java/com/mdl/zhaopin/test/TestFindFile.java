package com.mdl.zhaopin.test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.test
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月10日 09:39
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class TestFindFile {

    //最多搜三层
    private static Integer maxCount = 4;

    private Integer currCount = 0;


    /**
     * 在指定目录中查找包含关键字的文件(或查找后缀名为XXX的文件)，返回包含指定关键字的文件路径.
     * @param folder
     * @param keyword
     * @return
     */
    public List<File> searchFiles(File folder, String keyword) {
        currCount = currCount + 1;

        System.out.println("searchFiles:" + folder.getName());
        List<File> result = new ArrayList<File>();
        if (folder.isFile())
            result.add(folder);

//        File[] subFolders = folder.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                if (file.isDirectory()) {
//                    return true;
//                }
//                if (file.getName().toLowerCase().contains(keyword)) {
//                    return true;
//                }
//                return false;
//            }
//        });

        //查找后缀名
        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                System.out.println("--------------fileName------>" + file.getName());
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().toLowerCase().endsWith(keyword)) {
                    return true;
                }
                return false;
            }
        });

        if (subFolders != null) {
            for (File file : subFolders) {
                String name = file.getName();
                System.out.println(name);
                if(name.endsWith(keyword)){
                    result.add(file);
                }

                if(currCount <= maxCount){
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    searchFiles(file, keyword);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TestFindFile findFile = new TestFindFile();
        List<File> files = findFile.searchFiles(new File("/Users/admin/Documents/workspace/sunlands/zhaopin_cloud/zhaopin_cloud"), ".mvn");
        System.out.println("\n共找到:" + files.size() + "个文件");
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

}
