package com.pradipta.ejb.client;

import com.pradipta.ejb.poc.entity.Books;
import com.pradipta.ejb.poc.entity.LibraryBooksEntityStateless;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.math.BigDecimal;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import weblogic.servlet.cluster.wan.Update;

public class LibraryBooksEntityStatelessClient {
    public static void main(String[] args) {
        try {
            LibraryBooksEntityStatelessClient libraryBooksEntityStatelessClient = new LibraryBooksEntityStatelessClient();
            libraryBooksEntityStatelessClient.testLibraryStatelessEJB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    private Context getInitialContext() throws NamingException {
        @SuppressWarnings("rawtypes")
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        return new InitialContext(env);
    }

    private void showGUI() {
        System.out.println("\n");
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\t\t2. List primary Bean booklist\t\t");
        System.out.print("3. List secondary bean booklist \t\t4. List aux Bean booklist \t\t");
        System.out.print("5. Update Book\t\t6. Remove Book\t\t7. Exit \nEnter Choice: ");
    }

    private LibraryBooksEntityStateless getNewBean() {
        Context context = null;
        try {
            context = getInitialContext();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LibraryBooksEntityStateless libraryBooksEntityStatelessLocal = null;
        try {
            libraryBooksEntityStatelessLocal =
                (LibraryBooksEntityStateless) context.lookup("SoaSpringComponentPoC-HelloWorldEJB-LibraryBooksEntityStateless#com.pradipta.ejb.poc.entity.LibraryBooksEntityStateless");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return libraryBooksEntityStatelessLocal;
    }

    private void showBookList(LibraryBooksEntityStateless libraryBooksEntityStateless) {
        List<Books> bookList = libraryBooksEntityStateless.getBooksFindAll();
        System.out.println("Book(s) entered so far: " + bookList.size());
        int i = 0;
        for (Books book : bookList) {
            System.out.println(book.getId() + ". " + book.getName());
        }
    }
    
    private void updateBook(LibraryBooksEntityStateless libraryBooksEntityStateless, String bookname, int id) {
        Books book = new Books();
        book.setId(id);
        book.setName(bookname);
        libraryBooksEntityStateless.mergeBooks(book);
    }
    
    private void removeBook(LibraryBooksEntityStateless libraryBooksEntityStateless, int id) {
        Books book = new Books();
        book.setId(id);
        libraryBooksEntityStateless.removeBooks(book);
    }

    private void addBooktoBean(LibraryBooksEntityStateless libraryBooksEntityStateless, String bookname) {
        Books book = new Books();
        book.setName(bookname);
        libraryBooksEntityStateless.persistBooks(book);
    }

    private void testLibraryStatelessEJB() {
        BufferedReader brConsoleReader = null;
        LibraryBooksEntityStateless libraryBooksEntityStatelessPrimarybeanRef = null;
        LibraryBooksEntityStateless libraryBooksEntityStatelessSecondaryBeanRef = null;
        LibraryBooksEntityStateless libraryBooksEntityStatelessAuxBeanRef = null;
        try {
            int choice = 0;
            brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
            libraryBooksEntityStatelessPrimarybeanRef = getNewBean();
            libraryBooksEntityStatelessSecondaryBeanRef = getNewBean();

            while (true) {
                String bookName;
                int id;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                System.out.println("Input : " + strChoice);
                if (choice == 1) {
                    System.out.println("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    addBooktoBean(libraryBooksEntityStatelessPrimarybeanRef, bookName);
                } else if (choice == 2) {
                    System.out.println("Booklist from primary bean: ");
                    showBookList(libraryBooksEntityStatelessPrimarybeanRef);
                } else if (choice == 3) {
                    System.out.println("Booklist from secondary bean: ");
                    showBookList(libraryBooksEntityStatelessSecondaryBeanRef);
                } else if (choice == 4) {
                    System.out.println("Booklist from new aux bean: ");
                    libraryBooksEntityStatelessAuxBeanRef = getNewBean();
                    showBookList(libraryBooksEntityStatelessAuxBeanRef);
                } else if (choice == 5) {
                    System.out.println("Enter book id: ");
                    id = Integer.parseInt(brConsoleReader.readLine(),10);
                    System.out.println("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    updateBook(libraryBooksEntityStatelessPrimarybeanRef, bookName, id);
                } else if (choice == 6) {
                    System.out.println("Enter book id: ");
                    id = Integer.parseInt(brConsoleReader.readLine(),10);
                    removeBook(libraryBooksEntityStatelessPrimarybeanRef, id);
                } else if (choice == 7) {
                    break;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (brConsoleReader != null) {
                    brConsoleReader.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
