<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.RefeicaoVO" %>
<%@ page import="entidades.enumerados.Turno" %>
<%@ page import="java.util.EnumSet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<style><%@include file="style.css"%></style>
<% RefeicaoVO refeicao = (RefeicaoVO)request.getAttribute("refeicao antigo");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Atualizar Refeição</title>
</head>
<body>
	<form action="VerRefeicao" method="post">
		<%try
		{
			String descricao = refeicao.getDescricao();
			String op_vegetariana = refeicao.getOp_vegetariana();
			String turno = refeicao.getTurno().name();
			Long id = refeicao.getId();%>
			
		<input type="text" style="display:none;" name="id" value="<%=id%>">
		<table style="margin-top: 80px; margin-left: 30px;">
		
			<tr>
				<td>
					Descrição 		
				</td>
				<td>			
					<textarea name ="descricao" value=""><%=descricao%></textarea>				
				</td>
			</tr>
			<tr>
				<td>
					Opção Vegetariana 		
				</td>
				<td>			
					<input type="text" name ="op_vegetariana" value = "<%=op_vegetariana%>">				
				</td>
			</tr>
			<tr>
				<td>
					Turno 		
				</td>
				<td>			
					<select name ="turno">
						<% for(Turno turnoi : EnumSet.allOf(Turno.class)){ %>
						<option value="<%=turnoi.name()%>"<%= turnoi.name().equals(turno)? "selected" : ""  %>><%=turnoi.name()%></option>
						<% } %>				
					</select>					
				</td>
			</tr>	
		</table>
		<div class="botoes">
		<%}catch (NullPointerException e) {}%>
			<input type="submit" name="acaoVer" value="Voltar">
		</div>
		
	</form>
</body>
</html>