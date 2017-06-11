<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<div class="modal fade" id="errModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">Ошибка</h4>
	      </div>
	      <div class="modal-body">
	        <p><c:out value="${errorMsg}"/>&hellip;</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" data-dismiss="modal">Закрыть</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<c:if test="${not empty errorMsg}">
		<script type="text/javascript">
			$('#errModal').modal('show')
	    </script>
	</c:if>
	<c:if test="${empty errorMsg}">
		<script type="text/javascript">
			$('#errModal').modal('hide')
	    </script>
	</c:if>		
	
	