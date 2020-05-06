package com.signup.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signup.ui.Test;


/**
 * Servlet implementation class ImgUp
 */
@WebServlet("/ImgUp")
public class ImgUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String phone= request.getParameter("phone");
		System.out.println(phone);
	
		if (phone!=null) {
			InputStream in = request.getInputStream();
			OutputStream out = new FileOutputStream("D:/poetryLinePic/"+phone+".jpg");
			String pathString = "D:/poetryLinePic/"+phone+".jpg";
			byte[] bytes = new byte[1024];
			int n = -1;
			while((n = in.read(bytes)) != -1) {
				out.write(bytes, 0, n);
				out.flush();
			}
			System.out.println("上传");
			in.close();
			out.close();
	
			int a = Test.imgUp(phone,pathString);
			if(a!=0) {				
				response.getWriter().write("头像上传成功");
			}else {
				response.getWriter().write("上传失败");
			}
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
