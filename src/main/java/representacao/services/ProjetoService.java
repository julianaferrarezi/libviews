package representacao.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import representacao.wrappers.BibliotecaWrapper;
import representacao.wrappers.PacoteProjetoWrapper;
import representacao.wrappers.PacoteWrapper;

public class ProjetoService {
	
	private List<BibliotecaWrapper> bibliotecasUtilizadas = new ArrayList<BibliotecaWrapper>();

	private List<BibliotecaWrapper> buscaBibliotecas(String projectDirectory) throws JDOMException, IOException {
		//Busca bibliotecas no pom.xml
		List<BibliotecaWrapper> bibliotecas = new ArrayList<BibliotecaWrapper>();
		
		//Lista todas as dependências do pom
    	String pathPom = projectDirectory + "/pom.xml";
		
    	Document doc = null;
        SAXBuilder builder = new SAXBuilder();
        
        doc = builder.build(pathPom);
        Element config = doc.getRootElement();
        List<Element> lista = config.getChildren();
      
	    for (Element e: lista ){
	    	  if (e.getName().equals("dependencies")) {
	    		  List<Element> listaDependencias = e.getChildren();
	    		  
	    		  for (Element dependencia : listaDependencias) {
	    			  
	    			  List<Element> listaAtributos = dependencia.getChildren();
	    			  
	    			  String groupId = "";
	    			  String artifactId = "";
	    			  String version = "";
	    			  String scope = "";
	    			  
	    			  for (Element atributo : listaAtributos) {
	    				  if (atributo.getName().equals("groupId")) {
	    					  groupId = atributo.getText();
	    				  }
	    				  if (atributo.getName().equals("artifactId")) {
	    					  artifactId = atributo.getText();
	    				  }
	    				  if (atributo.getName().equals("version")) {
	    					  version = atributo.getText();
	    				  }
	    				  if (atributo.getName().equals("scope")) {
	    					  scope = atributo.getText();
	    				  }
	    			  }
	    			  BibliotecaWrapper bibliotecaWrapper = new BibliotecaWrapper();
	    			  bibliotecaWrapper.setArtifactId(artifactId);
	    			  bibliotecaWrapper.setGroupId(groupId);
	    			  bibliotecaWrapper.setVersion(version);
	    			  bibliotecaWrapper.setPath(groupId.replace(".", "/") + "/" + artifactId + "/" + version.replace("-", ".") + "/" + artifactId + "-" + version.replace("-", ".") + ".jar");
		              
	    			  //Carrega classes da biblioteca
	    		      //Faz listagem das classes do projeto
        			  JarFile jarFile = new JarFile(bibliotecaWrapper.getPath());
    	              Enumeration allEntries = jarFile.entries();
    	              while (allEntries.hasMoreElements()) {
    	                  JarEntry entry = (JarEntry) allEntries.nextElement();
    	                  String name = entry.getName();
    	                  if (name.toLowerCase().endsWith(".class") && !name.contains("$")) {
    	                	  bibliotecaWrapper.getNomeClasses().add(name);
    	                  }
    	              }
	    			  
		              bibliotecas.add(bibliotecaWrapper);
	    		  }
	    	  }
	    }
	    Collections.sort(bibliotecas);
        return bibliotecas;
	}
	
	private HashMap<String, List<String>> listarArquivosDiretorio(String dir, HashMap<String, List<String>> listaClasses) {
		File diretorio = new File(dir); 
        File fList[] = diretorio.listFiles(); 

        for ( int i = 0; i < fList.length; i++ ){ 
      	  if (fList[i].isFile() && fList[i].getName().toLowerCase().endsWith(".class") && !fList[i].getName().contains("$")) {
      		  String key = fList[i].getAbsolutePath().replace("/" + fList[i].getName(), "");
      		  
      		  //Verifica se já tem classes desse pacote
      		  List<String> inseridos = listaClasses.get(key);
      		  
      		  if (inseridos == null) {
      			  inseridos = new ArrayList<String>();
      		  }
      		  
      		  inseridos.add(fList[i].getName());
      		  
      		  listaClasses.put(key, inseridos);
      		  
      	  } else if (fList[i].isDirectory()) {
      		listarArquivosDiretorio(dir + "/" + fList[i].getName(), listaClasses);  
      	  }
      	  
        }  
        
        return listaClasses;
	}
	
	  
	
