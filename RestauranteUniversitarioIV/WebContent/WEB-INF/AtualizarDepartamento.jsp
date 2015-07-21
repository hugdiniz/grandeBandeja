<%@ page import="entidades.value_objects.DepartamentoVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<%@include file="messagePage.jsp" %>
<% DepartamentoVO dept = (DepartamentoVO)request.getAttribute("departamento antigo");%>
<div class="container">
	<form action="AtualizarDepartamento" class="form-horizontal" method="post">
<% try{ 
	String nome = dept.getNome();
	String sigla = dept.getSigla(); %>
	<label>Nome</label>
	<input type="text" class="form-control" style="width:50%" name ="nome" value = "<%=nome%>">
	<label>Sigla</label>
	<br/>
	<%=sigla%>
	<br/>
	<input type="hidden" class="form-control" style="width:50%" name ="sigla" value = "<%=sigla%>">
	<br>
	<input type="submit" name="acaoAtualizar" class="btn btn-primary" value="Atualizar">
	<input type="submit" name="acaoAtualizar" class="btn btn-primary" value="Cancelar">
<% } catch (NullPointerException e)  { %>
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } %>
	</form>


</body>

</html>