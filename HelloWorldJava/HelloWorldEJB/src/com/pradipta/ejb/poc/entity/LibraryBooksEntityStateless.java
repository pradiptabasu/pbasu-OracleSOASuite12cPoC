package com.pradipta.ejb.poc.entity;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LibraryBooksEntityStateless {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);

    Books persistBooks(Books books);

    Books mergeBooks(Books books);

    void removeBooks(Books books);

    List<Books> getBooksFindAll();
}
