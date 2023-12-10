package mft.model.repository;

import mft.model.repository.impl.Da;
import mft.model.entity.User;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDa implements Da<User>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public User save(User user) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        System.out.println("UserRepository - Save");
        preparedStatement = connection.prepareStatement(
                "SELECT USER_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        user.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO USER_TBL(ID, USERNAME, PASSWORD, ACTIVE) VALUES (?,?,?,?)"
        );

        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setBoolean(4, user.isActive());
        preparedStatement.execute();
        return user;
    }

    @Override
    public User edit(User user) throws Exception {
        System.out.println("UserRepository - Edit");
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE USER_TBL SET USERNAME=? ,PASSWORD=? ,ACTIVE=? WHERE ID=?"
        );

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setBoolean(3, user.isActive());
        preparedStatement.setInt(4, user.getId());
        preparedStatement.execute();
        return user;
    }

    @Override
    public User remove(int id) throws Exception {
        System.out.println("UserRepository - Remove");
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM USER_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<User> findAll() throws Exception {
        System.out.println("UserRepository - FindAll");
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            User user =
                    User
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .active(resultSet.getBoolean("ACTIVE"))
                            .build();
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User findById(int id) throws Exception {
        System.out.println("UserRepository - FindById");
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        while (resultSet.next()) {
            user =
                    User
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .active(resultSet.getBoolean("ACTIVE"))
                            .build();
        }
        return user;
    }

    public User findByUsername(String username) throws Exception {
        System.out.println("UserRepository - FindByUsername");
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME=?"
        );
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;
        while (resultSet.next()) {
            user =
                    User
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .active(resultSet.getBoolean("ACTIVE"))
                            .build();
        }
        return user;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
