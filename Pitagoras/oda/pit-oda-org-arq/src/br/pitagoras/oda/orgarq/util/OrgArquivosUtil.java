package br.pitagoras.oda.orgarq.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OrgArquivosUtil {

    public static List<String> obterRegistrosDoArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        List<String> registros = new ArrayList<String>();
        String s = br.readLine();
        while (s != null) {
            registros.add(s);
            s = br.readLine();
        }
        br.close();
        return registros;
    }

    public static Map<String, String> obterRegistrosIndexadosDoArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        Map<String, String> registros = new HashMap<String, String>();
        String s = br.readLine();
        String indice = null;
        while (s != null) {
        	indice = s.split(";")[0];
            registros.put(indice, s);
            s = br.readLine();
        }
        br.close();
        return registros;
    }

    /**
     * permite buscar um registro especifico de um arquivo de dados
     * @param chave a chave de busca
     * @param nomeArquivo o nome do arquivo
     * @return uma String contento os campos do registro
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String buscarRegistroDeArquivo(String chave, String nomeArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String s = br.readLine();
        while (s != null) {
        	if (s.split(";")[0].equals(chave)){
        		br.close();
        		return s;
        	}
            s = br.readLine();
        }
        br.close();
        return null;
    }

    public static List<String> pesquisarRegistrosDeArquivo(String valor, String nomeArquivo) throws IOException {
    	List<String> registros = new ArrayList<String>();
    	BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String s = br.readLine();
        while (s != null) {
        	if (s.contains(valor)){
        		registros.add(s);
        	}
            s = br.readLine();
        }
        br.close();
        return registros;
    }

    public static void atualizarRegistroEmArquivo(String chave, String dados, String nomeArquivo) throws IOException{
    	String registroOriginal = buscarRegistroDeArquivo(chave, nomeArquivo);
    	if (StringUtil.isEmpty(registroOriginal)){
    		System.out.println("AVISO: Registro não existe");
    	} else {
    		List<String> dadosArquivo = obterRegistrosDoArquivo(nomeArquivo);
    		int pos = dadosArquivo.indexOf(registroOriginal);
    		dadosArquivo.set(pos, dados);
    		escreverRegistrosEmArquivo(nomeArquivo, dadosArquivo);
    	}
    }
    
    public static void atualizarRegistroEmArquivo(String chave, String indice, String dados, String nomeArquivo) throws IOException{
    	String registroOriginal = buscarRegistroDeArquivo(chave, nomeArquivo);
    	if (StringUtil.isEmpty(registroOriginal)){
    		System.out.println("AVISO: Registro não existe");
    	} else {
    		List<String> dadosArquivo = obterRegistrosDoArquivo(nomeArquivo);
    		dadosArquivo.set(Integer.parseInt(indice), dados);
    		escreverRegistrosEmArquivo(nomeArquivo, dadosArquivo);
    	}
    }
    
    public static void escreverMapaRegistrosEmArquivo(String nomeArquivo, Map<String, String> mapaRegistros) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
        String s = null;
        
        Map<String, String> mapaRegistrosOrdenado = new TreeMap<String, String>(mapaRegistros);
        for (String k : mapaRegistrosOrdenado.keySet()) {
        	s = k + ";" + mapaRegistrosOrdenado.get(k);
            bw.write(s);
            bw.newLine();
        }
        bw.close();    	
    }
    
    public static void escreverMapaRegistrosDiretosEmArquivo(String nomeArquivo, Map<String, String> mapaRegistros) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
        String s = null;
        
        Map<String, String> mapaRegistrosOrdenado = new TreeMap<String, String>(mapaRegistros);
        for (String k : mapaRegistrosOrdenado.keySet()) {
        	s = mapaRegistrosOrdenado.get(k);
            bw.write(s);
            bw.newLine();
        }
        bw.close();    	
    }
    
    public static Map<String, String> obterMapaRegistrosDoArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        
        Map<String, String> mapaIndices = new TreeMap<String, String>();
        String s = br.readLine();
        while (s != null) {
        	String[] campos = s.split(";");
            mapaIndices.put(campos[0], campos[1]);
            s = br.readLine();
        }
        br.close();
        return mapaIndices;
    }

    public static Map<String, String> obterMapaRegistrosDiretosDoArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        
        Map<String, String> mapaDados = new TreeMap<String, String>();
        String s = br.readLine();
        while (s != null) {
        	String[] campos = s.split(";");
        	mapaDados.put(campos[0], s);
            s = br.readLine();
        }
        br.close();
        return mapaDados;
    }

    public static void escreverRegistrosEmArquivo(String nomeArquivo, List<String> registros) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
        for (String v : registros) {
            bw.write(v);
            bw.newLine();
        }
        bw.close();
        System.out.println(registros.size() + " registros escritos com sucesso.");
    }
	
}
