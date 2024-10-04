package com.ssafy.login.dao;

import com.ssafy.login.model.LoginDto;

public interface LoginDao {
    boolean login(String username, String password);
    void logout(String username);
    String findPassword(String email);
}