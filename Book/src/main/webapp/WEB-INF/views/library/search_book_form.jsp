<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/write_form.js"></script>
<link rel="stylesheet" type="text/css" href="css/library.css"/>
<title>책 등록하기</title>
</head>
	<body>
		<article id="search_book">
			<header>
				<h2 class="center-block">도서 검색</h2>
			</header>
			<form name="frm" method="GET">
				<select id="option" name="option">
					<option value="title" selected>제목
					<option value="author">저자
				</select>
				<input type="text" name="keyword" id="keyword">
				<input type="button" value="검색" onclick="search_check()">
			</form>
			<div id="result">
			</div>
			<nav aria-label="Page navigation example">
		  		<ul class="pagination">
		  		</ul>
			</nav>
		</article>
	</body>
</html>