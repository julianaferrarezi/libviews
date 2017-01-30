package representacao.wrappers;

import java.util.ArrayList;
import java.util.List;


public class PacoteWrapper {
	
	private String path;
	private List<PacoteWrapper> pacotes;
	private List<String> classes;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<PacoteWrapper> getPacotes() {
		if (pacotes == null) {
			pacotes = new ArrayList<PacoteWrapper>();
		}
		return pacotes;
	}

	public void setPacotes(List<PacoteWrapper> pacotes) {
		this.pacotes = pacotes;
	}

	public List<String> getClasses() {
		if (classes == null) {
			classes = new ArrayList<String>();
		}
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
}
