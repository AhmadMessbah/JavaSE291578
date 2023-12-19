package mft.view;

<<<<<<< HEAD
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Person;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class borrowFrameController implements Initializable {
    @FXML
    private Button saveBtn,editBtn,removeBtn;

    @FXML
    private TextField personTxt,bookTxt;

    @FXML
    private DatePicker borrowDate,returnDate;

    @FXML
    private TableView<Borrow> borrowTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveBtn.setOnAction(event -> {});
        editBtn.setOnAction(event -> {});
        removeBtn.setOnAction(event -> {});
    }

    public void showDataBorrow(List<Borrow> borrowList){
        ObservableList<Borrow> borrows = FXCollections.observableList(borrowList);

        TableColumn<Borrow , Integer> idcol = new TableColumn<>("Id");
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Borrow , Person> personCol = new TableColumn<>("Person");
        idcol.setCellValueFactory(new PropertyValueFactory<>("person"));

        TableColumn<Borrow , Book> bookCol = new TableColumn<>("Book");
        idcol.setCellValueFactory(new PropertyValueFactory<>("book"));

        TableColumn<Borrow , Date> borrowDateCol = new TableColumn<>("Borrow Date");
        idcol.setCellValueFactory(new PropertyValueFactory<>("borrowTimeStamp"));

        TableColumn<Borrow , Date> returnDateCol = new TableColumn<>("Return Date");
        idcol.setCellValueFactory(new PropertyValueFactory<>("returnTimeStamp"));

        borrowTbl.getColumns().addAll(idcol,personCol,bookCol,borrowDateCol,returnDateCol);
        borrowTbl.setItems(borrows);
=======
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class borrowFrameController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

>>>>>>> origin/master
    }
}
