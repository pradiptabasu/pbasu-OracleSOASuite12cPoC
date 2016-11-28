package com.pradipta.ejb.client;

import com.pradipta.ejb.poc.stateless.withDelay.LibraryStatelessWithDelay;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LibraryStatelessWithDelayClient {
    
    public static void main(String[] args) {
        try {
            LibraryStatelessWithDelayClient libraryStatelessClient = new LibraryStatelessWithDelayClient();
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

    private LibraryStatelessWithDelay getNewBean() {
        Context context = null;
        try {
            context = getInitialContext();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LibraryStatelessWithDelay libraryStatelessWithDelayLocal = null;
        try {
            libraryStatelessWithDelayLocal =
                (LibraryStatelessWithDelay) context.lookup("SoaSpringComponentPoC-HelloWorldEJB-LibraryStatelessWithDelay#com.pradipta.ejb.poc.stateless.withDelay.LibraryStatelessWithDelay");
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("will leave getbean now ");
        return libraryStatelessWithDelayLocal;
    }

    private void showBookList(LibraryStatelessWithDelay libraryStatelessWithDelay) {
        List<String> bookList = libraryStatelessWithDelay.getBooks();
        System.out.println("Book(s) entered so far: " + bookList.size());
        int i = 0;
        for (String book : bookList) {
            System.out.println((i + 1) + ". " + book);
            i++;
        }
    }

    private void addBooktoBean(LibraryStatelessWithDelay libraryStatelessWithDelay, String bookname) {
        libraryStatelessWithDelay.addBook(bookname);
    }

    private void testLibraryStatelessEJB() {
        BufferedReader brConsoleReader = null;
        LibraryStatelessWithDelay libraryStatelessWithDelayPrimarybeanRef = null;
        LibraryStatelessWithDelay libraryStatelessWithDelaySecondaryBeanRef = null;
        LibraryStatelessWithDelay libraryStatelessWithDelayAuxBeanRef = null;
        try {
            int choice = 0;
            brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
            libraryStatelessWithDelayPrimarybeanRef = getNewBean();
            libraryStatelessWithDelaySecondaryBeanRef = getNewBean();
            while (true) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                System.out.println("Input : " + strChoice);
                if (choice == 1) {
                    System.out.println("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    addBooktoBean(libraryStatelessWithDelayPrimarybeanRef, bookName);
                } else if (choice == 2) {
                    System.out.println("Booklist from primary bean: ");
                    showBookList(libraryStatelessWithDelayPrimarybeanRef);
                } else if (choice == 3) {
                    System.out.println("Booklist from secondary bean: ");
                    showBookList(libraryStatelessWithDelaySecondaryBeanRef);
                } else if (choice == 4) {
                    System.out.println("Booklist from new aux bean: ");
                    libraryStatelessWithDelayAuxBeanRef = getNewBean();
                    showBookList(libraryStatelessWithDelayAuxBeanRef);
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
