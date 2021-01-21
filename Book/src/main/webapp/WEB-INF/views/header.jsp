<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Reading Record</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/member.css" />
<link rel="stylesheet" type="text/css" href="css/post.css" />
<link rel="stylesheet" type="text/css" href="css/library.css" />
<link rel="stylesheet" type="text/css" href="css/qna.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="is-preload">
	<!-- Wrapper -->
	<div id="wrapper">
		<!-- Header -->
		<header id="header">
			<form name="search_form" action="search_keyword" method="get">
				<nav class="navbar navbar-expand-lg">
					<!--샌드위치 화면에 medium사이즈 이하에서 나타나도록 설정-->
					<a class="navbar-brand" href="index">Reading Record</a>
					<button class="navbar-toggler navbar-dark " type="button"
						data-toggle="collapse" data-target="#main-navigation"
						aria-controls="main-navigation" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="main-navigation">
						<ul class="navbar-nav">
							<c:choose>
								<c:when test="${loginUser == null}">
									<li class="nav-item"><a class="nav-link" href="login_form">로그인</a></li>
									<li class="nav-item"><a class="nav-link" href="join_form">회원가입</a></li>
									<li><input type="text" name="keyword" id="keyword" placeholder="검색어 입력"></li>
									<li><input type="submit" name="search" id="search" value="검색" onclick="return search_keyword()"></li>
								</c:when>
								<c:otherwise>
									<li class="nav-item">
										<a class="nav-link" href="library?id=${loginUser.id}">
											<span style="font-weight: bold">${loginUser.nick_name}</span>님의 서재
										</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="pass_check_form.do?id=${loginUser.id}">내정보</a></li>
									<li class="nav-item"><a class="nav-link" href="qna_list.do">QnA</a></li>
									<li class="nav-item"><a class="nav-link" href="logout">로그아웃</a></li>
									<li><input type="text" name="keyword" id="keyword" placeholder="검색어 입력"></li>
									<li><input type="submit" name="search" id="search" value="검색" onclick="return search_keyword()"></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</nav>
			</form>
		</header>