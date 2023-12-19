package mft.model.repository;

import mft.model.entity.Receipt;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReceiptRepository {
    private Connection connection;
    private PreparedStatement statement;

    public void save(Receipt receipt) throws Exception {
        System.out.println("ReceiptRepository - save");
        connection = JdbcProvider.getConnection();
        statement = connection.prepareStatement(
                "SELECT RECEIPT_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        receipt.setId(resultSet.getInt("NEXT_ID"));

        statement = connection.prepareStatement(
                "INSERT INTO RECEIPT_TBL (ID,Amount,Description) VALUES (?,?,?)"
        );
        statement.setInt(1, receipt.getId());
        statement.setInt(2, receipt.getAmount());
        statement.setString(3, receipt.getDescription());
        statement.execute();
        close();

    }
    public void edit(Receipt receipt) throws Exception {
        System.out.println("ReceiptRepository - edit");
        connection = JdbcProvider.getConnection();
        statement = connection.prepareStatement(
"update receipt_tbl  SET Amount=?, Description=? WHERE ID=?"
        );
        statement.setInt(1, receipt.getAmount());
        statement.setString(2, receipt.getDescription());
        statement.setInt(3, receipt.getId());
        statement.execute();
        close();
    }


    public void remove(int id) throws Exception {
        connection = JdbcProvider.getConnection();
        statement = connection.prepareStatement(
                "DELETE FROM receipt_tbl WHERE ID=?"
        );
        statement.setInt(1, id);
        statement.execute();
        close();

    }


    public void findAll() throws Exception {
        connection = JdbcProvider.getConnection();
        statement = connection.prepareStatement(
                "SELECT * FROM receipt_tbl"
        );
        ResultSet resultSet = statement.executeQuery();

        List<Receipt> Receiptlist = new ArrayList<>();

        while (resultSet.next()) {
            Receipt receipt =
                    Receipt
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .amount(Integer.parseInt(resultSet.getString("Amount")))
                            .description(resultSet.getString("description"))
                            .build();
            Receiptlist.add(receipt);
        }

        close();

    }

 
    public void findById(int id) throws Exception {
        connection = JdbcProvider.getConnection();
        statement = connection.prepareStatement(
                "SELECT * FROM receipt_tbl WHERE ID=?"
        );
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Receipt receipt = null;
        while (resultSet.next()) {
            receipt =
                    Receipt
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .amount(Integer.parseInt(resultSet.getString("Amount")))
                            .description(resultSet.getString("description"))
                            .build();
        }
        close();

    }
    public void findByAmount(int amount) throws Exception {
        System.out.println("ReceiptRepository-FindByAmount");
        connection = JdbcProvider.getConnection();
    statement = connection.prepareStatement(
"SELECT * FROM receipt_tbl WHERE amount=?"
        );
        ResultSet resultSet = statement.executeQuery();
        Receipt receipt = null;
if (resultSet.next()){
            receipt =
 Receipt
.builder()
.id(resultSet.getInt("ID"))
.amount(Integer.parseInt(resultSet.getString("Amount")))
 .description(resultSet.getString("description"))
 .build();
        }
        close();
    }
    public Receipt findByDescription(String description) throws Exception {
        System.out.println("RecpectRepository - FindByDescraption");
        connection = JdbcProvider.getConnection();
        statement = connection.prepareStatement(
                "SELECT * FROM RECEIPT_TBL WHERE description=?"
        );
        statement.setString(1, description);
        ResultSet resultSet = statement.executeQuery();
        Receipt receipt = null;
        if (resultSet.next()) {
            receipt =
                    Receipt
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .amount(resultSet.getInt("ANOUNT"))
                            .description(resultSet.getString("Description"))
                            .build();

        }
        return receipt;

    }
    public void close() throws Exception{
        statement.close();
        connection.close();
    }}


