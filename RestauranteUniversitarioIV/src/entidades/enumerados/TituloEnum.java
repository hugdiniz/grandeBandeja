package entidades.enumerados;

import java.util.ArrayList;
import java.util.Collection;

public enum TituloEnum
{
	ESPECIALIZACAO, MESTRADO, DOUTORADO;
	
	
	public static Collection valores()
	{
		Collection valors =new ArrayList();
		for (TituloEnum valor : TituloEnum.values())
		{
			valors.add(valor.name());
		}
		return valors;

	}
}
