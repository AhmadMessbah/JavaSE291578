package mft.model.da;

import mft.model.da.impl.Da;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Person;
import mft.model.tools.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BorrowDa implements Da<Borrow> {
    PreparedStatement preparedStatement;

    @Override
    public Borrow save(Borrow borrow) throws Exception {
        DatabaseConnector.getConnection();
        preparedStatement = DatabaseConnector.getConnection().prepareStatement(
                "select borrow_seq.nextval as NEXT_BORROW_ID from dual"
        );

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        borrow.setId(resultSet.getInt("NEXT_BORROW_ID"));

        preparedStatement = DatabaseConnector.getConnection().prepareStatement(
                "insert into borrow_tbl(id,person_id,book_id,borrow_TimeStamp,return_TimeStamp) values (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, borrow.getId());
        preparedStatement.setInt(2, borrow.getPerson().getId());
        preparedStatement.setInt(3, borrow.getBook().getId());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(borrow.getBorrowTimeStamp()));
        preparedStatement.setTimestamp(5, Timestamp.valueOf(borrow.getReturnTimeStamp()));
        preparedStatement.execute();

        close();
        return borrow;
    }

    @Override
    public Borrow edit(Borrow borrow) throws Exception {
        // todo : change Edit Code
        DatabaseConnector.getConnection();

        preparedStatement = DatabaseConnector.getConnection().prepareStatement(
                "update borrow_tbl  SET    borrow_TimeStamp = ? , return_TimeStamp = ?  where  borrow_tbl.id =? "
        );
        preparedStatement.setTimestamp(1, Timestamp.valueOf(borrow.getBorrowTimeStamp()));
        preparedStatement.setTimestamp(2, Timestamp.valueOf(borrow.getReturnTimeStamp()));
        preparedStatement.setInt(3, borrow.getId());
        preparedStatement.execute();

        close();
        return borrow;
    }

    @Override
    public Borrow remove(int id) throws Exception {
        DatabaseConnector.getConnection();

        preparedStatement = DatabaseConnector.getConnection().prepareStatement(
                "delete from borrow_tbl  where id = ?  "
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        close();
        return null;
    }

    @Override
    public List<Borrow> findAll() throws Exception {
        DatabaseConnector.getConnection();

        preparedStatement = DatabaseConnector.getConnection().prepareStatement(
                "select * from borrow_tbl , person_tbl , book_tbl where borrow_tbl.person_id = person_tbl.id and borrow_tbl.book_id = book_tbl.id"

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
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .build())
                    .book(Book
                            .builder()
                            .id(resultSet.getInt("book_id"))
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .build())

                    .borrowTimeStamp(resultSet.getTimestamp("timeStamp").toLocalDateTime())
                    .returnTimeStamp(resultSet.getTimestamp("returnTimeStamp").toLocalDateTime())
                    .build();
            borrowList.add(borrow);
        }

        close();
        return borrowList;
    }

    @Override
    public Borrow findById(int id) throws Exception {
        DatabaseConnector.getConnection();

        preparedStatement = DatabaseConnector.getConnection().prepareStatement(
                "select * from borrow_tbl , person_tbl , book_tbl  where borrow_tbl.person_id = person_tbl.id and borrow_tbl.book_id = book_tbl.id and borrow_tbl.id = ?"
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
                            .name(resultSet.getString("name"))
                            .family(resultSet.getString("family"))
                            .build())
                    .book(Book
                            .builder()
                            .id(resultSet.getInt("book_id"))
                            .title(resultSet.getString("title"))
                            .author(resultSet.getString("author"))
                            .build())

                    .borrowTimeStamp(resultSet.getTimestamp("timeStamp").toLocalDateTime())
                    .returnTimeStamp(resultSet.getTimestamp("returnTimeStamp").toLocalDateTime())
                    .build();

        }

        close();
        return borrow;
    }

    private void close() throws Exception {
        preparedStatement.close();
        DatabaseConnector.getConnection().close();
    }
}
