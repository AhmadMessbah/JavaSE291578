package mft.model.repository;

import mft.model.entity.enums.Gender;
import mft.model.repository.impl.Da;
import mft.model.entity.Person;
import mft.model.tools.JdbcProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDa implements Da<Person>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Person save(Person person) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PERSON_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON_TBL(ID, NAME, FAMILY, BIRTH_DATE, GENDER, ACTIVE) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setDate(4, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(5, person.getGender().name());
        preparedStatement.setBoolean(6, person.isActive());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person edit(Person person) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON_TBL SET NAME=?, FAMILY=?, BIRTH_DATE=?, GENDER=?, ACTIVE=? WHERE ID=?"
        );
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setDate(3, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(4, person.getGender().name());
        preparedStatement.setBoolean(5, person.isActive());
        preparedStatement.setInt(6, person.getId());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Person> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
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
                            .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                            .gender(Gender.valueOf(resultSet.getString("GENDER").trim()))
                            .active(resultSet.getBoolean("ACTIVE"))
                            .build();
            personList.add(person);
        }
        return personList;
    }

    @Override
    public Person findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
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
                            .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                            .gender(Gender.valueOf(resultSet.getString("GENDER").trim()))
                            .active(resultSet.getBoolean("ACTIVE"))
                            .build();
        }
        return person;
    }

    public List<Person> findByFamily(String family) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON_TBL WHERE FAMILY LIKE ?"
        );
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("Name"))
                            .family(resultSet.getString("FAMILY"))
                            .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                            .gender(Gender.valueOf(resultSet.getString("GENDER").trim()))
                            .active(resultSet.getBoolean("ACTIVE"))
                            .build();
            personList.add(person);
        }
        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
