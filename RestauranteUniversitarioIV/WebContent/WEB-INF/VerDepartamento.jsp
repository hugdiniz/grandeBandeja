<%@ page import="entidades.value_objects.DepartamentoVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.html"></jsp:include>
<%@include file="messagePage.jsp" %>
<% DepartamentoVO dept = (DepartamentoVO)request.getAttribute("departamento antigo");%>
<div class="container">
	<form action="VerDepartamento" method="post">
<% try{ 
	String nome = dept.getNome();
	String sigla = dept.getSigla(); %>
	<label>Nome :</label> <%=nome%>
	<br/>
	<label>Sigla :</label> <%=sigla%> <input type="hidden" name ="sigla" value = "<%=sigla%>">
	<br>
<% } catch (NullPointerException e)  {  } %>
<input type="submit" class="btn btn-primary" name="acaoVer" value="Voltar">
	</form>
</div>

</body>

</html>