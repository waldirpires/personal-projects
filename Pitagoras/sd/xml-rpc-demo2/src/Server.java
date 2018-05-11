import java.io.IOException;

import org.apache.xmlrpc.WebServer;


public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int port = 8080;
		WebServer webserver = new WebServer (port);
		System.out.println("Server started on port " + port);
	    webserver.addHandler ("calc", new Calculator());	
	    webserver.setParanoid(false);
	    System.out.println("done");
	}

}
