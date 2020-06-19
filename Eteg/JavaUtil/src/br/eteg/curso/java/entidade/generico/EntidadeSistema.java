package br.eteg.curso.java.entidade.generico;

public abstract class EntidadeSistema {

	private static int numeroSequencial = 0;
	private Integer id;

	public EntidadeSistema()
	{
		atribuirNumeroSequencial();
	}
	
	private void atribuirNumeroSequencial()
	{
		id = numeroSequencial;
		numeroSequencial++;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
}
