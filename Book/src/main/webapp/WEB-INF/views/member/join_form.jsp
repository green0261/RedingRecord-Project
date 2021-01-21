<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row col-8">
	<div class="off-1">
		<form method="POST" action="join" name="frm">
			<h1>회원가입</h1>
			<div class="form-group">
				<label for="id">아이디</label> <input type="text" class="form-control" id="id" name="id" placeholder="아이디" autocomplete="off" oninput="id_check()">
				<input type="hidden" id="checked_id">
				<h4><span class="msg" style="color: green"></span></h4>
			</div>
			<div class="form-group">
				<label for="name">이름</label> <input type="text" class="form-control" id="name" name="name" placeholder="이름">
			</div>
			<div class="form-group">
				<label for="nick_name">별명</label> <input type="text" class="form-control" id="nick_name" name="nick_name" placeholder="별명">
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email" class="form-control" id="email" name="email" placeholder="이메일">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
			</div>
			<div class="form-group">
				<label for="repass">비밀번호 확인</label> <input type="password" class="form-control" id="repass" placeholder="비밀번호 확인">
			</div>
			<button type="submit" onclick="return join_check()">회원가입</button>
		</form>
	</div>
</div>
<%@include file="../sidebar.jsp"%>
