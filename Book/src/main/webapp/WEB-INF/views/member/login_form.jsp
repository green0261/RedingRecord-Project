<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="row col-12">
	<div class="off-5">
		<form method="POST" name="frm">
			<h1>로그인</h1>
			<div class="form-group">
				<label for="id">아이디</label>
				<input type="text" class="form-control" id="id" name="id" placeholder="아이디">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
			</div>
			<a href="admin_login_form">관리자 로그인</a><br>
			<a href="find_id_form">아이디/비밀번호 찾기</a><br> <a href="join_form">회원가입</a>
			<h4><span style="color: red">${message}</span></h4>
			<input type="button" value="로그인" onclick="login_check()">
		</form>
	</div>
</div>
<script type="text/javascript" src="js/member.js"></script>