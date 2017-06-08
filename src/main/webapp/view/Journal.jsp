<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<form action="${pageContext.request.contextPath}/ClearAll" method="post" class="form-inline">
				<a href="#" onClick="this.parentNode.submit(); return false;">  
<!-- 					<span class="glyphicon-class">Очистить все</span>     		   			 -->
					<span class="glyphicon glyphicon-trash"></span>
				</a>
			</form>		
			<table class="table table-hover"> 
				<thead> <tr> <th>ID</th> <th>Название</th> <th>Описание</th> <th>Дата/время</th> </tr> </thead> 
				<tbody> 
				<c:forEach var="task" items="${journal.getTasks()}">
					<tr> 
						<th scope="row">${task.getID()}</th>
						<td scope="row">${task.getTitle()}</td> 
						<td scope="row">${task.getDescription()}</td> 
						<td scope="row">${task.getDate()}</td> 
	
						<td>
							<form action="${pageContext.request.contextPath}/Delete" method="post" class="form-inline">
								<a href="#" onClick="this.parentNode.submit(); return false;">       		   			
									<span class="glyphicon glyphicon-trash"></span>
<!-- 									<input name="command" type="hidden" value="del" />  -->
<%-- 									<c:set var="command" value="del"  scope="session"/> --%>
									<input name="task" type="hidden" value="${task.getTitle()}" /> 
								</a>
							</form>
		       			</td>
							<td>
								<form action="${pageContext.request.contextPath}/Edit" method="post" class="form-inline">
									<a href="#" onClick="this.parentNode.submit(); return false;">    
										<span class="glyphicon glyphicon-pencil"></span>
										<input name="id" type="hidden" value="${task.getID()}" /> 
										<input name="title" type="hidden" value="${task.getTitle()}" /> 
										<input name="description" type="hidden" value="${task.getDescription()}" /> 
										<input name="date" type="hidden" value="${task.getStringDate()}" /> 
									</a>
								</form>
							</td>
						<td>
							<form action="${pageContext.request.contextPath}/AddSubTask" method="post" class="form-inline">
								<a href="#" onClick="this.parentNode.submit(); return false;">       		   			
									<span class="glyphicon glyphicon-plus"></span>
									<input name="id" type="hidden" value="${task.getID()}" /> 
									<input name="title" type="hidden" value="${task.getTitle()}" /> 
<%-- 									<c:set var="task" value="${task}"  scope="session"/> --%>
<%-- 									<c:set var="command" value="add subtask"  scope="session"/> --%>
								</a>
							</form>
		       			</td>
						<c:forEach var="entry" items="${task.getSubTasks().entrySet()}">
							<tr class="info">
								<td>${entry.getValue().getID()}</td>
								<td>${entry.getValue().getTitle()}</td> 
								<td>${entry.getValue().getDescription()}</td> 
								<td>${entry.getValue().getDate()}</td> 
								<td>
									<form action="${pageContext.request.contextPath}/DeleteSubTask" method="post" class="form-inline">
										<a href="#" onClick="this.parentNode.submit(); return false;">       		   			
											<span class="glyphicon glyphicon-trash"></span>
											<input name="owner" type="hidden" value="${entry.getValue().getOwner()}" /> 
											<input name="id" type="hidden" value="${entry.getValue().getID()}" /> 
										</a>
									</form>
								</td>
								<td>
									<form action="${pageContext.request.contextPath}/EditSubTask" method="post" class="form-inline">
										<a href="#" onClick="this.parentNode.submit(); return false;">    
											<span class="glyphicon glyphicon-pencil"></span>
											<input name="ownerTitle" type="hidden" value="${task.getTitle()}" /> 
											<input name="id" type="hidden" value="${entry.getValue().getID()}" /> 
											<input name="title" type="hidden" value="${entry.getValue().getTitle()}" /> 
											<input name="description" type="hidden" value="${entry.getValue().getDescription()}" /> 
											<input name="date" type="hidden" value="${entry.getValue().getStringDate()}" /> 
										</a>
									</form>
								</td>
							</tr>
						</c:forEach> 
					</tr>
				</c:forEach> 
				</tbody> 
			</table>
<!-- 	</body> -->
<!-- </html> -->