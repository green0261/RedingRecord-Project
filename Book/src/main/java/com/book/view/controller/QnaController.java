package com.book.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.record.member.MemberVO;
import com.book.record.qna.QnaService;
import com.book.record.qna.QnaVO;
import com.book.record.utils.Criteria;
import com.book.record.utils.PageMaker;

@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	/*
	 * qna 게시판으로 이동
	 */
	@RequestMapping(value="/qna_list.do")
	public String qnaList(@RequestParam(value="id",defaultValue="",required=true)String id,
						MemberVO memberVO,Criteria criteria,Model model,HttpSession session) {
		
		List<QnaVO> quaList = qnaService.getQnaList(id,criteria);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		//조회된 총 qna 개수
		int qnaCnt = qnaService.countQna(id);
		pageMaker.setTotalCount(qnaCnt);
		System.out.println("페이지 정보: "+pageMaker);
		
		model.addAttribute("qnaList",quaList);
		model.addAttribute("qnaCnt",qnaCnt);
		model.addAttribute("pageMaker",pageMaker);
		model.addAttribute("id",id);
		
		return "qna/qna_list";
	}
	/*
	 * 문의글 상세보기
	 */
	@RequestMapping(value="/qna_view")
	public String qnaView(@RequestParam(value="qseq")String qseq,
							Criteria criteria,HttpSession session,Model model) {
		
		QnaVO qnaVO = qnaService.getQna(qseq);
		model.addAttribute("qna",qnaVO);
		
		model.addAttribute("criteria",criteria);
		
		return "qna/qna_view";
	}
	
	/*
	 * 문의글 작성 페이지로 이동
	 */
	@RequestMapping(value="/qna_write_form.do")
	public String qnaWriteView() {
		
		return "qna/qna_write";
	}
	
	/*
	 * 문의글 작성
	 */
	@RequestMapping(value="/qna_write.do")
	public String qnaWrite(@RequestParam(value="id",defaultValue="",required=true)String id,
							QnaVO qnaVO, HttpSession session) {
		qnaVO.setId(id);
		qnaService.insertQna(qnaVO);
		return "redirect:qna_list.do";
	}
	/*
	 * 문의글 삭제
	 */
	@RequestMapping(value="/qna_delete.do")
	public String qnaDelete(@RequestParam(value="qseq")String qseq) {
		qnaService.deleteQna(qseq);
		
		return "redirect:qna_list.do";
	}
	
}
