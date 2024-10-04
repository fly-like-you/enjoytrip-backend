package com.ssafy.member.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";
		switch(action) {
		
			case "login":
				path = login(request, response);
				forward(request, response, path);
				break;
			case "join":
				path = join(request, response);
				forward(request, response, path);
				break;
			case "modify":
				path = modify(request, response);
				forward(request, response, path);
				break;
		}
	}
	
	private String modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/member/modify.jsp";
	}

	private String join(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "/member/join.jsp";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		
		return "/member/login.jsp";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
