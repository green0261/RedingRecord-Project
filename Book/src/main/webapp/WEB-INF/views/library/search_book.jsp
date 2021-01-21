<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<title>책 등록하기</title>
</head>
<body>
	<form name="frm" method="GET">
		<select id="option" size="1">
			<option value="title">제목
			<option value="author">저자
		</select>
		<label for="keywork">도서명</label>
		<input type="text" name="keyword" id="keyword">
		<input type="button" value="검색" onclick="go_search()">		
	</form>
</body>
</html>