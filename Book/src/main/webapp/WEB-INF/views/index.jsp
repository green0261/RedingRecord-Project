<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<!-- Main -->
<div id="main">
	<h4>최근 올라온 감상문</h4>
	<!-- Post -->
	<c:forEach var="post" items="${postList}">
		<article class="post">
			<form name="frm" id="frm" method="POST">
				<input type="hidden" id="id" name="id" value="${post.id}">
				<header>
					<div class="title">
						<h5>
							<a href="post_view?pseq=${post.pseq}&id=${post.id}">&lt;${post.title}&gt;</a>
						</h5>
					</div>
					<div class="meta">
						<fmt:formatDate value="${post.regdate}" type="date" />
						<a href="library?id=${post.id}" class="author">
							<span class="name">${post.nick_name}</span>
						</a>
					</div>
				</header>
				<p>${post.p_content}</p>
			</form>
		</article>
	</c:forEach>
</div>
<%@include file="sidebar.jsp"%>
