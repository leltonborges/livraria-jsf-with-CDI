package br.com.caelum.livraria.bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

//@ManagedBean // Gerenciado beans pelo JSF
//@SessionScoped // Request do JSF package javax.faces.bean.SessionScoped

@Named // Gerenciando beans com CDI
@SessionScoped // Request do CDI, package javax.enterprise.context.SessionScoped

// CDI necessita do Serializablee
public class TemaBean implements Serializable {
	private final long serialVersionUID = 1l;
	
	private String tema = "luna-blue";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String[] getTemas() {
		return new String[]{"afterdark","afternoon","afterwork","black-tie", "luna-blue","dark-hive"};
	}
	

}
