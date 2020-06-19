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

void imprimirHora(struct hora hora)
{
  printf("\nA hora eh: %2d:%2d:%2d\n", 
    hora.hora, hora.minuto, hora.segundo);     
}     

int lerCampoValidado(int min, int max, char * mensagem)
{
  int validado = -1;
  int valor;
  while (validado == -1)
  {
    printf(mensagem);
    scanf("%d", &valor);
    if (valor > max || valor < min)
    {
      printf("\nO valor está fora do intervalo. Tente novamente.");
    } else
    {
      validado = 0;
    }
  }
  return valor;
}

struct hora obterHora()
{
  struct hora hora;
  hora.hora = lerCampoValidado(MIN_HORA_MIN_SEG, MAX_HORA, "\nDigite as horas (24h): ");
  hora.minuto = lerCampoValidado(MIN_HORA_MIN_SEG, MAX_MINUTO,"\nDigite os minutos (60m): ");
  hora.segundo = lerCampoValidado(MIN_HORA_MIN_SEG, MAX_SEGUNDO, "nDigite os segundos (60s): ");
  imprimirHora(hora);
  
  return hora;  
 }

struct hora obterDiferenca(struct hora hora1, struct hora hora2)
{
  struct hora hora3;
  hora3.hora = 0;
  hora3.minuto = 0;
  hora3.segundo = 0;
  int segundos1 = hora1.segundo + hora1.minuto*60 + hora1.hora*60*60;
  int segundos2 = hora2.segundo + hora2.minuto*60 + hora2.hora*60*60;
  int diferenca = segundos1 - segundos2;
  if (diferenca < 0)
    diferenca *=-1;
  hora3.hora = diferenca / (60*60);
  diferenca = diferenca % (60*60);
  hora3.minuto = diferenca / 60;
  diferenca = diferenca % 60;
  hora3.segundo = diferenca;
  return hora3;
}

int main(int argc, char *argv[])
{
  struct hora hora1, hora2, hora3;
  int validado = -1;
  
  hora1 = obterHora();
  hora2 = obterHora();
  hora3 = obterDiferenca(hora1, hora2);
  imprimirHora(hora3);
  system("PAUSE");	
  return 0;
}
