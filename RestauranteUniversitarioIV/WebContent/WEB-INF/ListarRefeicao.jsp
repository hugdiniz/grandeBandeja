<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.RefeicaoVO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listando Refeicoes</title>
</head>
<body>
<%@include file="messagePage.jsp" %>

	<form action="ListarRefeicao" method="post">
		<table width="80%">
		  <tr>
		    <th>Turno</th>
		    <th>Descricao</th>
		    <th>Op_vegetariana</th>
		  </tr>
		  
		  <%
			  try{
				  Collection<RefeicaoVO> refeicoesDisponiveis = (Collection<RefeicaoVO>)request.getAttribute("refeicoes");
				  for (RefeicaoVO refeicao: refeicoesDisponiveis){
		  %>
			  <tr align="center">
			    <td><%=refeicao.getTurno()%>'><%=refeicao.getTurno()%></td>
			    <td><%=refeicao.getDescricao()%></td>
			    <td><%=refeicao.getOp_vegetariana()%></td>
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