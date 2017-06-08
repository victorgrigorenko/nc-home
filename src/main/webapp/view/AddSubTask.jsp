<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add SubTask</title>
		<%@ include file="Header.jsp" %>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker').datetimepicker({
                    locale: 'ru'
                });
            });
        </script>
	</head>
	<body style="background-color:#495f59;	color:#888">
		<div class="container" style="background-color:aliceblue; min-height:100vh;">
			<%@ include file="MainMenu.jsp" %>
		
			<h3>Добавление подзадачи для задачи <c:out value="${title}"/></h3>
			<form role="form" method="get" action="${pageContext.request.contextPath}/AddSubTask">			
				<input name="ownerID" type="hidden" value="${id}" /> 
				<input name="ownerTitle" type="hidden" value="${title}" /> 

				<div class="form-group">
					<label for="inputTitle">Название подзадачи</label>
					<input name="title" type="text" class="form-control" id="inputTitle" placeholder="Введите название подзадачи">
				</div>
	            <div class="form-group">
					<label for="datetimepicker">Дата и время выполнения подзадачи</label>
	                <div class='input-group date' id='datetimepicker'>
	                    <input name="date" type='text' class="form-control" placeholder="Введите дату и время выполнения подзадачи (DD.MM.YYY hh:mm)"/>
	                    <span class="input-group-addon">
	                        <span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
	            </div>
	
					<div class="form-group">
						<label for="inputDescription">Описание</label>
						<textarea name="description" id="inputDescription" class="form-control" rows="5" placeholder="Введите описание подзадачи"></textarea>
					</div>

<!-- 					<input name="command" type="hidden" value="add subtask" />  -->
<%-- 					<input name="journal" type="hidden" value="${journal.getTasks()}" />  --%>
<%-- 					<input name="taskID" type="hidden" value="${task.getID()}" />  --%>
					<button type="submit" class="btn btn-default">Отправить</button>
			</form>			
		</div>
	</body>
</html>