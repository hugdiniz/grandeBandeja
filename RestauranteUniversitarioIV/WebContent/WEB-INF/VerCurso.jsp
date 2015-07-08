<%@ page import="entidades.value_objects.CursoVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ver curso</title>
</head>
<%@include file="messagePage.jsp" %>
<% CursoVO curso = (CursoVO)request.getAttribute("curso antigo");%>
<body>
	<form action="VerCurso" method="post">
<% try{ 
	String nome = curso.getNome();
	String sigla = curso.getSigla(); 
	String siglaDepartamento = curso.getDepartamento().getSigla();
%>
	Nome : <%=nome%>
	Sigla : <%=sigla%> <input type="hidden" name ="sigla" value = "<%=sigla%>">
	Departamento : <%=siglaDepartamento%> 
	<br>
<% } catch (NullPointerException e)  {  } %>
<input type="submit" name="acaoVer" value="Voltar">
	</form>


</body>

</html>