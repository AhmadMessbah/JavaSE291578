package mft.model.da;

import mft.model.da.impl.Da;
import mft.model.entity.Person;
import mft.model.entity.Stuff;
import mft.model.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuffDa implements Da<Stuff> {
    private PreparedStatement preparedStatement;
    private Connection connection;
    @Override
    public Stuff save(Stuff stuff) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
          "select STUFF_SEQ.nextval as stuff_id from DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        stuff.setId(resultSet.getInt("stuff_id"));

        preparedStatement = connection.prepareStatement(
                "insert into STUFF_TBL(ID, NAME, BRAND, GROUP_TITLE) values (?,?,?,?)"
        );
        preparedStatement.setInt(1,stuff.getId());
        preparedStatement.setString(2,stuff.getName());
        preparedStatement.setString(3,stuff.getBrand());
        preparedStatement.setString(4,stuff.getGroupTitle());
        preparedStatement.execute();

        close();
        return stuff;
    }

    @Override
    public Stuff edit(Stuff stuff) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
          "update STUFF_TBL set NAME = ? , BRAND = ? , GROUP_TITLE = ? where ID = ?"
        );
        preparedStatement.setString(1,stuff.getName());
        preparedStatement.setString(2,stuff.getBrand());
        preparedStatement.setString(3,stuff.getGroupTitle());
        preparedStatement.setInt(4,stuff.getId());

        preparedStatement.execute();
        close();

        return stuff;
    }

    @Override
    public Stuff remove(int id) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "delete from STUFF_TBL where ID = ?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        close();
        return null;
    }

    @Override
    public List<Stuff> findAll() throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from STUFF_TBL"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Stuff> stuffList = new ArrayList<>();
        while (resultSet.next()){
            Stuff stuff =
                    Stuff
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("Name"))
                            .brand(resultSet.getString("BRAND"))
                            .brand(resultSet.getString("GROUP_TITLE"))
                            .build();
            stuffList.add(stuff);

        }
        close();
        return stuffList;
    }

    @Override
    public Stuff findById(int id) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
          "select * from STUFF_TBL where ID = ?"
        );
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Stuff stuff = null;
        while (resultSet.next()) {
            stuff =
                    Stuff
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("Name"))
                            .brand(resultSet.getString("BRAND"))
                            .brand(resultSet.getString("GROUP_TITLE"))
                            .build();
        }
        close();
        return stuff;

    }

    public List<Stuff> findByGroupTitle(String groupTitle)  throws Exception{
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "select * from STUFF_TBL where GROUP_TITLE = ?"
        );
        preparedStatement.setString(1,groupTitle);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Stuff> stuffList = new ArrayList<>();
        while (resultSet.next()){
            Stuff stuff =
                    Stuff
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("Name"))
                            .brand(resultSet.getString("BRAND"))
                            .brand(resultSet.getString("GROUP_TITLE"))
                            .build();
            stuffList.add(stuff);

        }
        close();
        return stuffList;
    }
    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
