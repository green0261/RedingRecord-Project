<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reading Record</title>
<link rel="stylesheet" href="admin/css/admin.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="admin/product/product.js"></script>
</head>
<body>
	<div id="wrap">
		<header>			
			<div id="logo">
				<a href="admin_member_list"> 
					<img style="width:800px" src="admin/image/logo.png">
				</a>
			</div>	
			<input class="btn" type="button" value="logout" style="float: right;" onClick="location.href='admin_logout'">			
		</header>
