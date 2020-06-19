#include <stdio.h>
#include <stdlib.h>

double calcularTemperatura(int tipo, int valor)
{
  double res = 0;
  if (tipo == 2)
  {
    res = (5/9) * (valor-32);
  } else if (tipo == 1)
  {
    res = (9/5) * valor + 32;
  }
  return res;
}

int main(int argc, char *argv[])
{
  int valor = 0;
  int tipo;
  printf("\nDigite o tipo de conversão (1=C->F, 2=F->C)");
  scanf("%d", &tipo);
  if (tipo == 1)
  {
    printf("\nDigite a temperatura em Celsius:");
  } else
  {
    printf("\nDigite a temperatura em Fahrenheit:");
  }
  scanf("%d", &valor);
  valor = calcularTemperatura(tipo, valor);
  if (tipo == 2)
  {
    printf("\nA temperatura correspondente em Celsius eh de %2d\n", valor);
  } else
  {
    printf("\nA temperatura correspondente em Fahrenheit eh de %2d\n", valor);
  }
  system("PAUSE");	
  return 0;
}
