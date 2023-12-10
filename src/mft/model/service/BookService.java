package mft.model.service;

import mft.model.entity.Book;
import mft.model.repository.BookDa;

import java.util.List;

public class BookService {
    private static BookService service = new BookService();

    private BookService() {
    }

    public static BookService getService() {
        return service;
    }

    public Book save(Book book) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            if (bookDa.findById(book.getId()) == null) {
                return bookDa.save(book);
            } else {
                throw new Exception();
            }
        }
    }

    public Book edit(Book book) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            return  bookDa.edit(book);
        }
    }

    public Book remove(int id) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            return bookDa.remove(id);
        }
    }

    public List<Book> findAll() throws Exception {
        try (BookDa bookDa = new BookDa()) {
            return bookDa.findAll();
        }
    }

    public Book findById(int id) throws Exception {
        try (BookDa bookDa = new BookDa()) {
            return bookDa.findById(id);
        }
    }
}
