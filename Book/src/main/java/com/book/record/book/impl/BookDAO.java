package com.book.record.book.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.record.book.BookVO;

@Repository
public class BookDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//�ش� ���� ���� ���� Ȯ��
	public int getBookByIsbn(String isbn) {
		return mybatis.selectOne("BookDAO.getBookByIsbn",isbn);
	}
	//���� ���� ����
	public void insertBook(BookVO bookVO) {
		mybatis.insert("BookDAO.insertBook",bookVO);
	}
}
