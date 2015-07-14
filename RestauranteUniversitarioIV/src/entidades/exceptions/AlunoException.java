package entidades.exceptions;

public class AlunoException extends Exception
{
	public AlunoException() 
	{
		super();
	}
	public AlunoException(String excecaoFrase) 
	{
		super(excecaoFrase);
	}
	public AlunoException(Exception exception) 
	{
		super(exception.getMessage());
	}
	public AlunoException(String excecaoFrase, Exception exception) 
	{
		super(excecaoFrase + "\n" +exception.getMessage());
	}
}
