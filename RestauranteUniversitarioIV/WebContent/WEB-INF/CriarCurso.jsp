<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.DepartamentoVO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="messagePage.jsp" %>
<% Collection<DepartamentoVO> departamentosDisponiveis = (Collection<DepartamentoVO>)request.getAttribute("departamentosDisponiveis"); %>
<jsp:include page="header.html"></jsp:include>
<div class="container">	
	<form action="CriarCurso" class="form-horizontal" method="post">
	<label>Nome</label>
	<input type="text" class="form-control" style="width:50%" name ="nome" value = "">
	<label>Sigla</label>
	<input type="text" class="form-control" style="width:50%" name ="sigla" value = "">
	<label>Departamento</label>
	<select class="form-control" style="width:35%" name ="departamento">
	<option value=""></option>
	<% for(DepartamentoVO dptoi : departamentosDisponiveis){ %>
		<option value="<%=dptoi.getId()%>"><%=dptoi.getNome()%></option>
	<% } %>
	</select>
	<br>
	<input type="submit" class="btn btn-primary" name="acaoCriar" value="Criar">
	<input type="submit" class="btn btn-primary" name="acaoCriar" value="Cancelar">
		</form>
</div>
</body>

</html>