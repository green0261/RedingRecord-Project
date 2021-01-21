package com.book.record.comment;

import java.util.List;

public interface CommentService {

	List<CommentVO> getComments(String pseq);
	
	//�Խñ� ���
	public int writeComment(CommentVO commentVO);
	
	//��� ����
	public int deleteComment(CommentVO commentVO);
	
	//pseq�� ��� ����
	public void deleteCommentByPseq(String pseq);
}