package mft.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.controller.UserController;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFrameController implements Initializable {
    @FXML
    private Button saveBtn, editBtn, removeBtn;

    @FXML
    private TextField idTxt,usernameTxt,passwordTxt;

    @FXML
    private ToggleGroup activeToggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        EventHandler saveEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                RadioButton radioButton= (RadioButton) activeToggleGroup.getSelectedToggle();
                UserController.getController().save(
                        usernameTxt.getText(),
                        passwordTxt.getText(),
                        (radioButton.getText().equals("Enable")?true:false));
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"User Saved");

            }
        };
        saveBtn.setOnAction(saveEventHandler);

        EventHandler editEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                RadioButton radioButton= (RadioButton) activeToggleGroup.getSelectedToggle();
                UserController.getController().edit(
                        Integer.valueOf(idTxt.getText()),
                        usernameTxt.getText(),
                        passwordTxt.getText(),
                        (radioButton.getText().equals("Enable")?true:false));
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"User Edited");
            }
        };
        editBtn.setOnAction(editEventHandler);

        EventHandler removeEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                UserController.getController().remove(
                        Integer.valueOf(idTxt.getText()));
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"User Removed");
            }
        };
        removeBtn.setOnAction(removeEventHandler);
    }
}
