package com.book.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.book.record.member.MemberService;
import com.book.record.member.MemberVO;
import com.book.record.post.PostService;
import com.book.record.post.PostVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model, HttpSession session, MemberVO memberVO) {

		List<PostVO> postList = postService.getPostList();
		model.addAttribute("postList", postList);

		return "index";
	}

}
