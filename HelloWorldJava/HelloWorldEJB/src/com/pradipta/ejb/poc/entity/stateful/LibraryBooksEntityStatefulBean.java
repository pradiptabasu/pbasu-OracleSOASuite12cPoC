package com.pradipta.ejb.poc.entity.stateful;

import com.pradipta.ejb.poc.entity.Books;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;

@Stateful(name = "LibraryBooksEntityStateful",
          mappedName = "SoaSpringComponentPoC-HelloWorldEJB-LibraryBooksEntityStateful")
public class LibraryBooksEntityStatefulBean implements LibraryBooksEntityStateful, LibraryBooksEntityStatefulLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "HelloWorldEJB", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public LibraryBooksEntityStatefulBean() {
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

    /** <code>select o from Books o order by o.id</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Books> getBooksFindAll() {
        return em.createNamedQuery("Books.findAll", Books.class).getResultList();
    }
    
    @PostConstruct
    public void postConstruct(){
       System.out.println("*********************************  LibraryBooksEntityStatefulBean.postConstruct::"
          + " bean created.");
    }

    @PreDestroy
    public void preDestroy(){
       System.out.println("*********************************  LibraryBooksEntityStatefulBean.preDestroy:"
          + " bean removed.");
    }

    @PostActivate
    public void postActivate(){
       System.out.println("*********************************  LibraryBooksEntityStatefulBean.postActivate:"
          + " bean activated.");
    }

    @PrePassivate
    public void prePassivate(){
       System.out.println("*********************************  LibraryBooksEntityStatefulBean.prePassivate:"
          + " bean passivated.");
    }  
}
