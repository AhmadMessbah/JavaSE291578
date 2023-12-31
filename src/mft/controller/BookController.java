package mft.controller;

import mft.model.entity.Book;
import mft.model.entity.Stuff;
import mft.model.repository.BookDa;
import mft.model.repository.StuffDa;

import java.util.regex.Pattern;

public class BookController {
    private static BookController controller = new BookController();

    private BookController() {
    }

    public static BookController getController() {
        return controller;
    }


    public Book save(String title, String author) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", title) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", author)) {
                Book book = Book.builder().title(title).author(author).build();

                BookDa bookDa = new BookDa();
                bookDa.save(book);
                return book;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    public Book edit(String title, String author) {
        try {
            if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", title) &&
                    Pattern.matches("^[a-zA-Z\\s]{3,30}$", author)) {
                Book book = Book.builder().title(title).author(author).build();

                BookDa bookDa = new BookDa();
                bookDa.save(book);
                return book;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }


}
