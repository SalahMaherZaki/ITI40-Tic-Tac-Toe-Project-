package xo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BattleHistoryUI extends Pane {

    protected final Label BattleLabel;
    protected final Button BackBtn;
    
    //File Length & List Names
    int recordCounter;
    int spacesBetweenButtons = 50;

    public BattleHistoryUI() {

        BattleLabel = new Label();
        BackBtn = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BattleLabel.setLayoutX(20.0);
        BattleLabel.setLayoutY(30.0);
        BattleLabel.setText("Your Battle History");
        BattleLabel.setOpaqueInsets(new Insets(0.0));
        BattleLabel.setId("BattleLabel");
        BattleLabel.setPadding(new Insets(10.0, 0.0, 10.0, 150.0));
        
        
        BackBtn.setLayoutX(30.0);
        BackBtn.setLayoutY(540.0);
        BackBtn.setMnemonicParsing(false);
        BackBtn.setPrefHeight(51.0);
        BackBtn.setPrefWidth(90.0);
        BackBtn.setText("Back");
        BackBtn.setId("BackBtn");

        // Creates Buttons Represent Each TextFile
        Path games = Paths.get("C:\\XORecords");
        recordCounter = new File(games.toString()).list().length;
        Button[] buttons = new Button[recordCounter];
        //To List All Files in The Directory
        File repo = new File("C:\\XORecords");
        File[] fileList = repo.listFiles();
        for(int i = 0;i < recordCounter;i++)
        {
            buttons[i] = new Button();
            buttons[i].setLayoutX(25.0);
            buttons[i].setLayoutY(35.0 + spacesBetweenButtons);
            buttons[i].setMnemonicParsing(false);
            buttons[i].setPrefHeight(51.0);
            buttons[i].setPrefWidth(170.0);
            buttons[i].setStyle("-fx-background-radius: 15;");
            buttons[i].setFont(new Font("Baskerville Old Face", 24.0));
            
            buttons[i].setText(fileList[i].getName());
            //Add This Button To The Event Handler
            buttons[i].addEventHandler(ActionEvent.ACTION, e -> {
                getSelectedFileRecords(e);
            });
            spacesBetweenButtons += 60;
            getChildren().add(buttons[i]);
        }
        getChildren().add(BattleLabel);
        getChildren().add(BackBtn);
        //Set The Pane By Default Visible False
        this.setVisible(false);
        
        BackBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
            if(ProjectManger.vsComputer && !ProjectManger.onlineMode)
            {
                GamePane.newGame.fire();
                ProjectManger.viewPane(ProjectManger.homeSingle);
                GamePane.scoreOne.setText("0");
                GamePane.scoreTwo.setText("0");
                HomeUISingle.recordCheck.setSelected(false);
            }
            else if(!ProjectManger.vsComputer && !ProjectManger.onlineMode)
            {
                GamePane.newGame.fire();
                ProjectManger.viewPane(ProjectManger.homeMulti);
                GamePane.scoreOne.setText("0");
                GamePane.scoreTwo.setText("0");
                HomeUIMulti.recordCheck.setSelected(false);
            }
            else
            {
                GamePane.newGame.fire();
                ProjectManger.viewPane(ProjectManger.homeOnline);
                GamePane.scoreOne.setText("0");
                GamePane.scoreTwo.setText("0");
                HomeUIOnline.recordCheck.setSelected(false);
            }
            }
        });
        
    }
    public void getSelectedFileRecords(ActionEvent e)
    {
        
        ProjectManger.recordHasBeenStarted = true; // Up The Flag That The Record Has Been Started
        Button clickedButton = (Button) e.getSource();
        String FileName = clickedButton.getText();
        File selectedFile = new File("C:\\XORecords\\"+FileName);
        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            byte[] data = new byte[(int)selectedFile.length()];
            fis.read(data);
            String convertedData = new String(data);
            startShowRecord(convertedData);
            //System.out.println(convertedData);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BattleHistoryUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BattleHistoryUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProjectManger.viewPane(ProjectManger.gamePane);
    }
    public void hidePane(){this.setVisible(false);}
    private void startShowRecord(String s)
     {
        int counter = 0;
        StringTokenizer str = new StringTokenizer(s,",");
        while (str.hasMoreTokens()) 
        {
            counter += 1;
            String teststr = str.nextToken();
            if(teststr.equals("btn11"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn11.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn11.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn11.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn12"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn12.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn12.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn12.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn13"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn13.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn13.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn13.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn21"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn21.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn21.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn21.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn22"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn22.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn22.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn22.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn23"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn23.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn23.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn23.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn31"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn31.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn31.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn31.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn32"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn32.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn32.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn32.setText(anothertest));
                pause.play();
            }
            if(teststr.equals("btn33"))
            {
                String anothertest = str.nextToken();
                if(anothertest.equals("O"))
                    ProjectManger.gamePane.btn33.setTextFill(GamePane.oForeground);
                else
                    ProjectManger.gamePane.btn33.setTextFill(GamePane.xForeground);
                PauseTransition pause = new PauseTransition(Duration.seconds(1+counter));
                pause.setOnFinished(event ->
                ProjectManger.gamePane.btn33.setText(anothertest));
                pause.play();
            }
            
        }
     }
}
