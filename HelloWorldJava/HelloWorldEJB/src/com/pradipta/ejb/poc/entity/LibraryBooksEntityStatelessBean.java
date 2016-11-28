package com.pradipta.ejb.poc.entity;

import com.pradipta.ejb.poc.interceptor.LibraryBooksEntityStatelessInterceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.interceptor.*;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Interceptors({LibraryBooksEntityStatelessInterceptor.class})
@Stateless(name = "LibraryBooksEntityStateless",
           mappedName = "SoaSpringComponentPoC-HelloWorldEJB-LibraryBooksEntityStateless")
public class LibraryBooksEntityStatelessBean implements LibraryBooksEntityStateless, LibraryBooksEntityStatelessLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "HelloWorldEJB")
    private EntityManager em;

    public LibraryBooksEntityStatelessBean() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
    }

    public Books persistBooks(Books books) {
        em.persist(books);
        return books;
    }

    public Books mergeBooks(Books books) {
        return em.merge(books);
    }

    public void removeBooks(Books books) {
        books = em.find(Books.class, books.getId());
        em.remove(books);
    }

    /** <code>select o from Books o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Books> getBooksFindAll() {
        return em.createNamedQuery("Books.findAll", Books.class).getResultList();
    }
    
    // Callbacks ----------------------------------------------------------------
    @PostConstruct
    public void postConstructCallback()
    {
       System.out.println("*********************************  PostConstruct - Have EntityManager: LibraryBooksEntityStatelessBean");
    }

    @PreDestroy
    public void preDestroyCallback()
    {
       System.out.println("*********************************  PreDestory - Have EntityManager: LibraryBooksEntityStatelessBean");
    }
}
