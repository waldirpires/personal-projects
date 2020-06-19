package br.org.eteg.curso.javaoo.capitulo03.figuras;


/**
 * Write a description of class Circulo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Circulo extends Figura
{
    private double raio;    

    public Circulo()
    {
    }
    
    public Circulo(Ponto p, double raio)
    {
        
        pontos = new Ponto[1];
        pontos[0] = p;
        this.raio = raio;
    }
    
    public void calcularPerimetro()
    {
        perimetro = 3.14 * raio * 2;
    }
    
    public double getRaio()
    {
        return raio;
    }
        
        
}
