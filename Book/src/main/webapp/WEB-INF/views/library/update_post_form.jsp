<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- Main -->
<div id="main">
<!-- Post -->
	<article class="post">
		<form id="frm" method="GET">
			<input type="hidden" name="id" id="id" value="${loginUser.id}">
			<input type="hidden" name="pseq" id="pseq" value="${post.pseq}">
		<header>
			<div class="title">
				<h2>독서 기록</h2>
			</div>
		</header>
			<section class="book_info">
				<span class="cover_image"><img id="book_img" src="${post.cover}"/></span>
				<table>
					<tr>
						<td id="title">${post.title}</td>
					</tr>
					<tr>
						<td id="author">${post.author}</td>
					</tr>
					<tr>
						<td id="pubDate">${post.pubDate}</td>
					</tr>
					<tr>
						<td id="publisher">${post.publisher}</td>
					</tr>
				</table>
					<c:choose>
						<c:when test="${post.readyn == 'Y'}">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="readyn" id="readyn1" value="Y" checked>
								<label class="form-check-label" for="readyn1">
									완독
								</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="readyn" id="readyn2" value="N">
								<label class="form-check-label" for="readyn2">
									미완독
								</label>
							</div>
						</c:when>
						<c:otherwise>
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
						</c:otherwise>
					</c:choose>
			</section>
			<section id="write_form">
				<header>
					<h3>감상을 적어주세요</h3>
				</header>
				<div id="content">
					<table>
						<tr>
							<th>제목</th>
							<td><input type="text" id="post_title" name="post_title" value="${post.post_title}"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea id="p_content" name="p_content" rows="10">${post.p_content}</textarea></td>
						</tr>
					</table>
					<input type="button" value="수정" onclick="update_p_check()">
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