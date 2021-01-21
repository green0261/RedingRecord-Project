package com.book.record.member;

import java.util.List;

import com.book.record.utils.Criteria;

public interface MemberService {

	//���̵� ��й�ȣ ��ġ ȸ�� ��������
	MemberVO getMember(MemberVO memberVO);
	
	//ȸ�� ���� ������Ʈ
	void updateMember(MemberVO memberVO);
	
	//���̵� �ߺ� üũ
	public int findMemberById(String id);
	
	//���̵�� ȸ�� ���� ��������
	public MemberVO getMemberById(MemberVO memberVO);
	
	//���̵� ã��
	public MemberVO getMemberByNameAndEmail(MemberVO memberVO);
		
	//��й�ȣ ã��
	public MemberVO findPassword(MemberVO memberVO);
		
	//ȸ������ ó��
	public void insertMember(MemberVO memberVO);
	
	//ȸ�� Ż�� ó��
	public void deleteMember(MemberVO memberVO);
	
	public void updateUseyn(MemberVO memberVO);
	
	//ģ�� �߰�
	public int addFriend(String id,String f_id);
	
	//ȸ�� ��� ��������
	public List<MemberVO> getMemberList(String key,Criteria cri);
		
	//ȸ�� ��� ��
	public int countMemberList(String key);
	

}