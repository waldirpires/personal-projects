package br.org.eteg.curso.javaoo.capitulo07.clone;

public class SerHumano implements Cloneable{

	private String nome;
	private String nacionalidade;
	private String idade;
	private String CPF;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		SerHumano sh = new SerHumano();
		sh.setNome(getNome());
		sh.setNacionalidade(getNacionalidade());
		sh.setIdade(getIdade());
		sh.setCPF(getCPF());
		return sh;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		CPF = cpf;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = PRIME * result + ((idade == null) ? 0 : idade.hashCode());
		result = PRIME * result + ((nacionalidade == null) ? 0 : nacionalidade.hashCode());
		result = PRIME * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SerHumano other = (SerHumano) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (idade == null) {
			if (other.idade != null)
				return false;
		} else if (!idade.equals(other.idade))
			return false;
		if (nacionalidade == null) {
			if (other.nacionalidade != null)
				return false;
		} else if (!nacionalidade.equals(other.nacionalidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
}
