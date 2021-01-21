package com.book.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.book.record.admin.AdminService;
import com.book.record.admin.AdminVO;
import com.book.record.member.MemberService;
import com.book.record.member.MemberVO;
import com.book.record.qna.QnaService;
import com.book.record.qna.QnaVO;
import com.book.record.utils.Criteria;
import com.book.record.utils.PageMaker;

@Controller
@SessionAttributes("adminUser")
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private MemberService memberSerivce;
	@Autowired
	private QnaService qnaService;

	/*
	 * 로그인 페이지로 이동
	 */
	@RequestMapping(value="admin_login_form")
	public String adminLoginView() {
		return "admin/login_form";
	}
	
	/*
	 * 로그인 처리
	 */
	@RequestMapping(value="admin_login")
	public String adminLogin(AdminVO vo,Model model) {
		
		int result = adminService.adminCheck(vo);
		
		if(result == 1) { //아이디와 비밀번호가 일치하면
			AdminVO adminUser = adminService.getAdmin(vo.getId());
			
			model.addAttribute("adminUser",adminUser);
			
			return "redirect:admin_member_list";
			
			
		}else {
			
			model.addAttribute("message","아이디와 비밀번호를 확인해주세요");
			return "admin/login_form";
		}
		
	}
	
	/*
	 * 회원 리스트
	 */
	@RequestMapping(value="/admin_member_list")
	public String adminProductList(@RequestParam(value="key", defaultValue="",required=false)
									String key,
									Criteria criteria,
									Model model, HttpSession session) {
		
		AdminVO adminUser = (AdminVO)session.getAttribute("adminUser");
		
		if(adminUser == null) {
			
			return "admin/login_form";
			
		}else {
			List<MemberVO> memberList = memberSerivce.getMemberList(key,criteria);
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria);
			
			//검색된 총 회원 수
			int memberCount = memberSerivce.countMemberList(key);
			pageMaker.setTotalCount(memberCount);

			model.addAttribute("memberList",memberList);
			model.addAttribute("memberListSize",memberCount);
			model.addAttribute("pageMaker",pageMaker);
			
			return "admin/member/member_list";
		}
	}
	
	/*
	 * QNA 리스트 보기
	 */
	@RequestMapping(value="/admin_qna_list")
	public String adminQnaList(@RequestParam(value="key", defaultValue="",required=false)
								String key,
								Criteria criteria,
								Model model, HttpSession session) {
		
		List<QnaVO> qnaList = qnaService.getQnaList(key,criteria);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		//검색된 총 qna 개수
		int qnaCount = qnaService.countQnaList(key);
		pageMaker.setTotalCount(qnaCount);
		
		model.addAttribute("qnaList",qnaList);
		model.addAttribute("pageMaker",pageMaker);
		return "admin/qna/qna_list";
	}
	
	/*
	 * qna 불러오기
	 */
	@RequestMapping(value="/admin_qna_detail")
	public String adminQnaDetail(@RequestParam(value="qseq") String qseq
								, Model model,Criteria criteria) {
		
		QnaVO qna = qnaService.adminGetQna(qseq);
			
		model.addAttribute("qnaVO",qna);
		model.addAttribute("criteria",criteria);
		
		return "admin/qna/qna_detail";
		
	}
	
	/*
	 * qna 답변 등록
	 */
	@RequestMapping(value="/admin_qna_repsave")
	public String adminQnaRepSave(QnaVO vo) {
		
		qnaService.updateQna(vo);
		
		return "redirect: admin_qna_list";
	}
	
	/*
	 * 로그아웃
	 */
	@RequestMapping(value="/admin_logout")
	public String adminLogout(HttpSession session) {
		session.invalidate();
		
		return "admin/login_form";
	}
}
