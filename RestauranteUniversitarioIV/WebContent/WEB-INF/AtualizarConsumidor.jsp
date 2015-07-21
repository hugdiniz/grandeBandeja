<%@page import="entidades.value_objects.ConsumidorVO"%>
<%@page import="entidades.enumerados.SexoEnum"%>
<%@page import="entidades.enumerados.TituloEnum"%>
<%@page import="entidades.value_objects.DepartamentoVO"%>
<%@page import="entidades.value_objects.CursoVO"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.html"></jsp:include>
<%@include file="messagePage.jsp" %>
<style><%@include file="style.css"%></style>

<% Collection<CursoVO> cursoVOs = (Collection<CursoVO>)request.getAttribute("cursos"); 
Collection<DepartamentoVO> departamentoVOs = (Collection<DepartamentoVO>)request.getAttribute("departamentos");
ConsumidorVO consumidorVO = (ConsumidorVO)request.getAttribute("consumidor"); 
String checkAluno = consumidorVO.getIdCurso() == null ? "" : "checked";
String checkFuncionario = checkAluno.equals("checked") ? "" : "checked";
%>

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
<form action="AtualizarConsumidor" method="post">
	<div style="margin-top: 80px; margin-left: 30px;">
		<div>
			<input onclick="alterarConsumidor('aluno')" type="radio" name="tipoConsumidor" value="aluno" <%=checkAluno%>>Aluno
			<input onclick="alterarConsumidor('funcionario')" type="radio" name="tipoConsumidor" value="funcionario" <%=checkFuncionario%>>Funcionario
		</div>
		<table class="table">
			<tr>
				<td>
					Nome 		
				</td>
				<td>			
					<input type="text" name ="nome" value = "<%=consumidorVO.getNome()%>">				
				</td>
			</tr>
			<tr>
				<td>
					Matricula 		
				</td>
				<td>			
					 <input type="text" name ="matricula" value = "<%=consumidorVO.getMatricula()%>">				
				</td>
			</tr>
			<tr>
				<td>
					Ano do Ingresso  		
				</td>
				<td>			
					<input type="text" name ="anoIngresso" value = "<%=consumidorVO.getAnoIngresso()%>">				
				</td>
			</tr>
			<tr>
				<td>
					Sexo		
				</td>
				<td>			
					<select name ="sexo">
						<option value=""></option>
						<% for(SexoEnum sexoEnum : SexoEnum.values()){ %>
							<option value="<%=sexoEnum.name()%>"><%=sexoEnum.name()%></option>
						<% } %>		
					</select>		
				</td>
			</tr>
			<tr>
				<td>
					Titulo 
				</td>
				<td>			
					<select name ="titulo">
						<option value=""></option>
						<% for(TituloEnum tituloEnum : TituloEnum.values()){ %>
							<option value="<%=tituloEnum.name()%>"><%=tituloEnum.name()%></option>
						<% } %>		
					</select>		
				</td>
			</tr>
			<tr>
				<td>
					CPF 
				</td>
				<td>			
					<input type="text" name ="cpf" value = "<%=consumidorVO.getCpf()%>">			
				</td>
			</tr>
			<tr id="funcionario">
				<td>
					Departamento		
				</td>
				<td>			
					<select name ="departamento">
						<option value=""></option>
						
					</select>			
				</td>
			</tr>
			<tr id="aluno">
				<td>
					Curso		
				</td>
				<td>			
					<select name ="curso">
						<option value=""></option>
						<% for(CursoVO cursoVO : cursoVOs){ %>
							<option value="<%=cursoVO.getId()%>"><%=cursoVO.getNome()%></option>
						<% } %>
					</select>			
				</td>
			</tr>
			
			
			
			
		</table>
		<div class="botoes">
			<input type="submit" class="btn btn-primary" name="acaoAtualizar" value="Atualizar">
			<input type="submit" class="btn btn-primary" name="acaoAtualizar" value="Cancelar">
		</div>
	</div>
</form>
</body>







<%-- <jsp:include page="footer.html"></jsp:include> --%>