package com.mdl.zhaopin.service;

import com.mdl.zhaopin.DTO.ResumeObjDTO;

/**
 * @Project : resume-parse
 * @Package Name : com.mdl.zhaopin.service.impl
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月23日 11:05
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public interface PlugParseResumeService {

    /**
     * 解析插件获取的html中的简历信息
     * @param html
     * @return
     */
    ResumeObjDTO getResumeInfo(String html);

}
