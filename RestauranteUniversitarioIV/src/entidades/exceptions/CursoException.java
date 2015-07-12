package entidades.exceptions;

public class CursoException extends Exception
{
	public CursoException() 
	{
		super();
	}
	public CursoException(String excecaoFrase) 
	{
		super(excecaoFrase);
	}
	public CursoException(Exception exception) 
	{
		super(exception.getMessage());
	}
	public CursoException(String excecaoFrase, Exception exception) 
	{
		super(excecaoFrase + "\n" +exception.getMessage());
	}
}
