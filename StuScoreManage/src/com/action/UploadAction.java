package com.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/**
 * 
 * @author mxy
 *
 */
@Controller
public class UploadAction {
	 @RequestMapping(value="/file/upload.action")
	 public String upLoad2(HttpServletRequest request, HttpServletResponse response) 
	   throws IllegalStateException, IOException{
	  //解析器解析request的上下文
	  CommonsMultipartResolver multipartResolver = 
	    new CommonsMultipartResolver(request.getSession().getServletContext()); 
	  //先判断request中是否包涵multipart类型的数据，
	  if(multipartResolver.isMultipart(request)){
	   //再将request中的数据转化成multipart类型的数据
	   MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	   Iterator iter = multiRequest.getFileNames();
	   while(iter.hasNext()){
	    MultipartFile file = multiRequest.getFile((String)iter.next());
	    if(file != null){
	     String fileName = file.getOriginalFilename();
	     String path = "D:/" + fileName;//干脆直接到D盘 服务器如果是windowsserver 也有D盘
	     
//	     File directory = new File("");//参数为空 
//	     String courseFile = directory.getCanonicalPath() ;
//	     //获取tomcat当前
//	     //D:\tomcat\apache-tomcat-8.0.28\bin
//	     System.out.println(courseFile);
//	     String path = "../../" + courseFile;
	     System.out.println(path);
	     File localFile = new File(path);
	     //写文件到本地
	     file.transferTo(localFile);
	     
	    }
	   }
	  }
	  return "redirect:/stumanager/analyze.html";
	 }

}
