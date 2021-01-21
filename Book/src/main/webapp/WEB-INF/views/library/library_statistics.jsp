<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
</script>
<div id="main">
	<article class="statistics">
		<input type="hidden" id="id" name="id" value="${owner.id}">
		<div align="center">
			<h1>서재 통계</h1>
			<input type="button" value="2020년" onclick="library_statistics(2020)">
			<input type="button" value="2021년" onclick="library_statistics(2021)">
			<table>
				<tr>
					<td><div id="barchart_div"></div></td>
					<td><div id="piechart_div"></div></td>
				</tr>
			</table>
		</div>
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