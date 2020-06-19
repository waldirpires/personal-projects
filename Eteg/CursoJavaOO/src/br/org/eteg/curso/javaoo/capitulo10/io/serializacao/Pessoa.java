package br.org.eteg.curso.javaoo.capitulo10.io.serializacao;

import java.util.*;
import java.io.*;

public class Pessoa implements Serializable
{
    String nome;
    int idade;
    String endereco;
    Date aniver;

    public Pessoa(String nome, int age, String address, Date date){
        this.nome = nome;
        this.idade = age;
        this.endereco = address;
        this.aniver = date;
    }
    
    public String toString(){
        return nome + " | " + idade + " | " + endereco + " | " + 
            aniver.toString();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(nome);
        s.writeObject(new Integer(idade));
        s.writeObject(endereco);
        s.writeObject(aniver);
    }    

    private void readObject(ObjectInputStream s) throws IOException, 
                                            ClassNotFoundException  {
        s.defaultReadObject();
        this.nome = (String) s.readObject();
        this.idade = ((Integer) s.readObject()).intValue();
        this.endereco = (String) s.readObject();
        this.aniver = (Date) s.readObject();
    }        
}
