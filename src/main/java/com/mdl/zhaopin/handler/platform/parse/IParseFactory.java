package com.mdl.zhaopin.handler.platform.parse;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.handler.platform.parse
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 12:44
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface IParseFactory {

    IResumeParser parseResume(String platform, String resumeUrl);

}
