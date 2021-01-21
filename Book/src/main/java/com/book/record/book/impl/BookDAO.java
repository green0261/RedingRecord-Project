package com.book.record.book.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.record.book.BookVO;

@Repository
public class BookDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//해당 도서 존재 여부 확인
	public int getBookByIsbn(String isbn) {
		return mybatis.selectOne("BookDAO.getBookByIsbn",isbn);
	}
	//도서 정보 저장
	public void insertBook(BookVO bookVO) {
		mybatis.insert("BookDAO.insertBook",bookVO);
	}
}
