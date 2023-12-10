package mft.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textField = new TextField();

        Button saveBtn = new Button("Save");
        Button editBtn = new Button("Edit");

        editBtn.setOnAction((event)->{
            System.out.println(textField.getText());
        });

        VBox hBox = new VBox();
        hBox.getChildren().add(saveBtn);
        hBox.getChildren().add(editBtn);
        hBox.getChildren().add(textField);

        Group group = new Group();
        group.getChildren().add(hBox);

        Scene scene = new Scene(group, 300,300);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("First App");
        stage.show();
    }
}
