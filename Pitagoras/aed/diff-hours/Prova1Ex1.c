#include <stdio.h>
#include <stdlib.h>

struct hora{
       int hora;
       int minuto;
       int segundo;
       };

int main(int argc, char *argv[])
{
  struct hora hora;
  printf("\nDigite as horas (24h): ");
  scanf("%d", &hora.hora);
  printf("\nDigite os minutos (60m): ");
  scanf("%d", &hora.minuto);
  printf("\nDigite os segundos (60s): ");
  scanf("%d", &hora.segundo);
  printf("\nA hora digitada foi: %2d:%2d:%2d\n", 
    hora.hora, hora.minuto, hora.segundo);
  
  system("PAUSE");	
  return 0;
}
