package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.enumerados.SexoEnum;
import entidades.enumerados.TituloEnum;
import entidades.enumerados.Turno;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.CursoVO;
import entidades.value_objects.DepartamentoVO;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TicketVO;
import entidades.value_objects.VO;

public class RepositorioTicket extends Repositorio
{
	private static RepositorioTicket repositorio;
	
	
	public static RepositorioTicket getInstance() 
	{
		if (repositorio == null) 
		{
			repositorio = new RepositorioTicket();
		}
		return repositorio;
	}

	@Override
	public void inserirOuAtualizar(VO vo) throws SQLException
	{
		TicketVO ticketVO = (TicketVO) vo;
		Collection vars = new ArrayList();
		
		StringBuilder stringVars = new StringBuilder();
		
		StringBuilder campos = new StringBuilder();
		
		if (ticketVO.getId() != null)
		{
			campos.append("UPDATE ticket SET (");
			stringVars.append(" = (");
		}
		else
		{
			campos.append("INSERT INTO ticket (");
			stringVars.append("VALUES (");
			
		}	
		
		
		Boolean primeiroCampo = true;		
		if (ticketVO.getId()!= null && !ticketVO.getId().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("id");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",id");
				stringVars.append(",?");
			}
			
			vars.add(ticketVO.getId());
		}
		if (ticketVO.getPago() != null)
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("pago");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",pago");
				stringVars.append(",?");
			}
			
			vars.add(ticketVO.getPago());
		}
		if (ticketVO.getIdConsumidor()!= null)
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("consumidor_fk");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",consumidor_fk");
				stringVars.append(",?");
			}
			
			vars.add(ticketVO.getIdConsumidor());
		}

		if (ticketVO.getIdRefeicao()!= null )
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				campos.append("refeicao_fk");
				stringVars.append("?");
			}
			else 
			{
				campos.append(",refeicao_fk");
				stringVars.append(",?");
			}
			
			vars.add(ticketVO.getIdRefeicao());
		}
		
		campos.append(") ");

		campos.append(stringVars);

		if (ticketVO.getId() != null) {
			campos.append(") WHERE id=?;");
			vars.add(ticketVO.getId());
		} else {
			campos.append(");");
		}
		
		System.out.println(campos);
		insertOrUpdateSQL(campos.toString(), vars);	

	}

	@Override
	public Collection buscar(VO vo) throws SQLException
	{
		TicketVO ticketVO = (TicketVO) vo;
		Collection vars = new ArrayList();		
		StringBuilder sql = new StringBuilder();
		StringBuilder restricao = new StringBuilder();
		StringBuilder tabela = new StringBuilder();
		StringBuilder projecao = new StringBuilder();
		ResultSet resultSet = null;	
		
		projecao.append("select * ");		
		
		Boolean primeiroCampo = true;
		
		
		if (ticketVO.getId() != null && !ticketVO.getId().equals(""))
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where ticket.id like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and ticket.id like");
				restricao.append(" ? ");
			}
			
			vars.add(ticketVO.getId());
		}
		if (ticketVO.getPago() != null)
		{			
			if (primeiroCampo)
			{
				primeiroCampo = false;
				restricao.append(" where ticket.pago like ");
				restricao.append(" ? ");
			}
			else 
			{
				restricao.append(" and ticket.pago like");
				restricao.append(" ? ");
			}
			
			vars.add(ticketVO.getPago());
		}
		
		if (ticketVO.getConsumidorVO()!= null)
		{	
			ConsumidorVO consumidorVO = ticketVO.getConsumidorVO();
			
			if (consumidorVO.getId() != null && !consumidorVO.getId().equals(""))
			{			
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where consumidor.id like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append(" and consumidor.id like");
					restricao.append(" ? ");
				}
				
				vars.add(consumidorVO.getId());
			}
			if (consumidorVO.getNome()!= null && !consumidorVO.getNome().equals(""))
			{			
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where consumidor.nome like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append(" and consumidor.nome like");
					restricao.append(" ? ");
				}
				
				vars.add(consumidorVO.getNome());
			}
			if (consumidorVO.getHabilitado()!= null && !consumidorVO.getHabilitado().equals(""))
			{			
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where consumidor.habilitado like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append(" and consumidor.habilitado like");
					restricao.append(" ? ");
				}
				
				vars.add(consumidorVO.getHabilitado());
			}
			if (consumidorVO.getMatricula()!= null && !consumidorVO.getMatricula().equals(""))
			{			
				if (primeiroCampo)
				{
					primeiroCampo = false;
					restricao.append(" where consumidor.matricula like ");
					restricao.append(" ? ");
				}
				else 
				{
					restricao.append(" and consumidor.matricula like");
					restricao.append(" ? ");
				}
				
				vars.add(consumidorVO.getHabilitado());
			}
		}
		
		
		/*
		 * Parte do Joins
		 */
		tabela.append("from ticket ");
		tabela.append(" left join consumidor on ticket.consumidor_fk = consumidor.id ");
		tabela.append(" left join refeicao on refeicao.id = ticket.refeicao_fk ");		
		
		
		sql.append(projecao);
		sql.append(tabela);
		sql.append(restricao);
		sql.append(";");
		
		System.out.println(sql);
		resultSet = executeSQL(sql.toString(), vars);		
		
		Collection vos = new ArrayList(); 
		while (resultSet.next()) 
		{
			TicketVO ticketVOSaida = new TicketVO();			
			ticketVOSaida.setId(resultSet.getLong("ticket.id"));
			
			ConsumidorVO consumidorVOSaida = new ConsumidorVO();
			consumidorVOSaida.setId(resultSet.getLong("consumidor.id"));
			consumidorVOSaida.setAnoIngresso(resultSet.getString("consumidor.anoIngresso"));
			consumidorVOSaida.setNome(resultSet.getString("consumidor.nome"));
			consumidorVOSaida.setMatricula(resultSet.getString("consumidor.matricula"));
			consumidorVOSaida.setCpf(resultSet.getString("consumidor.cpf"));
			consumidorVOSaida.setTitulo(TituloEnum.valueOf(resultSet.getString("consumidor.titulo")));
			consumidorVOSaida.setSexo(SexoEnum.valueOf(resultSet.getString("consumidor.sexo")));
			
			RefeicaoVO refeicaoSaida = new RefeicaoVO();
			refeicaoSaida.setDescricao(resultSet.getString("refeicao.descricao"));
			refeicaoSaida.setId(resultSet.getLong("refeicao.id"));
			refeicaoSaida.setOp_vegetariana(resultSet.getString("refeicao.opcaoVegan"));
			Turno turno = Turno.valueOf(resultSet.getString("refeicao.turno"));
			
			refeicaoSaida.setValorAluno(turno.getValorAluno());
			refeicaoSaida.setValorFuncionario(turno.getValorFuncionario());
			refeicaoSaida.setTurno(turno);
			
			ticketVOSaida.setRefeicaoVO(refeicaoSaida);
			ticketVOSaida.setConsumidorVO(consumidorVOSaida);
			
			vos.add(ticketVOSaida);
			
		}
		
		return vos;
	}

	@Override
	public Collection remover(VO vo)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
