package com.book.record.comment.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.record.comment.CommentVO;

@Repository
public class CommentDAO{

	@Autowired
	private SqlSessionTemplate mybatis;
	
	//�Խñۿ� �޸� ��� ��������
	public List<CommentVO> getComments(String pseq){
		return mybatis.selectList("CommentDAO.getComments",pseq);
	}
	
	//��� ���
	public int writeComment(CommentVO commentVO) {
		return mybatis.insert("CommentDAO.writeComment",commentVO);
	}
	
	//��� ����
	public int deleteComment(CommentVO commentVO) {
		return mybatis.delete("CommentDAO.deleteComment",commentVO);
	}
	
	//pseq�� ��� ����
	public void deleteCommentByPseq(String pseq) {
		mybatis.delete("CommentDAO.deleteCommentByPseq",pseq);
	}
	
	
	
}
