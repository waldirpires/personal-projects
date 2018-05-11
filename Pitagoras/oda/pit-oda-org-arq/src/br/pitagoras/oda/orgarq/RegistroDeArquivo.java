package br.pitagoras.oda.orgarq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroDeArquivo {

	private List<String> campos;
	
	public RegistroDeArquivo(String registro) {
		campos = new ArrayList<String>();
		String[] regs = registro.split(";");
		
		for (int i = 0; i < regs.length; i++){
			campos.add(regs[i].trim());
		}
	}
	
	public List<String> getCampos() {
		return Collections.unmodifiableList(campos);
	}
	
	@Override
	public String toString() {
		return campos.toString();
	}
	
	public String obterCampos(){
		StringBuffer buffer = new StringBuffer();
		
		for (String k: campos)
		{
			buffer.append(k).append(";");
		}
		return buffer.toString().substring(0, buffer.length()-1);
		
	}
	
	public String getId(){
		return campos.get(0);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return toString().equals(obj.toString());
	}
	
	public void atualizar(String valorAtual, String novoValor){
		int index = campos.indexOf(valorAtual);
		if (index <=0){
			System.out.println("ERRO: valor atual não encontrado ou valor atual corresponde a uma chave");
		} else {
			campos.set(index, novoValor);
			System.out.println("Registro atualizado com sucesso");
		}
	}
}
