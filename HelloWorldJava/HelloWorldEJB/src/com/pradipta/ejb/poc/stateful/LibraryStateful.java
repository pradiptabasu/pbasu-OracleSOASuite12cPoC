package com.pradipta.ejb.poc.stateful;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LibraryStateful {
    void addBook(String bookName);
    List<String> getBooks();
}
