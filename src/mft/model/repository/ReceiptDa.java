package mft.model.repository;

import mft.model.entity.Receipt;
import mft.model.repository.impl.Da;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDa implements Da<Receipt> {
    private static PreparedStatement preparedStatement;
    private static Connection connection;


    public  Receipt save(Receipt receipt) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT RECEIPT_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        receipt.setId(resultSet.getInt("NEXT_ID"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO RECEIPT_TBL(ID, AMOUNT, DESCRIPTION) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1,receipt.getId());
        preparedStatement.setString(2, receipt.getAmount());
        preparedStatement.setString(3, receipt.getDescription());
        preparedStatement.execute();

        close();
        return receipt;
    }



    @Override
    public Receipt edit(Receipt receipt) throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE RECEIPT_TBL SET AMOUNT=?, DESCRIPTION=? WHERE ID=?"
        );

        preparedStatement.setString(1, receipt.getAmount());
        preparedStatement.setString(2, receipt.getDescription());
        preparedStatement.setInt(3, receipt.getId());
        preparedStatement.execute();
        close();
        return receipt;
    }

    @Override
    public Receipt remove(int id) throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM receipt_tbl WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        close();
        return null;
    }

    @Override
    public List<Receipt> findAll() throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM receipt_tbl"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Receipt> Receiptlist = new ArrayList<>();

        while (resultSet.next()) {
            Receipt receipt =
                    Receipt
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .amount(resultSet.getString("Amount"))
                            .description(resultSet.getString("description"))
                            .build();
            Receiptlist.add(receipt);
        }

        close();
        return Receiptlist;
    }

    @Override
    public Receipt findById(int id) throws Exception {
        connection = JdbcProvider.getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM receipt_tbl WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Receipt receipt = null;
        while (resultSet.next()) {
            receipt =
                    Receipt
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .amount(resultSet.getString("Amount"))
                            .description(resultSet.getString("description"))
                            .build();
        }
        close();
        return receipt;
    }

    private static void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }




}


