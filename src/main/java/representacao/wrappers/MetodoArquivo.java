package representacao.wrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MetodoArquivo implements Comparable<MetodoArquivo> {
	private String nome;
	
	private HashMap<Integer, String> tiposParametro;
	
	private String tipoRetorno;
	
	private String modificador;
	
	private String classe;
	
	private List<Integer> listReferenciasProjeto = new ArrayList<Integer>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public HashMap<Integer, String> getTiposParametro() {
		return tiposParametro;
	}

	public void setTiposParametro(HashMap<Integer, String> tiposParametro) {
		this.tiposParametro = tiposParametro;
	}

	public String getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public String getModificador() {
		return modificador;
	}

	public void setModificador(String modificador) {
		this.modificador = modificador;
	}
	
	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	@Override
	public int compareTo(MetodoArquivo o) {
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
