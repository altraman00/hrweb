package com.mdl.zhaopin.handler.hanlp.parse;


import com.mdl.zhaopin.handler.hanlp.parse.HanlpPraseResume;
import com.mdl.zhaopin.handler.hanlp.read.ReadFile;
import com.mdl.zhaopin.handler.hanlp.read.impl.ReadHtml;

public class TurnHtmlToText extends HanlpPraseResume {

    private String filePath;

    public TurnHtmlToText(String filePath) {
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
