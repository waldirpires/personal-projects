package br.org.eteg.curso.javaoo.capitulo07.innerclass;

public class DeFora {

	private int i;
	
	public class DeDentro
	{
		
	}
	
	public static class DeDentroEstatica
	{
		public void imprimir()
		{
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		
		DeFora deFora = new DeFora();
		DeFora.DeDentro deDentro = deFora.new DeDentro();
		
		// erro: eu preciso ter primeiro uma instancia de DeFora para criar uma DeDentro
		DeFora.DeDentro deDentro2 = new DeFora.DeDentro();
		
		//Ok: como o DeDentro é estatico, posso criar um obj do tipo DeDentro sem precisar
		// criar um obj do tipo DeFora
		DeFora.DeDentroEstatica deDentroEstatica = 
			new DeFora.DeDentroEstatica();
		
	}
}
