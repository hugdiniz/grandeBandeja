<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.RefeicaoVO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listando Refeicoes</title>
</head>
<body>
<%@include file="messagePage.jsp" %>

	<form action="ListarRefeicao" method="post">
		<table style="margin-top: 80px; margin-left: 30px;">
		  <tr>
		    <th>Descricao</th>
		    <th>Opeção Vegetariana</th>
		    <th>Turno</th>
		  </tr>
		  <%
			  try{
				  Collection<RefeicaoVO> refeicoes = (Collection<RefeicaoVO>)request.getAttribute("refeicoes");
				  for (RefeicaoVO refeicao: refeicoes){
		  %>
			  <tr align="center">
			  	<td><input type="radio" name="id" value="<%=refeicao.getId()%>"></td>
			    <td><%=refeicao.getDescricao()%></td>
			    <td><%=refeicao.getOp_vegetariana()%></td>
			    <td><%=refeicao.getTurno().name()%></td> 	
			  </tr>
		  <%
				  }
			  }catch(Exception e){ 
				  %> <span>seu Burro</span> <% 
			  }
		  %>
		</table>
	<div class="botoes">
		<input type="submit" name ="acaoListar" value = "Criar">
		<input type="submit" name ="acaoListar" value = "Atualizar">
		<input type="submit" name ="acaoListar" value = "Ver">
	</div>	
	</form>

</body>
</html>