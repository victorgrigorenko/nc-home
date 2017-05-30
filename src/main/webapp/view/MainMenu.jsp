<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
		<nav class="navbar navbar-default"> 
			<div class="container-fluid"> 
				<div class="collapse navbar-collapse"> 
					<ul class="nav navbar-nav"> 
						<li> 
							<form action="${pageContext.request.contextPath}/MainServlet" class="form-inline"> 
								<a href="#first" class="navbar-brand" onClick="this.parentNode.submit(); return false;">
									<span name="add" class="glyphicon glyphicon-plus" aria-hidden="true"></span>
									<input name="add" type="hidden" value="false" /> 
								</a>	
							</form>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}">Главная</a>
						</li> 
					</ul> 
					<form action="${pageContext.request.contextPath}/MainServlet" class="navbar-form navbar-left"> 
						<div class="form-group"> 
							<input name="search" class="form-control" placeholder="Поиск"> 
						</div> 
						<button type="submit" class="btn btn-default">Поиск</button> 
					</form> 
					<ul class="nav navbar-nav navbar-right"> 
						<li class="dropdown"> 
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Меню	<span class="caret"></span></a> 
							<ul class="dropdown-menu"> 
								<li><a href="#">Сохранить журнал</a></li> 
								<li><a href="#">Загрузить журнал</a></li> 
								<li><a href="#">Уведомления</a></li> 
								<li role="separator" class="divider"></li> 
								<li>
									<form action="${pageContext.request.contextPath}/MainServlet">
										<a href="#" onClick="this.parentNode.submit(); return false;">Справка!</a>
										<input name="help" type="hidden" value="" /> 
									</form> 
								</li>
							</ul> 
						</li> 
					</ul> 
				</div> 
			</div> 
		</nav>
