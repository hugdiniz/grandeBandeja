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
import entidades.exceptions.ConsumidorException;
import entidades.exceptions.CursoException;
import entidades.exceptions.DepartamentoException;
import entidades.value_objects.ConsumidorVO;

@WebServlet("/AtualizarConsumidor")
public class AtualizarConsumidor extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		action(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		action(request, response);
	}
	
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = (String) request.getParameter("acaoAtualizar");
		Collection departamentosDisponiveis = null;
		Collection cursos = null;
		
		try
		{
			departamentosDisponiveis = GerirConsumidor.getInstance().listarDepartamentos();
			cursos = GerirConsumidor.getInstance().listarCursos();
			
		}
		catch ( DepartamentoException | CursoException e)
		{			
			e.printStackTrace();
		}
			
		if (acao == null)
			acao = "";

		switch (acao)
		{
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarConsumidor").forward(request,response);
				break;
			case "Atualizar":
				atualizarConsumidorAntigo(request,response);
				break;
			default:
				try
				{
					String nome = (String) request.getParameter("nome");
					String sigla = (String) request.getParameter("sigla");
					
					ConsumidorVO consumidorVO = new ConsumidorVO();
					if (request.getParameter("idConsumidor") != null && !request.getParameter("idConsumidor").equals(""))
					{
						consumidorVO.setId(Long.parseLong((String) request.getParameter("idConsumidor")));
					}
					
					ConsumidorVO consumidorAntigo = GerirConsumidor.getInstance().buscarConsumidor(consumidorVO);
					request.setAttribute("consumidor",consumidorAntigo);
					request.setAttribute("titulos",TituloEnum.valores());
					if (consumidorAntigo.getTitulo() != null)
					{
						request.setAttribute("tituloConsumidor",consumidorAntigo.getTitulo().name());
					}
					else
					{
						request.setAttribute("tituloConsumidor","");
					}
					
					request.setAttribute("sexos",SexoEnum.valores());
					if (consumidorAntigo.getSexo() != null)
					{
						request.setAttribute("sexoConsumidor",consumidorAntigo.getSexo().name());
					}
					else
					{
						request.setAttribute("sexoConsumidor","");
					}
					
					if (consumidorAntigo.getCursoVO() != null &&  consumidorAntigo.getCursoVO().getNome() != null && !consumidorAntigo.getCursoVO().getNome().equals(""))
					{
						request.setAttribute("cursos", cursos);
						request.setAttribute("cursoConsumidor",consumidorAntigo.getCursoVO());
					}
					else
					{
						request.setAttribute("departamentoConsumidor",consumidorAntigo.getDepartamentoVO());
						request.setAttribute("departamentos", departamentosDisponiveis);
					}	
					
					request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request,response);
				}
				catch (ConsumidorException | CursoException | DepartamentoException e2)
				{
					request.setAttribute("erro", e2.getMessage());
					request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request,response);
				}	
		}

	}
	
	private void atualizarConsumidorAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nome = (String) request.getParameter("nome");
		String matricula = (String) request.getParameter("matricula");
		String anoIngresso = (String) request.getParameter("anoIngresso");
		String sexoString = (String) request.getParameter("sexo");
		String tituloString = (String) request.getParameter("titulo");
		String cpf = (String) request.getParameter("cpf");
		Long idDepartamento = null;
		Long idCurso = null;
		Long id = null;
		
		if (request.getParameter("departamento") != null && !request.getParameter("departamento").equals(""))
		{
			idDepartamento = Long.parseLong((String) request.getParameter("departamento"));
		}
		if (request.getParameter("curso") != null && !request.getParameter("curso").equals(""))
		{
			idCurso= Long.parseLong((String) request.getParameter("curso"));
		}
		if (request.getParameter("id") != null && !request.getParameter("id").equals(""))
		{
			id = Long.parseLong((String) request.getParameter("id"));
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
		consumidorVO.setId(id);
		consumidorVO.setNome(nome);
		consumidorVO.setMatricula(matricula);
		consumidorVO.setCpf(cpf);
		consumidorVO.setAnoIngresso(anoIngresso);
		consumidorVO.setSexo(sexo);
		consumidorVO.setTitulo(titulo);
		
		consumidorVO.setIdCurso(idCurso);
		consumidorVO.setIdDepartamento(idDepartamento);
		
		
		
		try 
		{
			GerirConsumidor.getInstance().atualizarConsumidor(consumidorVO);
			request.setAttribute("message", "Consumidor atualizado!");
			request.getRequestDispatcher("ListarConsumidor").forward(request,response);
		}
		catch (ConsumidorException e)
		{
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CriarConsumidor.jsp").forward(request,response);
		} 			
		
	}
}
