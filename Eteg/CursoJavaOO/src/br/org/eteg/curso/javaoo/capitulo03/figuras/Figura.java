package br.org.eteg.curso.javaoo.capitulo03.figuras;


/**
 * Write a description of class Figura here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Figura
{
    protected double perimetro;
    public static int quantidade;
    protected Ponto [] pontos;
    
    /**
     * Costrutor da classe figura
     */
    public Figura()
    {
        quantidade ++;
    }
    
    public Figura(double perimetro)
    {
        // chamando o construtor padrão
        this();
        this.perimetro = perimetro;
    }
    
    public abstract void calcularPerimetro();
    
    public double getPerimetro()
    {
        return perimetro;
    }
    
    public Ponto [] getPontos()
    {
        return pontos;   
    }
}
