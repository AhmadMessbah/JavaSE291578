package mft.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.controller.ReceiptController;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceiptFrameController implements Initializable {
    @FXML
    private Button saveBtn, editBtn, removeBtn;
    @FXML
    private TextField idTxt, amountTxt, descriptionTxt;

    @FXML
    private ToggleGroup activeToggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler saveEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();
                ReceiptController.getController().save(
                        Integer.parseInt(amountTxt.getText()),
                        descriptionTxt.getText(),
                        (radioButton.getText().equals("Enable")
                        )
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Receipt Saved");

            }
        };
        saveBtn.setOnAction(saveEventHandler);

        EventHandler editEventHandler = new EventHandler() {
            public void handle(Event event) {
                RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();
                ReceiptController.getController().edit(
                        Integer.valueOf(idTxt.getText()),
                        Integer.parseInt(amountTxt.getText()),
                        descriptionTxt.getText(),
                        (radioButton.getText().equals("Enable")
                        )
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Receipt Edit");
            }
        };
        editBtn.setOnAction(editEventHandler);

        EventHandler romoveEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {

              ReceiptController.getController().remove(
                        Integer.valueOf(idTxt.getText())
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Receipt Remove");
            }
        };
        removeBtn.setOnAction(romoveEventHandler);
    }
}
