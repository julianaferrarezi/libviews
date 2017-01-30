//package representacao.services;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream.GetField;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.jar.JarEntry;
//import java.util.jar.JarFile;
//
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.input.SAXBuilder;
//
//import representacao.wrappers.BibliotecaWrapper;
//import representacao.wrappers.PacoteProjetoWrapper;
//import representacao.wrappers.PacoteWrapper;
//
//public class MonitorarProjeto {
//	public static void main(String args[]) {  
//        try {
//        	//Busca bibliotecas no pom.xml
//    		List<BibliotecaWrapper> bibliotecas = new ArrayList<BibliotecaWrapper>();
//    		
//    		//Lista todas as dependências do pom
//        	String pathPom = "/home/juliana/workspace/administrativo/pom.xml";
//        	String pathMaven = "/home/juliana/workspace/administrativo/repository/";
//        	
//        	
//        	Document doc = null;
//            SAXBuilder builder = new SAXBuilder();
//            
//            try {
//                  
//            	doc = builder.build(pathPom);
//                Element config = doc.getRootElement();
//                List<Element> lista = config.getChildren();
//                  
//                 for (Element e: lista ){
//                	  if (e.getName().equals("dependencies")) {
//                		  List<Element> listaDependencias = e.getChildren();
//                		  
//                		  for (Element dependencia : listaDependencias) {
//                			  
//                			  List<Element> listaAtributos = dependencia.getChildren();
//                			  
//                			  String groupId = "";
//                			  String artifactId = "";
//                			  String version = "";
//                			  String scope = "";
//                			  
//                			  for (Element atributo : listaAtributos) {
//                				  if (atributo.getName().equals("groupId")) {
//                					  groupId = atributo.getText();
//                				  }
//                				  if (atributo.getName().equals("artifactId")) {
//                					  artifactId = atributo.getText();
//                				  }
//                				  if (atributo.getName().equals("version")) {
//                					  version = atributo.getText();
//                				  }
//                				  if (atributo.getName().equals("scope")) {
//                					  scope = atributo.getText();
//                				  }
//                			  }
//                			  BibliotecaWrapper bibliotecaWrapper = new BibliotecaWrapper();
//                			  bibliotecaWrapper.setArtifactId(artifactId);
//                			  bibliotecaWrapper.setGroupId(groupId);
//                			  bibliotecaWrapper.setVersion(version);
//                			  bibliotecaWrapper.setPath(pathMaven + groupId.replace(".", "/") + "/" + artifactId + "/" + version.replace("-", ".") + "/" + artifactId + "-" + version.replace("-", ".") + ".jar");
//                			  //Faz listagem das classes do projeto
//                			  JarFile jarFile = new JarFile(bibliotecaWrapper.getPath());
//            	              Enumeration allEntries = jarFile.entries();
//            	              while (allEntries.hasMoreElements()) {
//            	                  JarEntry entry = (JarEntry) allEntries.nextElement();
//            	                  String name = entry.getName();
//            	                  if (name.toLowerCase().endsWith(".class") && !name.contains("$")) {
//            	                	  bibliotecaWrapper.getNomeClasses().add(name);
//            	                  }
//            	              }
//            	              
//            	              bibliotecas.add(bibliotecaWrapper);
//                		  }
//                	  }
//                  }
//            } catch (Exception e) {
//                  e.printStackTrace();
//            }
//        	
//            ClasseServiceProjeto classeService = new ClasseServiceProjeto();
//            
//            //Lista com as classes que serão exibidas
//            HashMap<String, List<String>>  listaClasses = new HashMap<String, List<String>>();
//
//            try {
//                  //Listar arquivos de um diretório
//                  String dirWorkspace = "/home/juliana/workspace/";
//                  String nomeProjeto = "administrativo";
//                  String pacoteBeans = "br.unesp.administrativo.beans";
//                		  
//                  listaClasses = classeService.listarArquivosDiretorio(dirWorkspace + nomeProjeto + "/", listaClasses);
//                  
//                  //Organiza hierarquicamente as classes
//                  //Pacote padrão
//                   
////                  String pacotePadrao = "com/sun/jersey/";
//                  
//                  Collections.sort(bibliotecas);
//                  
//                  List<PacoteProjetoWrapper> pacotes = new ArrayList<PacoteProjetoWrapper>();
//                  
//                  List<BibliotecaWrapper> bibliotecasUtilizadas = new ArrayList<BibliotecaWrapper>();
//                  
//                  for (String key : listaClasses.keySet()) {
//                	  PacoteProjetoWrapper pacoteProjetoWrapper = new PacoteProjetoWrapper();
//                	  pacoteProjetoWrapper.setNomePacote(key.replace(dirWorkspace + nomeProjeto + "/src/", "").replace(dirWorkspace + nomeProjeto + "/src-api/", "").replace("/", "."));
//                	  
//                	  List<String> caminhoClasses =  listaClasses.get(key);
//                	  
//                	  for (String caminhoClasse : caminhoClasses) {
////                		  System.out.println(key + "/" + caminhoClasse);
//                		  //Abrir arquivo e listar todas dependências dele
//                		  FileInputStream stream = new FileInputStream(key + "/" + caminhoClasse);
//                		  InputStreamReader reader = new InputStreamReader(stream);
//                		  BufferedReader br = new BufferedReader(reader);
//                		  String linha = null;
//                		  do{
//                			  linha = br.readLine();
//                			 
//                			  
//                			  if ((linha != null) && (linha.indexOf("import ") == 0) && (linha.indexOf(nomeProjeto) == -1)) {
//                				  
//                				  //Tira o nome da classe 
//                				  String nomeImport =  linha.replace("import ", "").replace(";", "");
//
//                				  boolean encontrou = false;
//                				  for (BibliotecaWrapper bibliotecaWrapper : bibliotecas) {
//                					  for (String nomeClasse : bibliotecaWrapper.getNomeClasses()) {
////                						  System.out.println("if (" + nomeClasse + ".contains("+ nomeImport + ")) {");
//                						  if (nomeClasse.replace("/", ".").contains(nomeImport)) {
//                							  bibliotecasUtilizadas.add(bibliotecaWrapper);
//                							  pacoteProjetoWrapper.getRelacionamentos().add(bibliotecaWrapper.getGroupId() + "." + bibliotecaWrapper.getArtifactId());
//                							  encontrou = true;
//                							  break;
//                						  }
//									  }
//								  }           
//                				  if (!encontrou) {
////                					  if (!nomeImport.contains("java.") && !nomeImport.contains("br.unesp."))
////                					  System.out.println("Não encontrou: " + nomeImport);
//                				  }
//                			  }
//                		  } while (linha != null);
//                	  }
//                	  
//                	  pacotes.add(pacoteProjetoWrapper);
//                  }
//                  
//                  Collections.sort(bibliotecas);
//                  Collections.sort(pacotes);
//                  
//                  String separadorPacote = "";
//                  for (PacoteProjetoWrapper pacote : pacotes) {
//                	  String item = separadorPacote + " { " + 
//                	  				"\"type\":\"episode\"," +
//				          	 		"\"name\":\""+ pacote.getNomePacote() +"\","+
//				          	 		"\"description\":\""+ pacote.getNomePacote() +"\","+
//				          	 		"\"episode\":1,"+
//				          	 		"\"date\":\"2012-05-05 23:50:11\","+
//				          	 		"\"slug\":\""+ pacote.getNomePacote() +"\"," +
//				          	 		"\"links\":[";
//                	  
//                	 String separadorLink = "";
//                	 for (String string : pacote.getRelacionamentos()) {
//                		 item += separadorLink + "\"" + string + "\"";
//                		 separadorLink = ",";
//					 }
//				     item += "]}";
//                	  
//				     separadorPacote = ",";
//				     
////                	  System.out.println(item);
//                  }              
//                  
//                  separadorPacote = "";
//                  int mediana = new BigDecimal(bibliotecas.size()).divideToIntegralValue(new BigDecimal(2)).intValue();
//                  for (int i = 0; i< mediana; i++) {
//                	  //Bibliotecas não utilizadas
//                	  boolean encontrouBiblioteca = false;
//                	  for (BibliotecaWrapper bibliotecaWrapper2 : bibliotecasUtilizadas) {
////						System.out.println(bibliotecaWrapper2.getPath());
//                	  }
//                	  
//                	  
//                	  String item = separadorPacote + " { " + 
//	          	  					"\"type\":\"theme\"," +
//				          	 		"\"name\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
//				          	 		"\"description\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
//				          	 		"\"slug\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\"";
//				     item += "}";
//	          	  
//				     separadorPacote = ",";
////               	  System.out.println(item);				     
//				     
//                  }
//                  
//                  separadorPacote = "";
//                  for (int i = mediana; i < bibliotecas.size(); i++) {
//                	  String item = separadorPacote + " { " + 
//	          	  					"\"type\":\"perspective\"," +
//				          	 		"\"name\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
//				          	 		"\"description\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\","+
//				          	 		"\"slug\":\""+ bibliotecas.get(i).getGroupId() + "." + bibliotecas.get(i).getArtifactId() +"\"";
//				     item += "}";
////               	  System.out.println(item);				     
//				    
//                  }
//            } catch (Exception e) {
//                  e.printStackTrace();
//            }
//
//    }  
//        catch (Throwable e) {  
//            System.err.println(e);  
//        }  
//    }
//	
//	
//}
