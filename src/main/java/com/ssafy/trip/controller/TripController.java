package com.ssafy.trip.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.model.MemberDto;
import com.ssafy.trip.model.TripDto;
import com.ssafy.tripattraction.dao.TripAttractionDao;
import com.ssafy.tripattraction.dao.TripAttractionDaoImpl;
import com.ssafy.tripattraction.model.TripAttractionDto;
import com.ssafy.tripattraction.model.TripAttractionsDto;
import com.ssafy.tripattraction.service.TripAttractionService;
import com.ssafy.tripattraction.service.TripAttractionServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/trip")
public class TripController extends HttpServlet {
    private final TripService tripService = TripServiceImpl.getInstance();
	private final TripAttractionService tripAttractionService = TripAttractionServiceImpl.getInstance();
	private final AttractionService attractionService = AttractionServiceImpl.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (checkLogin(request, response)) return;

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (checkLogin(request, response)) return;

		String action = request.getParameter("action");

		String path = "";
		switch(action) {
			case "create":
				path = create(request, response);
				forward(request, response, path);
				break;
			default:
				forward(request, response, "error.jsp");
		}
	}

	private String create(HttpServletRequest request, HttpServletResponse response) {
		TripDto trip = createTrip(request, response);
		Integer tripId = tripService.createTrip(trip);
		trip.setId(tripId);
		System.out.println("생성된 여행: " + trip);
		TripAttractionsDto tripAttractions = createTripAttraction(request, response, trip);

		for (TripAttractionDto tripAttraction : tripAttractions.getTripAttractions()) {
			System.out.println("입력할 tripAttraction: " + tripAttraction);
			tripAttractionService.createTripAttraction(tripAttraction);
		}

		return "/main";
	}

	private static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("member") == null) {
			response.sendRedirect(request.getContextPath() + "/member?action=mvLogin");
			return true;
		}
		return false;
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

	public TripDto createTrip(HttpServletRequest request, HttpServletResponse response) {
		// TripDto 객체 생성 및 초기화
		TripDto tripDto = new TripDto();
		tripDto.setId(null);  // 디폴트 값 설정 (id는 자동 생성되므로 null로 설정)
		tripDto.setName("새로운 여행");  // 기본 여행 이름

		// 세션에서 memberId 가져오기
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		tripDto.setMemberId(member.getId());

		tripDto.setStartDate(new Date(System.currentTimeMillis()));  // 디폴트 시작일 설정
		tripDto.setEndDate(new Date(System.currentTimeMillis()));  // 디폴트 종료일 설정
		tripDto.setCreatedAt(new Date(System.currentTimeMillis()));  // 현재 시간으로 생성일 설정

		return tripDto;
	}

	public TripAttractionsDto createTripAttraction(HttpServletRequest request, HttpServletResponse response, TripDto tripDto) {
		try {
			// 요청 본문에서 JSON 데이터를 읽어들입니다.
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String jsonString = sb.toString();

			// ObjectMapper를 사용하여 JSON 데이터를 파싱합니다.
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonString);



			// TripAttractionsDto 객체 생성 및 초기화
			TripAttractionsDto tripAttractionsDto = new TripAttractionsDto();
			List<TripAttractionDto> tripAttractionList = new ArrayList<>();

			// JSON 배열 "places"를 읽어들입니다.
			JsonNode placesNode = jsonNode.get("places");
			if (placesNode != null && placesNode.isArray()) {
				for (int i = 0; i < placesNode.size(); i++) {
					int attractionId = placesNode.get(i).asInt();

					// TripAttractionDto 객체 생성 및 설정
					TripAttractionDto tripAttractionDto = new TripAttractionDto();
					tripAttractionDto.setId(null);  // 디폴트 값 설정 (id는 자동 생성되므로 null로 설정)
					tripAttractionDto.setTripId(tripDto.getId());
					tripAttractionDto.setTrip(tripDto);
					tripAttractionDto.setAttractionId(attractionId);
					tripAttractionDto.setAttraction(null);  // AttractionDto는 기본적으로 null로 설정
					tripAttractionDto.setOrder(i);  // 인덱스를 order로 설정

					// 리스트에 추가
					tripAttractionList.add(tripAttractionDto);
				}
			}

			// TripAttractionsDto에 리스트 설정
			tripAttractionsDto.setTripAttractions(tripAttractionList);
			return tripAttractionsDto;

		} catch (Exception e) {
			e.printStackTrace();
			// 에러 처리
		}

		return null;
	}
	
}
