#include <stdio.h>
#include <stdlib.h>
#define MAX_HORA 23
#define MAX_MINUTO 59
#define MAX_SEGUNDO 59
#define MIN_HORA_MIN_SEG 0

struct hora{
       int hora;
       int minuto;
       int segundo;
       };

int main(int argc, char *argv[])
{
  struct hora hora;
  int validado = -1;
  while (validado == -1)
  {
    printf("\nDigite as horas (24h): ");
    scanf("%d", &hora.hora);
    if (hora.hora > MAX_HORA || hora.hora < MIN_HORA_MIN_SEG)
    {
      printf("\nO valor está fora do intervalo. Tente novamente.");
    } else
    {
      validado = 0;
    }
  }
  validado = -1;
  while (validado == -1)
  {
    printf("\nDigite os minutos (60m): ");
    scanf("%d", &hora.minuto);
    if (hora.minuto > MAX_MINUTO || hora.minuto < MIN_HORA_MIN_SEG)
    {
      printf("\nO valor está fora do intervalo. Tente novamente.");
    } else
    {
      validado = 0;
    }
  }
  validado = -1;
  while (validado == -1)
  {
    printf("\nDigite os segundos (60s): ");
    scanf("%d", &hora.segundo);
    if (hora.segundo > MAX_SEGUNDO || hora.segundo < MIN_HORA_MIN_SEG)
    {
      printf("\nO valor está fora do intervalo. Tente novamente.");
    } else
    {
      validado = 0;
    }
  }
  printf("\nA hora digitada foi: %2d:%2d:%2d\n", 
    hora.hora, hora.minuto, hora.segundo);
  
  system("PAUSE");	
  return 0;
}
