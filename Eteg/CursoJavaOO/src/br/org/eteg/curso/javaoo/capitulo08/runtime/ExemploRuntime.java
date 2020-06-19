package br.org.eteg.curso.javaoo.capitulo08.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExemploRuntime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
        if (args.length > 0){
            try {
                Process proc = runtime.exec(args);
                BufferedReader reader = new BufferedReader(
                		new InputStreamReader(proc.getInputStream()));
                String lineRead = null;
                while((lineRead = reader.readLine()) != null)
                 {
                    System.out.println(lineRead); }                
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Number of processors: " + runtime.availableProcessors());
        System.out.println("Free memory: " + runtime.freeMemory() + " Bytes");
        System.out.println("Maximum memory: " + runtime.maxMemory() + " Bytes");
        System.out.println("Total memory: " + runtime.maxMemory() + " Bytes");
	}

}
