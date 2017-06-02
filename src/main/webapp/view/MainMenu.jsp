<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
		<nav class="navbar navbar-default"> 
			<div class="container-fluid"> 
				<div class="collapse navbar-collapse"> 
					<ul class="nav navbar-nav"> 
						<li> 
							<form action="${pageContext.request.contextPath}/view/AddTask.jsp" class="form-inline"> 
								<a href="#first" class="navbar-brand" onClick="this.parentNode.submit(); return false;">
									<span name="command" class="glyphicon glyphicon-plus" aria-hidden="true"></span>
									<input name="command" type="hidden" value="add" /> 
								</a>	
							</form>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/MainServlet">Главная</a>
						</li> 
					</ul> 
					<form action="${pageContext.request.contextPath}/MainServlet" class="navbar-form navbar-left"> 
						<div class="form-group"> 
							<input name="task" class="form-control" placeholder="Поиск"> 
							<input name="command" type="hidden" value="search" /> 
						</div> 
						<button type="submit" class="btn btn-default">Поиск</button> 
					</form> 
					<ul class="nav navbar-nav navbar-right"> 
						<li class="dropdown"> 
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Меню	<span class="caret"></span></a> 
							<ul class="dropdown-menu"> 
								<li>
									<form action="${pageContext.request.contextPath}/view/RecordJournal.jsp">
										<a href="#" onClick="this.parentNode.submit(); return false;">Сохранить журнал</a>
										<c:set var="journal" value="${journal}" scope="session"/>
									</form>
								</li> 
								<li>
									<form action="${pageContext.request.contextPath}/view/ReadJournal.jsp">
										<a href="#" onClick="this.parentNode.submit(); return false;">Загрузить журнал</a>
									</form>
								</li> 
								<li><a href="#">Уведомления</a></li> 
								<li role="separator" class="divider"></li> 
								<li>
									<form action="${pageContext.request.contextPath}/MainServlet">
										<a href="#" onClick="this.parentNode.submit(); return false;">Справка!</a>
										<input name="command" type="hidden" value="help" /> 
									</form> 
								</li>
							</ul> 
						</li> 
					</ul> 
				</div> 
			</div> 
		</nav>
