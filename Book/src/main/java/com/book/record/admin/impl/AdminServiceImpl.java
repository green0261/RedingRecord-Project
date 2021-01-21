package com.book.record.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.record.admin.AdminService;
import com.book.record.admin.AdminVO;
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public int adminCheck(AdminVO adminVO) {
		
		return adminDAO.adminCheck(adminVO);
	}

	@Override
	public AdminVO getAdmin(String id) {
		
		return adminDAO.getAdmin(id);
	}

}
