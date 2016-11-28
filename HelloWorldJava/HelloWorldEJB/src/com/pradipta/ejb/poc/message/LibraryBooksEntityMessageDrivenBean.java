package com.pradipta.ejb.poc.message;

import com.pradipta.ejb.poc.entity.LibraryBooksEntityStateless;
import com.pradipta.ejb.poc.entity.Books;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(name = "LibraryBooksEntityMessageDrivenBean", mappedName = "jms/testLocalJMSqueue1",
               messageListenerInterface = MessageListener.class, activationConfig = {
               @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
               @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/testLocalJMSqueue1")
    })
public class LibraryBooksEntityMessageDrivenBean implements MessageListener {
    @Resource
    private MessageDrivenContext mdctx;

    @EJB
    LibraryBooksEntityStateless libraryBean;

    public void onMessage(Message message) {
        ObjectMessage objectMessage = null;
        try {
            objectMessage = (ObjectMessage) message;
            Books book = (Books) objectMessage.getObject();
            libraryBean.persistBooks(book);
        } catch (JMSException ex) {
            mdctx.setRollbackOnly();
        }
    }
    
    // Callbacks ----------------------------------------------------------------
    @PostConstruct
    public void postConstructCallback()
    {
       System.out.println("*********************************  PostConstruct - Have EntityManager: LibraryBooksEntityMessageDrivenBean");
    }

    @PreDestroy
    public void preDestroyCallback()
    {
       System.out.println("*********************************  PreDestory - Have EntityManager: LibraryBooksEntityMessageDrivenBean");
    }
}