	private List<PacoteProjetoWrapper> buscaPacotes(List<BibliotecaWrapper> bibliotecas, String dirProject, List<PacoteWrapper> pacotesWrapper) throws IOException {
		
		ClasseServiceProjeto classeService = new ClasseServiceProjeto();
	    
	    //Lista com as classes que serão exibidas
		HashMap<String, List<String>>  listaClasses = classeService.listarArquivosDiretorio(dirProject + "/", new HashMap<String, List<String>>());

		List<PacoteProjetoWrapper> pacotes = new ArrayList<PacoteProjetoWrapper>();
      
		  for (String key : listaClasses.keySet()) {
   			//Retirar caminho do projeto
			  String nome = key.replace(dirProject, "");
			  if (nome.substring(0, 1).equals("/")) {
				  nome = nome.substring(1);
			  } 
			  PacoteProjetoWrapper pacoteProjetoWrapper = new PacoteProjetoWrapper();
			  pacoteProjetoWrapper.setNomePacote(nome.replace("src/", "").replace("src-api/", "").replace("/", "."));
		  
			  List<String> caminhoClasses =  listaClasses.get(key);
			  
			  for (String caminhoClasse : caminhoClasses) {
				  //Abrir arquivo e listar todas dependências dele
				  FileInputStream stream = new FileInputStream(key + "/" + caminhoClasse);
				  InputStreamReader reader = new InputStreamReader(stream);
				  BufferedReader br = new BufferedReader(reader);
				  String linha = null;
				  do{
					  linha = br.readLine();
					 
					  
	//				  if ((linha != null) && (linha.indexOf("import ") == 0) && (linha.indexOf(nomeProjeto) == -1)) {
					  if ((linha != null) && (linha.indexOf("import ") == 0)) {
						  
						  //Tira o nome da classe 
						  String nomeImport =  linha.replace("import ", "").replace(";", "");
			
						  boolean encontrou = false;
						  for (BibliotecaWrapper bibliotecaWrapper : bibliotecas) {
							  for (String nomeClasse : bibliotecaWrapper.getNomeClasses()) {
			//    						  System.out.println("if (" + nomeClasse + ".contains("+ nomeImport + ")) {");
								  if (nomeClasse.replace("/", ".").contains(nomeImport)) {
									  bibliotecasUtilizadas.add(bibliotecaWrapper);
									  pacoteProjetoWrapper.getRelacionamentos().add(bibliotecaWrapper.getGroupId() + "." + bibliotecaWrapper.getArtifactId());
									  encontrou = true;
									  break;
								  }
							  }
						  }           
						  if (!encontrou) {
			//    					  if (!nomeImport.contains("java.") && !nomeImport.contains("br.unesp."))
			//    					  System.out.println("Não encontrou: " + nomeImport);
							  }
						  }
					  } while (linha != null);
				  }
				  
			  pacotes.add(pacoteProjetoWrapper);
		  }
	    
		Collections.sort(pacotes);
	    return pacotes;
	}
	
	public void geraArquivo(String dirProject) throws JDOMException, IOException {
		
		String[] a = dirProject.split("/");
		String nomeProjeto = a[a.length - 1]; 
		
		//Cria arquivo metadata.js com os dados que o 3Djs vai consumir para gerar a representação
		FileWriter arq = new FileWriter("/home/juliana/java8/workspace/representacao/src/main/webapp/" + nomeProjeto + ".js");
		PrintWriter gravarArq = new PrintWriter(arq);

		//Dados das bibliotecas e dos pacotes
		List<BibliotecaWrapper> bibliotecas =  buscaBibliotecas(dirProject);
		List<PacoteProjetoWrapper> pacotes = buscaPacotes(bibliotecas, dirProject, new ArrayList<PacoteWrapper>());       

		//Coloca dados no arquivo
		String separadorPacote = "";
		gravarArq.println("{");
		gravarArq.println("\"episodes\":");
		gravarArq.println("[");
		for (PacoteProjetoWrapper pacote : pacotes) {
		 	  String item = separadorPacote + " { " + 
				"\"type\":\"episode\"," +
		  	 		"\"name\":\""+ pacote.getNomePacote() +"\","+
		  	 		"\"description\":\""+ pacote.getNomePacote() +"\","+
		  	 		"\"episode\":1,"+
		  	 		"\"date\":\"2012-05-05 23:50:11\","+
		  	 		"\"slug\":\""+ pacote.getNomePacote() +"\"," +
		  	 		"\"links\":[";
		  
			 String separadorLink = "";
			 for (String string : pacote.getRelacionamentos()) {
				 item += separadorLink + "\"" + string + "\"";
			 separadorLink = ",";
			 }
			 item += "]}";
			  
			 separadorPacote = ",";
			 
			 gravarArq.println(item);
		}              
		gravarArq.println("],");
		   
		separadorPacote = "";
		gravarArq.println("\"themes\":");
		gravarArq.println("[");
		int mediana = new BigDecimal(bibliotecas.size()).divideToIntegralValue(new BigDecimal(2)).intValue();
		for (int i = 0; i< mediana; i++) {
//		 	  Bibliotecas não utilizadas
			  boolean encontrouBiblioteca = false;
			  for (BibliotecaWrapper bibliotecaWrapper2 : bibliotecasUtilizadas) {
					System.out.println(bibliotecaWrapper2.getPath());
			  }
			  
			  
			  String item = separadorPacote + " { " + 
					"\"type\":\"theme\"," +
			  	 		"\"name\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
			  	 		"\"description\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
			  	 		"\"slug\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\"";
			  item += "}";
			  
			  separadorPacote = ",";
				
			  gravarArq.println(item);				     
		 }
	   gravarArq.println("],");	
		   
	   separadorPacote = "";
	   gravarArq.println("\"perspectives\":");
		gravarArq.println("[");
	   for (int i = mediana; i < bibliotecas.size(); i++) {
	 	  String item = separadorPacote + " { " + 
			"\"type\":\"perspective\"," +
	  	 		"\"name\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
	  	 		"\"description\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
	  	 		"\"slug\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\"";
	 	  item += "}";
	 	  
	 	 separadorPacote = ",";
	 	  
	 	 gravarArq.println(item);				     
	   }
	   gravarArq.println("]");
	   gravarArq.println("}");

		//Grava arquivo e fecha
		arq.close();

	}
}
