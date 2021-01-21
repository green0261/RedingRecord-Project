<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="sub_menu.jsp"%>
<div class="row col-10">
	<article id="qna" class="off-0">
		<header>
			<h2>QnA 게시판</h2>
		</header>
		<form id="qna_form" name="qna_form" method="GET">
			<input type="hidden" id="qseq" name="qseq" value="${qna.qseq}">
			<table id="qna">
				<tr>
					<th>제목</th>
					<td>${qna.qna_title}</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td><fmt:formatDate value="${qna.indate}" type="date" /></td>
				</tr>
				<tr>
					<th>질문내용</th>
					<td>${qna.qna_content}</td>
				</tr>
				<c:if test="${qna.rep=='Y'}">
					<tr>
						<th>답변 내용</th>
						<td>${qna.reply}
					</tr>
				</c:if>
				<c:if test="${qna.id == loginUser.id}">
					<tr>
						<td colspan="2"><input type="button" value="삭제하기" onclick="delete_qna()">
					</tr>
				</c:if>
			</table>
			<div class="clear"></div>
			<div id="buttons" style="float: right">
				<input type="button" value="목록으로"
					onclick="go_list('${criteria.pageNum}', '${criteria.numPerPage}')">
			</div>
		</form>
	</article>
</div>
<script type="text/javascript" src="js/qna.js"></script>
