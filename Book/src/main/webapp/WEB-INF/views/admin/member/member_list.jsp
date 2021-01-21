<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sub_menu.jsp"%>
<article>
	<h1>회원리스트</h1>
	<form name="frm" method="post" action="admin_member_list">
		<table style="float: right;">
			<tr>
				<td>회원 이름 
					<input type="text" name="key" id="key">
					<input type="submit" class="btn" value="검색">
				</td>
			</tr>
		</table>
		<br>
		<table id="memberList">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>닉네임</th>
				<th>가입일</th>
				<th>비고</th>
			</tr>
			<c:choose>
				<c:when test="${memberListSize <= 0}">
					<tr>
						<td width="100%" colspan="7" align="center" height="23">
							회원이 존재하지 않습니다
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${memberList}" var="member">
						<tr>
							<td>${member.id}</td>
							<td>${member.name}</td>
							<td>${member.email}</td>
							<td>${member.nick_name}</td>
							<td><fmt:formatDate value="${member.regdate}" /></td>
							<td>
								<c:choose>
									<c:when test='${member.useyn=="Y"}'>
										활동회원
      								</c:when>
									<c:otherwise>
       									탈퇴
      								</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
	<%@ include file="page_area.jsp"%>
</article>
<%@ include file="../footer.jsp"%>
</body>
</html>