package com.pradipta.ejb.poc.stateful;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateful;

@Stateful(name = "LibraryStateful", mappedName = "SoaSpringComponentPoC-HelloWorldEJB-LibraryStateful")
public class LibraryStatefulBean implements LibraryStateful, LibraryStatefulLocal {
    @Resource
    SessionContext sessionContext;
    
    List<String> bookShelf; 

    public LibraryStatefulBean() {
        bookShelf = new ArrayList<String>();
    }
    
    public void addBook(String bookName) {
       bookShelf.add(bookName);
    }    
    
    public List<String> getBooks() {
       return bookShelf;
    }
}
