package entidades.value_objects;

public class TicketVO implements VO
{
	private Long idConsumidor;
	private Long idRefeicao;
	private boolean pago;
	private Long id;
	
	public TicketVO() {
		// TODO Auto-generated constructor stub
	}
	public TicketVO(Long cons, Long ref, boolean pg)
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
	public boolean isPago() 
	{
		return pago;
	}
	public void setPago(boolean pago) 
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
	
}
