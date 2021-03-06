package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

//@ManagedBean // Gerenciado beans pelo JSF
//@ViewScoped // Request do JSF package javax.faces.bean.ViewScoped

@Named // Gerenciando beans com CDI
@ViewScoped // Request do CDI, package javax.faces.view.ViewScoped

// CDI necessita do Serializable
public class LoginBean implements Serializable {
	private final static long serialVersionUID = 1l;

	@Inject
	private UsuarioDAO usuarioDAO;
	@Inject
	FacesContext context;

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		System.out.println("fazendo login do usuario " + this.usuario.getEmail());

		boolean existe = usuarioDAO.existe(this.usuario);
		if(existe ) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
}
