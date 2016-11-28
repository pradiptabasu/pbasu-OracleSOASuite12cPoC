package com.pradipta.ejb.client;

import com.pradipta.ejb.poc.entity.Books;
import com.pradipta.ejb.poc.entity.stateful.LibraryBooksEntityStateful;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LibraryBooksEntityStatefulClient {
    public static void main(String[] args) {
        try {
            LibraryBooksEntityStatefulClient libraryBooksEntityStatefulClient = new LibraryBooksEntityStatefulClient();
            libraryBooksEntityStatefulClient.testLibraryStatelessEJB();
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

    private LibraryBooksEntityStateful getNewBean() {
        Context context = null;
        try {
            context = getInitialContext();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LibraryBooksEntityStateful LibraryBooksEntityStatefulLocal = null;
        try {
            LibraryBooksEntityStatefulLocal =
                (LibraryBooksEntityStateful) context.lookup("SoaSpringComponentPoC-HelloWorldEJB-LibraryBooksEntityStateful#com.pradipta.ejb.poc.entity.stateful.LibraryBooksEntityStateful");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return LibraryBooksEntityStatefulLocal;
    }

    private void showBookList(LibraryBooksEntityStateful LibraryBooksEntityStateful) {
        List<Books> bookList = LibraryBooksEntityStateful.getBooksFindAll();
        System.out.println("Book(s) entered so far: " + bookList.size());
        int i = 0;
        for (Books book : bookList) {
            System.out.println(book.getId() + ". " + book.getName());
        }
    }

    private void updateBook(LibraryBooksEntityStateful LibraryBooksEntityStateful, String bookname, int id) {
        Books book = new Books();
        book.setId(id);
        book.setName(bookname);
        LibraryBooksEntityStateful.mergeBooks(book);
    }

    private void removeBook(LibraryBooksEntityStateful LibraryBooksEntityStateful, int id) {
        Books book = new Books();
        book.setId(id);
        LibraryBooksEntityStateful.removeBooks(book);
    }

    private void addBooktoBean(LibraryBooksEntityStateful LibraryBooksEntityStateful, String bookname) {
        Books book = new Books();
        book.setName(bookname);
        LibraryBooksEntityStateful.persistBooks(book);
    }

    private void testLibraryStatelessEJB() {
        BufferedReader brConsoleReader = null;
        LibraryBooksEntityStateful LibraryBooksEntityStatefulPrimarybeanRef = null;
        LibraryBooksEntityStateful LibraryBooksEntityStatefulSecondaryBeanRef = null;
        LibraryBooksEntityStateful LibraryBooksEntityStatefulAuxBeanRef = null;
        try {
            int choice = 0;
            brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
            LibraryBooksEntityStatefulPrimarybeanRef = getNewBean();
            LibraryBooksEntityStatefulSecondaryBeanRef = getNewBean();

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
                    addBooktoBean(LibraryBooksEntityStatefulPrimarybeanRef, bookName);
                } else if (choice == 2) {
                    System.out.println("Booklist from primary bean: ");
                    showBookList(LibraryBooksEntityStatefulPrimarybeanRef);
                } else if (choice == 3) {
                    System.out.println("Booklist from secondary bean: ");
                    showBookList(LibraryBooksEntityStatefulSecondaryBeanRef);
                } else if (choice == 4) {
                    System.out.println("Booklist from new aux bean: ");
                    LibraryBooksEntityStatefulAuxBeanRef = getNewBean();
                    showBookList(LibraryBooksEntityStatefulAuxBeanRef);
                } else if (choice == 5) {
                    System.out.println("Enter book id: ");
                    id = Integer.parseInt(brConsoleReader.readLine(), 10);
                    System.out.println("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    updateBook(LibraryBooksEntityStatefulPrimarybeanRef, bookName, id);
                } else if (choice == 6) {
                    System.out.println("Enter book id: ");
                    id = Integer.parseInt(brConsoleReader.readLine(), 10);
                    removeBook(LibraryBooksEntityStatefulPrimarybeanRef, id);
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
