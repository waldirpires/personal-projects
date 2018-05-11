package br.pit.sd.rpc.servidor;

import org.apache.xmlrpc.WebServer;

public class Servidor {

	public static void main(String[] args) {
		int port = 8080;
		WebServer webserver = new WebServer (port);
		System.out.println("Server started on port " + port);
	    webserver.addHandler ("calc", new Calculator());	
	    webserver.setParanoid(false);
	    System.out.println("done");
	}
}
