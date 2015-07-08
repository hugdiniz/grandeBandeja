package controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Aluno;
import entidades.CPF;
import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.Funcionario;
import entidades.Sexo;
import entidades.Titulo;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/ListarConsumidor")
public class ListarConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarConsumidores(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				criarConsumidor(request,response);
				break;
			case "Atualizar":
				atualizarConsumidor(request,response);
				break;
			case "Ver":
				verConsumidor(request,response);
				break;
			// nos requisitos nao podemos remover consumidores
//			case "Remover":
//				removeConsumidor(request,response);
//				break;
			case "":
			default:
				listarConsumidores(request,response);				
		}
	}

	private void criarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CriarConsumidor").forward(request,response);
	}
	
	private void atualizarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AtualizarConsumidor").forward(request,response);
	}	

	private void verConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("VerConsumidor").forward(request,response);
	}

//	private void removeConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("RemoverConsumidor").forward(request,response);
//	}

	private void listarConsumidores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("consumidores", _listarConsumidoresDisponiveis(request));
		request.getRequestDispatcher("WEB-INF/ListarConsumidor.jsp").forward(request,response);
	}

	// metodos de persistencia de Consumidor

	public static Collection<Consumidor> _listarConsumidoresDisponiveis(HttpServletRequest request){
		
		HttpSession session = request.getSession();

		Collection<Consumidor> consumidoresDisponiveis = (Collection<Consumidor>)session.getAttribute("consumidoresDisponiveis");
		if (consumidoresDisponiveis == null){
			consumidoresDisponiveis = new HashSet<Consumidor>();
		}
		
		for (int i = 0; i < 3; i++) {
			CursoVO c1 = new CursoVO("aaa","bbb",new DepartamentoVO("11","22"));
//			System.out.println(i);
			
			try {
				consumidoresDisponiveis.add(new Aluno("Fellipe"+i,i,2015,Sexo.MASCULINO,Titulo.MESTRADO,new CPF(1,2,3,4,5,6,7,8,0,1,2), c1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 10; i++) {
			DepartamentoVO d1 = new DepartamentoVO("Departamento"+i,"d"+i);
//			System.out.println(i);
			
			try {
				consumidoresDisponiveis.add(new Funcionario("Func"+i,i,2015,Sexo.MASCULINO,Titulo.MESTRADO,new CPF(1,2,3,4,5,6,7,8,0,1,2), d1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		

		session.setAttribute("consumidoresDisponiveis", consumidoresDisponiveis);

		return consumidoresDisponiveis;
	}
	
	public static void _adicionarConsumidor(HttpServletRequest request, Consumidor consumido) {
		Set<Consumidor> consumidoresDisponiveis = (Set<Consumidor>)request.getSession().getAttribute("consumidoresDisponiveis");
		consumidoresDisponiveis.add(consumido);
		request.getSession().setAttribute("consumidoresDisponiveis",consumidoresDisponiveis);
	}

	public static Consumidor _buscarConsumidor(HttpServletRequest request, CPF cpfConsumido) {
		Set<Consumidor> consumidoresDisponiveis = (Set<Consumidor>)request.getSession().getAttribute("consumidoresDisponiveis");
		for(Consumidor ci : consumidoresDisponiveis){
			if (cpfConsumido.equals(ci.getCpf()))
				return ci;
		}
		return null;
	}

	public static void _atualizarConsumidor (HttpServletRequest request, Consumidor consumidor) throws ServletException, IOException {
		Collection<Consumidor> consumidoresDisponiveis = (Collection<Consumidor>)request.getSession().getAttribute("consumidoresDisponiveis");
		Consumidor consumidorAntigo = _buscarConsumidor(request,consumidor.getCpf());
//		consumidoAntigo.setNome(consumido.getNome());
//		consumidoAntigo.setDepartamento(consumido.getDepartamento());
		consumidoresDisponiveis.add(consumidorAntigo);
		
		request.getSession().setAttribute("consumidoresDisponiveis", consumidoresDisponiveis);
	}

}
