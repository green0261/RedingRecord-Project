<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reading Record Admin</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script type="text/javascript">
function worker_check()
{
  if(document.frm.id.value==""){
      alert("아이디를 입력하세요.");
      return false;
  }else if(document.frm.password.value==""){
     alert("비밀번호를 입력하세요.");
      return false;
    }
    return true;  
}
</script>
</head>

<body>
	<div id="wrap">
		<header>
			<div id="logo">
				<a href="admin_login_form">
					<img src="admin/image/logo.png" style="float: left">
				</a>
			</div>
		</header>
		<article>
			<div id="loginform">
				<form name="frm" method="post" action="admin_login">
					<table>
						<tr>
							<td>아 이 디</td>
							<td><input type="text" name="id" size="10" value="admin1"></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password" size="10" value="1234"></td>
						</tr>
						<tr align="center">
							<td colspan="2"><input class="btn" type="submit" value="업무 로그인" onclick="return worker_check()">
								<input class="btn" type="button" value="메인으로" onclick="location.href='index'">
								<h4 style="color: red">${message}</h4>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</article>
	</div>
</body>
</html>