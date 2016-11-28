package com.pradipta.ejb.poc.stateless;

import java.util.List;

import javax.ejb.Local;

@Local
public interface LibraryStatelessLocal {
    void addBook(String bookName);
    List<String> getBooks();
}
