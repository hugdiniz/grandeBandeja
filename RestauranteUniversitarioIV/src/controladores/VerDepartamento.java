package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirDepartamento;
import controladores.ccu.exceptions.DepartamentoNotFound;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/VerDepartamento")
public class VerDepartamento extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		String acao = (String) request.getParameter("acaoVer");
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		if (request.getParameter("sigla") != null)
		{
			departamentoVO.setSigla(request.getParameter("sigla"));
		}
		
		if (acao == null)
			acao = "";

		switch (acao) 
		{
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			default:
			DepartamentoVO departamentoAntigo;
			try 
			{
				departamentoAntigo = GerirDepartamento.getInstance().buscarDepartamento(departamentoVO);
				request.setAttribute("departamento antigo",departamentoAntigo);
				request.getRequestDispatcher("WEB-INF/VerDepartamento.jsp").forward(request,response);
			} catch (DepartamentoException e) 
			{
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/VerDepartamento.jsp").forward(request,response);
			}
		}
	}
}