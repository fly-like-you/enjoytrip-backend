package com.ssafy.tripattraction.controller;

import com.ssafy.member.model.MemberDto;
import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.service.TripService;
import com.ssafy.trip.service.TripServiceImpl;
import com.ssafy.tripattraction.model.TripAttractionDto;
import com.ssafy.tripattraction.model.TripAttractionsDto;
import com.ssafy.tripattraction.service.TripAttractionService;
import com.ssafy.tripattraction.service.TripAttractionServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tripAttraction")
public class TripAttractionController extends HttpServlet {
    private static final TripAttractionService tripAttractionService = TripAttractionServiceImpl.getInstance();
    private static final TripService tripService = TripServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String path = "";

        try {
            switch (action) {
                case "read":
                    path = read(request, response);
                    forward(request, response, path);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String read(HttpServletRequest request, HttpServletResponse response) {
        MemberDto member = (MemberDto) request.getSession().getAttribute("member");
        if (member == null) return "/error/error.jsp";
        int tripId = Integer.parseInt(request.getParameter("tripId"));

        // tripAttraction?action=read&id=${trip.id}
        TripDto trip = tripService.findById(tripId);
        TripAttractionsDto tripAttractionsDto = tripAttractionService.searchTripAttractionsByTripId(tripId);
        System.out.println(tripAttractionsDto.getTripAttractions().get(0));
        // 3. view 반환
        request.setAttribute("tripAttractions", tripAttractionsDto.getTripAttractions());
        request.setAttribute("trip", trip);
        return "/member/myTripAttraction.jsp";
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
