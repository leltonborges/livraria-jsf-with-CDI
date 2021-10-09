package br.com.caelum.livraria.dao;

import br.com.caelum.livraria.modelo.Autor;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class AutorDAO implements Serializable {
    private static final long serialVersionUID = 1l;
    @Inject
    EntityManager em;
    private DAO<Autor> dao;

    @PostConstruct
    void init(){
        this.dao = new DAO<Autor>(this.em, Autor.class);
    }

    public void adiciona(Autor autor) {
        dao.adiciona(autor);
    }

    public void remove(Autor autor) {
        dao.remove(autor);
    }

    public void atualiza(Autor autor) {
        dao.atualiza(autor);
    }

    public List<Autor> listaTodos() {
        return dao.listaTodos();
    }

    public Autor buscaPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public int contaTodos() {
        return dao.contaTodos();
    }
}
