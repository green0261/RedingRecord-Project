<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="sub_menu.jsp"%>
<div class="row col-10">
	<article id="qna" class="off-0">
		<header>
			<h2>QnA 게시판</h2>
		</header>
		<form name="qna_form" method="post">
			<table id="qna">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
					<th>답변 여부</th>
				</tr>
				<c:if test="${qnaCnt==0}">
      				문의글이 없습니다
      			</c:if>
				<c:forEach items="${qnaList}" var="qna">
					<input type="hidden" id="id" name="id" value="${qna.id}">
					<input type="hidden" id="qseq" name="qseq" value="${qna.qseq}">
					<tr>
						<td>${qna.qseq}</td>
						<td><a href="qna_view${pageMaker.makeQuery(pageMaker.cri.pageNum)}&qseq=${qna.qseq}">${qna.qna_title}</a></td>
						<td><fmt:formatDate value="${qna.indate}" type="date" /></td>
						<td>
							<c:choose>
								<c:when test="${qna.rep=='Y'}"> 완료 </c:when>
								<c:when test="${qna.rep=='N'}"> 미완료 </c:when>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@include file="page_area.jsp"%>
			<div class="clear"></div>
			<div id="buttons" style="float: right">
				<input type="button" value="문의글 작성하기" class="submit" onclick="location.href='qna_write_form.do'">
				<input type="button" value="메인으로 이동" class="cancel" onclick="location.href='index'">
			</div>
		</form>
	</article>
</div>