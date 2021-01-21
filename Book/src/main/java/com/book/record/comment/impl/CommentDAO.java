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
	
	//게시글에 달린 댓글 가져오기
	public List<CommentVO> getComments(String pseq){
		return mybatis.selectList("CommentDAO.getComments",pseq);
	}
	
	//댓글 등록
	public int writeComment(CommentVO commentVO) {
		return mybatis.insert("CommentDAO.writeComment",commentVO);
	}
	
	//댓글 삭제
	public int deleteComment(CommentVO commentVO) {
		return mybatis.delete("CommentDAO.deleteComment",commentVO);
	}
	
	//pseq로 댓글 삭제
	public void deleteCommentByPseq(String pseq) {
		mybatis.delete("CommentDAO.deleteCommentByPseq",pseq);
	}
	
	
	
}
