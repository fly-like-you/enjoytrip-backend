package com.ssafy.login.dao;

import com.ssafy.login.model.LoginDto;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LoginDaoImpl implements LoginDao {
    private static LoginDao loginDao = new LoginDaoImpl();
    private Map<String, LoginDto> loggedInUsers = new HashMap<>();

    private LoginDaoImpl() {}

    public static LoginDao getLoginDao() {
        return loginDao;
    }

    @Override
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM members WHERE member_id = ? AND password = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    LoginDto loginDto = new LoginDto(username, password);
                    loginDto.setLoggedIn(true);
                    loggedInUsers.put(username, loginDto);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void logout(String username) {
        loggedInUsers.remove(username);
    }

    @Override
    public String findPassword(String email) {
        String sql = "SELECT name, password FROM members WHERE email = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    return name + "의 비밀번호는 : " + password + "입니다.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "해당 이메일로 등록된 사용자를 찾을 수 없습니다.";
    }
}