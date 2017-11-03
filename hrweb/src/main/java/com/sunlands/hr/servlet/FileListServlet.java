package com.sunlands.hr.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/FileListServlet",loadOnStartup = 1) 
public class FileListServlet extends HttpServlet {
    
    private ServletContext sc;
    private String savePath;
    
    private static final long serialVersionUID = 151650843430214502L;

    public FileListServlet() {  
        super();  
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);  
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        savePath = config.getInitParameter("savePath");
        sc = config.getServletContext();        
    }
    
}