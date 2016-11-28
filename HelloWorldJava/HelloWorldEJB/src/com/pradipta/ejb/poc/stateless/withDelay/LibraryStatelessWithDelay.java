package com.pradipta.ejb.poc.stateless.withDelay;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LibraryStatelessWithDelay {
    void addBook(String bookName);
    List<String> getBooks();
}
