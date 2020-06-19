package br.org.eteg.curso.javaoo.capitulo02.enums;

public enum DiaSemana {

	DOMINGO {
		public String getTarefa()
		{
			return "descansar";
		}
	}
	,
	SEGUNDA {
		public String getTarefa()
		{
			return "trabalhar";
		}
	}
	, 
	TERCA {
		public String getTarefa()
		{
			return "trabalhar";
		}
	}
	,
	QUARTA {
		public String getTarefa()
		{
			return "ir ao cimena";
		}
	}
	,
	QUINTA {
		public String getTarefa()
		{
			return "sair a noite";
		}
		
	}
	,
	SEXTA {
		public String getTarefa()
		{
			return "sair a noite de novo";
		}
		
	}
	,
	SABADO {
		public String getTarefa()
		{
			return "ir ao mineirao";
		}
	};
	
	DiaSemana()
	{
	}
	
	public String getTarefa()
	{
		return "nao sei o que fazer";
	}	
}
