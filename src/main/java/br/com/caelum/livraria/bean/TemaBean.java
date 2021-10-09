package br.com.caelum.livraria.bean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

//@ManagedBean // Gerenciado beans pelo JSF
//@ViewScoped // Request do JSF package javax.faces.bean.ViewScoped

@Named // Gerenciando beans com CDI
@ViewScoped // Request do CDI, package javax.faces.view.ViewScoped

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
		return new String[]{"afterdark","afternoon","afterwork","aristo",
			"black-tie","blitzer","bluesky","bootstrap","casablanca",
			"cupertino","cruze","dark-hive","delta","dot-luv","eggplant",
			"excite-bike","flick","glass-x","home","hot-sneaks",
			"humanity","le-frog","midnight","mint-choc","overcast",
			"pepper-grinder","redmond","rocket","sam","smoothness",
			"south-street","start","sunny","swanky-purse","trontastic",
			"ui-darkness","ui-lightness","vader"};
	}
	

}
