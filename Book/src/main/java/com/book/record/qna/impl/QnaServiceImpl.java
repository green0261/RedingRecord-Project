package com.book.record.qna.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.record.qna.QnaService;
import com.book.record.qna.QnaVO;
import com.book.record.utils.Criteria;

@Service("qnaService")
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDAO qnaDAO;

	@Override
	public List<QnaVO> getQnaList(String id,Criteria cri) {
		return qnaDAO.getQnaList(id,cri);
	}
	
	@Override
	public int countQna(String id) {
		return qnaDAO.countQna(id);
	}

	@Override
	public QnaVO getQna(String qseq) {
		return qnaDAO.getQna(qseq);
	}

	@Override
	public int selectMaxQseq() {
		return qnaDAO.selectMaxQseq();
	}
	@Override
	public void insertQna(QnaVO qnaVO) {
		//qna게시글 번호 가져오기
		int qseq = selectMaxQseq();
		qnaVO.setQseq(qseq);
		qnaDAO.insertQna(qnaVO);

	}

	@Override
	public void deleteQna(String qseq) {
		qnaDAO.deleteQna(qseq);

	}

	@Override
	public QnaVO adminGetQna(String qseq) {
		return qnaDAO.adminGetQna(qseq);
	}
	
	@Override
	public int countQnaList(String key) {
		return qnaDAO.countQnaList(key);
	}

	@Override
	public void updateQna(QnaVO qnaVO) {
		qnaDAO.updateQna(qnaVO);
		
	}


}
