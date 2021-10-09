package br.com.caelum.livraria.transaction;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Calendar;

@Interceptor
@Transacional
public class InteceptorTime implements Serializable {
    private final static long serialVersionUID = 1l;

    @AroundInvoke
    public Object whileTime(InvocationContext context) throws Exception {
        long initTime = Calendar.getInstance().getTimeInMillis();

        Object result = context.proceed();

        long finalTime = Calendar.getInstance().getTimeInMillis();

        System.out.println("\n\nTempo de : " + (finalTime - initTime) + "ms\n\n");
        return result;
    }
}
