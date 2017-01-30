package representacao.view;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.jdom.JDOMException;

import representacao.services.ProjetoService;
import representacao.wrappers.BibliotecaWrapper;
import representacao.wrappers.PacoteProjetoWrapper;
import representacao.wrappers.PacoteWrapper;

@ManagedBean
@ViewScoped
public class InicioMB implements Serializable {

	private static final long serialVersionUID = -1217921343871934910L;
	
	private String dirProject;
	
	public String project() {
		File diretorio = new File(dirProject);
		if (!diretorio.exists()) {
			String msg = "Directory not found";
			FacesContext.getCurrentInstance().addMessage(dirProject, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not found", msg));
			return "";
		} else {
			//Gera arquivo para visualização do projeto
			File arquivoPom = new File(dirProject + "/pom.xml");
			if (!arquivoPom.exists()) {
				String msg = "Maven project not found";
				FacesContext.getCurrentInstance().addMessage(dirProject, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not found", msg));
				return "";
			}
		}
		
		ProjetoService projetoService = new ProjetoService();
		try {
			projetoService.geraArquivo(dirProject);
		} catch (Exception e) {
			String msg = "Can't access the project";
			FacesContext.getCurrentInstance().addMessage(dirProject, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));
			return "";
		} 
		
		return "project.xhtml";
	}

	public String getDirProject() {
		return dirProject;
	}

	public void setDirProject(String dirProject) {
		this.dirProject = dirProject;
	}

		
}
