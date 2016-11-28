package com.pradipta.ejb.client;

import com.pradipta.ejb.poc.entity.Books;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LibraryBooksEntityMessageDrivenBeanClient {
    public static void main(String[] args) throws JMSException, NamingException {
        BufferedReader brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
        final Context ic = getInitialContext();
        final QueueConnectionFactory qcf = (QueueConnectionFactory) ic.lookup("jms/testLocalJMSconFact");
        // Lookup should specify the queue name that is mentioned as "mappedName" in MessageDriven Bean.
        final Queue destQueue = (Queue) ic.lookup("jms/testLocalJMSqueue1");
        ic.close();
        final QueueConnection connection = qcf.createQueueConnection();
        try {
            final QueueSession session = connection.createQueueSession(false, 0);
            final QueueSender sender = session.createSender(destQueue);
            System.out.println("Enter book name: ");
            String bookName = brConsoleReader.readLine();
            Books book = new Books();
            book.setName(bookName);
            final ObjectMessage objectMessage = session.createObjectMessage(book);
            sender.send(objectMessage);
            sender.close();
            session.close();
            connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // Add InitialContext property assignments here.
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        // Note that by default WebLogic server is not created with security, so credentials are not needed.
        // TODO: Verify the server address and port number
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        return new InitialContext(env);
    }
}
