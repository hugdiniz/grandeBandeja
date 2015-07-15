<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Consumidor" %>
<%@ page import="entidades.Ticket" %>
<%@ page import="entidades.value_objects.RefeicaoVO" %>
<%@ page import="entidades.enumerados.Turno" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<style><%@include file="style.css"%></style>
<% RefeicaoVO refeicao = (RefeicaoVO)request.getAttribute("refeicao antigo");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listando Tickets</title>
</head>
<body>

<form action="ListarTicket" method="post">
		<table style="margin-top: 80px; margin-left: 30px;">
		  <tr>
		    <td>Matricula</td>
		    <td><input type="text" name="matricula" value=""></td>
		    <td><input type="button" name ="buscarMatr" value = "buscarMatricula"></td>
		  </tr>
		  <tr>
		    <td><input type="radio" onchange="habilita()" id="consumidor" name="consumidor" value="aluno">Aluno</td>
		    <td><input type="radio" onchange="habilita()" id="consumidor" name="consumidor" value="funcionario" >Fucionario</td>
		  </tr>
		  <tr>
		    <td>Turno:</td>
		    	<tr id="refeicao">
				    <tr>
				    	<td><input type="radio" onchange="habilita()" id="refeicao" name="refeicao" value="<%=Turno.MANHA.name()%>"><%=Turno.MANHA.name()%></td>
				    	<td><input type="radio" onchange="habilita()" id="refeicao" name="refeicao" value="<%=Turno.TARDE.name()%>"><%=Turno.TARDE.name()%></td>
				    	<td><input type="radio" onchange="habilita()" id="refeicao" name="refeicao" value="<%=Turno.NOITE.name()%>"><%=Turno.NOITE.name()%></td>
				    	<td></td>
				    </tr>
			    </tr>
			</td>    
		  </tr>
		  <tr>
		  <td>Refeição:</td>
		  <td><select name ="refeicao">
				<option value=""></option>
			</select>
		</td>
		  </tr>
		  <tr>
		  	<td>Total R$:</td>
		  	<td><input type="text" name ="totalValor" value = "0"></td>
		  	<td aligns="right"><input type="checkbox" id="pago" name="pago" value="">Pago</td>
		  </tr>
		</table>
	<div class="botoes">
		<input type="submit" name ="acaoListar" value = "Criar">
		<input type="submit" name ="acaoListar" value = "Atualizar">
		<input type="submit" name ="acaoListar" value = "Ver">
	</div>	
	</form>
</body>

</html>