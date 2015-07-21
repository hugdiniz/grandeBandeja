<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="messagePage.jsp" %>
<jsp:include page="header.html"></jsp:include>
<div class="container">
	<form action="CriarDepartamento" class="form-horizontal" method="post">
	<label>Nome</label>
	<input type="text" class="form-control" style="width:50%" name ="nome" value = "">
	<label>Sigla</label>
	<input type="text" class="form-control" style="width:50%" name ="sigla" value = "">
	<br>
	<input type="submit" class="btn btn-primary" name="acaoCriar" value="Criar">
	<input type="submit" class="btn btn-primary" name="acaoCriar" value="Cancelar">
	</form>
</div>
</body>

</html>