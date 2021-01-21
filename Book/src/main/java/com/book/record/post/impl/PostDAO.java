package com.book.record.post.impl;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.record.book.LibraryStatistics;
import com.book.record.book.ReadynStatistics;
import com.book.record.post.PostVO;
import com.book.record.post.RecordVO;
import com.book.record.utils.Criteria;

@Repository
public class PostDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<PostVO> getPostList() {
		return mybatis.selectList("PostDAO.getPostList");
	}
	
	public PostVO getPost(String pseq) {
		return mybatis.selectOne("PostDAO.getPost",pseq);
	}
	
	public List<PostVO> getCoverList(String id){
		return mybatis.selectList("PostDAO.getCoverList",id);
	}
	
	public List<RecordVO> getRecordList(String id){
		return mybatis.selectList("PostDAO.getRecordList",id);
	}
	
	//회원별 전체,완/미완독 도서 불러오기
	public List<PostVO> getBookByReadyn(PostVO postVO,Criteria cri){
		HashMap<String,Object> map = new HashMap<>();
		map.put("postVO", postVO);
		map.put("criteria", cri);
		return mybatis.selectList("PostDAO.getBookByReadyn",map);
	}
	
	public int countPostList(PostVO postVO) {
		return mybatis.selectOne("PostDAO.countPostList",postVO);
	}
	
	public List<RecordVO> getRecordByPseq(String pseq){
		return mybatis.selectList("PostDAO.getRecordByPseq",pseq);
	}
	
	//post삭제
	public void deletePost(String pseq) {
		mybatis.delete("PostDAO.deletePost",pseq);
	}
	
	//post 수정
	public void updatePost(PostVO postVO) {
		mybatis.update("PostDAO.updatePost",postVO);
	}
	
	public RecordVO getRecord(String rseq) {
		return mybatis.selectOne("PostDAO.getRecord",rseq);
	}
	
	public int updateRecord(RecordVO recordVO) {
		return mybatis.update("PostDAO.updateRecord",recordVO);
	}
	
	//post 저장
	public void insertPost(PostVO postVO) {
		mybatis.insert("PostDAO.insertPost",postVO);
	}
	
	//record 저장
	public int insertRecord(RecordVO recordVO) {
		return mybatis.insert("PostDAO.insertRecord",recordVO);
	}
	
	//Pseq로 record 삭제
	public void deleteRecordByPseq(String pseq) {
		mybatis.delete("PostDAO.deleteRecordByPseq",pseq);
	}
	
	//rseq로 record 삭제
	public int deleteRecord(String rseq) {
		return mybatis.delete("PostDAO.deleteRecord",rseq);
	}
	
	//키워드로 게시글 불러오기
	public List<PostVO> getPostListByKeyword(String keyword,Criteria cri){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("criteria", cri);
		return mybatis.selectList("PostDAO.getPostListByKeyword",map);
	}
	
	//키워드로 조회된 총 게시글 수
	public int countSearchedPost(String keyword){
		return mybatis.selectOne("PostDAO.countSearchedPost",keyword);
	};
	
	//친구 리뷰 불러오기
	public List<PostVO> getFriendPost(String id,String startNum,String endNum){
		HashMap<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		return mybatis.selectList("PostDAO.getFriendPost",map);
	}
	
	//서재 리뷰 통계 가져오기
	public List<LibraryStatistics> getLibraryStatistics(String id,String year){
		HashMap<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("year",year);
		
		return mybatis.selectList("PostDAO.getLibraryStatistics",map);
	}
	
	//완,미완 통계 가져오기
	public List<ReadynStatistics> getReadynStatistics(String id,String year){
		HashMap<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("year", year);
		
		return mybatis.selectList("PostDAO.getReadynStatistics",map);
	}
	
}
