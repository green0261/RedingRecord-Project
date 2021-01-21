<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<div class="row col-11">
	<div class="off-4">
		<br><br>
		<c:choose>
			<c:when test="${result == 1}">
				회원님의 아이디는 <span style="font-weight:bold">${id}</span> 입니다<br><br>
				<input type="button" value="로그인하기" onclick="location.href='login_form'">
				<input type="button" value="메인으로" onclick="location.href='index'">
		  	</c:when>
		  	<c:otherwise>
			  	일치하는 정보가 없습니다<br><br>
			  	<input type="button" value="아이디 찾기" onclick="location.href='find_id_form'">
				<input type="button" value="메인으로" onclick="location.href='index'">
		  </c:otherwise>
		</c:choose>
	</div>
</div>