package com.book.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.book.record.member.MemberService;
import com.book.record.member.MemberVO;
import com.book.record.post.PostService;
import com.book.record.post.PostVO;
import com.book.record.post.RecordVO;

@Controller
@SessionAttributes("loginUser")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private PostService postService;
	
	/*
	 * 로그인 화면으로 이동
	 */
	@RequestMapping(value="/login_form")
	public String loginFormView() {
		return "member/login_form";
	}
	
	/*
	 * 로그인 처리
	 */
	@RequestMapping(value="/login")
	public String login(MemberVO memberVO, Model model) {
		MemberVO loginUser = memberService.getMember(memberVO);
		
		if(loginUser == null) {
			model.addAttribute("message","아이디와 비밀번호를 확인해주세요");
			
			return "member/login_form";
			
		}else {
			model.addAttribute("loginUser",loginUser);
			return "redirect:index";
		}
	}
	
	/*
	 * 회원가입 페이지로 이동
	 */
	@RequestMapping(value="/join_form")
	public String joinFormView() {
		
		return "member/join_form";
	}
	
	/*
	 * 아이디 중복 확인
	 */
	@ResponseBody
	@RequestMapping(value="/id_check", method=RequestMethod.POST)
	public int idCheck(@RequestBody String id) throws Exception{
		id = id.substring(3);
		
		int result = memberService.findMemberById(id);
		
		return result;
	}
	
	/*
	 * 회원가입 처리
	 */
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(MemberVO memberVO) {
		memberService.insertMember(memberVO);
		
		return "member/login_form";
	}
	
	/*
	 * 아이디, 비밀번호 찾기 페이지 이동
	 */
	@RequestMapping(value="/find_id_form")
	public String findIdView() {
		return "member/find_id_form";
	}
	
	/*
	 * 아이디 찾기 처리
	 */
	@RequestMapping(value="find_id", method=RequestMethod.POST)
	public String findIdAction(MemberVO vo, Model model) {
		MemberVO member = memberService.getMemberByNameAndEmail(vo);
		
		if(member != null) {//해당 회원이 존재
			
			model.addAttribute("result",1);
			
			model.addAttribute("id",member.getId());
			
		}else {//해당 회원이 존재하지 않음
			model.addAttribute("result",-1);
		}
		return "member/find_id_result";
	}
	
	//비밀번호 찾기 처리
	@RequestMapping(value="/find_password",method=RequestMethod.POST)
	public String findPasswordAction(MemberVO vo, Model model) {
		
		MemberVO member = memberService.findPassword(vo);
		if(member != null) {
			
			model.addAttribute("result", 1);
			model.addAttribute("password",member.getPassword());
			
		}else {
			model.addAttribute("result", -1);
		}
		
		return "member/find_pwd_result";
	}
	
	/*
	 * 로그아웃 처리
	 */
	@RequestMapping(value="/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		
		return "redirect:index";
	}
	
	/*
	 * 서재로 이동
	 */
	@RequestMapping(value="/library")
	public String library(MemberVO memberVO, Model model,HttpSession session) {
		
		MemberVO owner = memberService.getMemberById(memberVO);
		
		model.addAttribute("owner",owner);
		
		List<PostVO> coverList = postService.getCoverList(owner.getId());
		model.addAttribute("coverList",coverList);
		
		List<RecordVO> recordList = postService.getRecordList(owner.getId());
		model.addAttribute("recordList",recordList);
		
		return "library/library";
	}
	
	/*
	 * 비밀번호 확인 페이지로 이동
	 */
	@RequestMapping(value="/pass_check_form.do")
	public String psswordCheckFormView() {
		return "member/pass_check_form";
	}
	
	/*
	 * 비밀번호가 일치하는지 확인
	 */
	@RequestMapping(value="/pass_check.do")
	public String passCheck(MemberVO memberVO,Model model) {
		MemberVO result  = memberService.getMember(memberVO);
		
		if(result == null) {
			model.addAttribute("message","비밀번호를 확인해주세요");
			return "member/pass_check_form";
		}else {
			return "member/update_form";
		}
	
	}
	/*
	 * 회원 정보 수정
	 */
	@RequestMapping(value="/update_member.do", method=RequestMethod.POST)
	public String updateMember(@RequestParam(value="new_image")MultipartFile uploadFile,
									MemberVO memberVO,HttpSession session,Model model) {
		
		String fileName = "";
		if(!uploadFile.isEmpty()) {
			String root_path =
					session.getServletContext().getRealPath("WEB-INF/resources/images/member/");
			System.out.println("파일 저장 경로: "+root_path);
			fileName = uploadFile.getOriginalFilename();
			
			File file = new File(root_path + fileName);
			

				try {
					uploadFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				memberVO.setImage(fileName);
				
		}
		
		
		memberService.updateMember(memberVO);
		
		MemberVO loginUser = (MemberVO)memberService.getMemberById(memberVO);
		model.addAttribute("loginUser", loginUser);
		
		return "redirect:index";
		}
	
	/*
	 * 회원 탈퇴 페이지로 이동
	 */
	@RequestMapping(value="/withdraw_form.do")
	public String withdrawView() {
		return "member/withdraw_form";
	}
	
	/*
	 * 회원 탈퇴
	 */
	@RequestMapping(value="/delete_member.do")
	public String deleteMember(MemberVO memberVO) {
		memberService.updateUseyn(memberVO);
		
		return "redirect:index";
	}
	
	//친구 추가
	@RequestMapping(value="/add_friend.do")
	@ResponseBody
	public int addFriend(@RequestParam(value="f_id",required=true)String f_id,
							HttpSession session) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		String id = loginUser.getId();
		int result = memberService.addFriend(id,f_id);
		
		return result;
	}
		
}
