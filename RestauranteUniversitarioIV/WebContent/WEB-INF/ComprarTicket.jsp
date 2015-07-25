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
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"     uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %> 

<style><%@include file="style.css"%></style>
<% RefeicaoVO refeicao = (RefeicaoVO)request.getAttribute("refeicao antigo");%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listando Tickets</title>
</head>
<body>
<script>

</script>
<form action="ComprarTicket" method="post">

	
	<div align="center">
		<div align="left" style="width:50%">
			<div style="margin-top: 80px; margin-left: 30px;">			    
			    <input class="form-control" type="text" name="matricula" style="width: 50%;"  placeholder="Matricula" value="">
			    <input class="form-control" type="hidden" name="matriculaFinal" style="width: 50%;"  placeholder="Matricula" value="${consumidor.matricula}">
			    <input class="btn btn-primary btn-block" style="width: 20%; margin-top: 2%;" type="submit" name ="buscarMatr" value = "Buscar">		  		 
			</div>
			<c:if test='${consumidor != null}'>	
				<fieldset style="margin-top: 10%">
					<legend>
						<c:choose>
						  <c:when test="${consumidor.cursoVO != null && consumidor.cursoVO.id != null && consumidor.cursoVO.id > 0}">
						  	<span style="font-size:18px;color:#337ab7">Aluno</span>
						  </c:when>
						  <c:otherwise>
						  	<span style="font-size:18px;color:#337ab7">Funcionario</span>			    
						  </c:otherwise>
						</c:choose>
					</legend>
					<table class="table">     
				      <tbody>
				        <tr>
				          <th scope="row">Nome</th>
				          <td>${consumidor.nome}</td>	          
				        </tr>
				        <tr>
				          <th scope="row">Matricula</th>
				          <td>${consumidor.matricula}</td>	          
				        </tr>	      
				      </tbody>
			    	</table>
			    	<a href="/RestauranteUniversitario/ListarTicket?id=${consumidor.id}">Listar Ticket</a>
				</fieldset>
				
								
				<fieldset style="margin-top: 10%">
					<legend>
						<span style="font-size:18px;color:#337ab7">Comprar Ticket</span>
					</legend>
					<table class="table">     
				      <tbody>   	
				        <tr>
				          <th scope="row">Refeição</th>
				          <td>				          
							<select name ="refeicao">
								<option onclick="$('#valorPago').val('')" value=""></option>
								<c:forEach var="refeicao" items="${refeicaos}">	
									<c:choose>
									  <c:when test="${consumidor.cursoVO != null && consumidor.cursoVO.id != null && consumidor.cursoVO.id > 0}">
									  	<option onclick="$('#valorPago').val(${refeicao.valorAluno})" value="${refeicao.id}">${refeicao.descricao}	Turno: ${refeicao.turno}</option>
									  </c:when>
									  <c:otherwise>
									  	<option onclick="$('#valorPago').val(${refeicao.valorFuncionario})" value="${refeicao.id}">${refeicao.descricao}	Turno: ${refeicao.turno}</option>		    
									  </c:otherwise>
									</c:choose>
																				 
								</c:forEach>		
							</select>
						   </td>	          
				        </tr>	
				        <tr>
				          <th scope="row">Valor</th>
				          <td>				          	
				          	<span><input id="valorPago" type="text" name="pago" disabled="disabled"></span>
				          	<span>Pago: </span> <input TYPE="checkbox" NAME="pago"> 
				          </td>	          
				        </tr>      
				      </tbody>
			    	</table>
			    	 <input class="btn btn-primary btn-block" style="width: 20%; margin-top: 2%;" type="submit" name ="comprar" value = "comprar">
				</fieldset>
				
				
			</c:if>			
		</div>
	</div>
</form>
</body>
</html>