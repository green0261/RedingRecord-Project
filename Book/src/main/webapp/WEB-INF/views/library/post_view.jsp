<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- Main -->
<div id="main">
	<!-- Post -->
	<article class="post">
		<form id="form" name="form" method="POST">
			<input type="hidden" name="id" value="${owner.id}"> <input
				type="hidden" name="pseq" value="${post.pseq}">
			<header id="p_header">
				<div class="title">
					<h2>
						<a href="post_view?pseq=${post.pseq}&id=${post.id}">${post.post_title}</a>
					</h2>
					<p>${post.title}</p>
				</div>
				<div class="meta">
					<fmt:formatDate value="${post.regdate}" type="date" />
					<a href="library?id=${post.id}" class="author">
						<spanclass="name">${post.nick_name}</span>
					</a>
				</div>
			</header>
			<section class="book_info">
				<span class="cover_image"><img src="${post.cover}" /></span>
				${post.title}<br> ${post.author}<br> ${post.publisher}<br>
				${post.pubDate}
			</section>
			<%--리뷰 영역 --%>
			<div id="p_content">
				<p>${post.p_content}</p>
			</div>
			<%--책갈피 영역 --%>
			<section id="records">
				<header>
					<h3>책갈피</h3>
					<c:if test="${loginUser.id==post.id}">
						<input type="button" id="add_btn" value="기록 추가" onclick="add_record_form()">
						<input type="hidden" id="btn_state" value="open">
					</c:if>
				</header>
				<div id="record_list">
					<c:forEach var="record" items="${recordList}">
						<ul class="posts">
							<c:choose>
								<c:when test="${loginUser.id==record.id}">
									<li class="record" onclick="javascript:update_record_form(${record.rseq})">
								</c:when>
								<c:otherwise>
									<li class="record">
								</c:otherwise>
							</c:choose>
							<h5>${record.page}&nbsp;Page</h5>
							<p>${record.content}</p>
							</li>
						</ul>
					</c:forEach>
				</div>
				<table id="record_form">
					<tr>
						<th>page</th>
						<td><input type="text" id="page" name="page"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea id="r_content" name="r_content" rows="4" cols="50"></textarea></td>
					</tr>
					<tr>
						<td colspan="2"><input type="button" value="등록" onclick="add_record_check()"></td>
					</tr>
				</table>
			</section>
			<footer>
				<ul class="stats">
					<c:if test="${loginUser.id == post.id}">
						<li><a href="#" onclick="delete_post()">삭제</a></li>
						<li><a href="#" onclick="update_post_form()">수정</a></li>
					</c:if>
				</ul>
			</footer>
		</form>
		<article id="comments">
			<form name="frm" method="POST">
				<header>
					<input type="hidden" name="id" id="id" value="${owner.id}">
					<input type="hidden" name="pseq" id="pseq" value="${post.pseq}">
					<input type="hidden" name="readyn" value="${readyn}">
					<%--get_books에 필요 --%>
					<h3>댓글</h3>
					<textarea id="content" name="content" rows="4" cols="50" placeholder="댓글을 입력해주세요"></textarea>
					<input type="button" value="댓글등록" onclick="cmt_check()">
				</header>
				<section class="comment">
					<table>
						<c:forEach var="comment" items="${commentList}">
							<input type="hidden" name="cseq" id="cseq" value="${comment.cseq}">
							<tr>
								<td><a href="library?id=${comment.id}">${comment.nick_name}</a></td>
								<td>${comment.content}</td>
								<td class="right"><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${comment.regdate}" /><br>
									<c:if test="${loginUser.id == comment.id}">
										<div class="deleteBtn" onclick="javascript:delete_cmt(${comment.cseq})">삭제</div>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</section>
			</form>
		</article>
		<c:choose>
			<c:when test="${readyn==null}">
				<input type="button" value="목록으로" onclick="history.go(-1)">
			</c:when>
			<c:otherwise>
				<input type="button" value="목록"
					onclick="go_list('${criteria.pageNum}','${criteria.numPerPage}')">
			</c:otherwise>
		</c:choose>
	</article>
</div>
<c:choose>
	<c:when test="${loginUser == null}">
		<%@include file="../sidebar.jsp"%>
	</c:when>
	<c:otherwise>
		<%@include file="sidebar.jsp"%>
	</c:otherwise>
</c:choose>