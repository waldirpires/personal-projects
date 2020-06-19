package br.org.eteg.curso.javaoo.capitulo08.comparable;

public class Pessoa implements Comparable<Pessoa>{
	
	private String nome;
	private String cpf;
	private int idade;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString()
	{
		return nome + "|" + cpf + "|" + idade;
	}
	
	public int compareTo(Pessoa o) {

		return toString().compareTo(o.toString());
	}
	
	
}
