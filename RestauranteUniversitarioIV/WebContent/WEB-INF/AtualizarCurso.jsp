<%@ page import="entidades.value_objects.CursoVO" %>
<%@ page import="entidades.value_objects.DepartamentoVO" %>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<%@include file="messagePage.jsp" %>
<% CursoVO curso = (CursoVO)request.getAttribute("curso antigo");%>
<% Collection<DepartamentoVO> departamentosDisponiveis = (Collection<DepartamentoVO>)request.getAttribute("departamentosDisponiveis"); %>

<body>
	<form action="AtualizarCurso" class="form-horizontal" method="post">
<% try{ 
	String nome = curso.getNome();
	String sigla = curso.getSigla(); 
	Long id = curso.getId(); %>
	<input type="text" style="display:none;" name="id" value="<%=id%>">
	<label>Nome</label>
	<input type="text" class="form-control" style="width:50%" name ="nome" value = "<%=nome%>">
	<label>Sigla</label>
	<br/>
	<%=sigla%>
	<br/>
	<input type="hidden" class="form-control" style="width:50%" name ="sigla" value = "<%=sigla%>">
	<br>
	<label>Departamento</label>
	<select name ="departamento" class="form-control" style="width:35%">
	<% for(DepartamentoVO dptoi : departamentosDisponiveis){ %>
		<option value="<%=dptoi.getId()%>" <%= dptoi.equals(curso.getDepartamentoVO())? "selected" : ""  %>><%=dptoi.getNome()%></option>
	<% } %>
	</select>	
	<br>
	<input type="submit" class="btn btn-primary" name="acaoAtualizar" value="Atualizar">
	<input type="submit" class="btn btn-primary" name="acaoAtualizar" value="Cancelar">
<% } catch (NullPointerException e)  { %>
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } %>
	</form>


</body>

</html>