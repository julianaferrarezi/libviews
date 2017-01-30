package representacao.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import representacao.wrappers.PacoteWrapper;

public class ClasseServiceProjeto {
	public Collection<String> listarArquivos(String dir, Collection<String> listaClasses, String caminhoPacotePadrao) {
		File diretorio = new File(dir); 
        File fList[] = diretorio.listFiles(); 

        for ( int i = 0; i < fList.length; i++ ){ 
      	  if (fList[i].isFile() && fList[i].getName().toLowerCase().endsWith(".class") && !fList[i].getName().contains("$")) {
      		  listaClasses.add(fList[i].getAbsolutePath().replace(caminhoPacotePadrao, ""));
      	  } else if (fList[i].isDirectory()) {
      		listarArquivos(dir + "/" + fList[i].getName(), listaClasses, caminhoPacotePadrao);  
      	  }
        }
        
        return listaClasses;
	}
	
	public HashMap<String, List<String>> listarArquivosDiretorio(String dir, HashMap<String, List<String>> listaClasses) {
		File diretorio = new File(dir); 
        File fList[] = diretorio.listFiles(); 

        for ( int i = 0; i < fList.length; i++ ){ 
      	  if (fList[i].isFile() && fList[i].getName().toLowerCase().endsWith(".java")) {
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
	
//	public void imprimiPacotes(String pacotePadrao, List<PacoteWrapper> pacotesWrapper, String primeiroPacoteAnterior) {
//		//Imprimi pacotes
//        for (PacoteWrapper pacoteWrapper : pacotesWrapper) {
////        	System.out.println(pacoteWrapper.getPath());
//        	
//        	String ultimoPacoteAnterior = "";
//        	String pacoteAnterior = "";
//        	if (pacoteWrapper.getClasses() != null && !pacoteWrapper.getClasses().isEmpty()) {
////        		System.out.println((pacotePadrao + pacoteWrapper.getPath()).replace("/", "."));
//        		for (String string : pacoteWrapper.getClasses()) {
//        			
//        			String[] aNomePacote = pacoteWrapper.getPath().split("/");
//        			
//        			String subPacote = "";
//        			for (int i = 0; i < aNomePacote.length - 1; i++) {
//        				subPacote += aNomePacote[i] + "/";
//					}
//        			
//        			for (String p : aNomePacote) {
//        				if (pacoteAnterior.contains(subPacote)) {
//        					if (p.equals(aNomePacote[aNomePacote.length-1])) {
//        						if (ultimoPacoteAnterior.equals(p)) {
//        							System.out.println(",");
//        						} else if (!p.equals(primeiroPacoteAnterior)) {
//        							System.out.println("},\""+ p + "\": {");
//        						}
//        					}
//        					
//        				} else {
//        					System.out.println("\""+ p + "\": {");
//        				}
//        					
//					}
//        			pacoteAnterior = pacoteWrapper.getPath();
//        			ultimoPacoteAnterior = aNomePacote[aNomePacote.length - 1];
//        			primeiroPacoteAnterior = aNomePacote[0];
//        			
//        			System.out.println("\"" + string + "\": [0,0,15]");
////					System.out.println((pacotePadrao + pacoteWrapper.getPath()).replace("/", ".") + string);
//				}
//        		
//        	}
//        	
//        	if (pacoteWrapper.getPacotes().size() > 0) {
//        		imprimiPacotes(pacotePadrao, pacoteWrapper.getPacotes(), primeiroPacoteAnterior);
//        	}
//		}
//	}
	
	public PacoteWrapper buscaNosPrincipais(List<PacoteWrapper> pacotes, String pathPacoteAtual) {
		if (pacotes != null) {
			for (PacoteWrapper pacoteWrapper : pacotes) {
				if (pacoteWrapper.getPath().equals(pathPacoteAtual)) {
					return pacoteWrapper;
				} 
			}
		}
		return null;
	}
	
	public PacoteWrapper buscaNo(List<PacoteWrapper> pacotesWrapper, String pathPacoteAtual) {
		PacoteWrapper retorno = null;
        for (PacoteWrapper pacoteWrapper : pacotesWrapper) {
	       	if (pacoteWrapper.getPath().equals(pathPacoteAtual)) {
	       		retorno = pacoteWrapper;
	       	} else if (pacoteWrapper.getPacotes().size() > 0) {
	       		retorno = buscaNo(pacoteWrapper.getPacotes(), pathPacoteAtual);
	       	}
       	
	       	if(retorno != null){
	       		return retorno;
	       	}
		}
		    
		return null;
	}
	
	
	public PacoteWrapper buscarNoTeste(List<PacoteWrapper> pacotesWrapper, String pathPacoteAtual) {
		Boolean continuar = true;
		PacoteWrapper pacoteSelecionado = null;
		List<PacoteWrapper> listaFilhos = pacotesWrapper;
		
		pacoteSelecionado = buscaNosPrincipais(listaFilhos, pathPacoteAtual);
		
		if (pacoteSelecionado == null) {
			do {
				if (listaFilhos == null || listaFilhos.size() == 0) {
					continuar = false;
				} else {
					for (PacoteWrapper pacoteWrapper : listaFilhos) {
					
						if (pacoteWrapper.getPacotes() != null && pacoteWrapper.getPacotes().size() > 0) {
							pacoteSelecionado = buscaNosPrincipais(pacoteWrapper.getPacotes(), pathPacoteAtual);
							if (pacoteSelecionado != null) {
								continuar = false;
							} else {
								listaFilhos = pacoteWrapper.getPacotes();
							}
						} else {
							continuar = false;
						}
					}
				}
			} while (continuar);
		}
		return pacoteSelecionado;	
	}
	
	public boolean verificaNos(List<PacoteWrapper> pacotes, String nomePacoteAtual) {
		if (pacotes != null) {
			for (PacoteWrapper pacoteWrapper : pacotes) {
				if (nomePacoteAtual.equals(pacoteWrapper.getPath())) {
					return true;
				} else {
					return verificaNos(pacoteWrapper.getPacotes(), nomePacoteAtual);
				}
			}
		}
		return false;
	}
}
