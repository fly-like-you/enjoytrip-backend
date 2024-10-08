package com.ssafy.member.service;

import com.ssafy.member.model.MemberDto;

public interface MemberService {
	int idCheck(String userId) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;

	MemberDto loginMember(String userId, String userPwd) throws Exception;
	MemberDto findById(String memberId);

	int modify(MemberDto member);
	boolean resign(String id);

}