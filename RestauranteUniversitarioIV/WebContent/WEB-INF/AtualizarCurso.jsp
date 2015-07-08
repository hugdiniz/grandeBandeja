<%@ page import="entidades.value_objects.CursoVO" %>
<%@ page import="entidades.value_objects.DepartamentoVO" %>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Atualizar curso</title>
</head>
<%@include file="messagePage.jsp" %>
<% CursoVO curso = (CursoVO)request.getAttribute("curso antigo");%>
<% Collection<DepartamentoVO> departamentosDisponiveis = (Collection<DepartamentoVO>)request.getAttribute("departamentosDisponiveis"); %>

<body>
	<form action="AtualizarCurso" method="post">
<% try{ 
	String nome = curso.getNome();
	String sigla = curso.getSigla(); %>
	Nome : <input type="text" name ="nome" value = "<%=nome%>">
	Sigla : <%=sigla%> <input type="hidden" name ="sigla" value = "<%=sigla%>">
	Departamento : <select name ="departamento">
	<% for(DepartamentoVO dptoi : departamentosDisponiveis){ %>
		<option value="<%=dptoi.getSigla()%>" <%= dptoi.equals(curso.getDepartamento())? "selected" : ""  %>><%=dptoi.getNome()%></option>
	<% } %>
	</select>	
	<br>
	<input type="submit" name="acaoAtualizar" value="Atualizar">
	<input type="submit" name="acaoAtualizar" value="Cancelar">
<% } catch (NullPointerException e)  { %>
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } %>
	</form>


</body>

</html>