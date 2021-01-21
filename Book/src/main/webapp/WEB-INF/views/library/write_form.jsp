<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- Main -->
<div id="main">
<!-- Post -->
	<article class="post">
		<form id="frm" method="GET">
			<input type="hidden" id="title" name="title">
			<input type="hidden" id="author" name="author">
			<input type="hidden" id="pubDate" name="pubDate">
			<input type="hidden" id="publisher" name="publisher">
			<input type="hidden" id="cover" name="cover">
			<input type="hidden" id="isbn" name="isbn">
		<header>
			<div class="title">
				<h3>독서 기록</h3>
			</div>
		</header>
			<section class="book_info">
				<span class="cover_image"><img id="book_img" src="images/book/default.jpg"/></span>
				<table>
					<tr>
						<td id="book_title">${book.title}</td>
					</tr>
					<tr>
						<td id="book_author">${book.author}</td>
					</tr>
					<tr>
						<td id="book_pubDate">${book.pubDate}</td>
					</tr>
					<tr>
						<td id="book_publisher">${book.publisher}</td>
					</tr>
				</table>
				<input type="button" value="책 등록하기" onclick="search_book_form()">
			</section>
			<section id="write_form">
				<header>
					<h3>감상을 적어주세요</h3>
				</header>
				<div id="content">
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" id="post_title" name="post_title"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea id="p_content" name="p_content" rows="10"></textarea></td>
						</tr>
						<tr>
							<th>완독/미완독</th>
							<td>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="readyn" id="readyn1" value="Y">
									<label class="form-check-label" for="readyn1">
								   	 	완독
								 	</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="readyn" id="readyn2" value="N" checked>
									<label class="form-check-label" for="readyn2">
										미완독
									</label>
								</div>
							</td>
						</tr>
					</table>
					<input type="button" value="등록하기" onclick="write_check()">
				</div>
			</section>
		</form>
	</article>
</div>
<c:choose>
	<c:when test="${loginUser == null}">
		<%@include file="../sidebar.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file="sidebar.jsp" %>
	</c:otherwise>
</c:choose>