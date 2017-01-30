package representacao.wrappers;

import java.util.ArrayList;
import java.util.List;

public class ClasseArquivo implements Comparable<ClasseArquivo> {
	private String nome;
	
	private List<MetodoArquivo> metodos;
	
	private List<Integer> listReferenciasProjeto = new ArrayList<Integer>();

	public List<MetodoArquivo> getMetodos() {
		if (metodos == null) {
			metodos = new ArrayList<MetodoArquivo>();
		}
		return metodos;
	}

	public void setMetodos(List<MetodoArquivo> metodos) {
		this.metodos = metodos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(ClasseArquivo o) {
		return this.getNome().compareTo(o.getNome());
	}

	public List<Integer> getListReferenciasProjeto() {
		if (listReferenciasProjeto == null) {
			listReferenciasProjeto = new ArrayList<Integer>();
			listReferenciasProjeto.add(0);
		}
		return listReferenciasProjeto;
	}

	public void setListReferenciasProjeto(List<Integer> listReferenciasProjeto) {
		this.listReferenciasProjeto = listReferenciasProjeto;
	}
}
