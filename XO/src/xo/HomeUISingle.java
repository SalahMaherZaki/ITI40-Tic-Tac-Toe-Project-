package xo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomeUISingle extends Pane {

    protected final Button HistoryBtn;
    protected final Label playerLabel;
    protected static TextField playerTextField;
    protected final Label label;
    public static CheckBox recordCheck;
    protected final Button StartBtn;
    protected final Button BackBtn;
    protected static TextField SearchTextField;
    protected static Button SearchBtn;
    protected static ListView ListItems;

    public HomeUISingle() {

        
        HistoryBtn = new Button();
        playerLabel = new Label();
        playerTextField = new TextField();
        label = new Label();
        recordCheck = new CheckBox();
        StartBtn = new Button();
        BackBtn = new Button();
        SearchTextField = new TextField();
        SearchBtn = new Button();
        ListItems = new ListView();

         HistoryBtn.setLayoutX(35.0);
        HistoryBtn.setLayoutY(280.0);
        HistoryBtn.setMnemonicParsing(false);
        HistoryBtn.setPrefHeight(68.0);
        HistoryBtn.setPrefWidth(280.0);
        HistoryBtn.setText("Battle History");
        HistoryBtn.setId("HistoryBtn");

         playerLabel.setLayoutX(35.0);
        playerLabel.setLayoutY(85.0);
        playerLabel.setPrefHeight(34.0);
        playerLabel.setPrefWidth(121.0);
        playerLabel.setText("Name");
        playerLabel.setId("singlePlayerName");
        playerLabel.setFont(new Font(60.0));
        
        playerTextField.setDisable(true);
        playerTextField.setEditable(false);
        playerTextField.setLayoutX(169.0);
        playerTextField.setLayoutY(87.0);
        playerTextField.setPrefHeight(40.0);
        playerTextField.setPrefWidth(160.0);
 
        label.setLayoutX(33.0);
        label.setLayoutY(255.0);
        label.setFont(new Font(18.0));

       recordCheck.setLayoutX(35.0);
        recordCheck.setLayoutY(180.0);
        recordCheck.setMnemonicParsing(false);
        recordCheck.setText("Do you want to record ?");
        recordCheck.setId("singlePlayerCheckBox");

        recordCheck.setOnAction((ActionEvent event) -> {
            if(recordCheck.isSelected())
                ProjectManger.startRecordGame = true;
        });

         StartBtn.setLayoutX(130.0);
        StartBtn.setLayoutY(410.0);
        StartBtn.setMnemonicParsing(false);
        StartBtn.setId("StartBtn");
        StartBtn.setText("Start Game");

        BackBtn.setLayoutX(30.0);
        BackBtn.setLayoutY(540.0);
        BackBtn.setMnemonicParsing(false);
        BackBtn.setId("BackBtn");
        BackBtn.setText("Back");
        BackBtn.setOnAction((ActionEvent event) -> {
            if(ProjectManger.vsComputer)
                ProjectManger.viewPane(ProjectManger.singleNamePane);
        });

   
        SearchTextField.setLayoutX(500.0);
        SearchTextField.setLayoutY(30.0);
        SearchTextField.setPrefWidth(179.0);
        SearchTextField.setId("SearchTextField");
        SearchTextField.setDisable(true);
        SearchTextField.setEditable(false);
        
        SearchBtn.setLayoutX(500.0);
        SearchBtn.setLayoutY(80.0);
        SearchBtn.setMnemonicParsing(false);
        SearchBtn.setText("Players");
        SearchBtn.setId("SearchBtn");
        SearchBtn.setDisable(true);
        
        ListItems.setLayoutX(500.0);
        ListItems.setLayoutY(150.0);
        ListItems.setId("ListItems");
         ListItems.setDisable(true);
        ListItems.setEditable(false);
        
        getChildren().add(HistoryBtn);
         getChildren().add(playerLabel);
         getChildren().add(playerTextField);
         getChildren().add(recordCheck);
         getChildren().add(StartBtn);
         getChildren().add(BackBtn);
         //getChildren().add(SearchTextField);
         getChildren().add(SearchBtn);
         getChildren().add(ListItems);
       
        
        //Set The Pane By Default Visible False
        this.setVisible(false);
       StartBtn.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.gamePane);
            if(ProjectManger.singleNamePane.radioButton.isSelected()){
                ProjectManger.gamePane.playerOne.setText(playerTextField.getText());
                ProjectManger.gamePane.playerTwo.setText("PC");
            }
            else{
                ProjectManger.gamePane.playerOne.setText("PC");
                ProjectManger.gamePane.playerTwo.setText(playerTextField.getText());
            }
            
        });
       
        HistoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProjectManger.viewPane(ProjectManger.battleHistory);
            }
        });
         
    }
}
