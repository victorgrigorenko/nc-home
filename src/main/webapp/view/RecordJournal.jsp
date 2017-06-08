<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Record Journal</title>
		<%@ include file="Header.jsp" %>
	</head>
	<body style="background-color:#495f59;	color:#888">
		<div class="container" style="background-color:aliceblue; min-height:100vh;">
			<%@ include file="MainMenu.jsp" %>
		
			<h3>Сохранение журнала</h3>
		
			<form role="form" method="POST" action="${pageContext.request.contextPath}/Record">			
				<div class="form-group">
					<label for="inputFile">Имя сохраняемого журнала</label>
					<input name="fileName" type="text" class="form-control" id="inputFile" placeholder="Введите имя файла без расширения">
				</div>

<!-- 					<input name="command" type="hidden" value="record" />  -->
<%-- 					<c:set var="command" scope="session" value="record"/>  --%>
					<input name="journal" type="hidden" value="${journal}" /> 

					<button type="submit" class="btn btn-default">Сохранить</button>
			</form>			
		</div>
	</body>
</html>