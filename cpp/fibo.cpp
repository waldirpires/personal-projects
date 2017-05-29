#include <iostream>
using namespace std;

int numCalc = 0;

long fibonacci(int n){
    numCalc++;
    if (n < 2){
        cout << "f("<<n<<") = "<< n << endl;
        return n;
    }
    long f = fibonacci(n-1) + fibonacci(n-2);
    cout << "f("<<n<<") = "<< f << endl;
    return f;
}

int main() {
	int n;
	cout << "Digite um número: \n";
	cin >> n;
	
	cout << "Fibonacci de " << n << ": " << fibonacci(n)<<endl;
	cout << "Número de cálculos: " << numCalc << endl;
	return 0;
}



#include <iostream>
using namespace std;

long fibonacci(int n){
    if (n < 2){
        cout << "f("<<n<<") = "<< n << endl;
        return n;
    }
    long f = fibonacci(n-1) + fibonacci(n-2);
    cout << "f("<<n<<") = "<< f << endl;
    return f;
}

int main() {
	int n;
	cout << "Digite um número: \n";
	cin >> n;
	
	cout << "Fibonacci de " << n << ": " << fibonacci(n)<<endl;
	return 0;
}

Ferramentas 
Java
Online:
-https://www.jdoodle.com/online-compiler-c++
-https://www.codechef.com/ide
Offline:
-Eclipse IDE
-Netbeans

(C/C++)
Online:
-https://www.jdoodle.com/online-compiler-c++
-https://www.codechef.com/ide
Offline:
-Dev CPP
-Code Blocks

Problema 1: Fibonacci (Força Bruta sem Memória)
Parte A
Entrada: número natural (inteiro positivo) informado pelo usuário (N)
Saída: informar o eNésimo elemento da série de Fibonacci

Parte B
Alterar a solução acima para exibir cada um dos valores calculados da série de Fibonacci.

Exemplo:
Digite um número: 4
f(1) = 1
f(0) = 0
f(2) = 1
f(1) = 1
f(3) = 2
f(1) = 1
f(0) = 0
f(2) = 1
f(4) = 3
Fibonacci de 4: 3

Parte C
Alterar o programa acima para calcular e exibir a quantidade total de valores calculados.

Exemplo:
Digite um número: 
f(1) = 1
f(0) = 0
f(2) = 1
f(1) = 1
f(3) = 2
f(1) = 1
f(0) = 0
f(2) = 1
f(4) = 3
Fibonacci de 4: 3
Número de cálculos: 9

Parte D
Para cada um dos valores abaixo, apresentar uma planilha e um gráfico do número de cálculos em função de N.
Valores de N: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15

Dica: utilizar vetores e um laço de repetição

Problema 2: Fibonacci (Programação Dinâmica com Memória)
Parte A
Alterar o problema acima para permitir o armazenamento de cálculos já realizados. Utilizar um vetor para o armazenamento e reuso do cálculo realizado.

Parte B
Repetir a parte D do problema anterior para o Problema 2. Apresentar uma tabela e gráfico contendo ambas alternativas (sem memória e com memória)

Problema 3: Mochila fracionária
Entrada: um vetor contendo elementos E com peso e valor (w, v) e uma capacidade máxima W
Saída: Informar a configuração máxima de elementos em E que pode ser levada em uma mochila com capacidade máxima W
Obs: elementos podem ser divididos em frações

Problema 4: Mochila 0/1
Entrada: um vetor contendo elementos E com peso e valor (w, v) e uma capacidade máxima W
Saída: Informar a configuração máxima de elementos em E que pode ser levada em uma mochila com capacidade máxima W
Obs: elementos NÃO podem ser divididos em frações

