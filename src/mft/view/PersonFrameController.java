package mft.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import mft.controller.PersonController;
import mft.model.entity.enums.Gender;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonFrameController implements Initializable {
    @FXML
    private Button saveBtn,editBtn,removeBtn;

    @FXML
    private TextField idTxt,nameTxt,familyTxt;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ToggleGroup activeToggleGroup;

    @FXML
    private ComboBox<String> genderCmb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Gender value : Gender.values()) {
            genderCmb.getItems().add(value.name());
        }
        genderCmb.getSelectionModel().select(0);

        EventHandler saveEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();

                PersonController.getController().save(
                        nameTxt.getText(),
                        familyTxt.getText(),
                        birthDate.getValue(),
                        Gender.valueOf(genderCmb.getSelectionModel().getSelectedItem()),
                        (radioButton.getText().equals("Enable")?true:false)
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Saved");
            }
        };

        saveBtn.setOnAction(saveEventHandler);

        EventHandler editEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();

                PersonController.getController().edit(
                        Integer.valueOf(idTxt.getText()),
                        nameTxt.getText(),
                        familyTxt.getText(),
                        birthDate.getValue(),
                        Gender.valueOf(genderCmb.getSelectionModel().getSelectedItem()),
                        (radioButton.getText().equals("Enable")?true:false)
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Edit");
            }
        };
        editBtn.setOnAction(editEventHandler);
        EventHandler romoveEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {

                PersonController.getController().remove(
                        Integer.valueOf(idTxt.getText())
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person Remove");
            }
        };
        removeBtn.setOnAction(romoveEventHandler);
    }
}
