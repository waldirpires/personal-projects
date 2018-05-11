package br.org.mg.prodemge.curso.logprog.aula9;

import java.util.Scanner;

public class ProgramaSomar {

	static int somar(int a, int b){
		return a+b;
	}
	
	public static void main(String[] args) {

		int a, b, r;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o 1o número: ");
		a = sc.nextInt();
		System.out.println("Digite o 2o número: ");
		b = sc.nextInt();
		r = somar(a, b);
		System.out.println(a + " + " + b + " = " + r);		
	}

}
