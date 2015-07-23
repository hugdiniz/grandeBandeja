package entidades.value_objects;

public class TicketVO implements VO
{
	private Long idConsumidor;
	private Long idRefeicao;
	private Boolean pago;
	private Long id;
	private ConsumidorVO consumidorVO;
	private RefeicaoVO refeicaoVO;
	
	public TicketVO() 
	{
		// TODO Auto-generated constructor stub
	}
	public TicketVO(Long cons, Long ref, Boolean pg)
	{
		this.idConsumidor = cons;
		this.idRefeicao = ref;
		this.pago = pg;
	}
	
	public Long getIdConsumidor()
	{
		return idConsumidor;
	}
	public void setIdConsumidor(Long idConsumidor)
	{
		this.idConsumidor = idConsumidor;
	}
	
	public Long getIdRefeicao()
	{
		return idRefeicao;
	}
	public void setIdRefeicao(Long idRefeicao) 
	{
		this.idRefeicao = idRefeicao;
	}
	
	public Boolean getPago() 
	{
		return pago;
	}
	public void setPago(Boolean pago) 
	{
		this.pago = pago;
	}
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public ConsumidorVO getConsumidorVO()
	{
		return consumidorVO;
	}
	public void setConsumidorVO(ConsumidorVO consumidorVO)
	{
		this.consumidorVO = consumidorVO;
	}
	
	public RefeicaoVO getRefeicaoVO()
	{
		return refeicaoVO;
	}
	public void setRefeicaoVO(RefeicaoVO refeicaoVO)
	{
		this.refeicaoVO = refeicaoVO;
	}
	
}
