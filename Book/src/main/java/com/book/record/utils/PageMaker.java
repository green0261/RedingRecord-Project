package com.book.record.utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private Criteria cri; //현재 페이지 정보
	
	private int totalCount; //전체 게시글의 수
	private int startPage; //시작 페이지 번호
	private int endPage; //끝 페이지 번호
	private boolean prev; //이전 페이지의 유무
	private boolean next; //다음 페이지의 유무
	
	private int displayPageNum = 10; //화면 하단에 보이는 페이지의 수
	private int realEndPage; //전체 항목의 수에 따른 실제 마지막 페이지의 번호
	
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotalCount() {
		return totalCount;
	}
	
	//조회한 전체 항목을 저장할 때 각 멤버변수 초기화 수행
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		memberInit();
	}
	
	/*
	 *멤버 변수에 초기값 설정 
	 */
	public void memberInit() {
		//끝 페이지 계산
		//올림(현재 페이지 번호/표시할페이지 개수*표시할 페이지 개수)
		endPage = (int)Math.ceil(cri.getPageNum() / (double)displayPageNum)
					* displayPageNum;
		
		//시작 페이지: 끝페이지 번호 - 표시할 페이지 수 + 1
		startPage = endPage - displayPageNum + 1;
		
		//실제 마지막 페이지
		realEndPage = (int)Math.ceil(totalCount / (double)cri.getNumPerPage());
		
		//실제 마지막 페이지(realEndPage)와 계산한 마지막 페이지(endPage) 값이 다르면
//		마지막 페이지를 realEndPage로 수정함
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		prev = (startPage == 1 ? false : true);
		//조회한 전체 항목수가 마지막 페이지*페이지 당 표시 항목수보다 크면 다음 페이지가 존재함
		next = (totalCount > (endPage * cri.getNumPerPage()) ? true : false);
		
		
	}
	/*
	 * 페이지번호를 입력 받아 admin_product_list의 쿼리 스트링을 만들어 주는 메소드
	 * 입력 매개변수:
	 * 		page: 표시할 페이지 번호
	 * 출력 예:
	 * 		?pageNum=2?numPerPage=10 //이걸 하나하나 타이핑 하기 번거로우니까 
	 */
	public String makeQuery(int page) {
		UriComponents uriComp = UriComponentsBuilder.newInstance()
					.queryParam("pageNum",page)
					.queryParam("numPerPage",cri.getNumPerPage())
					.build();
		return uriComp.toString();
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public int getRealEndPage() {
		return realEndPage;
	}
	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}
	
	
}
