package br.eteg.curso.java.entidade.generico;

import java.util.ArrayList;
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
	
	/**
	 * retorna os valores de uma entidade.
	 * @return lista de valores
	 */
	public Collection<String> getValores()
	{
		Collection<String> propriedades = getListaPropriedades();
		Collection<String> valores = new ArrayList<String>();
		for (String s: propriedades)
		{
			valores.add(getValor(s));
		}
		return valores;
	}
	
	public String getListaPropriedadesFormatada()
	{
		StringBuilder sb = new StringBuilder();
		Collection<String> propriedades = getListaPropriedades();
		return String.format(criarFormato(propriedades.size()), propriedades.toArray());		
	}
	
	private String criarFormato(int numPropriedades)
	{
		StringBuilder sb = new StringBuilder();
		String formato = "$18s     ";
		for (int i = 1; i <= numPropriedades; i++)
		{
			sb.append("|%").append(i).append(formato);
		}
		return sb.toString();
	}
	
	public String getListaValoresFormatada()
	{
		StringBuilder sb = new StringBuilder();
		Collection<String> valores = getValores();
		return String.format(criarFormato(valores.size()), valores.toArray());		
	}
	
	public String getListaValoresFormatada2()
	{
		StringBuilder sb = new StringBuilder();
		String formato = "%1$20s   %2$20s   %3$20s   %4$20s   %5$20s   %6$20s   %7$20s";
		return String.format(formato, getListaPropriedades().toArray());		
	}
	
	public String getPropriedadesValores()
	{
		Collection<String> propriedades = getListaPropriedades();
		StringBuilder sb = new StringBuilder();
		for (String s: propriedades)
		{
			sb.append(s).append(" = ").append(getValor(s)).append(" | ");
		}
		return sb.toString();		
	}
	
	public void imprimirPropriedadesValores()
	{
		System.out.println(getPropriedadesValores());
	}
	
	@Override
	public String toString() {
		return getPropriedadesValores();
	}
}
