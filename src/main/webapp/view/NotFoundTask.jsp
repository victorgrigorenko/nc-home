<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Task Not Found</title>
		<%@ include file="Header.jsp" %>
	</head>
	<body style="background-color:#495f59;	color:#888">
		<div class="container" style="background-color:aliceblue; min-height:100vh;">
			<%@ include file="MainMenu.jsp" %>
		
			<h3>Просмотр задачи</h3>
			<br>
			<div class="container">
			<div class="col-md-3">
				<img src="${pageContext.request.contextPath}/storage/vamp.png" alt="...">
			</div>
			<div class="col-md-8">
				<p class="lead">Извините, запрашиваемая задача не была найдена</p>
				<p class="">Возможно вы допустили ошибку при вводе, попробуйте еще раз</p>
				<p class="">или воспользуйтесь справкой</p>
			</div>
			</div>
				
		</div>
	</body>
</html>