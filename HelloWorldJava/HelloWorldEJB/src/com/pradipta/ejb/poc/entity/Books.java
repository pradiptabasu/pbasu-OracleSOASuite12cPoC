package com.pradipta.ejb.poc.entity;

import com.pradipta.ejb.poc.entity.entityCallback.BooksEntityCallback;

import java.io.Serializable;

import javax.persistence.EntityListeners;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * To create ID generator sequence "BOOKS_ID_SEQ_GEN":
 * CREATE SEQUENCE "BOOKS_ID_SEQ_GEN" INCREMENT BY 1 START WITH 1;
 */
@Entity
@EntityListeners(BooksEntityCallback.class)
@NamedQueries({ @NamedQuery(name = "Books.findAll", query = "select o from Books o order by o.id") })
@Table(name = "BOOKS")
@SequenceGenerator(name = "Books_Id_Seq_Gen", sequenceName = "BOOKS_ID_SEQ_GEN", allocationSize = 1,
                   initialValue = 1)
public class Books implements Serializable {
    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = -738200461848243366L;
    //serialVersionUID = 5246387639147580207, local class serialVersionUID = -738200461848243366

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Books_Id_Seq_Gen")
    private int id;
    @Column(length = 50)
    private String name;

    public Books() {
    }

    public Books(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("id=");
        buffer.append(getId());
        buffer.append(',');
        buffer.append("name=");
        buffer.append(getName());
        buffer.append(']');
        return buffer.toString();
    }
}
