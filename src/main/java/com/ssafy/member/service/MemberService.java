package com.ssafy.member.service;

import com.ssafy.member.model.MemberDto;
import java.sql.SQLException;
import java.util.List;

public interface MemberService {
	int idCheck(String userId) throws Exception;
	int joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(String userId, String userPwd) throws Exception;
}