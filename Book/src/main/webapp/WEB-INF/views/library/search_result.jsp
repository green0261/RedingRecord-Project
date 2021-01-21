<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<!-- Main -->
	<!-- <div class="wrapper">-->
		<div id="main">
			<h1>검색 결과(${postListSize})</h1>
			<c:if test="${postListSize<=0}">
				<h3>해당 도서의 리뷰가 없습니다</h3>
			</c:if>
			<c:forEach var="post" items="${postList}">
				<article class="post">
					<form name="frm" id="frm" method="POST">
						<input type="hidden" id="id" name="id" value="${post.id}">
						<header>
							<div class="title">
								<h2><a href="post_view${pageMaker.makeQuery(pageMaker.cri.pageNum)}&id=${post.id}&pseq=${post.pseq}">${post.title}</a></h2>
								<p><a href="library?id=${post.id}">${post.nick_name}</a>님의 독서 기록</p>
							</div>
							<div class="meta">
								<fmt:formatDate value="${post.regdate}" type="date"/><br>
								&lt;${post.post_title}&gt;
							</div>
						</header>
						<p>${post.content}</p>
					</form>
				</article>
			</c:forEach>
				<%@include file="search_page_area.jsp" %>
		</div>
<c:choose>
	<c:when test="${loginUser == null}">
		<%@include file="../sidebar.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file="sidebar.jsp" %>
	</c:otherwise>
</c:choose>
