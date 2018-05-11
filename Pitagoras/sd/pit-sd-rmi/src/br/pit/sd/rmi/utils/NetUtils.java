package br.pit.sd.rmi.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

public class NetUtils {

	public static boolean isAvailable(String ip, int port) {
		LogUtils.info("--------------Testing port " + ip + ":" + port);
		Socket s = null;
		try {
			s = new Socket(ip, port);

			// If the code makes it this far without an exception it means
			// something is using the port and has responded.
			LogUtils.info("--------------Port " + ip + ":" + port
					+ " is not available");
			return false;
		} catch (IOException e) {
			LogUtils.info("--------------Port " + ip + ":" + port
					+ " is available");
			return true;
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					throw new RuntimeException("You should handle this error.",
							e);
				}
			}
		}
	}

	public static String getIpAddress() {
		InetAddress IP = null;
		try {
			IP = InetAddress.getLocalHost();
			System.out.println("IP of my system is := " + IP.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return IP.getHostAddress();
	}
	
	public static void listNetworkInterfaces(){
        try {
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets))
			    displayInterfaceInformation(netint);
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	System.out.printf("InetAddress: %s\n", inetAddress);
        }
        System.out.printf("\n");
     }	
}
