package br.org.eteg.curso.javaoo.capitulo07.polimorfismo.medicos;

public class ClinicaDeMedicos {

	public void chamarMedico(Medico m)
	{
		System.out.println("A sua especialidade: " + m.getClass().getSimpleName());
		m.atender();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Medico m = new Medico();
		OtorrinoLaringologista o = new OtorrinoLaringologista();
		Dentista d = new Dentista();
		Oftamologista oo = new Oftamologista();
		
		ClinicaDeMedicos clinica = new ClinicaDeMedicos();
		
		// paciente 1: clinico geral
		clinica.chamarMedico(m);
		
		// paciente 2: precisa do dentista
		clinica.chamarMedico(d);
		
		// paciente 3: precisa olhar os olhos
		clinica.chamarMedico(oo);
		
		// paciente 4: precisa olhar a garganta
		clinica.chamarMedico(o);
		
		// ================================================
		
		// regras de conversao e casting
		
		// ok, subindo a hierarquia
		Medico m2 = (Medico) oo;
		Medico m3 = oo;
		Medico m4 = o;
		
		// nao ok, descendo a hierarquia
		// erro em tempo de compilação
		Oftamologista oo2 = (Oftamologista) m2;
		//Oftamologista oo3 = (Oftamologista) o;
		
		// referencias e visibilidade
		oo.utilizarAparelhoEscaneamentoOcular();
		// operacao abaixo nao está visivel para o medico
		//m3.utilizarAparelhoEscaneamentoOcular();
		// OK
		Oftamologista oo4 = (Oftamologista) m3;
		oo4.utilizarAparelhoEscaneamentoOcular();
		// OK
		((Oftamologista) m3).utilizarAparelhoEscaneamentoOcular();
	}

}
