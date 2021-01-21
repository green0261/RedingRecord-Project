package com.book.record.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.record.member.MemberService;
import com.book.record.member.MemberVO;
import com.book.record.utils.Criteria;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO getMember(MemberVO memberVO) {
		return memberDAO.getMember(memberVO);
	}
	
	@Override
	public void updateMember(MemberVO memberVO) {
		memberDAO.updateMember(memberVO);

	}

	@Override
	public int findMemberById(String id) {
		return memberDAO.findMemberById(id);
	}
	
	@Override
	public MemberVO getMemberById(MemberVO memberVO) {
		return memberDAO.getMemberById(memberVO);
	}
	
	@Override
	public MemberVO getMemberByNameAndEmail(MemberVO memberVO) {
		return memberDAO.getMemberByNameAndEmail(memberVO);
	}

	@Override
	public MemberVO findPassword(MemberVO memberVO) {
		return memberDAO.findPassword(memberVO);
	}
	
	@Override
	public void insertMember(MemberVO memberVO) {
		memberDAO.insertMember(memberVO);
		
	}

	@Override
	public void deleteMember(MemberVO memberVO) {
		memberDAO.deleteMember(memberVO);
		
	}

	@Override
	public void updateUseyn(MemberVO memberVO) {
		memberDAO.updateUseyn(memberVO);
		
	}

	@Override
	public int addFriend(String id, String f_id) {
		return memberDAO.addFriend(id, f_id);
	}

	@Override
	public List<MemberVO> getMemberList(String key, Criteria cri) {
		return memberDAO.getMemberList(key, cri);
	}

	@Override
	public int countMemberList(String key) {
		return memberDAO.countMemberList(key);
	}

}
