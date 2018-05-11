package br.org.mg.prodemge.curso.logprog.aula9;

import java.util.Scanner;

public class ProgramaQuadRaiz {

	// calcula a raiz quadrada
	static double calcRaizQuad(int n){
		return Math.sqrt(n);
	}
	
	// calcula o quadrado
	static int calcQuad(int n){
		return n * n;
	}
	
	// m�todo principal
	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite um n�mero: ");
		n = sc.nextInt();
		System.out.println("Quadrado: " + calcQuad(n));
		System.out.println("Raiz Quadrada: " + calcRaizQuad(n));
	}

}
