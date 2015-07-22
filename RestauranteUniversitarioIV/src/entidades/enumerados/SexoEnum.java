package entidades.enumerados;

import java.util.ArrayList;
import java.util.Collection;

public enum SexoEnum 
{
	MASCULINO,FEMININO;
	
	public static Collection valores()
	{
		Collection valors =new ArrayList();
		for (SexoEnum valor : SexoEnum.values())
		{
			valors.add(valor.name());
		}
		return valors;

	}
}
