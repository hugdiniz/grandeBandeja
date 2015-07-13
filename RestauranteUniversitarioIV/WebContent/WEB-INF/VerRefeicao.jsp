<%@ page import="entidades.value_objects.RefeicaoVO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver refeicao</title>
</head>
<%@include file="messagePage.jsp" %>
<% RefeicaoVO refeicao = (RefeicaoVO)request.getAttribute("refeicao antigo");%>
<body>
<form action="VerRefeicao" method="post">
<% try{ 
	String turno = refeicao.getTurno();
	String descricao = refeicao.getDescricao();
	String op_vegetariana = refeicao.getOp_vegetariana(); %>
	Turno : <%=turno%>
	Descricao : <%=descricao%> 
	Op_vegetariana : <%=op_vegetariana%> 
	
	<br>
<% } catch (NullPointerException e)  {  } %>
<input type="submit" name="acaoVer" value="Voltar">
	</form>

</body>
</html>