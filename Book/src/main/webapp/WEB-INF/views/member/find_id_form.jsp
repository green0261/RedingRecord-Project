<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="row col-12">
	<div class="off-5">
		<form method="POST" name="frm">
			<h1>아이디 찾기</h1>
			<div class="form-group">
				<label for="name">이름</label> <input type="text" class="form-control" id="name" name="name" placeholder="이름">
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email" class="form-control" id="email" name="email" placeholder="이메일">
			</div>
			<input type="button" value="아이디 찾기" onclick="find_id()">
		</form>
		<hr>
		<form method="POST" name="p_frm">
			<h1>비밀번호 찾기</h1>
			<div class="form-group">
				<label for="id">아이디</label> <input type="text" class="form-control" id="id" name="id" placeholder="아이디">
			</div>
			<div class="form-group">
				<label for="name">이름</label> <input type="text" class="form-control" id="name" name="name" placeholder="이름">
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email" class="form-control" id="email" name="email" placeholder="이메일">
			</div>
			<input type="button" value="비밀번호찾기" onclick="find_pwd()">
		</form>
	</div>
</div>
<script type="text/javascript" src="js/member.js"></script>