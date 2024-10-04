package com.ssafy.member.dao;

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
    
    
//    MemberDto getMemberById(String memberId) throws SQLException;
//    List<MemberDto> getAllMembers() throws SQLException;
//    void updateMember(MemberDto memberDto) throws SQLException;
//    void deleteMember(String memberId) throws SQLException;
//    
//    // 추가 기능
//    boolean checkDuplicateId(String memberId) throws SQLException;
//    boolean checkDuplicateEmail(String email) throws SQLException;
//    List<MemberDto> searchMembersByName(String name) throws SQLException;
}