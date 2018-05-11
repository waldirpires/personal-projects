
List ips = [ "10.116.47.34"];

List ports = [ 80, 8080, 8181, 8282, 8443, 443, 22, 21, 20 ];

boolean isOpen = false;
for (int i = 0; i < ips.size; i++) {
String ip = ips.get(i);
for (int j = 0; j < ports.size; j++) {
	int port = ports.get(j);
	Socket socket = null;
	try {
		socket = new Socket();
		/*****This is my tomcat5.5 which running on port 1935*************/
		/***I can view it with url--> http://101.220.25.76:1935/**********/
		socket.connect(new InetSocketAddress(ip, port), 1000);
		isOpen = socket.isConnected();
		socket.close();
	} catch (Exception e) {
		//e.printStackTrace();
		isOpen = false;
	} finally{
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	System.out.println("IP: " + ip + " Port: " + port + " - isOpen: " + isOpen);
}
System.out.println("---------------------");

}
