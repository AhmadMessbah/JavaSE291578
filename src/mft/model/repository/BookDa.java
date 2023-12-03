package mft.model.repository;

import mft.model.repository.impl.Da;
import mft.model.entity.Book;

import java.util.List;

public class BookDa implements Da<Book> {
    @Override
    public Book save(Book book) throws Exception {
        Book book1 = new Book();
        return null;
    }

    @Override
    public Book edit(Book book) throws Exception {
        return null;
    }

    @Override
    public Book remove(int id) throws Exception {
        return null;
    }

    @Override
    public List<Book> findAll() throws Exception {
        return null;
    }

    @Override
    public Book findById(int id) throws Exception {
        return null;
    }
}
