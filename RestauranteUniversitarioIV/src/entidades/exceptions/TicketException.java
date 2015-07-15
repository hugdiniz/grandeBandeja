package entidades.exceptions;

public class TicketException extends Exception
{
	public TicketException() 
	{
		super();
	}
	public TicketException(String excecaoFrase) 
	{
		super(excecaoFrase);
	}
	public TicketException(Exception exception) 
	{
		super(exception.getMessage());
	}
	public TicketException(String excecaoFrase, Exception exception) 
	{
		super(excecaoFrase + "\n" +exception.getMessage());
	}
}
