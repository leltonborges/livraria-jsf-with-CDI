package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.transaction.Logs;
import br.com.caelum.livraria.transaction.Transacional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

//@ManagedBean // Gerenciado beans pelo JSF
//@ViewScoped // Request do JSF package javax.faces.bean.ViewScoped

@Named // Gerenciando beans com CDI
@ViewScoped // Request do CDI, package javax.faces.view.ViewScoped

// CDI necessita do Serializable
@Logs
public class AutorBean implements Serializable {
	private final static long serialVersionUID = 1l;

	@Inject
	private AutorDAO dao; // CDI fazer  new AutorDAO();

	private Autor autor = new Autor();

	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	@Logs
	public void carregarAutorPelaId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	@Transacional
	@Logs
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	@Transacional
	@Logs
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		this.dao.remove(autor);
	}

	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
