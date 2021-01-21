package com.book.record.comment;

import java.util.List;

public interface CommentService {

	List<CommentVO> getComments(String pseq);
	
	//게시글 등록
	public int writeComment(CommentVO commentVO);
	
	//댓글 삭제
	public int deleteComment(CommentVO commentVO);
	
	//pseq로 댓글 삭제
	public void deleteCommentByPseq(String pseq);
}