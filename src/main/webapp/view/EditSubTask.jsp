<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Subtask</title>
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
		
			<h3>Редактирование подзадачи для задачи<c:out value="${task.getTitle()}"/></h3>
		
			<form role="form" action="${pageContext.request.contextPath}/EditSubTask" method="get">	
				<input name="id" type="text" class="form-control" id="inputId" value="${subtask.getID()}" disabled>
				<input name="id" type="hidden" value="${subtask.getID()}"> <!-- если в поле указан аттрибут disabled, то он ен передастся -->

				<div class="form-group">
					<label for="inputTitle">Название подзадачи</label>
					<input required="required" name="title" type="text" class="form-control" id="inputTitle" value="${subtask.getTitle()}" placeholder="Введите название подзадачи">
					<input name="oldTitle" type="hidden" value="${title}" /> 
				</div>
	            <div class="form-group">
					<label for="datetimepicker">Дата и время выполнения подзадачи</label>
	                <div class='input-group date' id='datetimepicker'>
	                    <input required="required" name="date" type='text' class="form-control" value="${subtask.getStringDate()}" placeholder="Введите дату и время выполнения подзадачи (DD.MM.YYY hh:mm)">
	                    <span class="input-group-addon">
	                        <span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
	            </div>
	
				<div class="form-group">
					<label for="inputDescription">Описание</label>
					<textarea name="description" id="inputDescription" class="form-control" rows="5" placeholder="Введите описание подзадачи"><c:out value="${subtask.getDescription()}"/></textarea>
				</div>

				<input name="ownerTitle" type="hidden" value="${ownerTitle}">
				<button type="submit" class="btn btn-default">Отправить</button>
			</form>		
		    <%@ include file="ErrorModal.jsp" %>	
		</div>
	</body>
</html>