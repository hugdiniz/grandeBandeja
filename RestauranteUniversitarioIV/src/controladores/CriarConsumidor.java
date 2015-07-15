package controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ccu.GerirConsumidor;
import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;
import entidades.exceptions.AlunoException;
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.FuncionarioException;
import entidades.value_objects.ConsumidorVO;

@WebServlet("/CriarConsumidor")
public class CriarConsumidor extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			request.setAttribute("departamentoVOs", GerirConsumidor.getInstance().listarDepartamentos());
			request.setAttribute("cursoVOs", GerirConsumidor.getInstance().listarCursos());
			String acao = (String) request.getParameter("acaoCriar");
			
			if (acao != null)
			{
				switch (acao) 
				{
					case "Criar":
						initJsp(request,response);
						break;
					default:
						request.getRequestDispatcher("WEB-INF/CriarConsumidor.jsp").forward(request,response);
				}
			}
			else
			{
				request.getRequestDispatcher("WEB-INF/CriarConsumidor.jsp").forward(request,response);	
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			request.setAttribute("departamentoVOs", GerirConsumidor.getInstance().listarDepartamentos());
			request.setAttribute("cursoVOs", GerirConsumidor.getInstance().listarCursos());
			String acao = (String) request.getParameter("acaoCriar");
			
			if (acao != null)
			{
				switch (acao) 
				{
					case "Criar":
						initJsp(request,response);
						break;
					default:
						request.getRequestDispatcher("WEB-INF/CriarConsumidor.jsp").forward(request,response);
				}
			}
			else
			{
				request.getRequestDispatcher("WEB-INF/CriarConsumidor.jsp").forward(request,response);	
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
						
		
	}

	private void initJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AlunoException, FuncionarioException 
	{
		String nome = (String) request.getParameter("nome");
		String matricula = (String) request.getParameter("matricula");
		String anoIngresso = (String) request.getParameter("anoIngresso");
		String sexoString = (String) request.getParameter("sexo");
		String tituloString = (String) request.getParameter("titulo");
		String cpf = (String) request.getParameter("cpf");
		Long idDepartamento = null;
		Long idCurso = null;
		
		if (request.getParameter("departamento") != null && !request.getParameter("departamento").equals(""))
		{
			idDepartamento = Long.parseLong((String) request.getParameter("departamento"));
		}
		if (request.getParameter("curso") != null && !request.getParameter("curso").equals(""))
		{
			idCurso= Long.parseLong((String) request.getParameter("curso"));
		}
		
		
		String tipoConsumidor = (String) request.getParameter("tipoConsumidor");
		
		SexoEnum sexo = null;
		TituloEnum titulo = null;
		
		if (sexoString != null && !sexoString.equals(""))
		{
			sexo = SexoEnum.valueOf(sexoString);
		}
		if (tituloString != null && !tituloString.equals(""))
		{
			titulo = TituloEnum.valueOf(tituloString);
		}
		
		
		
		ConsumidorVO consumidorVO = new ConsumidorVO();
		
		consumidorVO.setNome(nome);
		consumidorVO.setMatricula(matricula);
		consumidorVO.setCpf(cpf);
		consumidorVO.setAnoIngresso(anoIngresso);
		consumidorVO.setSexo(sexo);
		consumidorVO.setTitulo(titulo);
		if (tipoConsumidor != null && tipoConsumidor.equals("aluno"))
		{
			consumidorVO.setIdCurso(idCurso);
		}
		else
		{
			consumidorVO.setIdDepartamento(idDepartamento);
		}	
		
		GerirConsumidor.getInstance().criarConsumidor(consumidorVO);
		
		request.setAttribute("message", "Novo departamento criado!");
		request.getRequestDispatcher("ListarCurso").forward(request,response);
	}

}
