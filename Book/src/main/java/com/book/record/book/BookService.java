package com.book.record.book;

public interface BookService {

	//�ش� ���� ���� ���� Ȯ��
	int getBookByIsbn(String isbn);

	//���� ���� ����
	void insertBook(BookVO bookVO);

}