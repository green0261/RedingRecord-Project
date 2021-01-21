package com.book.record.member;

import java.util.List;

import com.book.record.utils.Criteria;

public interface MemberService {

	//아이디 비밀번호 일치 회원 가져오기
	MemberVO getMember(MemberVO memberVO);
	
	//회원 정보 업데이트
	void updateMember(MemberVO memberVO);
	
	//아이디 중복 체크
	public int findMemberById(String id);
	
	//아이디로 회원 정보 가져오기
	public MemberVO getMemberById(MemberVO memberVO);
	
	//아이디 찾기
	public MemberVO getMemberByNameAndEmail(MemberVO memberVO);
		
	//비밀번호 찾기
	public MemberVO findPassword(MemberVO memberVO);
		
	//회원가입 처리
	public void insertMember(MemberVO memberVO);
	
	//회원 탈퇴 처리
	public void deleteMember(MemberVO memberVO);
	
	public void updateUseyn(MemberVO memberVO);
	
	//친구 추가
	public int addFriend(String id,String f_id);
	
	//회원 목록 가져오기
	public List<MemberVO> getMemberList(String key,Criteria cri);
		
	//회원 목록 수
	public int countMemberList(String key);
	

}