<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.CursoVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<div class="container">
<%@include file="messagePage.jsp" %>
	<form action="ListarCurso" method="post">
		<table class="table table-bordered table-striped">
		  <tr>
		    <th>Sigla</th>
		    <th>Nome</th>
		    <th>Departamento</th>
		  </tr>
		  
		  <%
			  try{
				  Collection<CursoVO> cursosDisponiveis = (Collection<CursoVO>)request.getAttribute("cursos");
				  for (CursoVO cursoi: cursosDisponiveis){
		  %>
			  <tr align="center">
			    <td><input type="radio" name='sigla' value='<%=cursoi.getSigla()%>'><%=cursoi.getSigla()%></td>
			    <td><%=cursoi.getNome()%></td>
			    <td><%=cursoi.getDepartamentoVO().getSigla()%></td>
			  </tr>
		  <%
				  }
			  }catch(Exception e){ 
				  %> <span>seu Burro</span> <% 
			  }
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