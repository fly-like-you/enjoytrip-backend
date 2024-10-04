package com.ssafy.trip.controller;

import java.io.IOException;

import com.ssafy.attraction.model.AttractionsDto;
import com.ssafy.attraction.service.AttractionService;
import com.ssafy.attraction.service.AttractionServiceImpl;
import com.ssafy.trip.service.TripService;
import com.ssafy.trip.service.TripServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/trip")
public class TripController extends HttpServlet {
    private TripService tripService = TripServiceImpl.getInstance();
    private AttractionService attractionService = AttractionServiceImpl.getInstance();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String path = "";
		switch(action) {
			case "plan":
				path = plan(request, response);
				forward(request, response, path);
				break;
			default:
				forward(request, response, "error.jsp");
		}
	}

	private String plan(HttpServletRequest request, HttpServletResponse response) {
		// 1. 요청 파라미터 탐색
		Integer areaCode = Integer.parseInt(request.getParameter("id"));
		
		// 2. DB 접근이 필요한가?
		// 파라미터를가지고 areacode에 맞는 attraction을 가져오기
		AttractionsDto attractions = attractionService.searchAttracionByAreaCode(areaCode);
		
		// 3. 뷰를 반환하기
		request.setAttribute("attractions", attractions);
		return "/trip/trip.jsp";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
}
