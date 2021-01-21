<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/library.js"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>책갈피 수정</title>
</head>
<body>
	<form id="frm" name="frm" method="GET">
		<h1>글 기록 수정</h1>
		<input type="hidden" id="pseq" name="pseq" value="${record.pseq}">
		<input type="hidden" id="rseq" name="rseq" value="${record.rseq}">
		<table>
			<tr>
				<th>페이지</th>
				<td><input type="text" id="page" name="page" value="${record.page}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea id="content" name="content">${record.content}</textarea>
				</td>
			</tr>
		</table>
		<c:if test="${record.id == loginUser.id}">
			<input type="button" value="수정하기" onclick="javascript:update_check()">
			<input type="button" value="삭제하기" onclick="javascirpt:delete_record()">
		</c:if>
	</form>
</body>
</html>