package br.edu.pitagoras.aed2.ordenacao;

import java.util.Arrays;
import java.util.Random;

public class PrincipalOrdenacao {

	// ordenacao por bolha
	private static void bolha(int [] v){
		// para cada indice i no vetor v
		for (int i = 0; i < v.length; i++){
		//   para cada indice j no vetor v
			for (int j = 0; j < v.length-1; j++){
		//     se o elemento na posição j for maior que o da 
		//     posição j + 1
				if (v[j] > v[j+1]){
		//       trocar valores j e j+1
					trocarValores(v, j, j+1);
				}
			}
		}
	}
	
	/**
	 * método que gera um vetor de numeros aleatorios
	 * @param tamanho tamanho do vetor a ser gerado
	 * @param limte limite superior dos numeros a serem gerados
	 * @return o vetor criado
	 */
	private static int [] gerarVetor(int tamanho, int limite){
		// inicializar o gerador pseudo-aleatório
		Random rnd = new Random(System.currentTimeMillis());
		// inicializar o vetor que receberá os numeros aleatórios
		int [] v = new int[tamanho];
		// para cada indice do vetor
		for (int i = 0; i < tamanho; i++){
		//   gerar o numero aleatorio
			int num = rnd.nextInt() % limite +1;
			num = Math.abs(num);
		//   armazena-lo no vetor
			v[i] = num;
		}
		// retornar o vetor
		return v;		
	}
	
	// ordenação por seleção
	private static void selecao(int [] v){
		int menor = -1;
		// para cada posicao i do vetor V		
		for (int i = 0; i < v.length; i++){
		//   assumir que o elemento no indice i é o menor
			menor = i;
		//   para cada posição subsequente j do vetor V
			for (int j = i+1; j< v.length;j++){
		//     se o elemento na posicao j for menor que o 
		//     elemento na posição i
				if (v[j] < v[menor]){
		//       marcar o elemento na posição j como sendo o menor
					menor = j;
				}
			}
		//   se o menor elemento encontrado for diferente do 
		//   elemento na posiçao i
			if (menor != i){
		//     trocar os valores menor e i
				trocarValores(v, i, menor);
			}
		}
	}
	
	private static void trocarValores(int[] v, int i, int menor) {
		int aux = v[i];
		v[i] = v[menor];
		v[menor] = aux;		
	}

	private static void merge(int pivot, int low, int length, int high, int[] a) {
		  int merge1, merge2, i;
		  int []working;

		  merge1 = 0;
		  merge2 = pivot - low + 1;
		  working = new int [length];

		  for (i = 0; i < length; i++){
		    working[i] = a[low + i];
		  }

		  for (i = 0; i < length; i++) {
		    // iterando dentro de cada subvetor
		    if (merge2 <= high - low) {
		      if (merge1 <= pivot - low) {
		        if (working[merge1] > working[merge2]) {
		          a[i + low] = working[merge2++];
		        } else {
		          a[i + low] = working[merge1++];
		        }
		      } else {
		        a[i + low] = working[merge2++];
		      }
		    } else {
		      a[i + low] = working[merge1++];
		    }
		  }
		}

		private static void mergesort(int []a, int low, int high) {
		  int length = high - low + 1;
		  int pivot = 0;

		  if (low == high) // caso base: vetor de tamanho 1
		    return;
		  pivot = (low + high) / 2; // cálculo do ponto médio

		  mergesort(a, low, pivot); // dividir a esquerda
		  mergesort(a, pivot + 1, high); // dividir a direita

		  merge(pivot, low, length, high, a); // junção das soluções
		}	
	
		private static void quicksort(int []a, int tam, int left, int right) {
			  int i, j, p;
			  i = left;
			  j = right;

			  p = a[(left + right) / 2]; // Elemento intermediário como “pivo” 
			  do {
			    while (a[i] < p && i < right)
			      i++;
			    while (a[j] > p && j > left)
			      j--;
			    if (i <= j) {
			      trocarValores(a, i, j);
			      i++;
			      j--;
			    }
			  } while (i <= j);
			  if (left < j)
			    quicksort(a, tam, left, j);
			  if (i < right)
			    quicksort(a, tam, i, right);
			}
		
		
	public static void main(String[] args) {
		// declaração de variáveis
		int tamanho = 100 * 1000000;
		int limite = 100 * 1000000;
		long time = 0; // tempo em ms
		// gerar o vetor de números aleatórios
		int [] v = gerarVetor(tamanho, limite);
		System.out.println("Vetor gerado: " + v.length);
		//imprimirVetor(v);
		// iniciar a tomada de tempo
		time = System.currentTimeMillis();
		// ordenar por selecao
		//selecao(v);
		// ordenar por bolha
		//bolha(v);
		// ordenar por quicksort
		//Arrays.sort(v);
		//mergesort(v, 0, v.length-1);
		quicksort(v, v.length, 0, v.length-1);
		// encerrar a tomada de tempo
		time = System.currentTimeMillis() - time;
		// exibir resultado de tempo
		System.out.println("Seleção " + tamanho + " - tempo: " + 
				time + " ms");
		//imprimirVetor(v);
	}

	private static void imprimirVetor(int[] v) {
		for (int i = 0; i < v.length; i++)
			System.out.print(v[i] + "\t");
		System.out.println();
	}
	
	
	
	

}
