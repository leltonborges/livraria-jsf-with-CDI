package br.com.caelum.livraria.dao;

import br.com.caelum.livraria.modelo.Livro;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class LivroDAO implements Serializable {
    private static final long serialVersionUID = 1l;

    @Inject
    private EntityManager em;
    private DAO<Livro> dao;

    @PostConstruct
    void init(){
        this.dao = new DAO<Livro>(this.em, Livro.class);
    }

    public void adiciona(Livro livro) {
        dao.adiciona(livro);
    }

    public void remove(Livro livro) {
        dao.remove(livro);
    }

    public void atualiza(Livro livro) {
        dao.atualiza(livro);
    }

    public List<Livro> listaTodos() {
        return dao.listaTodos();
    }

    public Livro buscaPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public int contaTodos() {
        return dao.contaTodos();
    }

    public List<Livro> listaTodosPaginada(int firstResult, int maxResults) {
        return dao.listaTodosPaginada(firstResult, maxResults);
    }
}
