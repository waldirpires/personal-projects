package ericsson.adpoint.test;

import java.util.ArrayList;
import java.util.List;

public class IPResults {
	String ip;
	List<Port> ports;
	
	public IPResults(String ip)
	{
		this.ip = ip;
		ports = new ArrayList<Port>();
	}

}
