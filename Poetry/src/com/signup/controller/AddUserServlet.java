package com.signup.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.signup.entity.User;
import com.signup.ui.Test;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String id= request.getParameter("id");
		String phone= request.getParameter("phone");
		String password= request.getParameter("password");
		String name = request.getParameter("name");
//		Test test = new Test();
		User user = new User();
//		user.setId(id);
		user.setPhone(phone);
		user.setPassword(password);
		user.setName(name);
		if (phone!=null) {
			Test.addUser(user);
			System.out.println("≤Â»Î≥…π¶");
			request.setAttribute("phone", phone);
			request.setAttribute("password", password);
			request.setAttribute("name", name);
			request.getRequestDispatcher( "index.jsp").forward(request,response);
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
