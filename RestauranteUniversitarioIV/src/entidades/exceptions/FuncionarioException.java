package entidades.exceptions;

public class FuncionarioException extends ConsumidorException
{
		public FuncionarioException() 
		{
			super();
		}
		public FuncionarioException(String excecaoFrase) 
		{
			super(excecaoFrase);
		}
		public FuncionarioException(Exception exception) 
		{
			super(exception.getMessage());
		}
		public FuncionarioException(String excecaoFrase, Exception exception) 
		{
			super(excecaoFrase + "\n" +exception.getMessage());
		}
	

}
