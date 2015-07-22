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
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"     uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %> 

<% Collection<CursoVO> cursoVOs = (Collection<CursoVO>)request.getAttribute("cursos"); 
Collection<DepartamentoVO> departamentoVOs = (Collection<DepartamentoVO>)request.getAttribute("departamentos");
ConsumidorVO consumidorVO = (ConsumidorVO)request.getAttribute("consumidor"); 
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
	//alterarConsumidor("aluno");	
	
});


</script>
<form action="AtualizarConsumidor" method="post">
	<div style="margin-top: 80px; margin-left: 30px;">
		<div>
			<c:choose>
			  <c:when test="${cursoConsumidor.id != null}">
			  	<span style="font-size:18px;color:#337ab7">Aluno</span>
			  </c:when>
			  <c:otherwise>
			  	<span style="font-size:18px;color:#337ab7">Funcionario</span>			    
			  </c:otherwise>
			</c:choose>
		</div>		
		<input type="hidden" name="id" value="${consumidorVO.id}">
		<table class="table" style="margin-top: 2%;">
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
						<c:forEach var="sexo" items="${sexos}">
			          			<c:choose>
								  <c:when test="${sexo == sexoConsumidor}">
								  	<option value="${sexo}" selected>${sexo}</option>
								  </c:when>
								  <c:otherwise>
								    <option value="${sexo}">${sexo}</option>
								  </c:otherwise>
								</c:choose>
						</c:forEach>		
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
						<c:forEach var="titulo" items="${titulos}">
			          			<c:choose>
								  <c:when test="${titulo == tituloConsumidor}">
								  	<option value="${titulo}" selected>${titulo}</option>
								  </c:when>
								  <c:otherwise>
								    <option value="${titulo}">${titulo}</option>
								  </c:otherwise>
								</c:choose>
						</c:forEach>							
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
			
			
			<c:choose>
			  <c:when test="${cursoConsumidor.id != null}">
				  <tr id="aluno">
					<td>
						Curso		
					</td>
					<td>
						<select name ="curso">
							<option value=""></option>
							<c:forEach var="curso" items="${cursos}">
				          			<c:choose>
									  <c:when test="${curso.nome == cursoConsumidor.nome}">
									  	<option value="${curso.nome}" selected>${curso.nome}</option>
									  </c:when>
									  <c:otherwise>
									    <option value="${curso.nome}">${curso.nome}</option>
									  </c:otherwise>
									</c:choose>
							</c:forEach>							
						</select>								
					</td>
				</tr>
			  </c:when>
			  <c:otherwise>
				<tr id="funcionario">
					<td>
						Departamento		
					</td>
					<td>			
						<select name ="departamento">
							<option value=""></option>
							<c:forEach var="departamento" items="${departamentos}">
				          			<c:choose>
									  <c:when test="${departamento.nome == departamentoConsumidor.nome}">
									  	<option value="${departamento.nome}" selected>${departamento.nome}</option>
									  </c:when>
									  <c:otherwise>
									    <option value="${departamento.nome}">${departamento.nome}</option>
									  </c:otherwise>
									</c:choose>
							</c:forEach>
						</select>			
					</td>
				</tr>
			  </c:otherwise>
			</c:choose>
			
			
			
		</table>
		<div class="botoes">
			<input type="submit" class="btn btn-primary" name="acaoAtualizar" value="Atualizar">
			<input type="submit" class="btn btn-primary" name="acaoAtualizar" value="Cancelar">
		</div>
	</div>
</form>
</body>







<%-- <jsp:include page="footer.html"></jsp:include> --%>