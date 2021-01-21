<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div id="main">
	<article class="post">
		<h1>한 번 탈퇴한 아이디는 복구가 불가능합니다<br>
			탈퇴하시겠습니까?</h1>
		<form name="frm" action="delete_member.do" method="POST">
			<input type="hidden" name="id" value="${loginUser.id}">
			<table>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<th colspan="2">${message}</th>
				</tr>
			</table>
			<input type="submit" value="확인" onclick="return pass_check()">
		</form>	
	</article>
</div>
<%@ include file="../sidebar.jsp"%>