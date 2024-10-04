package com.ssafy;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/trip")
public class HelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // 요청(request) 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 응답(response) 인코딩 설정
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		
		String path = "";
		if ("".equals(action)) {
			path = list(request, response);
			forward(request, response, path);
		} else if ("view".equals(action)) {
			path = view(request, response);
			forward(request, response, path);
		} 
	}


	private String list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "index2.jsp";
	}

	private String view(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	

}
