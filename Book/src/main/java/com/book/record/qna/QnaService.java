package com.book.record.qna;

import java.util.List;

import com.book.record.utils.Criteria;

public interface QnaService {

	/*
	 * qna 리스트 가져오기
	 */
	List<QnaVO> getQnaList(String id,Criteria cri);
	
	/*
	 * 모든 qna 개수
	 */
	int countQna(String id);
	
	/*
	 * 문의글 하나 가져오기
	 */
	QnaVO getQna(String qseq);
	
	/*
	 * 문의글 번호 생성
	 */
	int selectMaxQseq();

	/*
	 * 문의글 insert
	 */
	void insertQna(QnaVO qnaVO);

	/*
	 * 문의글 삭제
	 */
	void deleteQna(String qseq);
	
	/*
	 * Admin: 문의글 하나 가져오기
	 */
	QnaVO adminGetQna(String qseq);
	
	/*
	 * Admin: 검색된 총 문의글 개수
	 */
	int countQnaList(String key);
	
	/*
	 * Admin:qna 답변 처리
	 */
	public void updateQna(QnaVO qnaVO);
	

}