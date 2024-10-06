package com.ssafy.member.controller;

import com.ssafy.trip.model.TripsDto;
import com.ssafy.trip.service.TripService;
import com.ssafy.trip.service.TripServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.service.MemberService;
import com.ssafy.member.service.MemberServiceImpl;
import java.time.LocalDate;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static MemberService memberService = MemberServiceImpl.getInstance();
	private static TripService tripService = TripServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";

		try {
			switch(action) {
				case "login":
					System.out.println("로그인");
					path = login(request, response);
					redirect(request, response, path);
					break;
				case "join":
					path = join(request, response);
					redirect(request, response, path);
					break;
				case "modify":
					path = modify(request, response);
					redirect(request, response, path);
					break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String resign(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if (!memberService.resign(id)) {
			System.out.println("회원 탈퇴 실패 ㅜ");
			return "/error/error.jsp";
		}

		// logout
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("회원 탈퇴 성공!!");
		return "/main";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String path = "";
		try {

			switch (action) {
				case "mvLogin":
					path = mvLogin(request, response);
					forward(request, response, path);
					break;

				case "mvJoin":
					path = mvJoin(request, response);
					forward(request, response, path);
					break;

				case "mvModify":
					path = mvModify(request, response);
					forward(request, response, path);
					break;
				case "logout":
					path = logout(request, response);
					redirect(request, response, path);
					break;
				case "resign":
					path = resign(request, response);
					redirect(request, response, path);
					break;
				case "myPage":
					path = myPage(request, response);
					forward(request, response, path);
					break;
				default:
					redirect(request, response, path);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private String myPage(HttpServletRequest request, HttpServletResponse response) {
		MemberDto member = (MemberDto) request.getSession().getAttribute("member");
		if (member == null) return "/member/login.jsp";

		// 1. 요청 파라미터
		// 2. DB 접속
		TripsDto trips = tripService.findByMemberId(member.getId());
		System.out.println(trips.getTrips());

		// 3. 뷰 반환
		request.setAttribute("trips", trips.getTrips());
		return "/member/myPage.jsp";
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		// 1. 요청 파라미터
		System.out.println("--------- 유저 회원정보 수정 요청 ---------");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("password-confirm");
		if (password == null) return "/error/error.jsp";
		if (!password.equals(passwordConfirm)) {
			System.out.println("------ 입력 정보가 틀립니다 ------");
			return "/error/error.jsp";
		}
		System.out.println("비밀번호가 똑같고 빈 입력이 아닙니다. -- 회원정보 수정 요청");

		MemberDto member = new MemberDto();
		member.setId(Integer.parseInt(request.getParameter("id")));
		member.setNickname(request.getParameter("nickname"));
		member.setUserId(request.getParameter("user-id"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		member.setPassword(request.getParameter("password"));

		int cnt = memberService.modify(member);
		System.out.println("수정된 유저의 수: " + cnt);
		return "/main";
	}

	private String join(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("--------- 유저 회원가입 요청 ---------");
		MemberDto member = new MemberDto();
		member.setUserId(request.getParameter("member-id"));
		member.setNickname(request.getParameter("nickname"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setJoinDate(LocalDate.now().toString());

		System.out.println("회원 요청 정보: " + member);
		try {
			memberService.joinMember(member);
			System.out.println("--------- 회원 가입 승인 ---------");

			return "/main";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("e", e);
			return "/error/error.jsp";
		}
	}

	private String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 요청 파라미터 조회
		String userId = request.getParameter("member-id");
		String password = request.getParameter("password");

		// 2. DB 접속 여부
		MemberDto logined = memberService.loginMember(userId, password);
		System.out.println("조회된 로그인 유저 정보:" + logined);
		if (logined != null) {
			System.out.println("로그인 성공!!!");
			HttpSession session = request.getSession();
			session.setAttribute("member", logined);
			return "/main";
		} else {
			// 로그인 실패 시 에러 메시지 설정
			return "/member?action=mvLogin"; // 로그인 실패 시 로그인 페이지로 포워드
		}
	}
	private String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("-------로그아웃 요청 수신-------");
		// 1. 세션 무효화 (로그아웃 처리)
		HttpSession session = request.getSession(); // 세션이 존재할 경우에만 가져옴
        session.invalidate(); // 세션 무효화

        return "/main";
	}

	private String mvJoin(HttpServletRequest request, HttpServletResponse response) {
		return "/member/mvJoin.jsp";
	}
	
	private String mvLogin(HttpServletRequest request, HttpServletResponse response) {
		return "/member/mvLogin.jsp";
	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("member-id");
		MemberDto member = memberService.findById(id);
		if (member != null) {
			request.setAttribute("member", member);
			return "/member/mvModify.jsp";
		}

		return "/error.jsp";
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
