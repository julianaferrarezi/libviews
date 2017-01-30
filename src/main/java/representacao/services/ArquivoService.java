//package representacao.services;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
//import representacao.wrappers.BibliotecaWrapper;
//import representacao.wrappers.ClasseArquivo;
//import representacao.wrappers.MetodoArquivo;
//
//public class ArquivoService {
//	public BibliotecaWrapper lerArquivo(File file) throws IOException {
//		BibliotecaWrapper biblioteca = null;
//		ClasseArquivo classe = null;
//		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//		    String line;
//		    while ((line = br.readLine()) != null) {
//		       if (line.contains("groupId")) {
//		    	   biblioteca = new BibliotecaWrapper();
//		    	   biblioteca.setGroupId(line.replace("groupId: ", ""));
//		    	   
//		    	   biblioteca.setArtifactId(br.readLine().replace("artifactId: ", ""));
//		    	   biblioteca.setVersion(br.readLine().replace("version: ", ""));
//		       } else if (!line.trim().isEmpty()) {
//		    	   if (line.contains("Classe")) {
//		    		   classe = new ClasseArquivo();
//		    		   classe.setNome(line.replace("Classe: ", ""));
//		    		   
//		    		   biblioteca.getClasses().add(classe);
//		    	   } else if (line.contains("Método")) {
//		    		   MetodoArquivo metodo = new MetodoArquivo();
//		    		   metodo.setNome(line.replace("Método: ", ""));
//		    		   
//		    		   metodo.setModificador(br.readLine().replace("Modificador: ", ""));
//		    		   
//		    		   //Lista parâmetros
//		    		   String line2;
//		    		   HashMap<Integer, String> parametros = new HashMap<Integer, String>(); 
//		    		   while ((line2 = br.readLine()).contains("Parâmetro")) {
//		    			   String numero = line2.substring(line2.indexOf(":") - 1, line2.indexOf(":"));
//		    			   parametros.put(Integer.parseInt(numero), line2.substring(line2.indexOf(":") + 2));
//		    		   }
//		    		   
//		    		   if (parametros.isEmpty()) {
//		    			   metodo.setTipoRetorno(line2.replace("Tipo de retorno: ", ""));
//		    		   } else {
//		    			   metodo.setTiposParametro(parametros);
//		    			   metodo.setTipoRetorno(br.readLine().replace("Tipo de retorno: ", ""));
//		    		   }
//		    		   classe.getMetodos().add(metodo);
//		    	   }
//		       }
//		    }
//		}
//		
//		Collections.sort(biblioteca.getClasses());
//		
//		return biblioteca;
//	}
//	
//	public Integer calculaNumeroMetodos(BibliotecaWrapper biblioteca) {
//		Integer nMetodos = 0;
//		for (ClasseArquivo classe : biblioteca.getClasses()) {
//			System.out.println("Classe: " + classe.getNome() + ": " + classe.getMetodos().size());
//			nMetodos += classe.getMetodos().size();
//		}
//		return nMetodos;
//	}
//	
//	public BibliotecaWrapper geraVersaoBase(List<BibliotecaWrapper> bibliotecas) {
//		BibliotecaWrapper base = new BibliotecaWrapper();
//		base.setClasses(null);
//		for (BibliotecaWrapper bibliotecaWrapper : bibliotecas) {
//			for (ClasseArquivo classe : bibliotecaWrapper.getClasses()) {
//				boolean encontrou = false;
//				for (ClasseArquivo c : base.getClasses()) {
//					if (c.getNome().equals(classe.getNome())) {
//						encontrou = true;
//					}
//				}
//				if (!encontrou) {
//					base.getClasses().add(classe);
//				}
//			}
//		}
//		Collections.sort(base.getClasses());
//		return base;
//	}
//	
//	public void geraArquivoNNM(List<BibliotecaWrapper> bibliotecas) {
//		BibliotecaWrapper versaoAtual = geraVersaoBase(bibliotecas);
//		
//		String home = "/home/juliana/";
//    	
//    	FileWriter arq;
//		try {
//			arq = new FileWriter(home + "nnm.txt");
//			PrintWriter printArq = new PrintWriter(arq);
//			
//			
//			
//			for (ClasseArquivo classeBase : versaoAtual.getClasses()) {
//				
//				//Calculo de todas as versões
//				List<Integer> listNnm = new ArrayList<Integer>();
//				listNnm.add(0);
//				
//				//Número de métodos adicionados
//				for (int i = 1; i < bibliotecas.size(); i++) {
//					Integer numAdicionado = 0;
//					
//					//Calcula métodos adicionados por classe
//					BibliotecaWrapper bib1 = bibliotecas.get(i - 1);
//					BibliotecaWrapper bib2 = bibliotecas.get(i);
//					
//					ClasseArquivo c2 = null;
//					for (ClasseArquivo c : bib2.getClasses()) {
//						if (c.getNome().equals(classeBase.getNome())) {
//							c2 = c;
//						}
//					}
//					
//					ClasseArquivo c1 = null;
//					for (ClasseArquivo c : bib1.getClasses()) {
//						if (c.getNome().equals(classeBase.getNome())) {
//							c1 = c;
//						}
//					}
//					
//					//Numero de classes que estão em c2 e não estão em c1
//					if (c2 != null) {
//						if (c1 == null) {
//							numAdicionado = c2.getMetodos().size();
//						} else {
//							for (MetodoArquivo metodo2 : c2.getMetodos()) {
//								boolean encontrado = false;
//								for (MetodoArquivo metodo1 : c1.getMetodos()) {
//									if (metodo1.getNome().equals(metodo2.getNome())) {
//										encontrado = true;
//									}
//								}
//								if (!encontrado) {
//									numAdicionado++;
//								}
//							}
//						}
//					}
//					listNnm.add(numAdicionado);
//				}
//				
//				String numNnm = "";
//				for (Integer integer : listNnm) {
//					numNnm += integer + ",";
//				}
//				numNnm = numNnm.substring(0, numNnm.length() - 1);
//				
//				printArq.printf(classeBase.getNome() + ": [" + numNnm + "],");
////				"Propose": [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 30, 50, 50, 50],
//				printArq.printf("\n");
//			}
//			
//			printArq.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void geraArquivoNDRM(List<BibliotecaWrapper> bibliotecas, String dirProjeto) {
//
//		BibliotecaWrapper versaoAtual = geraVersaoBase(bibliotecas);
//		
//		String home = "/home/juliana/";
//    	
//    	FileWriter arq;
//		try {
//			arq = new FileWriter(home + "ndrm.txt");
//			PrintWriter printArq = new PrintWriter(arq);
//	
//			for (ClasseArquivo classeBase : versaoAtual.getClasses()) {
//				//Calculo de todas as versões
//				List<Integer> listNdrm = new ArrayList<Integer>();
//				listNdrm.add(0);
//				
//				//Número de métodos adicionados
//				for (int i = 1; i < bibliotecas.size(); i++) {
//					Integer numAdicionado = 0;
//					
//					//Calcula métodos adicionados por classe
//					BibliotecaWrapper bib1 = bibliotecas.get(i - 1);
//					BibliotecaWrapper bib2 = bibliotecas.get(i);
//					
//					List<MetodoArquivo> metodosRemovidos = listaMetodosRemovidos(bib1, bib2);
//					
//					System.out.println("Métodos removidos " + bib1.getVersion() +  bib2.getVersion());
//					for (MetodoArquivo metodoArquivo : metodosRemovidos) {
//						System.out.println(metodoArquivo.getNome());
//					}
//										
//					listNdrm.add(analisarProjeto(metodosRemovidos, dirProjeto));
//				}
//				
//				String numNdrm = "";
//				
//				for (Integer integer : listNdrm) {
//					numNdrm += integer + ",";
//				}
//				numNdrm = numNdrm.substring(0, numNdrm.length() - 1);
//				
////				for (MetodoArquivo metodo : classeBase.getMetodos()) {
////					System.out.println(metodo.getNome());
////				} 
//				printArq.printf(classeBase.getNome() + ": [" + numNdrm + "],");
////				"Propose": [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 30, 50, 50, 50],
//				printArq.printf("\n");
//			}
//			printArq.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
//	
//	public void geraArquivoNSM(List<BibliotecaWrapper> bibliotecas) {
//		BibliotecaWrapper versaoAtual = geraVersaoBase(bibliotecas);
//		
//		String home = "/home/juliana/";
//    	
//    	FileWriter arq;
//		try {
//			arq = new FileWriter(home + "nsm.txt");
//			PrintWriter printArq = new PrintWriter(arq);
//			
//			for (ClasseArquivo classe : versaoAtual.getClasses()) {
//				
//				//Calculo de todas as versões
//				List<Integer> listNsm = new ArrayList<Integer>();
//				
//				for (BibliotecaWrapper biblioteca: bibliotecas) {
//					boolean encontrou = false;
//					for (ClasseArquivo c : biblioteca.getClasses()) {
//						if (c.getNome().equals(classe.getNome())) {
//							listNsm.add(c.getMetodos().size());
//							encontrou = true;
//						}
//					}
//					if (!encontrou) {
//						listNsm.add(0);
//					}
//				}
//				
//				String numNsm = "";
//				for (Integer integer : listNsm) {
//					numNsm += integer + ",";
//				}
//				numNsm = numNsm.substring(0, numNsm.length() - 1);
//				
//				
//				printArq.printf(classe.getNome() + ": [" + numNsm + "],");
////				"Propose": [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 30, 50, 50, 50],
//				printArq.printf("\n");
//			}
//
//			printArq.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
//	public BigDecimal calculaWRM(BibliotecaWrapper biblioteca, BibliotecaWrapper biblioteca1) {
//		//Verifica quais métodos foram excluídos.
//		int metodosRemovidos = 0;
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
//						metodosRemovidos++;
//					}
//				}
//			}
//		}
//		
//		return new BigDecimal(metodosRemovidos);
//	}
//	
//	public Integer calculaPNM(BibliotecaWrapper biblioteca, BibliotecaWrapper biblioteca1) {
//		//Verifica os métodos que foram adicionados em biblioteca1
//		int metodosAdicionados = 0;
//		
//		for (ClasseArquivo classe1 : biblioteca1.getClasses()) {
//			//Verifica se encontrou classe
//			ClasseArquivo classeEncontrada = null;
//			for (ClasseArquivo classe : biblioteca.getClasses()) {
//				if (classe.getNome().equals(classe1.getNome())) {
//					classeEncontrada = classe;
//				}
//			}
//			if (classeEncontrada != null) {
//				//Verifica os métodos
//				for (MetodoArquivo metodo1 : classe1.getMetodos()) {
//					MetodoArquivo metodoEncontrado = null;
//					for (MetodoArquivo metodo : classeEncontrada.getMetodos()) {
//						if (metodo.getNome().equals(metodo1.getNome())) {
//							metodoEncontrado = metodo;
//						}
//					}
//					if (metodoEncontrado == null) {
//						metodosAdicionados++;
//					}
//				}
//			}
//		}
//		
//		return metodosAdicionados;
//	}
//	
//	public Integer analisarProjeto(MetodoArquivo metodo, String diretorio) {
//		Collection<String> listaArquivos = listarArquivos(diretorio, new ArrayList<String>());
//		
//		Integer referencias = 0;
//		
//		System.out.println("Classe: " + metodo.getClasse());
//		
//		String[] aClasse = (metodo.getClasse()).split("\\.");
//		String pacote =  "";
//		for (int i=0; i < (aClasse.length - 1); i++) {
//			pacote += aClasse[i];
//			if (i < (aClasse.length - 2)) {
//				pacote += ".";
//			}
//		}
//		String classe = aClasse[aClasse.length - 1];
//		
////				System.out.println("Classe: " + metodo.getClasse());
////				System.out.println("Pacote: " + pacote);
////				System.out.println("Nome classe: " + classe);
//		
//		for (String string : listaArquivos) {
//			try (BufferedReader br = new BufferedReader(new FileReader(string))) {
//			    String line;
//			    while ((line = br.readLine()) != null) {
////					    	System.out.println(line);
//			    	
//			    	if (line.contains(pacote) && line.contains(classe) && line.contains(metodo.getNome())) {
//			    		referencias++;
//			    	}
//			    }
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
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
//}
