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
public class BookDa implements Da<Book> {
    private PreparedStatement preparedStatement;
    private Connection connection;
    @Override
    public Book save(Book Book) throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "select book_SEQ.nextval as book_id from DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Book book;
        book.setId(resultSet.getInt("book_id"));

        preparedStatement = connection.prepareStatement(
                "insert into BOOK_TBL(ID, title,author) values (?,?,?)"
        );
        preparedStatement.setInt(1,book.getId());
        preparedStatement.setString(2,book.gettitle());
        preparedStatement.setString(3,book.getouther());
        preparedStatement.execute();

        close();
        return Book();
    }



    @Override
    public Book edit(Book book) throws Exception {


            connection = JdbcProvider.getConnection();
            preparedStatement = connection.prepareStatement(
                    "update BOOK_TBL set TITLE = ? , AUTHOR = ?  where ID = ?"
            );
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());


            preparedStatement.execute();
            close();

            return book;

    }

    @Override
    public Book remove(int id) throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "delete from BOOK_TBL where ID = ?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        close();
        return null;

    }

    @Override
    public List<Book> findAll() throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from BOOK_TBL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        List<book> bookList = new ArrayList<>();
        while (resultSet.next()){
            book book =
                    book
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .build();
            bookList.add(book);

        }
        close();
        return bookList;
    }
    @Override
    public Book findById(int id) throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from STUFF_TBL where ID = ?"
        );
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Book Book = null;
        while (resultSet.next()) {
            Book =
                    Book
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .build();
        }
        close();
        return Book;


        
    }
    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}