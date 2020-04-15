package com.mdl.zhaopin.handler.platform.parse;

import com.mdl.zhaopin.handler.platform.resume.Resume;

import java.io.File;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.handler.platform
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月14日 17:37
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface ResumeParser {

    String getName();

    boolean canParse(File file);

    Resume parse(File file) throws Exception;

}
