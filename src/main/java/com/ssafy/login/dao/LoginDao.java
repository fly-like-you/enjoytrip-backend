package com.ssafy.login.dao;

public interface LoginDao {
    boolean login(String username, String password);
    void logout(String username);
    String findPassword(String email);
}