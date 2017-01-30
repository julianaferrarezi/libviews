//package representacao.services;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import representacao.wrappers.BibliotecaWrapper;
//import representacao.wrappers.MetodoArquivo;
//
//public class DetalhaBiblioteca {
//	public static void main(String args[]) {  
//		ArquivoService arquivoService = new ArquivoService();
//		
//		List<BibliotecaWrapper> bibliotecas = new ArrayList<BibliotecaWrapper>();
//		File versao1 = new File("/home/juliana/Dropbox/Mestrado/primefaces-5.0.txt");
//		File versao2 = new File("/home/juliana/Dropbox/Mestrado/primefaces-5.1.txt");
//		File versao3 = new File("/home/juliana/Dropbox/Mestrado/primefaces-5.2.txt");
//		
//		try {
//			bibliotecas.add(arquivoService.lerArquivo(versao1));
//			bibliotecas.add(arquivoService.lerArquivo(versao2));
//			bibliotecas.add(arquivoService.lerArquivo(versao3));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		MetricaService metricaService = new MetricaService(); 
//		
//		//NDRM
//		List<Integer> listNDRM = new ArrayList<Integer>();
////		for (int i = 1; i < bibliotecas.size(); i++) {
////			
////			//Calcula métodos adicionados por classe
////			BibliotecaWrapper bib1 = bibliotecas.get(i - 1);
////			BibliotecaWrapper bib2 = bibliotecas.get(i);
////			
////			listNDRM.add(metricaService.calcularNDRM(bib1, bib2));
////		}
//		//Número de métodos adicionados
//		for (int i = 1; i < bibliotecas.size(); i++) {
//			Integer numAdicionado = 0;
//			
//			//Calcula métodos adicionados por classe
//			BibliotecaWrapper bib1 = bibliotecas.get(i - 1);
//			BibliotecaWrapper bib2 = bibliotecas.get(i);
//			
//			List<MetodoArquivo> metodosRemovidos = metricaService.listaMetodosRemovidos(bib1, bib2);
//			
//			System.out.println("Métodos removidos " + bib1.getVersion() +  bib2.getVersion());
//			for (MetodoArquivo metodoArquivo : metodosRemovidos) {
//				System.out.println(metodoArquivo.getNome());
//			}
//								
//			listNDRM.add(metricaService.analisarProjeto(metodosRemovidos, "/home/juliana/workspace/administrativo"));
//		}
//		
//		System.out.println("-------------");
//		System.out.println("NDRM");
//		for (Integer integer : listNDRM) {
//			System.out.println(integer);
//		}
//		
//		List<Integer> listNSM = new ArrayList<Integer>();
//		for (int i = 0; i < bibliotecas.size(); i++) {
//			listNSM.add(metricaService.calcularNSM(bibliotecas.get(i)));
//		}
//		
//		System.out.println("-------------");
//		System.out.println("NSM");
//		for (Integer integer : listNSM) {
//			System.out.println(integer);
//		}
//		
//		//NNM
//		List<Integer> listNNM = new ArrayList<Integer>();
//		for (int i = 1; i < bibliotecas.size(); i++) {
//			
//			//Calcula métodos adicionados por classe
//			BibliotecaWrapper bib1 = bibliotecas.get(i - 1);
//			BibliotecaWrapper bib2 = bibliotecas.get(i);
//			
//			listNNM.add(metricaService.calcularNNM(bib1, bib2));
//		}
//		
//		System.out.println("-------------");
//		System.out.println("NNM");
//		for (Integer integer : listNNM) {
//			System.out.println(integer);
//		}
//		
//		
////		arquivoService.geraArquivoNDRM(bibliotecas, "/home/juliana/workspace/administrativo/");
//		//Número de métodos por snapshot
////		arquivoService.geraArquivoNSM(bibliotecas);
////		arquivoService.geraArquivoNNM(bibliotecas);
//	}
//}
