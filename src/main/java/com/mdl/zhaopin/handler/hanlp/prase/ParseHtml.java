package com.mdl.zhaopin.handler.hanlp.prase;


import com.mdl.zhaopin.handler.hanlp.factory.ParseFileFactory;
import com.mdl.zhaopin.handler.read.ReadFile;
import com.mdl.zhaopin.handler.read.impl.ReadHtml;

public class ParseHtml extends ParseFileFactory {

    private String filePath;

    public ParseHtml(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String readFile() {

        ReadFile readHtml = new ReadHtml();

        String read = readHtml.read(filePath);

        super.separateStr = read;

        return read;

    }

}
