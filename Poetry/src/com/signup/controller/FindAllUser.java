package com.signup.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signup.ui.Test;

/**
 * Servlet implementation class FindAllUser
 */
@WebServlet("/FindAllUser")
public class FindAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone= request.getParameter("phone");
//		String buyerPassword= request.getParameter("buyerPassword");
		Test test = new Test();
		List list = test.selectAll();
		String str = list.toString();
		request.setAttribute( "TestAll",str);
		request.setAttribute("phone", phone);
		request.getRequestDispatcher( "index.jsp").forward(request,response);
		System.out.println("str: "+str);
		System.out.println("phone.md5:"+phone);
		test.showPhone(phone);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
