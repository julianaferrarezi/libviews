//package representacao.services;
//
//import java.util.List;
//
//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.input.SAXBuilder;
//
//public class DependenciasProjeto {
//	public static void main(String args[]) {  
//        try {
//        	//Lista todas as dependências do pom
//        	String pathPom = "/home/juliana/workspace/administrativo/pom.xml";
//        	
//        	Document doc = null;
//            SAXBuilder builder = new SAXBuilder();
//            
//            try {
//                  
//            	doc = builder.build(pathPom);
//            	System.out.println("Leu arquivo: " + pathPom);
//	            Element config = doc.getRootElement();
//	            List<Element> lista = config.getChildren();
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
//                			  
//                			  System.out.println(groupId + "." + artifactId);
////                			  System.out.println("groupId: " + groupId);
////                			  System.out.println("artifactId: " + artifactId);
////                			  System.out.println("version: " + version);
////                			  System.out.println("scope: " + scope);
//                			  
//                		
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
