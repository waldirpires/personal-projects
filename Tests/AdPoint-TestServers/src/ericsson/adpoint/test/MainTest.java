package ericsson.adpoint.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {

		String ips[] = {"10.31.2.109" };
				
//				"10.116.27.46", "10.116.27.47", "10.116.27.48",
//				"10.116.27.49", "10.116.27.56", "10.116.27.57", "10.116.27.58",
//				"10.116.27.59" };

		int ports[] = { 80, 1521, 1522, 1523, 8080, 8181, 8282, 443, 22, 21, 23, 24, 25 };

		boolean isOpen = false;
		for (int i = 0; i < ips.length; i++) {
			String ip = ips[i];
			for (int j = 0; j < ports.length; j++) {
				int port = ports[j];
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
	}

}
