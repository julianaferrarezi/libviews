package representacao.wrappers;

import java.util.ArrayList;
import java.util.List;

public class PacoteProjetoWrapper implements Comparable<PacoteProjetoWrapper> {
	
	public String nomePacote;
	
	public List<String> relacionamentos;
	
	@Override
	public int compareTo(PacoteProjetoWrapper o) {
		return this.getNomePacote().compareTo(o.getNomePacote());
	}

	public String getNomePacote() {
		return nomePacote;
	}

	public void setNomePacote(String nomePacote) {
		this.nomePacote = nomePacote;
	}

	public List<String> getRelacionamentos() {
		if (relacionamentos == null) relacionamentos = new ArrayList<String>();
		return relacionamentos;
	}

	public void setRelacionamentos(List<String> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}
}
