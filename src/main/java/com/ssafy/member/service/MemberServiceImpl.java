package com.ssafy.member.service;

import com.ssafy.member.dao.MemberDao;
import com.ssafy.member.dao.MemberDaoImpl;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;
import com.ssafy.trip.service.TripService;
import com.ssafy.trip.service.TripServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class MemberServiceImpl implements MemberService {
	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}
	
	public static MemberService getInstance() {
		return memberService;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberDao.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws Exception {
		return memberDao.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		return memberDao.loginMember(userId, userPwd);
	}
	
    
}