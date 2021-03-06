package br.com.caelum.livraria.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("livraria");

	@Produces
	@RequestScoped // tempo que a request vai viver
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// @Disposes -> para o CDI saber qual metodo chamara para fechar a request
	public void close(@Disposes EntityManager em) {
		em.close();
	}
}
