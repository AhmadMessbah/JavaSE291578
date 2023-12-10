package mft.model.repository;

import mft.model.repository.impl.Da;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Person;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BorrowDa implements Da<Borrow>, AutoCloseable {
    PreparedStatement preparedStatement;
    Connection connection;

    @Override
    public Borrow save(Borrow borrow) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "select borrow_seq.nextval as NEXT_BORROW_ID from dual"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        borrow.setId(resultSet.getInt("NEXT_BORROW_ID"));

        preparedStatement = connection.prepareStatement(
                "insert into borrow_tbl(id,person_id, book_id, borrow_TimeStamp, return_TimeStamp) values (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, borrow.getId());
        preparedStatement.setInt(2, borrow.getPerson().getId());
        preparedStatement.setInt(3, borrow.getBook().getId());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(borrow.getBorrowTimeStamp()));
        preparedStatement.setTimestamp(5, Timestamp.valueOf(borrow.getReturnTimeStamp()));
        preparedStatement.execute();
        return borrow;
    }

    @Override
    public Borrow edit(Borrow borrow) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "update borrow_tbl SET person_id=?, book_id=?, borrow_TimeStamp=?, return_TimeStamp=? where borrow_tbl.id=? "
        );
        preparedStatement.setInt(1, borrow.getPerson().getId());
        preparedStatement.setInt(2, borrow.getBook().getId());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(borrow.getBorrowTimeStamp()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(borrow.getReturnTimeStamp()));
        preparedStatement.setInt(5, borrow.getId());
        preparedStatement.execute();
        return borrow;
    }

    @Override
    public Borrow remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "delete from borrow_tbl where id=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Borrow> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Borrow> borrowList = new ArrayList<>();
        while (resultSet.next()) {
            Borrow borrow = Borrow
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person
                            .builder()
                            .id(resultSet.getInt("person_id"))
                            .name(resultSet.getString("person_name"))
                            .family(resultSet.getString("person_family"))
                            .build())
                    .book(Book
                            .builder()
                            .id(resultSet.getInt("book_id"))
                            .title(resultSet.getString("book_title"))
                            .author(resultSet.getString("book_author"))
                            .build())

                    .borrowTimeStamp(resultSet.getTimestamp("borrow_timeStamp").toLocalDateTime())
                    .returnTimeStamp(resultSet.getTimestamp("return_timestamp").toLocalDateTime())
                    .build();
            borrowList.add(borrow);
        }
        return borrowList;
    }

    @Override
    public Borrow findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT WHERE BORROW_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.executeQuery();
        Borrow borrow = null;
        while (resultSet.next()) {
            borrow = Borrow
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person
                            .builder()
                            .id(resultSet.getInt("person_id"))
                            .name(resultSet.getString("person_name"))
                            .family(resultSet.getString("person_family"))
                            .build())
                    .book(Book
                            .builder()
                            .id(resultSet.getInt("book_id"))
                            .title(resultSet.getString("book_title"))
                            .author(resultSet.getString("book_author"))
                            .build())

                    .borrowTimeStamp(resultSet.getTimestamp("borrow_timeStamp").toLocalDateTime())
                    .returnTimeStamp(resultSet.getTimestamp("return_timestamp").toLocalDateTime())
                    .build();
        }
        return borrow;
    }

    public List<Borrow> findByPersonId(int personId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, personId);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Borrow> borrowList = new ArrayList<>();
        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("person_id"))
                            .name(resultSet.getString("person_name"))
                            .family(resultSet.getString("person_family"))
                            .build();

            Book book =
                    Book
                            .builder()
                            .id(resultSet.getInt("book_id"))
                            .title(resultSet.getString("book_title"))
                            .author(resultSet.getString("book_author"))
                            .build();

            Borrow borrow = Borrow
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(person)
                    .book(book)
                    .borrowTimeStamp(resultSet.getTimestamp("borrow_timeStamp").toLocalDateTime())
                    .returnTimeStamp(resultSet.getTimestamp("return_timestamp").toLocalDateTime())
                    .build();
            borrowList.add(borrow);
        }
        return borrowList;
    }

    public List<Borrow> findByBookId(int bookId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_REPORT WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, bookId);
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Borrow> borrowList = new ArrayList<>();
        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("person_id"))
                            .name(resultSet.getString("person_name"))
                            .family(resultSet.getString("person_family"))
                            .build();

            Book book =
                    Book
                            .builder()
                            .id(resultSet.getInt("book_id"))
                            .title(resultSet.getString("book_title"))
                            .author(resultSet.getString("book_author"))
                            .build();

            Borrow borrow = Borrow
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(person)
                    .book(book)
                    .borrowTimeStamp(resultSet.getTimestamp("borrow_timeStamp").toLocalDateTime())
                    .returnTimeStamp(resultSet.getTimestamp("return_timestamp").toLocalDateTime())
                    .build();
            borrowList.add(borrow);
        }
        return borrowList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
