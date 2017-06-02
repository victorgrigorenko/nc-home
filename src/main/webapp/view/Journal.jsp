<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<form action="${pageContext.request.contextPath}/MainServlet" class="form-inline">
				<a href="#" onClick="this.parentNode.submit(); return false;">  
<!-- 					<span class="glyphicon-class">Очистить все</span>     		   			 -->
					<span class="glyphicon glyphicon-trash"></span>
					<input name="command" type="hidden" value="clear all" /> 
				</a>
			</form>		<table class="table table-hover"> 
			<thead> <tr> <th>#</th> <th>Название</th> <th>Описание</th> <th>Дата/время</th> </tr> </thead> 
			<tbody> 
			<c:forEach var="task" items="${journal.getTasks()}">
				<tr> 
					<th scope="row">#</th>
					<td>${task.getTitle()}</td> 
					<td>${task.getDescription()}</td> 
					<td>${task.getDate()}</td> 

					<td>
						<form action="${pageContext.request.contextPath}/MainServlet" class="form-inline">
							<a href="#" onClick="this.parentNode.submit(); return false;">       		   			
								<span class="glyphicon glyphicon-trash"></span>
								<input name="command" type="hidden" value="del" /> 
								<input name="task" type="hidden" value="${task.getTitle()}" /> 
							</a>
						</form>
	       			</td>
						<td>
							<form action="${pageContext.request.contextPath}/Edit" class="form-inline">
								<a href="#" onClick="this.parentNode.submit(); return false;">    
									<span class="glyphicon glyphicon-pencil"></span>
									<input name="id" type="hidden" value="${task.getID()}" /> 
									<input name="title" type="hidden" value="${task.getTitle()}" /> 
									<input name="description" type="hidden" value="${task.getDescription()}" /> 
									<input name="date" type="hidden" value="${task.getStringDate()}" /> 
<%-- 									<input name="task" type="hidden" value="${task}" />  --%>
<%-- 									<c:set var="task" value="${task}" scope="session"/> --%>
								</a>
							</form>
						</td>
				</tr>
			</c:forEach> 

			</tbody> 
		</table>
<!-- 	</body> -->
<!-- </html> -->