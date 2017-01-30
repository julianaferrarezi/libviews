package representacao.wrappers;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaWrapper implements Comparable<BibliotecaWrapper> {

	private String groupId;
	private String artifactId;
	private String version;
	
	private String path;
	
	private List<ClasseArquivo> classes;
	
	private List<String> nomeClasses;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getPackage() { 
		return groupId + "." + artifactId;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<ClasseArquivo> getClasses() {
		if (classes == null) {
			classes = new ArrayList<ClasseArquivo>();
		}
		return classes;
	}
	public void setClasses(List<ClasseArquivo> classes) {
		this.classes = classes;
	}
	@Override
	public int compareTo(BibliotecaWrapper o) {
		return this.getPackage().compareTo(o.getPackage());
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<String> getNomeClasses() {
		if (nomeClasses == null) {
			nomeClasses = new ArrayList<String>();
		}
		return nomeClasses;
	}
	public void setNomeClasses(List<String> nomeClasses) {
		this.nomeClasses = nomeClasses;
	}
}
