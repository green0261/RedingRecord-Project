package com.book.record.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.record.comment.CommentService;
import com.book.record.comment.CommentVO;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	@Override
	public List<CommentVO> getComments(String pseq) {
		return commentDAO.getComments(pseq);
	}

	@Override
	public int writeComment(CommentVO commentVO) {
		return commentDAO.writeComment(commentVO);
	}

	@Override
	public int deleteComment(CommentVO commentVO) {
		return commentDAO.deleteComment(commentVO);
	}

	@Override
	public void deleteCommentByPseq(String pseq) {
		commentDAO.deleteCommentByPseq(pseq);
		
	}
	
}
