package com.mdl.zhaopin.controller;

import com.mdl.zhaopin.service.ParseResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : reusme-parse
 * @Package Name : com.mdl.zhaopin.controller
 * @Description : TODO
 * @Author : xiekun
 * @Create Date : 2020年04月21日 10:45
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */

@RestController
@RequestMapping("/open")
public class OpenController {

    private static final Logger logger = LoggerFactory.getLogger(OpenController.class);

    @Autowired
    private ParseResumeService parseResumeService;

    @GetMapping("/hello")
    public String hello(){

        logger.debug("saveResume-->saveResume-->saveResume-->saveResume-->11111");
        logger.info("saveResume-->saveResume-->saveResume-->saveResume-->22222");
        logger.trace("saveResume-->saveResume-->saveResume-->saveResume-->33333");
        logger.error("saveResume-->saveResume-->saveResume-->saveResume-->44444");

        return "hello";
    }

}
