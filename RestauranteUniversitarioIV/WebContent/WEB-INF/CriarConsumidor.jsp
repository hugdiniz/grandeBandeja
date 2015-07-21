<%@page import="entidades.enumerados.SexoEnum"%>
<%@page import="entidades.enumerados.TituloEnum"%>
<%@page import="entidades.value_objects.DepartamentoVO"%>
<%@page import="entidades.value_objects.CursoVO"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.html"></jsp:include>
<%@include file="messagePage.jsp"%>
<style>
<%@include file="style.css"%>
</style>

<% Collection<CursoVO> cursoVOs = (Collection<CursoVO>)request.getAttribute("cursoVOs"); %>
<% Collection<DepartamentoVO> departamentoVOs = (Collection<DepartamentoVO>)request.getAttribute("departamentoVOs"); %>

<script>

function alterarConsumidor(consumidor)
{
		if(consumidor == "funcionario")
		{
			$("#aluno").css("display","none");
			$("#funcionario").css("display","");
		}
		else
		{
			$("#funcionario").css("display","none");
			$("#aluno").css("display","");
		}	
}

$(function ()
{
	alterarConsumidor("aluno");	
});


</script>
<div class="container">
	<form action="CriarConsumidor" class="form-horizontal" method="post">
		<div class="form-group">
			<div class="radio">
				<label> <input onclick="alterarConsumidor('aluno')"
					type="radio" name="tipoConsumidor" value="aluno" checked>
					Aluno
				</label>
			</div>
			<div class="radio">
				<label> <input onclick="alterarConsumidor('funcionario')"
					type="radio" name="tipoConsumidor" value="funcionario">
					Funcionario
				</label>
			</div>
			<label>Nome </label> <input type="text" class="form-control"
				name="nome" value="" style="width:50%"> <label>Matricula</label> <input
				type="text" class="form-control" name="matricula" value="" style="width:50%">
			<label>Ano do Ingresso</label> <input type="text"
				class="form-control" style="width:50%" name="anoIngresso" value=""> <label>Sexo</label>
			<select name="sexo" class="form-control" style="width:35%">
				<option value=""></option>
				<% for(SexoEnum sexoEnum : SexoEnum.values()){ %>
				<option value="<%=sexoEnum.name()%>"><%=sexoEnum.name()%></option>
				<% } %>
			</select> <label>Titulo</label> <select name="titulo" class="form-control" style="width:35%">
				<option value=""></option>
				<% for(TituloEnum tituloEnum : TituloEnum.values()){ %>
				<option value="<%=tituloEnum.name()%>"><%=tituloEnum.name()%></option>
				<% } %>
			</select> <label>CPF</label> <input type="text" style="width:50%" name="cpf" value=""
				class="form-control">
			<div id="funcionario">
				<label>Departamento</label> <select name="departamento"
					class="form-control" style="width:35%">
					<option value=""></option>
					<% for(DepartamentoVO departamentoVO : departamentoVOs){ %>
					<option value="<%=departamentoVO.getId()%>"><%=departamentoVO.getNome()%></option>
					<% } %>
				</select>
			</div>
			<div id="aluno">
				<label>Curso</label> <select name="curso" class="form-control" style="width:35%">
					<option value=""></option>
					<% for(CursoVO cursoVO : cursoVOs){ %>
					<option value="<%=cursoVO.getId()%>"><%=cursoVO.getNome()%></option>
					<% } %>
				</select>
			</div>
			<br>
			<input type="submit" class="btn btn-primary" name="acaoCriar" value="Criar">
			<input type="submit" class="btn btn-primary" name="acaoCriar" value="Cancelar">
		</div>
	</form>
</div>
</body>







<%-- <jsp:include page="footer.html"></jsp:include> --%>