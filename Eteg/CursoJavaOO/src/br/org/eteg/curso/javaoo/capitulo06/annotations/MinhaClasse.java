package br.org.eteg.curso.javaoo.capitulo06.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MinhaClasse {
	
	boolean teste = true;
	
	@MyAnnotation(testar=true)
	public void verificarAcesso()
	{
		System.out.println("Teste verificar Acesso");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MinhaClasse obj = new MinhaClasse();
		
		try {
			Class [] parametros = new Class[0];
			Method metodo = obj.getClass().getMethod("verificarAcesso", parametros);
			
			boolean anotacaoPresente = metodo.isAnnotationPresent(MyAnnotation.class);
			System.out.println("Anotacao Presente: " + anotacaoPresente);
			Annotation [] anotacoes = metodo.getAnnotations();
			System.out.println("Numero de anotacoes: " + anotacoes.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
