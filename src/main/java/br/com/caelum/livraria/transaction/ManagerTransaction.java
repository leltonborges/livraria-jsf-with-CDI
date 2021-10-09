package br.com.caelum.livraria.transaction;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.io.Serializable;

@Transacional
@Interceptor
public class ManagerTransaction implements Serializable {
    private static final long serialVersionUID = 1l;

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object beginTransaction(InvocationContext context) throws Exception {
        manager.getTransaction().begin();
        System.out.println("\n\nabrindo TX");

        Object result = context.proceed();
        System.out.println("ALGUMA COISA TX");

        manager.getTransaction().commit();
        System.out.println("Fechando TX\n\n");
        return  result;
    }
}
