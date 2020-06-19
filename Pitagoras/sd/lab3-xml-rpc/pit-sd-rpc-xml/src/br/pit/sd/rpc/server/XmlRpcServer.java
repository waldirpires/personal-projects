package br.pit.sd.rpc.server;

import java.io.IOException;

import org.apache.xmlrpc.WebServer;

import br.pit.sd.rpc.service.PingService;

public class XmlRpcServer {

	public static void main(String[] args) throws IOException {
		int port = 8080;
		WebServer server = new WebServer(port);
		System.out.println("Iniciando servidor na porta " + port);
		server.addHandler("ping", new PingService());
		System.out.println("Adicionando serviço " + "ping");
		server.setParanoid(false);		
	}	
}
