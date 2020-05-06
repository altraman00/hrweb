package com.mdl.zhaopin.handler.hanlp.parse;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.hanlp.prase
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 16:33
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class StrategyNLPParseFile {

    HanlpPraseResume hanlpPraseResume;


    public void setParseFileStrategy(HanlpPraseResume hanlpPraseResume) {
        this.hanlpPraseResume = hanlpPraseResume;
    }

    public HanlpPraseResume readFile(){
        hanlpPraseResume.readFile();
        return hanlpPraseResume;
    }

}
