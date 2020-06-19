package br.org.eteg.curso.javaoo.capitulo03.figuras;


/**
 * Write a description of class Ponto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ponto
{
    private double x, y;
    
    double tamanho;
    
    /**
     * construtor para criar um ponto com coordenadas X e Y
     */
    public Ponto(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public String toString()
    {
        return "x = " + x + "| y = " + y;   
    }
}
