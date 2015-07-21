<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Consumidor" %>
<%@ page import="entidades.Aluno" %>
<%@ page import="entidades.Funcionario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>

<style><%@include file="style.css"%></style>
<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.ConsumidorVO" %>

<div class="container">
	<%@include file="messagePage.jsp" %>
	<form class="form-horizontal" action="ListarConsumidor" method="post">		
	<table class="table table-bordered table-striped">	
	<tr>
	    <th>Nome</th>
	    <th>Matricula</th>
	  </tr>
			
		<%
			  try{
				  Collection<ConsumidorVO> departamentosDisponiveis = (Collection<ConsumidorVO>)request.getAttribute("consumidors");
				  for (ConsumidorVO depti: departamentosDisponiveis){
		  %>
			  <tr align="center">
			    <td><input type="radio" name='idConsumidor' value='<%=depti.getId()%>'><%=depti.getNome()%></td>
			    <td><%=depti.getMatricula()%></td>
			  </tr>
		  <%
				  }
			  }catch(Exception e){ }
		  %>
		  
	</table>
		<input type="submit" class="btn btn-primary" name ="acaoListar" value = "Criar">
		<input type="submit" class="btn btn-primary" name ="acaoListar" value = "Atualizar">
		<input type="submit" class="btn btn-primary" name ="acaoListar" value = "Ver">
	</form>
</div>
</body>

</html>