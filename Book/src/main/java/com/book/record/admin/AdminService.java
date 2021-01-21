package com.book.record.admin;

public interface AdminService {

	int adminCheck(AdminVO adminVO);

	AdminVO getAdmin(String id);

}