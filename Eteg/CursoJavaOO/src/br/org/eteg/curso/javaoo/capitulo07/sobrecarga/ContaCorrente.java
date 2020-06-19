package br.org.eteg.curso.javaoo.capitulo07.sobrecarga;

public class ContaCorrente {

	private double saldo;
	
	public void depositar(double d)
	{
		saldo +=d;
	}
	
	public void depositar(Double d)
	{
		saldo += d;
	}
	
	public void depositar(int i)
	{
		saldo += i;
	}
	
	public void depositar(Integer i)
	{
		saldo += i;
	}
	
	public double getSaldo()
	{
		return saldo;
	}
	
	public static void main(String[] args) {
		ContaCorrente cc = new ContaCorrente();
		
		cc.depositar(1.1);
		cc.depositar(new Double(2.0));
		cc.depositar(33);
		cc.depositar(new Integer(22));
		
		System.out.println(cc.getSaldo());
	}
}
