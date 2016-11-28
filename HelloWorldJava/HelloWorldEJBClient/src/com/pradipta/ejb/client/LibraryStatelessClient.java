package com.pradipta.ejb.client;

import com.pradipta.ejb.poc.stateless.LibraryStateless;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LibraryStatelessClient {
    
    public static void main(String[] args) {
        try {
            LibraryStatelessClient libraryStatelessClient = new LibraryStatelessClient();
            libraryStatelessClient.testLibraryStatelessEJB();
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
        System.out.print("Options \n1. Add Book\n2. List primary Bean booklist\n3. List secondary bean booklist \n4. List aux Bean booklist \n5. Exit \nEnter Choice: ");
    }

    private LibraryStateless getNewBean() {
        Context context = null;
        try {
            context = getInitialContext();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LibraryStateless libraryStatelessLocal = null;
        try {
            libraryStatelessLocal =
                (LibraryStateless) context.lookup("SoaSpringComponentPoC-HelloWorldEJB-LibraryStateless#com.pradipta.ejb.poc.stateless.LibraryStateless");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return libraryStatelessLocal;
    }

    private void showBookList(LibraryStateless libraryStateless) {
        List<String> bookList = libraryStateless.getBooks();
        System.out.println("Book(s) entered so far: " + bookList.size());
        int i = 0;
        for (String book : bookList) {
            System.out.println((i + 1) + ". " + book);
            i++;
        }
    }

    private void addBooktoBean(LibraryStateless libraryStateless, String bookname) {
        libraryStateless.addBook(bookname);
    }

    private void testLibraryStatelessEJB() {
        BufferedReader brConsoleReader = null;
        LibraryStateless libraryStatelessPrimarybeanRef = null;
        LibraryStateless libraryStatelessSecondaryBeanRef = null;
        LibraryStateless libraryStatelessAuxBeanRef = null;
        try {
            int choice = 0;
            brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
            libraryStatelessPrimarybeanRef = getNewBean();
            libraryStatelessSecondaryBeanRef = getNewBean();

            while (true) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                System.out.println("Input : " + strChoice);
                if (choice == 1) {
                    System.out.println("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    addBooktoBean(libraryStatelessPrimarybeanRef, bookName);
                } else if (choice == 2) {
                    System.out.println("Booklist from primary bean: ");
                    showBookList(libraryStatelessPrimarybeanRef);
                } else if (choice == 3) {
                    System.out.println("Booklist from secondary bean: ");
                    showBookList(libraryStatelessSecondaryBeanRef);
                } else if (choice == 4) {
                    System.out.println("Booklist from new aux bean: ");
                    libraryStatelessAuxBeanRef = getNewBean();
                    showBookList(libraryStatelessAuxBeanRef);
                } else if (choice == 5) {
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
