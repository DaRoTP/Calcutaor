package mainCalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/standardCalculator.fxml"));
        primaryStage.setTitle("Calculator");
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll("view/stylesheets/style.css");
        Image logo_icon = new Image("view/images/icon.png");
        primaryStage.getIcons().add(logo_icon);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
