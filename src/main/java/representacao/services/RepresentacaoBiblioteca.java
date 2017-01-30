//package representacao.services;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import representacao.wrappers.PacoteWrapper;
//
//public class RepresentacaoBiblioteca {
//	public static void main(String args[]) {  
//        try {
//            ClasseService classeService = new ClasseService();
//            
//            //Lista com as classes que serão exibidas
//            HashMap<String, List<String>>  listaClasses = new HashMap<String, List<String>>();
//
//            try {
//                  //Listar arquivos de um diretório
//                  String dir = "/home/juliana/.m2/repository/com/sun/jersey/jersey-servlet/1.18.2/";
//                  listaClasses = classeService.listarArquivosDiretorio(dir, listaClasses);
//                  
//                  //Organiza hierarquicamente as classes
//                  //Pacote padrão
//                   
//                  String pacotePadrao = "com/sun/jersey/";
//                  
////                  for (String key : listaClasses.keySet()) {
////                	  System.out.println(key);
////                  }
//                  
//                  //Para cada classe e pacote, crio nós
//                  List<PacoteWrapper> hierarquia = new ArrayList<PacoteWrapper>();
//                  for (String key : listaClasses.keySet()) {
//                	  String pathPacote  = key.replace(dir + pacotePadrao, "");
//                	  String[] arrayPacote = pathPacote.split("/");
//                	  
//                	  PacoteWrapper filho = null;
//                	  
//                	  String nomePacoteAtual = pathPacote;
//                	  for (int i = arrayPacote.length - 1; i >= 0; i--) {
//                		  
//                		  //Verifica se não existe para não inserir novamente
//                		  boolean existe = false;
//                		  for (PacoteWrapper pacoteWrapper : hierarquia) {
//								PacoteWrapper pacoteTeste = pacoteWrapper;
//								do {
//									if (nomePacoteAtual.equals(pacoteTeste.getPath())) {
//										existe = true;
//									} else {
//										existe = classeService.verificaNos(pacoteTeste.getPacotes(), nomePacoteAtual);
//									}
//								} while (pacoteTeste != null && !existe);
//						  }
//                		  
//                		  PacoteWrapper pacote = null;
//                		  if (existe) {
//                			  System.out.println("lindo");
//                		  } else {
//                			  pacote = new PacoteWrapper();
//                    		  pacote.setPath(nomePacoteAtual);
//                    		  if (filho == null) {
//                    			  pacote.setPacotes(null);
//                    		  } else {
//                    			  List<PacoteWrapper> pacotes = new ArrayList<PacoteWrapper>();
//                    			  pacotes.add(filho);
//                    			  
//                    			  pacote.setPacotes(pacotes);
//                    		  }
//                		  }
//                		  
//                		  filho = pacote;
//                		  nomePacoteAtual = nomePacoteAtual.replace("/" + arrayPacote[i], "");
//                	  }
//                	  
//                	  hierarquia.add(filho);
//                	  
//                  }
//                  
//                  System.out.println("aqui");
//
//                  
////                  //Cria um Objeto JSON 
////    			  JSONObject jsonObject = new JSONObject(); 
////    			  FileWriter writeFile = null; //Armazena dados em um Objeto JSON
////    			  
////    			  //Nó principal
////    			  jsonObject.put("name", "Dependências");
////    			  JSONArray array = new JSONArray();
////    			  
////    			  
////    			  for (PacoteWrapper pacote : hierarquia) {
////    				  if (pacote != null) {
////	    				  // Children - Cada pacote da raiz
////		    			  JSONObject jsonObjectChildren = new JSONObject();
////		    			  jsonObjectChildren.put("name", pacote.getPath());
////		    			  
////		    			  array.add(jsonObjectChildren);
////    				  }
////				  }
////    			  
//////    			  for (String key : listaClasses.keySet()) {
//////	    			  //Children - Cada pacote
//////	    			  JSONObject jsonObjectChildren = new JSONObject();
//////	    			  jsonObjectChildren.put("name", key);
//////	    			  
//////	    			  JSONArray arrayClasses = new JSONArray(); 
//////                	  List<String> listNomeClasse = listaClasses.get(key);
//////                	  for (String string : listNomeClasse) {
//////                		  JSONObject jsonObjectChildrenClasse = new JSONObject();
//////                		  jsonObjectChildrenClasse.put("name", string);
//////                		  jsonObjectChildrenClasse.put("size", 300);
//////    	    			  
//////    	    			  arrayClasses.add(jsonObjectChildrenClasse);
//////                	  }
//////	    			  jsonObjectChildren.put("children", arrayClasses);
//////	    			  
//////	    			  array.add(jsonObjectChildren);
//////    			  }
////        		  //Nó principal
////    			  jsonObject.put("children", array);
////                      			  
////    			  try{
////    				writeFile = new FileWriter("/home/juliana/Dropbox/Mestrado/projeto/representacao/src/main/webapp/saida.json"); //Escreve no arquivo conteudo do Objeto JSON 
////    			  	writeFile.write(jsonObject.toJSONString()); 
////    			  	writeFile.close(); 
////    			  } 
////    			  catch(IOException e){ 
////    				  e.printStackTrace(); 
////    			  }
//                  //Imprime na Tela o Objeto JSON para vizualização 
////    			  System.out.println(jsonObject);
//
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
//}
