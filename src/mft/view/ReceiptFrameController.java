package mft.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.ReceiptController;
import mft.model.entity.Receipt;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ReceiptFrameController implements Initializable {
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private TextField idTxt, amountTxt, descriptionTxt,searchTxt;

    @FXML
    private TableView<Receipt> receiptTbl;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showDataOnTable(ReceiptController.getController().findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Save Error " + e.getMessage());
            alert.show();
        }
        saveBtn.setOnAction((event) -> {
            try {
                Receipt receipt = ReceiptController.getController().save(
                        Integer.parseInt(amountTxt.getText()),
                        descriptionTxt.getText()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Receipt Saved");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Save Error " + e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction((event) -> {
            try {
                Receipt receipt = ReceiptController.getController().edit(
                        Integer.parseInt(idTxt.getText()),
                        Integer.parseInt(amountTxt.getText()),
                        descriptionTxt.getText()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Receipt Edited");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Save Error " + e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction((event) -> {
            try {
                Receipt receipt = ReceiptController.getController().remove
                        (Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Receipt Removed");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Save Error " + e.getMessage());
                alert.show();
            }
        });
     receiptTbl.setOnMouseClicked((event) -> {
            Receipt receipt = receiptTbl.getSelectionModel().getSelectedItem();

            idTxt.setText(String.valueOf(receipt.getId()));
            amountTxt.setText(String.valueOf(receipt.getId()));
            descriptionTxt.setText(receipt.getDescription());
        });

        searchTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(Collections.singletonList(ReceiptController
                        .getController().findByAmount(Integer.valueOf(searchTxt.getText()))));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });
    }
    private void showDataOnTable(List<Receipt> receiptList) {

        ObservableList<Receipt> receipts = FXCollections.observableList(receiptList);

        TableColumn<Receipt, Integer> idCol = new TableColumn<>(" Id ");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Receipt, Integer> amountCol = new TableColumn<>(" Amount ");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("Amount"));

        TableColumn<Receipt, String> descriptionCol = new TableColumn<>(" description ");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        receiptTbl.getColumns().addAll(idCol, amountCol, descriptionCol);
        receiptTbl.setItems(receipts);
    }

    public void resetForm() {
        try {
            idTxt.clear();
            amountTxt.clear();
            descriptionTxt.clear();

            showDataOnTable(ReceiptController.getController().findAll());
//            todo : set nameTxt focused

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Data Load Error" + e.getMessage());
            alert.show();
        }
    }

}
