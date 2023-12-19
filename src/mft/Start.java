package mft;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource(("view/ReceiptFrame.fxml")))
        );
        System.out.println("Receipt Infor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }}
