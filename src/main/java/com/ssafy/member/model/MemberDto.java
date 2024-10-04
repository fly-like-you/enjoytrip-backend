package com.ssafy.member.model;

import java.sql.Date;


public class MemberDto {
    private String id;
    private String member_id;
    private String name;
    private String nickname;
    private String password;
    private Integer phone_number;
    private String email;
    private Date joined_at;

    public MemberDto() {}
    
    

	public MemberDto( String member_id ,String name, String nickname, String password, Integer phone_number, String email,
			Date joined_at) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.phone_number = phone_number;
		this.email = email;
		this.joined_at = joined_at;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getMember_id() {
		return member_id;
	}



	public void setMember_id(String member_id) {
		this.member_id = member_id;
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



	public Integer getPhone_number() {
		return phone_number;
	}



	public void setPhone_number(Integer phone_number) {
		this.phone_number = phone_number;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getJoined_at() {
		return joined_at;
	}



	public void setJoined_at(Date joined_at) {
		this.joined_at = joined_at;
	}
	
	 @Override
	    public String toString() {
	        return "MemberDto{" +
	                "id='" + id + '\'' +
	                ", member_id='" + member_id + '\'' +
	                ", name='" + name + '\'' +
	                ", nickname='" + nickname + '\'' +
	                ", password='" + password + '\'' +
	                ", phone_number=" + phone_number +
	                ", email='" + email + '\'' +
	                ", joined_at=" + joined_at +
	                '}';
	    }
	
	
}



	