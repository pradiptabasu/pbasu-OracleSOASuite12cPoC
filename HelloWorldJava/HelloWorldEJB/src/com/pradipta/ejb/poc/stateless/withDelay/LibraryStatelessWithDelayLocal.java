package com.pradipta.ejb.poc.stateless.withDelay;

import java.util.List;

import javax.ejb.Local;

@Local
public interface LibraryStatelessWithDelayLocal {
    void addBook(String bookName);
    List<String> getBooks();
}
