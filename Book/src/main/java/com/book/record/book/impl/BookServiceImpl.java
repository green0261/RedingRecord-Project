package com.book.record.book.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.record.book.BookService;
import com.book.record.book.BookVO;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Override
	public int getBookByIsbn(String isbn) {
		return bookDAO.getBookByIsbn(isbn);
	}

	@Override
	public void insertBook(BookVO bookVO) {
		bookDAO.insertBook(bookVO);
		
	}

}
