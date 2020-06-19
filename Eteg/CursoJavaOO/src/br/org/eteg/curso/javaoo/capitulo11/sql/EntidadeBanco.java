package br.org.eteg.curso.javaoo.capitulo11.sql;

import java.util.Collection;
import java.util.HashMap;

public class EntidadeBanco {

	private HashMap<String, String> propriedades = new HashMap<String, String>();
	
	public void adicionarPropriedade(String propriedade, String valor)
	{
		propriedades.put(propriedade, valor);
	}
	
	public HashMap<String, String> getPropriedades()
	{
		return propriedades;
	}
	
	public String getValor(String propriedade)
	{
		return propriedades.get(propriedade);
	}

	public Collection<String> getListaPropriedades()
	{
		return propriedades.keySet();
	}
}
