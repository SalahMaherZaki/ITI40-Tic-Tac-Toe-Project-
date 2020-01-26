package xo;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;
import static xo.GamePane.ps;
import static xo.HomeUIOnline.ListItems;
import static xo.LogInPage.playerName;

public class videoOffline extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Button Continue;
    public static Button Back;
    protected final Label playagain;
    protected final MediaView videoarea;
    public static Button fireBack;
    //=Server
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    String recieveData, data;
    public videoOffline(String path) {

        Continue = new Button();
        Back=new Button();
        fireBack=new Button();
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        videoarea = new MediaView(mediaPlayer);
        videoarea.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        anchorPane = new AnchorPane();
        setId("AnchorPane");
        setPrefHeight(376.0);
        setPrefWidth(520.0);

        anchorPane.setPrefHeight(300.0);
        anchorPane.setPrefWidth(400.0);
        
        playagain=new Label();
        playagain.setLayoutX(50.0);
        playagain.setLayoutY(485.0);
        playagain.setMnemonicParsing(false);
        playagain.setPrefHeight(44.0);
        playagain.setPrefWidth(220.0);
        playagain.setText("Play Again?");
        playagain.setId("videoQ");
        
        Continue.setLayoutX(260.0);
        Continue.setLayoutY(470.0);
        Continue.setMnemonicParsing(false);
        Continue.setPrefHeight(44.0);
        Continue.setPrefWidth(90.0);
        Continue.setText("YES");
        Continue.setId("videoContinue");
        
        Back.setLayoutX(400.0);
         Back.setLayoutY(470.0);
         Back.setMnemonicParsing(false);
         Back.setPrefHeight(44.0);
         Back.setPrefWidth(90.0);
         Back.setText("NO");
         Back.setId("videoBack");
        
        fireBack.setLayoutX(630.0);
        fireBack.setLayoutY(580.0);
        fireBack.setMnemonicParsing(false);
        fireBack.setPrefHeight(44.0);
        fireBack.setPrefWidth(90.0);
        fireBack.setText("NO");
         
        getChildren().add(videoarea);
        getChildren().add(Continue);
        getChildren().add(playagain);
        getChildren().add(Back);
        
        videoarea.setFitHeight(330.0);
        videoarea.setFitWidth(500.0);
        videoarea.setLayoutX(100.0);
        videoarea.setLayoutY(80);
        videoarea.setPreserveRatio(false);

        anchorPane.getChildren().add(Continue);
        anchorPane.getChildren().add(videoarea);
        anchorPane.getChildren().add(Back);
        getChildren().add(anchorPane);
        
        ProjectManger.gamePane.setVisible(false);
        Back.setOnAction((ActionEvent event) -> {
            this.setVisible(false);
            mediaPlayer.setMute(true);
            if(ProjectManger.vsComputer)
            {
                GamePane.newGame.fire();
                ProjectManger.viewPane(ProjectManger.homeSingle);
                GamePane.scoreOne.setText("0");
                GamePane.scoreTwo.setText("0");
                HomeUISingle.recordCheck.setSelected(false);
           

            }
            else if(!ProjectManger.vsComputer & !ProjectManger.onlineMode)
            {
                GamePane.newGame.fire();
                ProjectManger.viewPane(ProjectManger.homeMulti);
                GamePane.scoreOne.setText("0");
                GamePane.scoreTwo.setText("0");
                HomeUIMulti.recordCheck.setSelected(false);
            }
        });
        Continue.setOnAction((ActionEvent event) -> {
            if(!ProjectManger.onlineMode)
            {
                this.setVisible(false);
                mediaPlayer.setMute(true);
                GamePane.newGame.fire();
                ProjectManger.viewPane(ProjectManger.gamePane);
            }
        });
    }
}
