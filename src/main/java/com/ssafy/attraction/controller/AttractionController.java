package com.ssafy.attraction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.attraction.model.AttractionsDto;
import com.ssafy.attraction.service.AttractionService;
import com.ssafy.attraction.service.AttractionServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/attraction")
public class AttractionController extends HttpServlet {

	private final AttractionService attractionService = AttractionServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지네이션 파라미터 받아오기
		int numOfRows = Integer.parseInt(request.getParameter("numOfRows"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String areaCode = request.getParameter("areaCode");
		String contentTypeId = request.getParameter("contentTypeId");
		// Service를 통해 데이터 조회
		AttractionsDto attractionsDto = attractionService.searchAttractions(numOfRows, pageNo, areaCode, contentTypeId);

		// JSON으로 반환
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		out.print(objectMapper.writeValueAsString(attractionsDto));
		out.flush();
	}
}
