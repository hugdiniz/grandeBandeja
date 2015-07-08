package entidades;

public class CPF {
	
	private int[] digitos;
	
	public CPF(int... digitos) throws Exception {
		if (digitos.length != 11){
			throw new Exception("O CPF deve conter 11 digitos!");
			// tambem podemos validar os calculos do dv aqui!
		}else{
			// usando o clone = nao permitir alteracoes externas a classe CPF nos valores do array
			this.digitos = digitos.clone();
		}
	}
	
	public int getDigitosVerificadores(){
		return digitos[9]*10 + digitos[10];
	}

	public int[] getListaDigitosVerificadores(){
		return new int[]{digitos[9], digitos[10]};
	}

	public int[] getListaDigitos(){
		return digitos.clone();
	}
	@Override
	public String toString() {
		String results = "";
		for (int i = 0; i < digitos.length; i++) {
			results += ""+digitos[i];
		}
		return results;
	}
	
	@Override
	public boolean equals(Object obj) {
		CPF outro = (CPF) obj;
		int[] digitosOutro = outro.getListaDigitos();
		
		boolean iguais = true;
		for (int i = 0; i < digitos.length; i++) {
			if (digitos[i]!= digitosOutro[i]){
				iguais = false;
				break;
			}
		}
		return iguais;
	}
	
	public static CPF fromString(String digitos) throws Exception{
		int[] arrayDigitos = new int[digitos.length()];
		
		for (int i = 0; i < arrayDigitos.length; i++) {
			arrayDigitos[i] = Integer.parseInt(""+digitos.charAt(i)); 
		}
		
		CPF cpf = new CPF(arrayDigitos);
		return cpf;
	}
}
