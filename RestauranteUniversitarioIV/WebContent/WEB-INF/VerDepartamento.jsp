<%@ page import="entidades.value_objects.DepartamentoVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ver departamento</title>
</head>
<%@include file="messagePage.jsp" %>
<% DepartamentoVO dept = (DepartamentoVO)request.getAttribute("departamento antigo");%>
<body>
	<form action="VerDepartamento" method="post">
<% try{ 
	String nome = dept.getNome();
	String sigla = dept.getSigla(); %>
	Nome : <%=nome%>
	Sigla : <%=sigla%> <input type="hidden" name ="sigla" value = "<%=sigla%>">
	<br>
<% } catch (NullPointerException e)  {  } %>
<input type="submit" name="acaoVer" value="Voltar">
	</form>


</body>

</html>