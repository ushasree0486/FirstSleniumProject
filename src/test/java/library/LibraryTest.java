package library;

import org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    private static final Book LORD_OF_THE_FLIES = new Book("Lord of the Flies", "Fred Perry");
    private static final Book LORD_OF_THE_RINGS = new Book("Lord of the Rings", "John Smith");
    private static final Book WAR_AND_PEACE = new Book("War And Peace", "Lord Bennett");
    private static final Book HARRY_POTTER = new Book("Harry Potter", "J.K.Rowling");

    private final Libraray library = new Libraray();
    @Before
    public void initialise(){
        library.add(LORD_OF_THE_FLIES);
        library.add(LORD_OF_THE_RINGS);
        library.add(WAR_AND_PEACE);
       // library.add(HARRY_POTTER);
    }
    @Test
    public void searchReturnsTheSingleBookThatMatchesTheEntireTitle(){
        List<Book> results =library.search("Lord of the Flies");
        assertEquals("macthing books",1,results.size());

        Book book=results.get(0);
        assertEquals(LORD_OF_THE_FLIES,book);
        assertEquals("Lord of the Flies",book.getTitle());
       // assertEquals("macthing books",0,library.search("no book").size());
    }
    @Test
    public void searchReturnsTheSingleBookThatMatchesCaseISensitiveTitle(){
        List<Book> results =library.search("lord of the flies");
        assertEquals("macthing books",1,results.size());
        //assertEquals("Lord Of The Flies",results.get(0).getTitle());
    }
    @Test
    public  void searchReturnsTheBookMathesByTitleOrAuthor(){
        List<Book> results =library.search("lord");
        assertEquals("macthing books",3,results.size());
        assertTrue(results.contains(LORD_OF_THE_FLIES));
        assertTrue(results.contains(LORD_OF_THE_RINGS));
        assertTrue(results.contains(WAR_AND_PEACE));
        assertFalse(results.contains(HARRY_POTTER));

    }


    }
