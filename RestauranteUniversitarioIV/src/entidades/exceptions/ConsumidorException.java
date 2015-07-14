package entidades.exceptions;

public class ConsumidorException extends Exception
{
	public ConsumidorException() 
	{
		super();
	}
	public ConsumidorException(String excecaoFrase) 
	{
		super(excecaoFrase);
	}
	public ConsumidorException(Exception exception) 
	{
		super(exception.getMessage());
	}
	public ConsumidorException(String excecaoFrase, Exception exception) 
	{
		super(excecaoFrase + "\n" +exception.getMessage());
	}
}
