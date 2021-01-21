package com.book.record.post;
import java.util.List;

import com.book.record.book.LibraryStatistics;
import com.book.record.book.ReadynStatistics;
import com.book.record.utils.Criteria;

public interface PostService {

		List<PostVO> getPostList();

		PostVO getPost(String pseq);
	
		//아이디로 책 표지 불러오기
		public List<PostVO> getCoverList(String id);
			
		//아이디로 책 기록 불러오기
		public List<RecordVO> getRecordList(String id);
			
		//한 사용자의 전체,완/미완독 도서 리스트 가져오기
		public List<PostVO> getBookByReadyn(PostVO postVO,Criteria cri);
		
		//전체 검색 데이터 총 개수
		public int countPostList(PostVO postVO);
		
		//한 게시글의 record 가져오기
		public List<RecordVO> getRecordByPseq(String pseq);
		
		//게시글 삭제
		public void deletePost(String pseq);
		
		//리뷰 수정
		public void updatePost(PostVO postVO);
		
		//글기록 가져오기
		public RecordVO getRecord(String rseq);
		
		//글 기록 업데이트
		public int updateRecord(RecordVO recordVO);
		
		//post 저장
		public void insertPost(PostVO postVO);
		
		//record 저장
		public int insertRecord(RecordVO recordVO);
		
		//pseq로 record삭제
		public void deleteRecordByPseq(String pseq);
		
		//rseq로 record 삭제
		public int deleteRecord(String rseq);
		
		//키워드로 게시글 불러오기
		public List<PostVO> getPostListByKeyword(String keyword,Criteria cri);
		
		////키워드로 조회된 총 게시글 수
		public int countSearchedPost(String keyword);
		
		//친구 리뷰 불러오기
		public List<PostVO> getFriendPost(String id,String startNum,String endNum);
		
		//서재 리뷰 통계 가져오기
		public List<LibraryStatistics> getLibraryStatistics(String id,String year);
		
		//완,미완 통계 가져오기
		public List<ReadynStatistics> getReadynStatistics(String id,String year);
}