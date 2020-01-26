package xo;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class XO extends Application {
    public static Button btn;
    public static Button btnOffline;
    public static String serverIP = "10.140.200.108";
    @Override
    public void start(Stage stage) throws Exception {
        btn = new Button();
        btnOffline = new Button();
        Pane root = new Pane();
        root.getChildren().add(ProjectManger.firstPage);
        root.getChildren().add(ProjectManger.singleNamePane);
        root.getChildren().add(ProjectManger.multiNamepane);
        root.getChildren().add(ProjectManger.loginPage);
        root.getChildren().add(ProjectManger.signupPage);
        root.getChildren().add(ProjectManger.homeSingle);
        root.getChildren().add(ProjectManger.homeMulti);
        root.getChildren().add(ProjectManger.homeOnline);
        root.getChildren().add(ProjectManger.battleHistory);
        root.getChildren().add(ProjectManger.gamePane);
        
        btn.setOnAction((ActionEvent event) -> {
            root.getChildren().add(GamePane.video);
        });
        btnOffline.setOnAction((ActionEvent event) -> {
            root.getChildren().add(GamePane.videoOffline);
        });
        Scene scene = new Scene(root);
        stage.setTitle("XO Project");
         scene.getStylesheets().add(getClass().getResource("XOCSS.css").toExternalForm());
          scene.getStylesheets().add(getClass().getResource("loginSignup.css").toExternalForm());
          scene.getStylesheets().add(getClass().getResource("game.css").toExternalForm());
        stage.setScene(scene);
       
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            ProjectManger.gamePane.exit.fire();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}