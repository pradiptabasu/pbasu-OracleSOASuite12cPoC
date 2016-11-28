package com.pradipta.ejb.poc.stateless;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.Init;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.jws.WebService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "LibraryStateless", mappedName = "SoaSpringComponentPoC-HelloWorldEJB-LibraryStateless")
@WebService(name="LibraryStatelessService", serviceName="LibraryStatelessService", portName="LibraryStatelessPort")
public class LibraryStatelessBean implements LibraryStateless, LibraryStatelessLocal {

    List<String> bookShelf; 
    
    public LibraryStatelessBean() {
        bookShelf = new ArrayList<String>();
    }

    @Override
    public void addBook(String bookName) {
        // TODO Implement this method
        bookShelf.add(bookName);
    }

    @Override
    public List<String> getBooks() {
        // TODO Implement this method
        return bookShelf;
    }
}
