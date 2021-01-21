<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<article>
	<h1>Q&amp;A 게시글 리스트</h1>
	<form name="frm" method="post">
		<input type="hidden" name="qseq">
		<table id="qnaList">
			<tr>
				<th>번호(답변여부)</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${qnaList}" var="qna">
				<tr>
					<td>${qna.qseq}
						<c:choose>
							<c:when test='${qna.rep=="N"}'>
								(미처리)
							</c:when>
							<c:otherwise>
								(답변처리완료)
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a href="admin_qna_detail${pageMaker.makeQuery(pageMaker.cri.pageNum)}&qseq=${qna.qseq}">
							${qna.qna_title}
						</a>
					</td>
					<td>${qna.id}</td>
					<td><fmt:formatDate value="${qna.indate}"/></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<%@include file="page_area.jsp"%>
</article>
<%@ include file="../footer.jsp"%>
</body>
</html>