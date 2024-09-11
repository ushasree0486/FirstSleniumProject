package library;

import library.exception.NoBookFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Libraray {
    private final List<Book> books = new ArrayList<>();

    public List<Book> search(String searchString) {
        // return null;//returns null pointer Exception
        // return Arrays.asList(new Book("Lord Of The Flies", "Fred Perry"));
        // Book matchingBook = null;
        //Book matchingBook = new Book();
        List<Book> matchingBooks = new ArrayList<>();
        for (Book b : books) {
            //if(b.getTitle().equalsIgnoreCase(searchString)){//search by caseInsensitive
            if (b.getTitle().toUpperCase().contains(searchString.toUpperCase())
                    || b.getAuthor().toUpperCase().contains(searchString.toUpperCase())) {
               //search by title or author

                //return Arrays.asList(b);
                // matchingBook=b;
                //break;
                //return Arrays.asList(matchingBook);
                matchingBooks.add(b);
            }
        }
        //return null;
        // return Arrays.asList(matchingBook);
        //throw new NoBookFoundException("Book with title "+searchString + " no found");
        return matchingBooks;
    }

    public void add(Book book) {
        books.add(book);
    }

    public List<Book> borrow(String id) {
        return null;
    }
}
