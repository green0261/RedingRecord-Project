package com.book.record.post.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.record.book.LibraryStatistics;
import com.book.record.book.ReadynStatistics;
import com.book.record.post.PostService;
import com.book.record.post.PostVO;
import com.book.record.post.RecordVO;
import com.book.record.utils.Criteria;

@Service("postService")
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO postDAO;
	
	@Override
	public List<PostVO> getPostList() {
		return postDAO.getPostList();
	}

	@Override
	public PostVO getPost(String pseq) {
		return postDAO.getPost(pseq);
	}

	@Override
	public List<PostVO> getCoverList(String id) {
		return postDAO.getCoverList(id);
	}

	@Override
	public List<RecordVO> getRecordList(String id) {
		return postDAO.getRecordList(id);
	}

	@Override
	public List<PostVO> getBookByReadyn(PostVO postVO,Criteria cri) {
		return postDAO.getBookByReadyn(postVO,cri);
	}
	
	@Override
	public int countPostList(PostVO postVO) {
		return postDAO.countPostList(postVO);
	}

	@Override
	public List<RecordVO> getRecordByPseq(String pseq) {
		return postDAO.getRecordByPseq(pseq);
	}

	@Override
	public void deletePost(String pseq) {
		postDAO.deletePost(pseq);
		
	}
	
	@Override
	public void updatePost(PostVO postVO) {
		postDAO.updatePost(postVO);
		
	}

	@Override
	public RecordVO getRecord(String rseq) {
		return postDAO.getRecord(rseq);
	}

	@Override
	public int updateRecord(RecordVO recordVO) {
		return postDAO.updateRecord(recordVO);
		
	}

	@Override
	public void insertPost(PostVO postVO) {
		postDAO.insertPost(postVO);
		
	}

	@Override
	public int insertRecord(RecordVO recordVO) {
		return postDAO.insertRecord(recordVO);
	}

	@Override
	public void deleteRecordByPseq(String pseq) {
		postDAO.deleteRecordByPseq(pseq);
		
	}

	@Override
	public int deleteRecord(String rseq) {
		return postDAO.deleteRecord(rseq);
	}

	@Override
	public List<PostVO> getPostListByKeyword(String keyword, Criteria cri) {
		return postDAO.getPostListByKeyword(keyword,cri);
	}

	@Override
	public int countSearchedPost(String keyword) {
		return postDAO.countSearchedPost(keyword);
	}

	@Override
	//친구 리뷰 불러오기
	public List<PostVO> getFriendPost(String id,String startNum,String endNum){
		return postDAO.getFriendPost(id,startNum,endNum);
	}

	@Override
	public List<LibraryStatistics> getLibraryStatistics(String id, String year) {
		return postDAO.getLibraryStatistics(id, year);
	}

	@Override
	public List<ReadynStatistics> getReadynStatistics(String id, String year) {
		return postDAO.getReadynStatistics(id, year);
	}

}
