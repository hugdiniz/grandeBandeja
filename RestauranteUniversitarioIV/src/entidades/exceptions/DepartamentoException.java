package entidades.exceptions;

public class DepartamentoException extends Exception
{
	public DepartamentoException() 
	{
		super();
	}
	public DepartamentoException(String excecaoFrase) 
	{
		super(excecaoFrase);
	}
	public DepartamentoException(Exception exception) 
	{
		super(exception.getMessage());
	}
	public DepartamentoException(String excecaoFrase, Exception exception) 
	{
		super(excecaoFrase + "\n" +exception.getMessage());
	}
}
