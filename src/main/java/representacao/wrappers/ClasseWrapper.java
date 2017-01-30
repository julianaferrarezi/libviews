package representacao.wrappers;

import java.util.List;

public class ClasseWrapper {
	
	private String nome;
	private List<ClasseWrapper> filhos;
	private String path;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<ClasseWrapper> getFilhos() {
		return filhos;
	}
	public void setFilhos(List<ClasseWrapper> filhos) {
		this.filhos = filhos;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
