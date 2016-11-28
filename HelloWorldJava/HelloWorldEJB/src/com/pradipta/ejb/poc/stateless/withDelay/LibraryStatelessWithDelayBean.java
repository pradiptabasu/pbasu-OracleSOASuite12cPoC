package com.pradipta.ejb.poc.stateless.withDelay;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import javax.jws.WebService;

@Stateless(name = "LibraryStatelessWithDelay", mappedName = "SoaSpringComponentPoC-HelloWorldEJB-LibraryStatelessWithDelay")
@WebService(name = "LibraryStatelessWithDelayService", serviceName = "LibraryStatelessWithDelayService",
            portName = "LibraryStatelessWithDelayPort")
public class LibraryStatelessWithDelayBean implements LibraryStatelessWithDelay, LibraryStatelessWithDelayLocal {

    List<String> bookShelf;

    public LibraryStatelessWithDelayBean() {
        for(int i=0; i<99999;i++);
        bookShelf = new ArrayList<String>();
    }

    @Override
    public void addBook(String bookName) {
        // TODO Implement this method
        for(int i=0; i<99999;i++);
        bookShelf.add(bookName);
    }

    @Override
    public List<String> getBooks() {
        // TODO Implement this method
        for(int i=0; i<99999;i++);
        return bookShelf;
    }
}
