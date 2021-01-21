package com.book.view.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.record.book.BookService;
import com.book.record.book.BookVO;
import com.book.record.book.LibraryStatistics;
import com.book.record.book.ReadynStatistics;
import com.book.record.comment.CommentService;
import com.book.record.comment.CommentVO;
import com.book.record.member.MemberService;
import com.book.record.member.MemberVO;
import com.book.record.post.PostService;
import com.book.record.post.PostVO;
import com.book.record.post.RecordVO;
import com.book.record.utils.Criteria;
import com.book.record.utils.PageMaker;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private BookService bookService;

	
	/*
	 * 리뷰 목록보기
	 */
	@RequestMapping(value = "/get_books")
	public String getBookByreadyn(MemberVO memberVO, PostVO postVO, Criteria criteria, Model model) {

		MemberVO owner = memberService.getMemberById(memberVO);
		model.addAttribute("owner", owner);

		List<PostVO> postList = postService.getBookByReadyn(postVO, criteria);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);

		// 조회할 총 개수
		int postCnt = postService.countPostList(postVO);
		pageMaker.setTotalCount(postCnt);
		System.out.println("페이지 정보:" + pageMaker);

		model.addAttribute("postList", postList);
		model.addAttribute("postListSize", postCnt);
		model.addAttribute("pageMaker", pageMaker);

		if (postVO.getReadyn().equals("Y")) {
			model.addAttribute("title", "완독 도서");
			model.addAttribute("readyn", "Y");
		} else if (postVO.getReadyn().equals("N")) {
			model.addAttribute("title", "미완독 도서");
			model.addAttribute("readyn", "N");
		} else {
			model.addAttribute("title", "전체 도서");
		}

		return "library/my_book";

	}

	/*
	 * 리뷰 상세화면으로 이동
	 */
	@RequestMapping(value = "/post_view")
	public String postView(@RequestParam(value = "pseq") String pseq,
			@RequestParam(value = "readyn", required = false) String readyn, MemberVO memberVO,
			Criteria criteria, Model model) {
		
		MemberVO owner = memberService.getMemberById(memberVO);
		model.addAttribute("owner", owner);

		PostVO post = postService.getPost(pseq); // 게시글 불러오기
		model.addAttribute("post", post);

		List<RecordVO> recordList = postService.getRecordByPseq(pseq); // 게시글 책갈피 불러오기
		model.addAttribute("recordList", recordList);

		List<CommentVO> commentList = commentService.getComments(pseq); // 게시글 댓글 불러오기
		model.addAttribute("commentList", commentList);

		model.addAttribute("criteria", criteria);

		model.addAttribute("readyn", readyn);

		return "library/post_view";

	}

	/*
	 * 리뷰 삭제
	 */
	@RequestMapping(value = "/delete_post.do")
	public String deletePost(@RequestParam(value = "pseq") String pseq, HttpSession session) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String id = loginUser.getId();
		commentService.deleteCommentByPseq(pseq);
		postService.deleteRecordByPseq(pseq);
		postService.deletePost(pseq);

		return "redirect:library?id=" + id;
	}
	
	/*
	 * 리뷰 수정 페이지로 이동
	 */
	@RequestMapping(value="/update_post_form.do")
	public String updatePostView(@RequestParam(value="pseq", required=true)String pseq,
								MemberVO memberVO,Model model) {
		
		MemberVO owner = memberService.getMemberById(memberVO);
		model.addAttribute("owner", owner);

		PostVO post = postService.getPost(pseq); // 게시글 불러오기
		model.addAttribute("post", post);
		
		return "library/update_post_form";
	}
	
	/*
	 * 리뷰 수정 처리
	 */
	@RequestMapping(value="/update_post.do")
	public String updatePost(PostVO postVO) {
		postService.updatePost(postVO);
		
		String pseq = postVO.getPseq();
		String id = postVO.getId();
		return "redirect:post_view"+"?pseq="+pseq+"&id="+id;
	}
	

	/*
	 * 리뷰 등록 페이지로 이동
	 */
	@RequestMapping(value = "/go_write.do")
	public String writePostView(MemberVO memberVO, Model model) {
		MemberVO owner = memberService.getMemberById(memberVO);
		model.addAttribute("owner", owner);
		return "library/write_form";
	}

	/*
	 * 리뷰 등록
	 */
	@RequestMapping(value = "/write_post.do")
	public String writePost(PostVO postVO, BookVO bookVO, HttpSession session) {


		// 이미 DB에 저장되어 있는 도서인지 확인
		int result = bookService.getBookByIsbn(bookVO.getIsbn());


		if (result <= 0) { // 저장되어 있지 않으면
			bookService.insertBook(bookVO); // Book 테이블에 저장
		}

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String id = loginUser.getId();
		postVO.setId(loginUser.getId());
		postService.insertPost(postVO);

		return "redirect:library" + "?id=" + id;

	}

	/*
	 * 책갈피 등록
	 */
	@RequestMapping(value = "/insert_record.do")
	@ResponseBody
	public int insertRecord(RecordVO recordVO, HttpSession session) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		recordVO.setId(loginUser.getId());
		int result = postService.insertRecord(recordVO);

		return result;
	}

	/*
	 * 리뷰할 도서 검색 페이지로 이동
	 */
	@RequestMapping(value = "/search_book_form.do")
	public String searchBookView() {
		return "library/search_book_form";
	}

	/*
	 * 책갈피 수정 페이지로 이동
	 */
	@RequestMapping(value = "/update_record_form.do")
	public String updateRecordForm(@RequestParam(value = "rseq") String rseq, Model model) {

		RecordVO record = postService.getRecord(rseq);
		model.addAttribute("record", record);
		
		return "library/update_record_form";
	}

	/*
	 * 책갈피 수정
	 */
	@RequestMapping(value = "/update_record.do")
	@ResponseBody
	public int updateRecord(RecordVO recordVO) {
		
		int result = postService.updateRecord(recordVO);
		
		return result;
	}

	/*
	 * 댓글 등록
	 */
	@RequestMapping(value = "/write_comment.do", method = RequestMethod.POST)
	@ResponseBody
	public int writeComment(CommentVO commentVO, HttpSession session) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		String login_id = loginUser.getId(); // 현재 로그인 중인 아이디
		commentVO.setId(login_id);

		int result = commentService.writeComment(commentVO); // 등록 성공 시 1 리턴
		return result;
	}

	/*
	 * 댓글 삭제
	 */
	@RequestMapping(value = "/delete_comment.do")
	@ResponseBody
	public int deleteComment(CommentVO commentVO) {
		int result = commentService.deleteComment(commentVO);

		return result;
	}

	/*
	 * 책갈피 삭제
	 */
	@RequestMapping(value = "/delete_record.do")
	@ResponseBody
	public int deleteRecord(@RequestParam(value = "rseq",defaultValue="-1",required=true) String rseq) {
		int result = postService.deleteRecord(rseq);
		
		return result;

	}

	/*
	 * 검색 기능
	 */
	@RequestMapping(value = "/search_keyword")
	public String searchKeyword(@RequestParam(value = "keyword", defaultValue = "", required = true) String keyword,
			Criteria criteria, Model model, HttpSession session) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser != null) {
			model.addAttribute("owner", loginUser);
		}
		List<PostVO> postList = postService.getPostListByKeyword(keyword, criteria);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);


		// 조회할 총 개수
		int postCnt = postService.countSearchedPost(keyword);
		pageMaker.setTotalCount(postCnt);
		System.out.println("페이지 정보: " + pageMaker);

		model.addAttribute("keyword", keyword);
		model.addAttribute("postList", postList);
		model.addAttribute("postListSize", postCnt);
		model.addAttribute("pageMaker", pageMaker);

		return "library/search_result";

	}

	/*
	 * 친구 최신글 보기
	 */
	@RequestMapping(value = "/friend_post.do")
	public String goFriendPost(@RequestParam(value = "id", required = true) String id,
								@RequestParam(value = "startNum", defaultValue = "0", required = true) String startNum,
								@RequestParam(value = "endNum", defaultValue = "3", required = true) String endNum,
								Model model,
								HttpSession session) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		List<PostVO> postList = postService.getFriendPost(id, startNum, endNum);
		model.addAttribute("postList", postList);
		model.addAttribute("owner", loginUser);
		model.addAttribute("title", "내 친구 최신글");

		return "library/post_list";

	}

	// 친구 최신 글 불러오기
	@RequestMapping(value = "/get_friend_post.do")
	@ResponseBody
	public Object getFriendPost(@RequestParam Map<String, Object> param, Model model) {
		String id = (String) param.get("id");
		String startNum = (String) param.get("startNum");
		String endNum = (String) param.get("endNum");

		List<PostVO> postList = postService.getFriendPost(id,startNum,endNum);
		model.addAttribute("title","내 친구 최신글");
		
		return postList;

	}
	/*
	 * 리뷰 통계 페이지로 이동
	 */
	@RequestMapping(value="/show_stats")
	public String showStatisView(MemberVO memberVO,Model model) {
		
		MemberVO owner = memberService.getMemberById(memberVO);
		model.addAttribute("owner",owner);
		
		return "library/library_statistics";
	}
	
	/*
	 * 리뷰 통계 조회
	 */
	@RequestMapping(value="/library_statistics",
					produces="application/json; charset=utf-8")
	@ResponseBody
	public List<LibraryStatistics> salesRecordChart(@RequestParam(value="id",defaultValue="abc",required=true)String id,
													@RequestParam(value="year",defaultValue="2020",required=true)String year){ 
	
		
		List<LibraryStatistics> list = postService.getLibraryStatistics(id,year);
		
		
		return list;
	}
	
	/*
	 * 완,미완독 도서 통계 조회
	 */
	@RequestMapping(value="/readyn_statistics",
					produces="application/json; charset=utf-8")
	@ResponseBody
	public List<ReadynStatistics> readynChart(@RequestParam(value="id",defaultValue="abc",required=true)String id,
											@RequestParam(value="year",defaultValue="2020",required=true)String year){
		
		List<ReadynStatistics> list = postService.getReadynStatistics(id,year);
		
		return list;
	}
	
}
