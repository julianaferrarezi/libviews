//package representacao.services;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import representacao.wrappers.BibliotecaWrapper;
//import representacao.wrappers.ClasseArquivo;
//import representacao.wrappers.MetodoArquivo;
//
//public class MetricaService {
//	public Integer calcularNDRM(BibliotecaWrapper bib1, BibliotecaWrapper bib2) {
//		Integer ndrm = 0;
//		
//		System.out.println("Comparação BIB1 (" + bib1.getVersion() + ") e BIB2 (" + bib2.getVersion() + ")");
//		
//		for (ClasseArquivo classe1 : bib1.getClasses()) {
//			boolean encontrou = false;
//			for (ClasseArquivo classe2 : bib2.getClasses()) {
//				if (classe1.getNome().equals(classe2.getNome())) {
//					encontrou = true;
//					
//					for (MetodoArquivo metodo1 : classe1.getMetodos()) {
//						boolean encontrouMetodo = false;
//						for (MetodoArquivo metodo2 : classe2.getMetodos()) {
//							if (metodo1.getNome().equals(metodo2.getNome())) {
//								encontrouMetodo = true;
//							}
//						}
//						if (!encontrouMetodo) {
////							System.out.println("Método removido da classe " + classe1.getNome() + ": " + metodo1.getNome());
//							ndrm++;
//						}
//					}
//				}
//			}
//			
//			//Se não encontrou, foi removido
//			if (!encontrou) {
//				ndrm++;
////				System.out.println("Classe removida: " + classe1.getNome());
//			}
//		}
//		
//		return ndrm;
//	}
//	
//	public Integer calcularNSM(BibliotecaWrapper bib) {
//		Integer nsm = 0;
//		for (ClasseArquivo classe : bib.getClasses()) {
//			nsm += classe.getMetodos().size();
//		}
//		return nsm;
//	}
//	
//	public Integer calcularNNM(BibliotecaWrapper bib1, BibliotecaWrapper bib2) {
//		Integer nnm = 0;
//		
////		System.out.println("Comparação BIB1 (" + bib1.getVersion() + ") e BIB2 (" + bib2.getVersion() + ")");
//		
//		for (ClasseArquivo classe2 : bib2.getClasses()) {
//			boolean encontrou = false;
//			for (ClasseArquivo classe1 : bib1.getClasses()) {
//				if (classe1.getNome().equals(classe2.getNome())) {
//					encontrou = true;
//					
//					for (MetodoArquivo metodo2 : classe2.getMetodos()) {
//						boolean encontrouMetodo = false;
//						for (MetodoArquivo metodo1 : classe1.getMetodos()) {
//							if (metodo1.getNome().equals(metodo2.getNome())) {
//								encontrouMetodo = true;
//							}
//						}
//						if (!encontrouMetodo) {
////							System.out.println("Método adicionado na classe " + classe2.getNome() + ": " + metodo2.getNome());
//							nnm++;
//						}
//					}
//				}
//			}
//			
//			//Se não encontrou, foi removido
//			if (!encontrou) {
//				nnm++;
////				System.out.println("Classe removida: " + classe2.getNome());
//			}
//		}
//		
//		return nnm;
//	}
//	
//	public List<MetodoArquivo> listaMetodosRemovidos(BibliotecaWrapper biblioteca, BibliotecaWrapper biblioteca1) {
//		List<MetodoArquivo> metodosRemovidos = new ArrayList<MetodoArquivo>();
//		
//		for (ClasseArquivo classe : biblioteca.getClasses()) {
//			//Verifica se encontrou classe
//			ClasseArquivo classeEncontrada = null;
//			for (ClasseArquivo classe1 : biblioteca1.getClasses()) {
//				if (classe.getNome().equals(classe1.getNome())) {
//					classeEncontrada = classe1;
//				}
//			}
//			if (classeEncontrada != null) {
//				for (MetodoArquivo metodo : classe.getMetodos()) {
//					MetodoArquivo metodoEncontrado = null;
//					for (MetodoArquivo metodo1 : classeEncontrada.getMetodos()) {
//						if (metodo.getNome().equals(metodo1.getNome())) {
//							metodoEncontrado = metodo1;
//						}
//					}
//					if (metodoEncontrado == null) {
//						metodo.setClasse(classeEncontrada.getNome());
//						metodosRemovidos.add(metodo);
//					}
//				}
//			}
//		}
//		
//		return metodosRemovidos;
//	}
//	
//	public Integer analisarProjeto(List<MetodoArquivo> metodosRemovidos, String diretorio) {
//		Collection<String> listaArquivos = listarArquivos(diretorio, new ArrayList<String>());
//		
//		Integer referencias = 0;
//		for (MetodoArquivo metodo : metodosRemovidos) {
////			System.out.println("Classe: " + metodo.getClasse());
//			
//			String[] aClasse = (metodo.getClasse()).split("\\.");
//			String pacote =  "";
//			for (int i=0; i < (aClasse.length - 1); i++) {
//				pacote += aClasse[i];
//				if (i < (aClasse.length - 2)) {
//					pacote += ".";
//				}
//			}
//			String classe = aClasse[aClasse.length - 1];
//			
////					System.out.println("Classe: " + metodo.getClasse());
////					System.out.println("Pacote: " + pacote);
////					System.out.println("Nome classe: " + classe);
//			
//			
//			for (String string : listaArquivos) {
//				boolean encontrouPacote = false;
//				boolean encontrouClasse = false;
//				boolean encontrouMetodo = false;
//				
//				try (BufferedReader br = new BufferedReader(new FileReader(string))) {
//				    String line;
//				    while ((line = br.readLine()) != null) {
////						    	System.out.println(line);
//				    	
//				    	if (line.contains(pacote)){
//				    		encontrouPacote = true;
//				    	}
//				    	if (line.contains(classe)) {
//				    		encontrouClasse = true;
//				    	}
//				    	if (line.contains(metodo.getNome())) {
//				    		encontrouMetodo = true;
//				    	}
//				    }
//				    if (encontrouPacote && encontrouClasse && encontrouMetodo) {
//				    	referencias++;
//				    }
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		return referencias;
//	}
//	
//	public Collection<String> listarArquivos(String dir, Collection<String> listaArquivos) {
//		File diretorio = new File(dir); 
//        File fList[] = diretorio.listFiles(); 
//
//        for ( int i = 0; i < fList.length; i++ ){ 
//      	  if ((fList[i].isFile() && fList[i].getName().toLowerCase().endsWith(".java") || (fList[i].isFile() && fList[i].getName().toLowerCase().endsWith(".jsp"))) && !fList[i].getName().contains("$")) {
//      		  listaArquivos.add(fList[i].getAbsolutePath());
//      	  } else if (fList[i].isDirectory()) {
//      		listarArquivos(dir + "/" + fList[i].getName(), listaArquivos);  
//      	  }
//        }
//        
//        return listaArquivos;
//	}
//
//	
//	
//}
