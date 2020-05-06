package com.mdl.zhaopin.handler.platform.parse.file;

import com.mdl.zhaopin.handler.platform.parse.IParseFactory;
import com.mdl.zhaopin.handler.platform.parse.IResumeParser;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse.file
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 12:27
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class FactoryResumeParserFile implements IParseFactory {

    @Override
    public IResumeParser parseResume(String platform,String resumeUrl) {
        IResumeParser iResumeParser = null;
//        if (PlatformEnums.ZHI_LIAN.getName().equals(platform)) {
//            iResumeParser = new ResumeParserFileDocZhilian();
//        } else if (PlatformEnums.JOBS.getName().equals(platform)) {
//            iResumeParser = new ResumeParserFileDocJob51();
//        }
        return iResumeParser;
    }
}
