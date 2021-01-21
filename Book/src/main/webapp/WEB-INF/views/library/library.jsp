<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<section id="library">
	<section class="main">
		<h5>${owner.nick_name}님의 책장</h5>
		<section id="bookCover">
			<div class="covers">
					<c:choose>
						<c:when test="${coverList.isEmpty()}">
							<h5>책장이 비어있습니다</h5>
						</c:when>
						<c:otherwise>
							<c:forEach var="cover" items="${coverList}">
								<div class="cover">
									<a href="post_view?pseq=${cover.pseq}&id=${owner.id}" class="image"><img src="${cover.cover}"/></a>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
		</section>
		<!-- Record List -->
			<section id="records">
				<header>
					<h5>${owner.nick_name}님의 책갈피</h5>
				</header>
				<c:choose>
					<c:when test="${recordList.isEmpty()}">
						<p>글이 없습니다</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="record" items="${recordList}">
							<ul class="posts">
								<li class="record" onclick="location.href='post_view?id=${record.id}&pseq=${record.pseq}'">		
									<h5>${record.page}&nbsp;Page</h5>
									<p>${record.content}</p>
								</li>
							</ul>
						</c:forEach>
				</c:otherwise>
			</c:choose>
		</section>
	</section>
</section>
<c:choose>
	<c:when test="${loginUser == null}">
		<%@include file="../sidebar.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file="sidebar.jsp" %>
	</c:otherwise>
</c:choose>