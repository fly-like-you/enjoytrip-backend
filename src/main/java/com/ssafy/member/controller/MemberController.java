package com.ssafy.member.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.service.MemberService;
import com.ssafy.member.service.MemberServiceImpl;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static MemberService memberService = MemberServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";
		
		switch(action) {
		
			case "mvLogin":
				path = mvLogin(request, response);
				forward(request, response, path);
				break;
			case "login":
				path = login(request, response);
				redirect(request, response, path);
				break;
			case "mvJoin":
				path = mvJoin(request, response);
				forward(request, response, path);
				break;
			case "join":
				path = join(request, response);
				redirect(request, response, path);
				break;
			case "mvModify":
				path = mvModify(request, response);
				forward(request, response, path);
				break;
			case "modify":
				path = modify(request, response);
				redirect(request, response, path);
				break;
			default:
				redirect(request, response, path);
		}
	}
	
	private String modify(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String join(HttpServletRequest request, HttpServletResponse response) {
		MemberDto memberDto = new MemberDto();
		memberDto.setNickname(request.getParameter("username"));
		memberDto.setUserId(request.getParameter("userid"));
		memberDto.setPassword(request.getParameter("userpwd"));
		memberDto.setEmail(request.getParameter("emailid"));
		
		try {
			memberService.joinMember(memberDto);
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원가입 중 에러 발생!!!");
			return "/error/error.jsp";
		}
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String mvJoin(HttpServletRequest request, HttpServletResponse response) {

		return "/member/join.jsp";
	}
	
	private String mvLogin(HttpServletRequest request, HttpServletResponse response) {
		
		return "/member/login.jsp";
	}
	
	private String mvModify(HttpServletRequest request, HttpServletResponse response) {

		return "/member/modify.jsp";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

}
