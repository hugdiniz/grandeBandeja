package controladores.ccu;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import controladores.ccu.exceptions.RefeicaoNotFound;
import entidades.Refeicao;
import entidades.exceptions.RefeicaoException;
import entidades.value_objects.RefeicaoVO;

public class GerirRefeicao
{
	private GerirRefeicao()
	{
		
	}
	
	private static GerirRefeicao gerirRefeicao;
	public static GerirRefeicao getInstance()
	{
		if (gerirRefeicao == null)
		{
			gerirRefeicao = new GerirRefeicao();
		}
		return gerirRefeicao;
	}

	public Collection listarRefeicoes() throws RefeicaoException
	{
		return Refeicao.getInstance().recuperarRefeicoes(new RefeicaoVO());
	}

	public RefeicaoVO buscarRefeicao(RefeicaoVO refeicaoVO) throws RefeicaoException
	{			
		RefeicaoVO refeicaoAntigo = Refeicao.getInstance().recuperarRefeicao(refeicaoVO);		

		return refeicaoAntigo;
	}	

	public void criarRefeicao(RefeicaoVO refeicaoVO) throws RefeicaoException 
	{
		Refeicao.getInstance().adicionarRefeicao(refeicaoVO);
	}

	public void atualizarRefeicao(RefeicaoVO refeicaoVO) throws RefeicaoException
	{
		Refeicao.getInstance().atualizarRefeicao(refeicaoVO);
	}
}

