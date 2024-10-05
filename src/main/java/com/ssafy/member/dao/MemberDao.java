package com.ssafy.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.member.model.MemberDto;



public interface MemberDao {
	//회원 CRUD
	
	//Create
	Integer createMember(MemberDto memberDto);
	
	//모든 회원 조회
	List<MemberDto> searchMemberAll();
	
	// 회원 정보 수정
    void updateMember(MemberDto memberDto);
    
    MemberDto findById(String memberId);
    
    // 회원 탈퇴 메서드 추가
    boolean deleteMember(String memberId);
    
	int idCheck(String userId) throws SQLException;
	MemberDto loginMember(String userId, String userPwd) throws SQLException;

	int modify(MemberDto member);
}