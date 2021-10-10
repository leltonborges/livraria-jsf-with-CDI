package br.com.caelum.livraria.dao;

import br.com.caelum.livraria.modelo.Venda;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class VendaDAO implements Serializable {
    private static final long serialVersionUID = 1l;
    @Inject
    EntityManager em;
    private DAO<Venda> dao;

    @PostConstruct
    void init(){
        this.dao = new DAO<Venda>(this.em, Venda.class);
    }

    public void adiciona(Venda Venda) {
        dao.adiciona(Venda);
    }

    public void remove(Venda Venda) {
        dao.remove(Venda);
    }

    public void atualiza(Venda Venda) {
        dao.atualiza(Venda);
    }

    public List<Venda> listaTodos() {
        return dao.listaTodos();
    }

    public Venda buscaPorId(Integer id) {
        return dao.buscaPorId(id);
    }

    public int contaTodos() {
        return dao.contaTodos();
    }
}
