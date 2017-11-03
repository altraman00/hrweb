package com.sunlands.hr.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {
    
    private ServletContext sc;
    private String savePath;
    private static final long serialVersionUID = 151650843430214502L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
    
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);//通过工厂生成一个处理文件上传的servlet对象
        
        try {
            List items = servletFileUpload.parseRequest(request);//解析request
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                if(item.isFormField()){//表单的参数字段
                    System.out.println("表单的参数名称："+item.getFieldName()+",表单的参数值："+item.getString("UTF-8"));
                }else {
                    if(item.getName()!=null && !item.getName().equals("")){//一个上传的文件
                        System.out.println("文件的名称："+item.getName());
                        System.out.println("文件的大小："+item.getSize());
                        System.out.println("文件的类型："+item.getContentType());
                        
                        File tempFile = new File(item.getName());//getName得到的文件名称包含了它在客户端的路径
//                        File file = new File(sc.getRealPath("/")+savePath,tempFile.getName());
                        File file = new File("C:\\Users\\xiekun\\Desktop\\file\\upload",tempFile.getName());
                        item.write(file);//将上传的文件写入到file中
                        
                        request.setAttribute("message", "上传文件成功！");
                    }else{
                        request.setAttribute("message", "没有选择上传文件！");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件失败！");
        }
        
        request.getRequestDispatcher("/page/fileUploadResult.jsp").forward(request, response);//转向，输出结果
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        savePath = config.getInitParameter("savePath");
        sc = config.getServletContext();        
    }
    
}