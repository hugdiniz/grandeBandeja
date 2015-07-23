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
	function abrirModal(id,refeicao,turno,pago)
	{
		$("#refeicaoModal").html(refeicao);
		$("#turnoModal").html(turno);
		if(pago)
		{
			$("#pagoModal").prop( "checked",true);
		}
		else
		{
			$("#pagoModal").prop( "checked",false);
		}
		$("#inputIdTicket").val(id);
		$('#modalAtualizar').modal("show");
	}
	function alterModal()
	{
		$("#inputPagoModal").val($("#pagoModal").prop("checked"));
		alert($("#inputPagoModal").val());
		$("#idFormListarTicket").submit();
	}
</script>
<form id="idFormListarTicket" action="ListarTicket" method="post">

	<div class="modal fade" id="modalAtualizar">
	 <div class="modal-dialog">
	   <div class="modal-content">
	     <div class="modal-header">
	       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	       <h4 class="modal-title">Atualizar Item</h4>
	     </div>
	     <div class="modal-body">
	     	<table>
	     		<thead>
			        <tr>		          
			          <th>Refeicao</th>
			          <th>Turno</th>
			          <th>Pago</th>
			        </tr>
			      </thead>
	     		<tbody>		      	
					<tr>				          
			          <td id="refeicaoModal"></td>
			          <td id="turnoModal"></td>
			          <td><input class="form-control" id="pagoModal" onclick='$("#inputPagoModal").val($("#pagoModal").prop("checked"));' type="checkbox" value=""></td>			         
			        </tr>	
		      </tbody>
			</table>
	     	<input class="btn btn-primary btn-block" style="width: 20%; margin-top: 2%;" type="submit" name ="atualizar" value = "atualizar">	
	     </div>
	     <div class="modal-footer">
	       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       <button onclick="salvarModal()" type="button" class="btn btn-primary">Save changes</button>
	     </div>
	   </div><!-- /.modal-content -->
	 </div><!-- /.modal-dialog -->
	</div>
	
	<input class="form-control" type="hidden" name="id" value="${id}">
	<input class="form-control" id="inputPagoModal" type="hidden" name="pagoModal" value="">	
	<input class="form-control" id="inputIdTicket" type="hidden" name="idTicket" style="width: 50%;"  value="">
	<div align="center">
		<div align="left" style="width:50%">
			<table class="table table-hover">
				 <thead>
			        <tr>		          
			          <th>Refeicao</th>
			          <th>Turno</th>
			          <th>Pago</th>
			        </tr>
			      </thead>
			      <tbody>
			      	<c:forEach var="ticket" items="${tickets}">	
						<tr>				          
				          <td>${ticket.refeicaoVO.descricao}</td>
				          <td>${ticket.refeicaoVO.turno}</td>
				          <td>
				          	<c:choose>
							  <c:when test="${ticket.pago}">
							  	<span aria-hidden="true" class="glyphicon glyphicon-ok"></span>
							  </c:when>
							  <c:otherwise>
							    <span aria-hidden="true" class="glyphicon glyphicon-remove"></span>
							  </c:otherwise>
							</c:choose>
				         </td>
				          <td>
				          	<button onclick="abrirModal(${ticket.id},'${ticket.refeicaoVO.descricao}','${ticket.refeicaoVO.turno}',${ticket.pago});" class="btn btn-primary" type="button"><span aria-hidden="true" class="glyphicon glyphicon-cog"></span></button>
				          </td>
				        </tr>															 
					</c:forEach>
			      </tbody>
			</table>
		</div>
	</div>
</form>
</body>
</html>