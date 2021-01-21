<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div id="main">
	<section class="post">
		<form name="frm" method="POST" enctype="multipart/form-data">
			<h1>정보수정</h1>
			<a href="withdraw_form">회원 탈퇴</a>
			<input type="hidden" id="id" name="id" value="${loginUser.id}">
			<input type="hidden" id="image" name="image" value="${loginUser.image}">
			<img src="images/member/${loginUser.image}">
			<input type="file" id="new_image" name="new_image">
			<table>
				<tr>
					<th>아이디</th>
					<td>${loginUser.id}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${loginUser.name}</td>
				</tr>
				<tr>
					<th>별명</th>
					<td><input type="text" id="nick_name" name="nick_name" value="${loginUser.nick_name}"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" id="email" name="email" value="${loginUser.email}"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" id="repass" name="repass"></td>
				</tr>
			</table>
			<input type="button" value="정보수정" onclick="update_member()">
		</form>
	</section>
</div>
<%@ include file="../sidebar.jsp"%>
