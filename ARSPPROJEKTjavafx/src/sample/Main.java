package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindowLayout.fxml"));

        primaryStage.setTitle("My App");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 640, 405));

        primaryStage.show();

    }

}
