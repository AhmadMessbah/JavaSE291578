package mft.model.da;

import mft.model.da.impl.Da;
import mft.model.entity.Person;
import mft.model.tools.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDa implements Da<Person> {
    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Person save(Person person) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PERSON_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON_TBL(ID, NAME, FAMILY) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1,person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.execute();

        close();
        return person;
    }

    @Override
    public Person edit(Person person) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON_TBL SET NAME=?, FAMILY=? WHERE ID=?"
        );
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setInt(3, person.getId());
        preparedStatement.execute();

        close();
        return person;
    }

    @Override
    public Person remove(int id) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        close();
        return null;
    }

    @Override
    public List<Person> findAll() throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("Name"))
                            .family(resultSet.getString("FAMILY"))
                            .build();
            personList.add(person);
        }

        close();
        return personList;
    }

    @Override
    public Person findById(int id) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Person person = null;
        while (resultSet.next()) {
            person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("Name"))
                            .family(resultSet.getString("FAMILY"))
                            .build();
        }

        close();
        return person;
    }

    public List<Person> findByFamily( String family) throws Exception {
        connection = DatabaseConnector.getConnection();
        preparedStatement = connection.prepareStatement(
            "SELECT * FROM PERSON_TBL WHERE FAMILY=?"
        );
        preparedStatement.setString(1,family);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("Name"))
                            .family(resultSet.getString("FAMILY"))
                            .build();
            personList.add(person);
        }

        close();
        return personList;
    }
    
    private void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
