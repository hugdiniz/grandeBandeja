<%@ page import="java.util.Collection" %>
<%@ page import="entidades.value_objects.RefeicaoVO" %>
<%@ page import="entidades.enumerados.Turno" %>
<%@ page import="java.util.EnumSet" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<style><%@include file="style.css"%></style>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar Refeição</title>
</head>
<body>
	<form action="CriarRefeicao" method="post">
	
		<table style="margin-top: 80px; margin-left: 30px;">
		
			<tr>
				<td>
					Descrição 		
				</td>
				<td>			
					<textarea name ="descricao"></textarea>				
				</td>
			</tr>
			<tr>
				<td>
					Opção Vegetariana 		
				</td>
				<td>			
					<input type="text" name ="op_vegetariana" value = "">				
				</td>
			</tr>
			<tr>
				<td>
					Turno 		
				</td>
				<td>			
					<select name ="turno">
						<% for(Turno turno : EnumSet.allOf(Turno.class)){ %>
						<option value="<%=turno.name()%>"><%=turno.name()%></option>
						<% } %>
					</select>					
				</td>
			</tr>	
		</table>
		<div class="botoes">
			<input type="submit" name="acaoCriar" value="Criar">
			<input type="submit" name="acaoCriar" value="Cancelar">
		</div>
	</form>
</body>
</html>