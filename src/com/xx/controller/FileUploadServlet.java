package com.xx.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xx.model.Files;
import com.xx.model.User;
import com.xx.service.FileService;
import com.xx.service.UserService;
import com.xx.util.dateUtil;;

public class FileUploadServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");
		String owner = user.getUsername();
		String box = "D:/workspace_jee/git/MVC-market/WebContent/fileupload";//上传文件的存放目录
		String fileName = ""; //文件原名称
		String name = "";
		
		DiskFileItemFactory sf= new DiskFileItemFactory();//实例化磁盘被文件列表工厂
		
		sf.setRepository(new File(box)); //设置文件存放目录
		sf.setSizeThreshold(1024*1024*3); //设置文件上传小于3M
		
		//从工厂得到servletupload文件上传类
		ServletFileUpload sfu = new ServletFileUpload(sf);
		
		try {
			List<FileItem> lst = sfu.parseRequest(request);//得到request中所有的元素
			for (FileItem fileItem : lst) {
				if(fileItem.isFormField()){
					if("name".equals(fileItem.getFieldName())){
						name = fileItem.getString("UTF-8");
					}
				}else{
					//获得文件名称
					fileName = fileItem.getName();
					//获得文件后缀
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					String hz = fileName.substring(fileName.lastIndexOf("."));

					String filename = dateUtil.instance.newDatestring() + "_"+ owner + hz ;
					//保存到数据库的url
					String url = box + "/" + filename ;
					//通过工具类获得当前时间
					String settime = dateUtil.instance.nowtime();
					
					boolean InsertFileInformation = FileService.instance.InsertFileInformation(filename, settime, url, owner);
					
					if(InsertFileInformation) {
						fileItem.write(new File(box, filename));
						//上传成功后
						Files file = new FileService().FileURL(owner);
						request.getSession().setAttribute("file",file);
						request.getRequestDispatcher("/person_center.jsp").forward(request, response);
						System.out.println("文件上传成功！");
					}else {
						response.sendRedirect("person_center.jsp");
						System.out.println("文件上传失败！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String username = user.getUsername();
		String introduction = request.getParameter("personInfo");
		
		//TODO
		UserService.instance.isExist_personInfo(username, introduction);
		
		request.getRequestDispatcher("person_center.jsp").forward(request, response);
		
		//boolean isExist = UserService.instance.isExist_personInfo(username, introduction);
	}
}
