package com.pradipta.ejb.poc.entity.entityCallback;

import com.pradipta.ejb.poc.entity.Books;

import javax.persistence.PrePersist;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class BooksEntityCallback {
    @PrePersist
       public void prePersist(Books book){
          System.out.println("*********************************  BooksEntityCallback.prePersist:" 
             + "Book to be created with book id: "+book.getId());
       }

       @PostPersist
       public void postPersist(Object book){
          System.out.println("*********************************  BooksEntityCallback.postPersist::"
             + "Book created with book id: "+((Books)book).getId());
       }

       @PreRemove
       public void preRemove(Books book)
       {
          System.out.println("*********************************  BooksEntityCallback.preRemove:"
             + " About to delete Book: " + book.getId());
       }

       @PostRemove
       public void postRemove(Books book)
       {
          System.out.println("*********************************  BooksEntityCallback.postRemove::"
             + " Deleted Book: " + book.getId());
       }

       @PreUpdate
       public void preUpdate(Books book)
       {
          System.out.println("*********************************  BooksEntityCallback.preUpdate::"
             + " About to update Book: " + book.getId());
       }

       @PostUpdate
       public void postUpdate(Books book)
       {
          System.out.println("*********************************  BooksEntityCallback.postUpdate::"
             + " Updated Book: " + book.getId());
       }

       @PostLoad
       public void postLoad(Books book)
       {
          System.out.println("*********************************  BooksEntityCallback.postLoad::"
             + " Loaded Book: " + book.getId());
       }
}
