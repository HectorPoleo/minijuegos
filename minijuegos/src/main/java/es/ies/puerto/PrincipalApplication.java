package es.ies.puerto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author hectorpoleo
 * @version 1.0.0
 */
public class PrincipalApplication extends Application {
    private static Stage currentStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pantalla Principal");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void main(String[] args) {
        launch();
    }
}