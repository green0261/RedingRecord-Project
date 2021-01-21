package com.book.record.member.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.record.member.MemberVO;
import com.book.record.utils.Criteria;

@Repository
public class MemberDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//아이디 비밀번호 일치 회원 가져오기
	public MemberVO getMember(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.getMember",memberVO);
	}
	
	//회원 정보 업데이트
	public void updateMember(MemberVO memberVO) {
		mybatis.update("MemberDAO.updateMember",memberVO);
	}
	
	//아이디 중복 확인
	public int findMemberById(String id) {
		return mybatis.selectOne("MemberDAO.findMemberById",id);
	}
	
	//아이디로 회원 정보 가져오기
	public MemberVO getMemberById(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.getMemberById", memberVO);
	}
	
	//아이디 찾기
	public MemberVO getMemberByNameAndEmail(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail",memberVO);
	}
	
	//비밀번호 찾기
	public MemberVO findPassword(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.findPassword",memberVO);
	}
	
	//회원 가입 처리
	public void insertMember(MemberVO memberVO) {
		mybatis.insert("MemberDAO.insertMember",memberVO);
	}
	
	//회원 탈퇴 처리
	public void deleteMember(MemberVO memberVO) {
		mybatis.delete("MemberDAO.deleteMember",memberVO);
	}
	
	//탈퇴한 회원으로 상태 변경
	public void updateUseyn(MemberVO memberVO) {
		mybatis.update("MemberDAO.updateUseyn",memberVO);
	}
	
	//친구 추가
	public int addFriend(String id,String f_id) {
		HashMap<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("f_id", f_id);
		
		return mybatis.insert("MemberDAO.addFriend",map);
	}
	//회원 목록 가져오기
	public List<MemberVO> getMemberList(String key,Criteria cri){
		HashMap<String,Object> map = new HashMap<>();
		map.put("key", key);
		map.put("cri",cri);
		return mybatis.selectList("MemberDAO.getMemberList",map);
	}
	
	//회원 목록 수
	public int countMemberList(String key) {
		return mybatis.selectOne("MemberDAO.countMemberList",key);
	}
}
