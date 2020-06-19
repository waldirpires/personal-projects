package br.pit.sd.rpc.service;

public class PingService {

	public String responder(String msg){
		System.out.println("Recebeu: " + msg);
		return "pong";
	}
	
}

