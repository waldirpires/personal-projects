package br.org.eteg.curso.javaoo.capitulo07.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExemploGenerics {

	public static void main(String[] args) {
		List lista = new ArrayList();
		lista.add(new String("Abacate"));
		lista.add(new String("Maçã"));
		lista.add(new String("Banana"));
		lista.add(new Integer(1));
		
		Iterator it = lista.iterator();
		try {
			while(it.hasNext())
			{
				Object obj = it.next();
				System.out.println(obj.getClass().getSimpleName());
				String item = (String) obj;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}
