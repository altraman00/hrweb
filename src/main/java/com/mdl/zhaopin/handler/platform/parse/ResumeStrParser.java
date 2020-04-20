package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Resume;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:37
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface ResumeStrParser {

    String getName();

    Resume parse(String resume) throws Exception;

}
