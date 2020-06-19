package br.org.eteg.curso.javaoo.capitulo08.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.org.eteg.curso.javaoo.capitulo08.comparable.Pessoa;

public class ExemploReflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String nomeClasse = "br.org.eteg.curso.javaoo.capitulo08.comparable.Pessoa";
		
		try {
			// obtem a representacao da classe pelo nome completo
			Class classe = Class.forName(nomeClasse);
			
			// cria uma nova instância da classe
			Object obj = classe.newInstance();
			
			// verificando a instancia
			System.out.println("Objeto instancia de Pessoa: " + 
					(obj instanceof Pessoa));
			((Pessoa)obj).setCpf("033.455.445.34");
			
			// obtendo os métodos de uma classe
			Method [] metodos = classe.getMethods();
			for (Method metodo:metodos)
			{
				System.out.println("Metodo: " + metodo.getName());
			}
			
			// invocando um método
			Class [] parametros = new Class[0];
			Method metodo = classe.getMethod("getCpf", parametros);
			Object [] params = new Object[0];
			Object resultado = metodo.invoke(obj, params);
			System.out.println("CPF via reflexão: " + resultado);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

}
