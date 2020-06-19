package br.org.eteg.curso.javaoo.capitulo02;

public class TesteTiposPrimitivos {
	
	static long d3;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int a = 0;
		double c = a;
		int b = ~1;
		
		a = (int)c;
		short m = 33;
		int s = ~m;
		int jj = Integer.MAX_VALUE;
		imprimirBinary(jj + 4);
		imprimirBinary(jj << 2);
		imprimirBinary(jj);
		imprimirBinary(jj++);
		imprimirBinary(jj++);
		int gg = -10;
		imprimirBinary(gg);
		int ff = gg >> 1;
		imprimirBinary(ff);
		
		imprimirBinary2(5);
		imprimirBinary2(1);
		imprimirBinary2(2);
		imprimirBinary2(Integer.MAX_VALUE);
		imprimirBinary2(-2);
		
		int x = 6;
		if (!(x > 3)) {
			
		}
		//byte bb = 128L;
		float fr = 628.26F;
		
		for (;;){
			
		}
			
		
	}
	
	public static void imprimirBinary(int n)
	{
		String s = Integer.toBinaryString(n);
		System.out.print(n + ": ");
		for (int i = 0; i < s.length(); i++)
		{
			System.out.print(s.charAt(i));
			if (i % 4 == 0 && i != 0)
			{
				System.out.print(" ");
			}
		}
		System.out.println();
	}

	public static void imprimirBinary2(int n)
	{
		int resto = n % 2;
		System.out.print(resto);
		while (resto != 0) {
			n = n / 2;
			resto = n % 2;
			System.out.print(resto);
		}
		System.out.println(n = n / 2);
	}
	
}
