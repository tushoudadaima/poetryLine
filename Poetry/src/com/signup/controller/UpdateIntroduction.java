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
 * Servlet implementation class UpdateIntroduction
 */
@WebServlet("/UpdateIntroduction")
public class UpdateIntroduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateIntroduction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone= request.getParameter("phone");
		String introduction= request.getParameter("introduction");
		PrintWriter writer = response.getWriter();
		String a = "";
		if (phone!=null&&introduction!=null) {
			a = Test.updateIntroduction(phone,introduction);
		}else {
			System.out.println("不允许为空");
		}
		
		writer.write(a);
		if (a=="1") {
			System.out.println("a=1 introduction修改成功");
		}else {
			System.out.println("a=0 introduction修改失败");
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
