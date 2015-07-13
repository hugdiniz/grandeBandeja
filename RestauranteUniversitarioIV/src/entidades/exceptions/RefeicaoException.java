package entidades.exceptions;

public class RefeicaoException extends Exception
{
	public RefeicaoException() 
	{
		super();
	}
	public RefeicaoException(String excecaoFrase) 
	{
		super(excecaoFrase);
	}
	public RefeicaoException(Exception exception) 
	{
		super(exception.getMessage());
	}
	public RefeicaoException(String excecaoFrase, Exception exception) 
	{
		super(excecaoFrase + "\n" +exception.getMessage());
	}
}
