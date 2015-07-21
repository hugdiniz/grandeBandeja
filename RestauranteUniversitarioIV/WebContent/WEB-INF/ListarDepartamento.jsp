<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.DepartamentoVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<%@include file="messagePage.jsp" %>
<div class="container">
	<form class="form-horizontal" action="ListarDepartamento" method="post">
		<table class="table table-bordered table-striped">
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

		<input type="submit" class="btn btn-primary" name ="acaoListar" value = "Criar">
		<input type="submit" class="btn btn-primary" name ="acaoListar" value = "Atualizar">
		<input type="submit" class="btn btn-primary" name ="acaoListar" value = "Ver">
		<!-- <input type="submit" name ="acaoListar" value = "Remover"> -->
	</form>
</div>
</body>

</html>