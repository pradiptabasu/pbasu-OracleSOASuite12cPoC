package com.pradipta.ejb.poc.stateful;

import java.util.List;

import javax.ejb.Local;

@Local
public interface LibraryStatefulLocal {
    void addBook(String bookName);
    List<String> getBooks();
}
