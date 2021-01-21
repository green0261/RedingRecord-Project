<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="sub_menu" class="list-group">
	<a href="qna_list.do" class="list-group-item list-group-item-action" aria-current="true">
	 	전체 문의글 보기
	</a>
	<a href="qna_list.do?id=${loginUser.id}" class="list-group-item list-group-item-action">
		내 문의글 보기
	</a>
	<a href="qna_write_form.do" class="list-group-item list-group-item-action">
		문의글 작성하기
	</a>
	<a href="index" class="list-group-item list-group-item-action">
		메인으로 돌아가기
	</a>
</div>