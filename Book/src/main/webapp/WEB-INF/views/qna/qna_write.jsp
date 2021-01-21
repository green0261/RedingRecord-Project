<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="sub_menu.jsp"%>
<div class="row col-10">
	<article id="qna" class="off-0">
		<h2>문의글 작성</h2>
		<form id="form" name="form" method="post" action="qna_write.do">
			<input type="hidden" id="id" name="id" value="${loginUser.id}">
			<table id="qna">
				<tr>
					<th>제목</th>
					<td><input type="text" id="qna_title" name="qna_title" size="20"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="8" cols="20" id="qna_content" name="qna_content"></textarea></td>
				</tr>
			</table>
			<div id="buttons" style="float: right">
				<input type="button" value="글쓰기" onclick="qna_write_check()">
			</div>
		</form>
	</article>
</div>
<script type="text/javascript" src="js/qna.js"></script>