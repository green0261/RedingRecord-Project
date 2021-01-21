<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div id="main">
	<article class="post">
		<p>본인확인을 위해 비밀번호를 입력해주세요</p>
		<form name="frm" action="pass_check.do" method="POST">
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