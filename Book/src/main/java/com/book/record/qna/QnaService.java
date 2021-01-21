package com.book.record.qna;

import java.util.List;

import com.book.record.utils.Criteria;

public interface QnaService {

	/*
	 * qna ����Ʈ ��������
	 */
	List<QnaVO> getQnaList(String id,Criteria cri);
	
	/*
	 * ��� qna ����
	 */
	int countQna(String id);
	
	/*
	 * ���Ǳ� �ϳ� ��������
	 */
	QnaVO getQna(String qseq);
	
	/*
	 * ���Ǳ� ��ȣ ����
	 */
	int selectMaxQseq();

	/*
	 * ���Ǳ� insert
	 */
	void insertQna(QnaVO qnaVO);

	/*
	 * ���Ǳ� ����
	 */
	void deleteQna(String qseq);
	
	/*
	 * Admin: ���Ǳ� �ϳ� ��������
	 */
	QnaVO adminGetQna(String qseq);
	
	/*
	 * Admin: �˻��� �� ���Ǳ� ����
	 */
	int countQnaList(String key);
	
	/*
	 * Admin:qna �亯 ó��
	 */
	public void updateQna(QnaVO qnaVO);
	

}