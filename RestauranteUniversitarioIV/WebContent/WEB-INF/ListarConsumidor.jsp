<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Consumidor" %>
<%@ page import="entidades.Aluno" %>
<%@ page import="entidades.Funcionario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listando consumidores</title>
</head>

<body>

<%! public String[] getHeaders(){
		return new String[]{"","Nome","Matricula","Ano Ingresso", "Sexo","CPF", "Departamento","Curso" };
	}

	public Collection<String> getValuesConsumidor(Consumidor consumidori){
		Collection<String> results = new ArrayList<String>();
		results.add("<input type='radio' name ='cpf' value = '"+consumidori.getCpf().toString()+"'>");
		results.add(consumidori.getNome());
		results.add(""+consumidori.getMatricula());
		results.add(""+consumidori.getAnoIngresso());
		results.add(consumidori.getSexo().toString());
		results.add(consumidori.getCpf().toString());
		try{
			Aluno alunoi = (Aluno)consumidori;
			results.add(alunoi.getCurso().getDepartamento().getSigla());
			results.add(alunoi.getCurso().getSigla());
		}catch (Exception e){
			Funcionario funcionarioi = (Funcionario)consumidori;
			results.add(funcionarioi.getDepartamento().getSigla());
			results.add("-");
		}
		return results;
	}

%>

<%@include file="messagePage.jsp" %>

	<form action="ListarConsumidor" method="post">
		<input type="submit" name ="acaoListar" value = "Atualizar">
		<input type="submit" name ="acaoListar" value = "Ver">
		<input type="submit" name ="acaoListar" value = "Criar Aluno">
		<input type="submit" name ="acaoListar" value = "Criar Funcionario">
				
		<table width="80%">
		  <tr>
		  <% for (String headeri: getHeaders()){ %>
		  <th><%=headeri %></th>
		  <%}%>
		  </tr>
		  <%
			  try{
				  Collection<Consumidor> consumidoresDisponiveis = (Collection<Consumidor>)request.getAttribute("consumidores");
				  for (Consumidor consumidori: consumidoresDisponiveis){
					  %> <tr align="center"> <%
					  for (String headeri: getValuesConsumidor(consumidori)){ %>
					  <td><%=headeri %></td>
					  <%}
					  %> </tr> <%
				  }
			  }catch(Exception e){ }
		  %>
		  
		</table>
	</form>
</body>

</html>