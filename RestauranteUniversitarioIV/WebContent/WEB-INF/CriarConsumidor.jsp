<%@page import="entidades.enumerados.SexoEnum"%>
<%@page import="entidades.enumerados.TituloEnum"%>
<%@page import="entidades.value_objects.DepartamentoVO"%>
<%@page import="entidades.value_objects.CursoVO"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="messagePage.jsp" %>
<jsp:include page="header.html"></jsp:include>
<% Collection<CursoVO> cursoVOs = (Collection<CursoVO>)request.getAttribute("cursoVOs"); %>
<% Collection<DepartamentoVO> departamentoVOs = (Collection<DepartamentoVO>)request.getAttribute("departamentoVOs"); %>


<body><form action="CriarConsumidor" method="post">
	
	<table style="margin-top: 80px; margin-left: 30px;">
		<tr>
			<td>
				Nome 		
			</td>
			<td>			
				<input type="text" name ="nome" value = "">				
			</td>
		</tr>
		<tr>
			<td>
				Matricula 		
			</td>
			<td>			
				 <input type="text" name ="matricula" value = "">				
			</td>
		</tr>
		<tr>
			<td>
				Ano do Ingresso  		
			</td>
			<td>			
				<input type="text" name ="anoIngresso" value = "">				
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
				<input type="text" name ="cpf" value = "">			
			</td>
		</tr>
		<tr>
			<td>
				Departamento		
			</td>
			<td>			
				<select name ="departamento">
					<option value=""></option>
					<% for(DepartamentoVO departamentoVO : departamentoVOs){ %>
						<option value="<%=departamentoVO.getId()%>"><%=departamentoVO.getNome()%></option>
					<% } %>
				</select>			
			</td>
		</tr>
		
		
		
		
	</table>
	<div style="margin-left: 25%;">
		<input type="submit" name="acaoCriar" value="Criar">
		<input type="submit" name="acaoCriar" value="Cancelar">
	</div>
	</form>
</body>







<%-- <jsp:include page="footer.html"></jsp:include> --%>