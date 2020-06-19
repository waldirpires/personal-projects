package br.pit.sd.ws.helloworld;

import javax.xml.ws.Endpoint;

//Endpoint publisher
public class HelloWorldPublisher{

	public static void main(String[] args) {
       String serviceName = "hello";
       String serviceLocation = "http://localhost:9999";
       System.out.println("Publisher: publishing service " + serviceName + " at " + serviceLocation);
	   Endpoint.publish(serviceLocation + "/ws/" + serviceName, new HelloWorldImpl());
       System.out.println("Publisher: DONE");
    }
}
