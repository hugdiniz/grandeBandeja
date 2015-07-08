<% if (request.getAttribute("message") != null){ %>
	<div>
		<strong><%=request.getAttribute("message") %></strong> 
	</div>
<% } %>

<% if (request.getAttribute("erro") != null){ %>
	<div style="color:rgb(184,0,0)">
		<strong><%=request.getAttribute("erro") %></strong>
	</div>
<% } %>