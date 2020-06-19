package br.pit.sd.ws.helloworld;

import javax.jws.WebService;

//Service Implementation
@WebService(endpointInterface = "br.pit.sd.ws.helloworld.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello World JAX-WS " + name;
	}
}