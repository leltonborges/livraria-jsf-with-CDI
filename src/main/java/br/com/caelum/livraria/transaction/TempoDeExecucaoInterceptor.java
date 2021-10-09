package br.com.caelum.livraria.transaction;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@Logs
public class TempoDeExecucaoInterceptor implements Serializable {
    private final static long serialVersionUID = 1l;

    @AroundInvoke
    public Object testTemp(InvocationContext context) throws Exception {
        System.out.println("\n\nCalculando o tempo");
        long init = System.currentTimeMillis();
        Object result = context.proceed();
        long fim = System.currentTimeMillis();

        System.out.println("Time: +" + String.format("%dms\n\n", (fim - init)));

        return result;
    }

}
