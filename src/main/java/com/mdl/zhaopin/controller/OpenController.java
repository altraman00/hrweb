package com.mdl.zhaopin.controller;

import com.mdl.zhaopin.DTO.ResumeBaseDTO;
import com.mdl.zhaopin.service.ParseResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

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
    public String hello(HttpServletRequest request, HttpServletResponse response){

        logger.debug("saveResume-->saveResume-->saveResume-->saveResume-->11111");
        logger.info("saveResume-->saveResume-->saveResume-->saveResume-->22222");
        logger.trace("saveResume-->saveResume-->saveResume-->saveResume-->33333");
        logger.error("saveResume-->saveResume-->saveResume-->saveResume-->44444");

        return "hello-resume-parse";

    }

    @PostMapping("/hello2")
    public void hello2(HttpServletRequest request, HttpServletResponse response,@RequestBody ResumeBaseDTO resumeBaseDTO){

        /**
         * 1.获得客户机信息
         */
        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        String requestUri = request.getRequestURI();//得到请求的资源
        String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String remoteUser = request.getRemoteUser();
        String method = request.getMethod();//得到请求URL地址时使用的方法
        String pathInfo = request.getPathInfo();
        String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
        String localName = request.getLocalName();//获取WEB服务器的主机名
        response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write("获取到的客户机信息如下：");
            out.write("<hr/>");
            out.write("请求的URL地址："+requestUrl);
            out.write("<br/>");
            out.write("请求的资源："+requestUri);
            out.write("<br/>");
            out.write("请求的URL地址中附带的参数："+queryString);
            out.write("<br/>");
            out.write("来访者的IP地址："+remoteAddr);
            out.write("<br/>");
            out.write("来访者的主机名："+remoteHost);
            out.write("<br/>");
            out.write("使用的端口号："+remotePort);
            out.write("<br/>");
            out.write("remoteUser："+remoteUser);
            out.write("<br/>");
            out.write("请求使用的方法："+method);
            out.write("<br/>");
            out.write("pathInfo："+pathInfo);
            out.write("<br/>");
            out.write("localAddr："+localAddr);
            out.write("<br/>");
            out.write("localName："+localName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
