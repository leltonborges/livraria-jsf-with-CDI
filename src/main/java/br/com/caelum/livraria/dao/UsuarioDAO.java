package br.com.caelum.livraria.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.transaction.Transacional;

import java.io.Serializable;

public class UsuarioDAO implements Serializable {
	private static final long serialVersionUID = 1l;

	@Inject
	private EntityManager manager;

	@Transacional
	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = manager.createQuery(
				  " select u from Usuario u "
				+ " where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			Usuario resultado =  query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		
		return true;
	}

}
