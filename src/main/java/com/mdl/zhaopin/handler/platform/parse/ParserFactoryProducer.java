package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.parse.file.FactoryResumeParserFile;
import com.mdl.zhaopin.handler.platform.parse.plug.FactoryResumeParserPlug;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 12:51
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class ParserFactoryProducer {

    public static IParseFactory getFactory(String type) {
        IParseFactory parserFactory = null;
        if (type.equals("plug")) {
            parserFactory = new FactoryResumeParserPlug();
        } else if (type.equals("file")) {
            parserFactory = new FactoryResumeParserFile();
        }
        return parserFactory;
    }

}
