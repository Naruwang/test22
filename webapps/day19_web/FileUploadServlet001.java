package com.itheim.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 需求: 提交表单打印普通项的name属性值和value属性值. 头像上传到G:/developer7/upload目录下.
 *
 * 步骤:
 *  1.创建磁盘项工厂对象 DiskFileItemFactory
 *  2.创建核心解析类 ServletFileUpload (传递 磁盘项工厂对象)
 *  3.核心解析类解析request对象,调用parseRequest(request)===> List<FileItem>
 *  4.循环List<FileItem>
 *      4.1判断是否为普通项  isFormFiled  true
 *          获取name属性值getFormFiled()    获取value值  getString()
 *      4.2判断如果是上传项
 *          获取源文件名称  getName();
 *          获取输入流 (流中有上传项的数据)
 *          创建一个输出流
 *          io的读写操作
 *          关闭资源
 */
@WebServlet("/FileUploadServlet01")
public class FileUploadServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 1.创建磁盘项工厂对象 DiskFileItemFactory
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2.创建核心解析类 ServletFileUpload (传递 磁盘项工厂对象)
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 3.核心解析类解析request对象,调用parseRequest(request)===> List<FileItem>
            List<FileItem> list = upload.parseRequest(request);
            // 4.循环List<FileItem>
            for (FileItem item : list) {
                // 4.1判断是否为普通项  isFormFiled  true
                if(item.isFormField()){
                    //  获取name属性值getFormFiled()    获取value值  getString()
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    System.out.println(fieldName + " , "+value);
                }else{
                    //获取上传项文件的名称打印
                    String fileName = item.getName();
                    System.out.println(fileName);
                    //把文件通过io写到G:developer/upload目录下
                    //获取输入流 (流中有上传项的数据)
                    InputStream inputStream = item.getInputStream();
                    //创建一个输出流
                    FileOutputStream outputStream =
                            new FileOutputStream(new File("G:\\developer7/upload/"+fileName));
                     //io的读写操作
                    int len = 0;
                    byte[] arr = new byte[1024<<2];  // 1024*2^2
                    while ((len = inputStream.read(arr)) != -1){
                        outputStream.write(arr,0,len);
                    }
                    //关闭资源
                    outputStream.close();
                    inputStream.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
