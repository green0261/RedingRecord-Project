<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- Posts List -->
<div class="row col-8">
	<section class="main off-1">
		<form name="frm" method="GET">
			<input type="hidden" id="id" name="id" value="${owner.id}">
			<ul class="posts">
				<h3>${title}&lt;${postListSize}&gt;</h3>
				<c:choose>
					<c:when test="${postListSize<=0}">
				${title}가 없습니다
				</c:when>
					<c:otherwise>
						<c:forEach var="post" items="${postList}">
							<li>
								<article>
									<header>
										<h3>
											<a href="post_view${pageMaker.makeQuery(pageMaker.cri.pageNum)}&id=${owner.id}&pseq=${post.pseq}&readyn=${readyn}">
												<span class="bold">&lt;${post.post_title}&gt;</span>
											</a>
										</h3>
										<fmt:formatDate value="${post.regdate}" type="date" />
										<br>
									</header>
									<a href="post_view${pageMaker.makeQuery(pageMaker.cri.pageNum)}&id=${owner.id}&pseq=${post.pseq}&readyn=${readyn}" class="image">
										<img src="${post.cover}" alt="${cover.book_title}" />
									</a>
								</article>
							</li>
						</c:forEach>
						<span style="color: rec">${paging}</span>
					</c:otherwise>
				</c:choose>
			</ul>
		</form>
		<%@include file="../page_area.jsp"%>
	</section>
</div>
<c:choose>
	<c:when test="${loginUser == null}">
		<%@include file="../sidebar.jsp"%>
	</c:when>
	<c:otherwise>
		<%@include file="sidebar.jsp"%>
	</c:otherwise>
</c:choose>