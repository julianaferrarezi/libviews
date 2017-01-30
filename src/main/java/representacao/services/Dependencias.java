//package representacao.services;
//
//import java.util.HashMap;
//import java.util.List;
//
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.input.SAXBuilder;
//
//public class Dependencias {
//	public static void main(String args[]) {  
//        try {
//        	//Lista todas as dependências do pom
//        	String pathPom = "/home/juliana/workspace/central/pom.xml";
//        	String rootJar = "/home/juliana/.m2/repository/";
//        	
//        	Document doc = null;
//            SAXBuilder builder = new SAXBuilder();
//            
//            ClasseService classeService = new ClasseService();
//            
//            //Lista com as classes que serão exibidas
//            HashMap<String, List<String>>  listaClasses = new HashMap<String, List<String>>();
//
//            try {
//                  
//            	doc = builder.build(pathPom);
//            	System.out.println("Leu arquivo: " + pathPom);
//	            Element config = doc.getRootElement();
//	            List<Element> lista = config.getChildren();
//                  
//
//                  
//                 for (Element e: lista ){
//                	  if (e.getName().equals("dependencies")) {
//                		  List<Element> listaDependencias = e.getChildren();
//                		  
//                		  for (Element dependencia : listaDependencias) {
//                			  
//                			  System.out.println("-----------");
//                			  System.out.println("Dependência");
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
//                			  System.out.println("groupId: " + groupId);
//                			  System.out.println("artifactId: " + artifactId);
//                			  System.out.println("version: " + version);
//                			  System.out.println("scope: " + scope);
//                			  
//                			  String caminhoJar = rootJar + groupId.replace(".", "/") + "/" + artifactId + "/" + version + "/";
//                			  String nomeJar = artifactId + "-" + version + ".jar";
//                			  Runtime run = Runtime.getRuntime();
//                			  String command;
//                			  
//                			  command = "jar xvf " + caminhoJar + nomeJar;
//                			  run.exec(command);
//
//                			  System.out.println("Caminho: " + caminhoJar);
//
//                			  
//                			  String pacote = groupId + "." + artifactId;
//                			  System.out.println("Pacote: " + pacote);
//                			  
//                		  }
//                		  break;
//                	  }
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
//	
//}
