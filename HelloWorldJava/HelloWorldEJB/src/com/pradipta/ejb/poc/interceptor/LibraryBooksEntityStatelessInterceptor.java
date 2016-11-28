package com.pradipta.ejb.poc.interceptor;

import javax.interceptor.*;

public class LibraryBooksEntityStatelessInterceptor {
    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception
    {
       System.out.println("*** Intercepting call to LibraryBooksEntityStatelessBean method: " 
       + ctx.getMethod().getName());
       return ctx.proceed();
    }
}
