package br.org.eteg.curso.javaoo.capitulo07.generics;

import java.util.ArrayList;
import java.util.List;

public class MinhaLista<E> {

	List<E> lista = new ArrayList<E>();
	
	public int getTamanho()
	{
		return lista.size();
	}
	
	public void inserir(E e)
	{
		lista.add(e);
	}
	
	public void remover(E e)
	{
		lista.remove(e);
	}
}
