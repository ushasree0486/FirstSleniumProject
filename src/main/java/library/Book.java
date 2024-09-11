package library;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Book {
    // private final String author;
    // private final String title;
    private String author;
    private String title;
    private String id;

    public Book() {
    }

    public Book(String title, String author) {
        this.author = author;
        this.title = title;
        //this.id=id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    /*public void setId(String id) {
        this.id = id;
    }*/

    @Override
    public String toString() {
        //return {new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(title);
        return title + " : " + author;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Book) {
            Book b = (Book) o;
            return b.getTitle().equals(this.getTitle()); //&& b.getAuthor().contains(this.getAuthor());
        } else return false;
    }
}
