//package representacao.services;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//
//import representacao.wrappers.PacoteWrapper;
//
//public class MontaArquivoTeste {
//	public static void main(String args[]) {  
//		//Listar arquivos de um diretório
//        String dir = "/home/juliana/.m2/repository/com/sun/jersey/jersey-servlet/1.18.2/";
//        String pacotePadrao = "com/sun/jersey/";
//        
//        ClasseService classeService = new ClasseService();
//        
//        Collection<String> listaClasses = classeService.listarArquivos(dir, new ArrayList<String>(), dir + pacotePadrao);
//        
//        List<PacoteWrapper> pacotesWrapper = new ArrayList<PacoteWrapper>();
//        HashMap<String, PacoteWrapper> mapEncontrados = new HashMap<String, PacoteWrapper>();
//        
//        for (String string : listaClasses) {
////        	System.out.println(string);
//        	
//        	String[] pacotes = string.split("/");
//        	
//        	String path = "";
//        	String separador = "/";
//        	PacoteWrapper pacotePai = null;
//        	PacoteWrapper pacotePrincipal = null;
//        	
//        	for (int i = 0; i < pacotes.length; i++) {
//        		
//        		//Adiciona classes
//        		// -1 porque não pega o nome da classe
//        		if (i == (pacotes.length - 1)) {
//        			pacotePai.getClasses().add(pacotes[i]);
//        		} else  {
//	        		path += pacotes[i] + separador;
//	        		
//	        		//Verifica se o pacote já existe na estrutura
////	        		PacoteWrapper pacoteWrapper = classeService.buscarNoTeste(pacotesWrapper, path);
//	        		PacoteWrapper pacoteWrapper = mapEncontrados.get(path);
//	        		
//	        		if (pacoteWrapper == null) {
//	        			pacoteWrapper = new PacoteWrapper();
//	    				pacoteWrapper.setPath(path);
//	    				
//	    				if (pacotePai != null) {
//							pacotePai.getPacotes().add(pacoteWrapper); 
//						}
//	        		}
//					
//					pacotePai = pacoteWrapper;
//					
//					if (i == 0) {
//						pacotePrincipal = pacoteWrapper;
////	        			PacoteWrapper pacoteNoWrapper = classeService.buscarNoTeste(pacotesWrapper, pacotePrincipal.getPath());
//						PacoteWrapper pacoteNoWrapper = mapEncontrados.get(pacotePrincipal.getPath());
//	            		if (pacoteNoWrapper == null) {
//	            			pacotesWrapper.add(pacotePrincipal);
//	            		}
//					}
//					
//					if (mapEncontrados.get(pacoteWrapper.getPath()) == null) {
//						mapEncontrados.put(path, pacoteWrapper);
//					}
//        		}
//			}
//		}
//        
////        classeService.imprimiPacotes(pacotesWrapper);
//        
//        //Testes
////        PacoteWrapper pacoteWrapper = classeService.buscaNoTeste(pacotesWrapper, "spi/");
//	}
//}
