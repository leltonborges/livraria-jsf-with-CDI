package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.LivroDAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.transaction.Logs;
import br.com.caelum.livraria.transaction.Transacional;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

//@ManagedBean // Gerenciado beans pelo JSF
//@ViewScoped // Request do JSF package javax.faces.bean.ViewScoped

@Named // Gerenciando beans com CDI
@ViewScoped // Request do CDI, package javax.faces.view.ViewScoped
@Logs // Anotação personalizada calcularar o tempo em ms
// CDI necessita do Serializable
public class LivroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LivroDAO livroDAO;
	@Inject
	private AutorDAO autorDAO;
	@Inject
	private FacesContext context;

	private Livro livro = new Livro();

	private Integer autorId;

	private List<Livro> livros;

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	@Logs
	public List<Livro> getLivros() {
		if(this.livros == null) {
			this.livros = livroDAO.listaTodos();
		}
		return livros;
	}

	public List<Autor> getAutores() {
		return this.autorDAO.listaTodos();
	}

	@Logs
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void carregarLivroPelaId() {
		this.livro = this.livroDAO.buscaPorId(this.livro.getId());
	}

	public void gravarAutor() {
		Autor autor = this.autorDAO.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}

	@Transacional
	@Logs
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		if(this.livro.getId() == null) {
			this.livroDAO.adiciona(this.livro);
			this.livros = this.livroDAO.listaTodos();
		} else {
			this.livroDAO.atualiza(this.livro);
		}

		this.livro = new Livro();
	}

	@Transacional
	@Logs
	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		this.livroDAO.remove(livro);
		this.livros = this.livroDAO.listaTodos();
	}

	@Logs
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	@Logs
	public void carregar(Livro livro) {
		System.out.println("Carregando livro");
		this.livro = livro;
	}
	
	public String formAutor() {
		System.out.println("Chamanda do formulário do Autor.");
		return "autor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"ISBN deveria começar com 1"));
		}

	}
}
