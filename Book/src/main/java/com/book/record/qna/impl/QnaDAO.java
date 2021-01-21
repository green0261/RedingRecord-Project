package com.book.record.qna.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.record.qna.QnaVO;
import com.book.record.utils.Criteria;

@Repository
public class QnaDAO{

	@Autowired
	private SqlSessionTemplate mybatis;
	
	/*
	 * qna 리스트 가져오기
	 */
	public List<QnaVO> getQnaList(String id,Criteria criteria){
		HashMap<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("criteria",criteria);
		return mybatis.selectList("QnaDAO.getQnaList",map);
	}
	
	/*
	 * 모든 qna 개수
	 */
	public int countQna(String id) {
		return mybatis.selectOne("QnaDAO.countQna",id);
	}
	
	/*
	 * 문의글 하나 가져오기
	 */
	public QnaVO getQna(String qseq) {
		
		return mybatis.selectOne("QnaDAO.getQna",qseq);
	}
	
	/*
	 * 가장 큰 qseq 가져오기
	 */
	public int selectMaxQseq() {
		return mybatis.selectOne("QnaDAO.selectMaxQseq");
	}
	
	/*
	 * 문의글 insert
	 */
	public void insertQna(QnaVO qnaVO) {
		mybatis.insert("QnaDAO.insertQna",qnaVO);
	}
	
	/*
	 * 문의글 삭제
	 */
	public void deleteQna(String qseq) {
		mybatis.delete("QnaDAO.deleteQna",qseq);
	}
	
	/*
	 * Admin:qna글 가져오기
	 */
	public QnaVO adminGetQna(String qseq) {
		return mybatis.selectOne("QnaDAO.getQna",qseq);
	}
	
	/*
	 * Admin:검색된 총 문의글 개수
	 */
	public int countQnaList(String key) {
		return mybatis.selectOne("QnaDAO.countQnaList",key);
	}
	
	/*
	 * Admin:qna 답변 처리
	 */
	public void updateQna(QnaVO qnaVO) {
		mybatis.update("QnaDAO.updateQna",qnaVO);
	}
}
