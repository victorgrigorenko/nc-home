<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html> -->
<!-- 	<head> -->
<!-- 		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<!-- 		<title>Insert title here</title> -->
<!-- 	</head> -->
<!-- 	<body> -->
			<form action="${pageContext.request.contextPath}/MainServlet" class="form-inline">
				<a href="#" onClick="this.parentNode.submit(); return false;">  
<!-- 					<span class="glyphicon-class">Очистить все</span>     		   			 -->
					<span class="glyphicon glyphicon-trash"></span>
					<input name="clear all" type="hidden" value="all" /> 
				</a>
			</form>		<table class="table table-hover"> 
			<thead> <tr> <th>#</th> <th>Название</th> <th>Описание</th> <th>Дата/время</th> </tr> </thead> 
			<tbody> 
			<c:forEach var="task" items="${journal.getTasks()}">
				<tr> 
					<th scope="row">#</th>
					<td>${task.getTitle()}</td> 
					<td>${task.getDecsription()}</td> 
					<td>${task.getDate()}</td> 

					<td>
						<form action="${pageContext.request.contextPath}/MainServlet" class="form-inline">
							<a href="#" onClick="this.parentNode.submit(); return false;">       		   			
								<span class="glyphicon glyphicon-trash"></span>
								<input name="del" type="hidden" value="${task.getTitle()}" /> 
							</a>
						</form>
	       			</td>
						<td>
							<form action="${pageContext.request.contextPath}/MainServlet" class="form-inline">
								<a href="#" onClick="this.parentNode.submit(); return false;">    
									<span class="glyphicon glyphicon-pencil"></span>
									<input name="edit" type="hidden" value="${task.getTitle()}" /> 
								</a>
							</form>
						</td>
				</tr>
			</c:forEach> 

			</tbody> 
		</table>
<!-- 	</body> -->
<!-- </html> -->