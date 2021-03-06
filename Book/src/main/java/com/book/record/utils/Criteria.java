package com.book.record.utils;
/*
 * 현재 페이지와 관련된 정보를 저장하는 클래스
 * -현재 페이지 번호
 * - 페이지당 출력 항목 개수
 * - 각 페이지의 시작 항목 번호
 */
public class Criteria {
	private int pageNum; //현재 페이지 번호
	private int numPerPage; //페이지 당 출력 항목 개수
	
	public Criteria() {
		//기본값 설정: 현재 페이지:1, 페이지 당 항목수:10
		this(1,10); //자기 자신의 생성자를 호출할 때는 this를 사용
		
	}

	public Criteria(int pageNum, int numPerPage) {
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if(pageNum <= 0) {
			this.pageNum = 1;
		}else {
			this.pageNum = pageNum;
		}
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	//페이지 당 항목 수 설정
	//최대 항목수: 30
	public void setNumPerPage(int numPerPage) {
		if(numPerPage <= 0 || numPerPage > 30) {
			this.numPerPage = 30;
		}else {
			this.numPerPage = numPerPage;
		}
		this.numPerPage = numPerPage;
	}
	
	//현재 페이지에서 시작 항목 번호를 반환
	public int getStartPage() {
		
		return (this.pageNum-1) * this.numPerPage + 1;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", numPerPage=" + numPerPage + "]";
	}
	
	
	
}
