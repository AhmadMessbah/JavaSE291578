package mft.model.repository;

import javafx.scene.Node;
import mft.model.entity.Stuff;
import mft.model.repository.impl.Da;
import mft.model.entity.Book;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDa implements Da<Book>,AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Book save(Book book) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select book_SEQ.nextval as book_id from DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        book.setId(resultSet.getInt("book_id"));

        preparedStatement = connection.prepareStatement(
                "insert into BOOK_TBL(ID, title,author) values (?,?,?)"
        );
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.execute();
        return book;
    }

    @Override
    public Book edit(Book book) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "update BOOK_TBL set TITLE = ? , AUTHOR = ?  where ID = ?"
        );
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.execute();
        return book;
    }

    @Override
    public Book remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "delete from BOOK_TBL where ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Book> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from BOOK_TBL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()) {
            Book book =
                    Book
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .build();
            bookList.add(book);
        }
        return bookList;
    }

    @Override
    public Book findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from BOOK_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Book book = null;
        while (resultSet.next()) {
            book =
                    Book
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .build();
        }
        return book;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}