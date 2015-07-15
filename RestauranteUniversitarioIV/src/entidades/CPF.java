package entidades;

public class CPF
{
	private static CPF aluno;
	
	private CPF()
	{
		// TODO Auto-generated constructor stub
	}
	
	public static CPF getInstance() 
	{
		if (aluno == null) 
		{
			aluno = new CPF();
		}
		return aluno;

	}
	
	private final Integer[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private Integer calcularDigito(String str, Integer[] peso)
	{
		
	      Integer soma = 0;
	      for (Integer indice=str.length()-1, digito; indice >= 0; indice-- )
	      {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }

	   public boolean isValidCPF(String cpf) 
	   {
	      if ((cpf==null) || (cpf.length()!=11)) return false;

	      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
	      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
	      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	   }

	
	
}
