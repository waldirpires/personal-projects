package br.org.eteg.curso.javaoo.capitulo06.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {	
	boolean testar() default false;
}
