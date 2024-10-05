package com.ssafy.member.model;


// 닉네임, 폰번호, 이메일, 가입일

import java.sql.Date;

public class MemberDto {
	private Integer id;
	private String userId;
	private String name;
	private String nickname;
	private String password;
	private String email;
	private String joinDate;
	private String phone;

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", userId=" + userId + ", name=" + name + ", nickname=" + nickname
				+ ", password=" + password + ", email=" + email + ", joinDate=" + joinDate + ", phone=" + phone + "]";
	}


	
}


	