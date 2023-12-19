package mft.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.controller.UserController;
import mft.model.entity.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFrameController implements Initializable {
    @FXML
    private Button saveBtn, editBtn, removeBtn, searchBtn;

    @FXML
    private TextField idTxt, usernameTxt, searchTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private ToggleGroup activeToggleGroup;

    @FXML
    private Label msgLbl;

    @FXML
    private TableView<User> userTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        saveBtn.setOnAction((event) -> {
            try {
                RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();
                User user = UserController.getController().save(
                        usernameTxt.getText(),
                        passwordTxt.getText(),
                        (radioButton.getText().equals("Enable") ? true : false));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Saved");
                alert.show();
                resetForm();

            } catch (Exception e) {
                msgLbl.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

        editBtn.setOnAction((event) -> {
            try {
                RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();
                User user = UserController.getController().edit(
                        Integer.valueOf(idTxt.getText()),
                        usernameTxt.getText(),
                        passwordTxt.getText(),
                        (radioButton.getText().equals("Enable") ? true : false));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Edited");
                alert.show();
                resetForm();

            } catch (Exception e) {
                msgLbl.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Edit Error " + e.getMessage());
                alert.show();
            }
        });

        removeBtn.setOnAction((event) -> {
            try {
                User user = UserController.getController().remove(Integer.valueOf(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Removed");
                alert.show();
                resetForm();

            } catch (Exception e) {
                msgLbl.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Remove Error " + e.getMessage());
                alert.show();
            }
        });

        userTbl.setOnMouseClicked((event) -> {
            RadioButton radioButton = (RadioButton) activeToggleGroup.getSelectedToggle();
            User user = userTbl.getSelectionModel().getSelectedItem();

            idTxt.setText(String.valueOf(user.getId()));
            usernameTxt.setText(user.getUsername());
            passwordTxt.setText(user.getPassword());
//            radioButton.setToggleGroup(user.isActive());

        });
        searchTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(UserController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });

        searchBtn.setOnAction((event) -> {
            try {
                showDataOnTable(UserController.getController().findByAll(searchTxt.getText()));

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "not found" + e.getMessage());
                alert.show();
            }
        });
    }
        private void showDataOnTable(List<User> userList) {

            ObservableList<User> users = FXCollections.observableList(userList);

            userTbl.getColumns().clear();

            TableColumn<User, Integer> idCol = new TableColumn<>("Id");
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn<User, String> usernameCol = new TableColumn<>("Username");
            usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

            TableColumn<User, String> passwordCol = new TableColumn<>("Password");
            passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

            TableColumn<User, Boolean> activeCol = new TableColumn<>("Active");
            activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

            userTbl.getColumns().addAll(idCol, usernameCol, passwordCol, activeCol);
            userTbl.setItems(users);
        }


        public void resetForm(){
            try {
                idTxt.clear();
                usernameTxt.clear();
                passwordTxt.clear();
                activeToggleGroup.getSelectedToggle();
                msgLbl.setVisible(false);

                showDataOnTable(UserController.getController().findAll());

            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
                alert.show();
            }
        }


        }

