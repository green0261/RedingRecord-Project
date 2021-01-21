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
	
	//���̵� ��й�ȣ ��ġ ȸ�� ��������
	public MemberVO getMember(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.getMember",memberVO);
	}
	
	//ȸ�� ���� ������Ʈ
	public void updateMember(MemberVO memberVO) {
		mybatis.update("MemberDAO.updateMember",memberVO);
	}
	
	//���̵� �ߺ� Ȯ��
	public int findMemberById(String id) {
		return mybatis.selectOne("MemberDAO.findMemberById",id);
	}
	
	//���̵�� ȸ�� ���� ��������
	public MemberVO getMemberById(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.getMemberById", memberVO);
	}
	
	//���̵� ã��
	public MemberVO getMemberByNameAndEmail(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail",memberVO);
	}
	
	//��й�ȣ ã��
	public MemberVO findPassword(MemberVO memberVO) {
		return mybatis.selectOne("MemberDAO.findPassword",memberVO);
	}
	
	//ȸ�� ���� ó��
	public void insertMember(MemberVO memberVO) {
		mybatis.insert("MemberDAO.insertMember",memberVO);
	}
	
	//ȸ�� Ż�� ó��
	public void deleteMember(MemberVO memberVO) {
		mybatis.delete("MemberDAO.deleteMember",memberVO);
	}
	
	//Ż���� ȸ������ ���� ����
	public void updateUseyn(MemberVO memberVO) {
		mybatis.update("MemberDAO.updateUseyn",memberVO);
	}
	
	//ģ�� �߰�
	public int addFriend(String id,String f_id) {
		HashMap<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("f_id", f_id);
		
		return mybatis.insert("MemberDAO.addFriend",map);
	}
	//ȸ�� ��� ��������
	public List<MemberVO> getMemberList(String key,Criteria cri){
		HashMap<String,Object> map = new HashMap<>();
		map.put("key", key);
		map.put("cri",cri);
		return mybatis.selectList("MemberDAO.getMemberList",map);
	}
	
	//ȸ�� ��� ��
	public int countMemberList(String key) {
		return mybatis.selectOne("MemberDAO.countMemberList",key);
	}
}
