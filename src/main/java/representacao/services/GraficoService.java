//package representacao.services;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class GraficoService {
//	public List<String> listarMetodos(String dir, String pacotePadrao) {
//		ClasseService classeService = new ClasseService();
//		Collection<String> listaClasses = classeService.listarArquivos(dir, new ArrayList<String>(), dir + pacotePadrao);
//		
//		for (String string : listaClasses) {
//			String pacoteClasse = string.replace(dir, "");
//			if (pacoteClasse.substring(0,1).equals("/")) {
//				pacoteClasse = pacoteClasse.substring(1);
//			}
//			pacoteClasse = pacoteClasse.replace("/", ".");
//			pacoteClasse = pacoteClasse.replace(".class", "");
//			
//			System.out.println(pacoteClasse);
//			//Classe
//        	try {
//				Class c = Class.forName(pacoteClasse);
//			} catch (ClassNotFoundException e) {
//				System.out.println("Classe não encontrada: " + pacoteClasse);
//			}
//        	
//		}
//		
//		
//		return null;
//	}
//	
//	public void calcularMetricas() {
//		//Valores iniciais que devem ser pesquisados
//		Integer M1 = 5;
//		Integer metodosRemovidos = 3;
//		Integer metodosNovos = 2;
//		
//		Integer M2 = M1 - metodosRemovidos + metodosNovos;
//		
//		//Cálculos
//		//Número de chamadas dos métodos removidos.
//		//Foreach entre métodos removidos para verificar quantas vezes estão sendo utilizados.
//		BigDecimal WRM = new BigDecimal(12);
//
//		//Variação de métodos
//		BigDecimal CEM = new BigDecimal(metodosRemovidos + metodosNovos);
//		
//		//Porcentagem de novos métodos
//		//Importante para saber qual a taxa de expansão da API
//		BigDecimal PNM = new BigDecimal(metodosNovos / (M1 + metodosNovos));
//	}
//}
