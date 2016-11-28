package com.pradipta.ejb.poc.stateless;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LibraryStateless {
    void addBook(String bookName);
    List<String> getBooks();
}
