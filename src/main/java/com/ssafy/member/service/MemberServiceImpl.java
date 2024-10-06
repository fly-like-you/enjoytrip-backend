package com.ssafy.member.service;

import com.ssafy.member.dao.MemberDao;
import com.ssafy.member.dao.MemberDaoImpl;
import com.ssafy.member.model.MemberDto;


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
		System.out.println("---------아이디 중복 검사 실행--------");
		if (memberDao.idCheck(memberDto.getUserId()) == 1) {
			System.out.println("아이디 중복!");
			throw new Exception("ID가 중복됩니다.");
		}
		System.out.println("아이디 중복 아님");
		return memberDao.createMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		MemberDto memberDto = memberDao.loginMember(userId, userPwd);
		if (memberDto != null) {
			if (memberDto.getPassword().equals(userPwd)) {
				return memberDto;
			}
		}
		return null;
	}

	@Override
	public MemberDto findById(String memberId) {
		return memberDao.findById(memberId);
	}

	@Override
	public int modify(MemberDto member) {
		return memberDao.modify(member);
	}

	@Override
	public boolean resign(String id) {
		return memberDao.deleteMember(id);
	}


}