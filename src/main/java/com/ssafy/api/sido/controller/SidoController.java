package com.ssafy.api.sido.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.sido.model.SidoDto;
import com.ssafy.api.sido.service.SidoService;
import com.ssafy.api.sido.service.SidoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/api/sido")
public class SidoController extends HttpServlet {

    private SidoService sidoService = SidoServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<SidoDto> sidoList = null;
        try {
            sidoList = sidoService.getAllSidos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(sidoList);

        System.out.println("생성된 JSON: " + jsonResponse);

        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

}
