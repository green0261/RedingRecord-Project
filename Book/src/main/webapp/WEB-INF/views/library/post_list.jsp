<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script type="text/javascript" src="js/post_list.js"></script>
<!-- Posts List -->
<div class="row col-8">
	<section class="main off-1">
		<form name="frm" method="GET">
			<input type="hidden" id="id" name="id" value="${loginUser.id}">
		<ul class="posts">
			<h3>${title}</h3>
			<c:choose>
				<c:when test="${postList.isEmpty()}">
				새로 올라온 리뷰가 없습니다
				</c:when>
				<c:otherwise>
			<c:forEach var="post" items="${postList}">
				<li>
					<article>
						<header>
							<c:choose>
								<c:when test="${post.days == 0}">
									<h3><a href="post_view?pseq=${post.pseq}&id=${post.id}"><span class="bold">${post.nick_name}</span>님이 <span class="bold">오늘</span> <span class="bold">&lt;${post.post_title}&gt;</span> 리뷰를 작성했습니다</a></h3>
								</c:when>
								<c:otherwise>
									<h3><a href="post_view?pseq=${post.pseq}&id=${post.id}"><span class="bold">${post.nick_name}</span>님이 <span class="bold">${post.days}</span>일 전 <span class="bold">&lt;${post.post_title}&gt;</span> 리뷰를 작성했습니다</a></h3>
								</c:otherwise>
							</c:choose>
						</header>
						<a href="post_view?pseq=${post.pseq}&id=${post.id}" class="image"><img src="${post.cover}" alt="${cover.book_title}" /></a>
					</article>
				</li>
			</c:forEach>
			</c:otherwise>
			</c:choose>
		</ul>
		</form>
	</section>
</div>
<c:choose>
	<c:when test="${loginUser == null}">
		<%@include file="../sidebar.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file="sidebar.jsp" %>
	</c:otherwise>
</c:choose>