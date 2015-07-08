<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.DepartamentoVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listando departamentos</title>
</head>

<body>

<%@include file="messagePage.jsp" %>

	<form action="ListarDepartamento" method="post">
		<table width="80%">
		  <tr>
		    <th>Sigla</th>
		    <th>Nome</th>
		  </tr>
		  
		  <%
			  try{
				  Collection<DepartamentoVO> departamentosDisponiveis = (Collection<DepartamentoVO>)request.getAttribute("departamentos");
				  for (DepartamentoVO depti: departamentosDisponiveis){
		  %>
			  <tr align="center">
			    <td><input type="radio" name='sigla' value='<%=depti.getSigla()%>'><%=depti.getSigla()%></td>
			    <td><%=depti.getNome()%></td>
			  </tr>
		  <%
				  }
			  }catch(Exception e){ }
		  %>
		</table>

		<input type="submit" name ="acaoListar" value = "Criar">
		<input type="submit" name ="acaoListar" value = "Atualizar">
		<input type="submit" name ="acaoListar" value = "Ver">
		<!-- <input type="submit" name ="acaoListar" value = "Remover"> -->
	</form>
</body>

</html>