package br.org.mg.prodemge.curso.logprog.aula2;

import java.util.Scanner;

public class NumeroEhPar {

	public static void main(String[] args) {
//		int n, r;
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Digite um número inteiro: ");
//		n = sc.nextInt();
//		r = n % 2;
//		if (r == 0){
//			System.out.println("Eh Par!");
//		} else {
//			System.out.println("Eh Ímpar!");
//		}
		int soma = 0;
		for (int i = 1; i <= 100; i++)
		{
			soma += i;
			System.out.println(i + ": " + soma);
		}
		
	}
}
