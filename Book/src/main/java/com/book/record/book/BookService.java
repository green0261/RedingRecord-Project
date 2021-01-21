package com.book.record.book;

public interface BookService {

	//해당 도서 존재 여부 확인
	int getBookByIsbn(String isbn);

	//도서 정보 저장
	void insertBook(BookVO bookVO);

}