package com.signup.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signup.ui.Test;

/**
 * Servlet implementation class UpdateName
 */
@WebServlet("/UpdateName")
public class UpdateName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone= request.getParameter("phone");
		String name= request.getParameter("name");
		PrintWriter writer = response.getWriter();
		int a = 0;
		if (phone!=null&&name!=null) {
			a = Test.updateName(phone,name);
		}else {
			System.out.println("不允许为空");
		}
		
//		writer.write(a);
		if (a==1) {
			System.out.println("a=1 name修改成功");
		}else {
			System.out.println("a=0 name修改失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
