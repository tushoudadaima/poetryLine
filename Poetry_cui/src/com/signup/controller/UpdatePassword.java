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
 * Servlet implementation class UodataUser
 */
@WebServlet("/UpdataUser")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone= request.getParameter("phone");
		String password= request.getParameter("password");
		PrintWriter writer = response.getWriter();
//		int a = buyerDao.updataBuyerPassword(buyerId, buyerPassword);
//		writer.write(a);
//		System.out.println(a);
		int a = Test.updatePassword(phone, password);
		writer.write(a);
		if (a==1) {
			System.out.println("a=1 √‹¬Î–ﬁ∏ƒ≥…π¶");
		}else {
			System.out.println("a=0 √‹¬Î–ﬁ∏ƒ ß∞‹");
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
