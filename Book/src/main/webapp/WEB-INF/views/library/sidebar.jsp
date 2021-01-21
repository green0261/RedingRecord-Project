<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Sidebar -->
<section id="sidebar">
<!-- Intro -->
	<section id="intro">
		<c:choose>
			<c:when test="${loginUser.id==owner.id}">
				<a href="library?id=${owner.id}" class="logo"><img src="images/member/${owner.image}"/></a>
				<header>
					<h4><a href="library?id=${owner.id}">${owner.nick_name}</a></h4>
					<p>${owner.nick_name}님의 서재입니다</p>
				</header>
			</c:when>
			<c:otherwise>
				<a href="library?id=${owner.id}" class="logo"><img src="images/member/${owner.image}"/></a>
				<header>
					<h2><a href="library?id=${owner.id}">${owner.nick_name}</a></h2>
					<p>${owner.nick_name}님의 서재입니다</p>
					<input type="button" value="친구 추가하기" onclick="add_friend('${owner.id}')">
				</header>
			</c:otherwise>
		</c:choose>
	</section>
	<!-- Menu -->
	<ul class="menu">
		<li onclick="location.href='get_books?id=${owner.id}&readyn='">
	  		전체 도서
		</li>
		<li onclick="location.href='get_books?id=${owner.id}&readyn=Y'">
	 		완독 도서
		</li>
		<li onclick="location.href='get_books?id=${owner.id}&readyn=N'">
	 		미완독 도서
		</li>
		<c:if test="${loginUser.id == owner.id}">
			<li onclick="location.href='go_write.do?id=${loginUser.id}'">
		 		독서 기록하기
			</li>
			<li onclick="location.href='friend_post.do?id=${loginUser.id}'">
		 		내 친구 최신 글 보기
			</li>
		</c:if>
		<li onclick="location.href='show_stats?id=${owner.id}'">
	 		서재 통계
		</li>
	</ul>
		<section id="footer">
			<p class="copyright">All contents Copyright 2013 Reading Inc. all rights reserved<br>
     Contact mail : readingr@readingr.com Tel: +82 64 123 4315 
     Fax +82 64 123 4321</p>
		</section>
	</section>
</div>
	
<!-- Script -->
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/browser.min.js"></script>
<script type="text/javascript" src="js/breakpoints.min.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/member.js"></script>
<script type="text/javascript" src="js/library.js"></script>
<script type="text/javascript" src="js/write_form.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
</section>
</body>
</html>
						