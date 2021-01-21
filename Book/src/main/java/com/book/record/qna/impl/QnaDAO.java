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
	 * qna ����Ʈ ��������
	 */
	public List<QnaVO> getQnaList(String id,Criteria criteria){
		HashMap<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("criteria",criteria);
		return mybatis.selectList("QnaDAO.getQnaList",map);
	}
	
	/*
	 * ��� qna ����
	 */
	public int countQna(String id) {
		return mybatis.selectOne("QnaDAO.countQna",id);
	}
	
	/*
	 * ���Ǳ� �ϳ� ��������
	 */
	public QnaVO getQna(String qseq) {
		
		return mybatis.selectOne("QnaDAO.getQna",qseq);
	}
	
	/*
	 * ���� ū qseq ��������
	 */
	public int selectMaxQseq() {
		return mybatis.selectOne("QnaDAO.selectMaxQseq");
	}
	
	/*
	 * ���Ǳ� insert
	 */
	public void insertQna(QnaVO qnaVO) {
		mybatis.insert("QnaDAO.insertQna",qnaVO);
	}
	
	/*
	 * ���Ǳ� ����
	 */
	public void deleteQna(String qseq) {
		mybatis.delete("QnaDAO.deleteQna",qseq);
	}
	
	/*
	 * Admin:qna�� ��������
	 */
	public QnaVO adminGetQna(String qseq) {
		return mybatis.selectOne("QnaDAO.getQna",qseq);
	}
	
	/*
	 * Admin:�˻��� �� ���Ǳ� ����
	 */
	public int countQnaList(String key) {
		return mybatis.selectOne("QnaDAO.countQnaList",key);
	}
	
	/*
	 * Admin:qna �亯 ó��
	 */
	public void updateQna(QnaVO qnaVO) {
		mybatis.update("QnaDAO.updateQna",qnaVO);
	}
}
