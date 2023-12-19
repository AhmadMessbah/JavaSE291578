package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mft.controller.UserController;
import mft.model.entity.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginUserFrameController implements Initializable {
    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button loginBtn;

    @FXML
    private Label msgLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBtn.setOnAction ((event) -> {
            try {
                User user = (User) UserController.getController().findByUsernameAndPassword(
                        usernameTxt.getText(),
                        passwordTxt.getText()
                );
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getResource("userFrame.fxml"))
                );

                stage.setScene(scene);
                stage.setTitle("User Information");
                stage.show();
                loginBtn.getParent().getScene().getWindow().hide();

            } catch (Exception e) {
                msgLbl.setVisible(true);
                Alert alert=new Alert(Alert.AlertType.ERROR ,"Error : "+ e.getMessage());
                alert.show();
            }
        });

    }
}
