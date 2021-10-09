package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

//@ManagedBean // Gerenciado beans pelo JSF
//@ViewScoped // Request do JSF package javax.faces.bean.ViewScoped

@Named // Gerenciando beans com CDI
@ViewScoped // Request do CDI, package javax.faces.view.ViewScoped

// CDI necessita do Serializable
public class AutorBean implements Serializable {
	private final static long serialVersionUID = 1l;

	private Autor autor = new Autor();

	private Integer autorId;



	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		new DAO<Autor>(Autor.class).remove(autor);
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
