package com.book.record.admin.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.book.record.admin.AdminVO;

@Repository
public class AdminDAO{

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int adminCheck(AdminVO adminVO) {
		return mybatis.selectOne("AdminDAO.adminCheck",adminVO);
	}
	
	public AdminVO getAdmin(String id) {
		return mybatis.selectOne("AdminDAO.getAdmin",id);
	}
}
