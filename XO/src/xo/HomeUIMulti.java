package xo;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class HomeUIMulti extends Pane {

    protected final Button HistoryBtn;
    protected final Label playerOneLabel;
    protected final TextField playerOneTextField;
    protected final Label label;
    public static CheckBox recordCheck;
    protected final Button StartBtn;
    protected final Button BackBtn;
    protected final Label playerTwoLabel;
    protected final TextField playerTwoTextField;
    protected final TextField SearchTextField;
    protected final Button SearchBtn;
    protected final ListView ListItems;

    public HomeUIMulti() {

        HistoryBtn = new Button();
        playerOneLabel = new Label();
        playerOneTextField = new TextField();
        label = new Label();
        recordCheck = new CheckBox();
        StartBtn = new Button();
        BackBtn = new Button();
        playerTwoLabel = new Label();
        playerTwoTextField = new TextField();
        SearchTextField = new TextField();
        SearchBtn = new Button();
        ListItems = new ListView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
  
        
        HistoryBtn.setLayoutX(35.0);
        HistoryBtn.setLayoutY(300.0);
        HistoryBtn.setMnemonicParsing(false);
        HistoryBtn.setPrefHeight(68.0);
        HistoryBtn.setPrefWidth(280.0);
        HistoryBtn.setText("Battle History");
        HistoryBtn.setId("HistoryBtn");

        playerOneLabel.setLayoutX(35.0);
        playerOneLabel.setLayoutY(79.0);
        playerOneLabel.setPrefHeight(34.0);
        playerOneLabel.setPrefWidth(121.0);
        playerOneLabel.setText("Player X");
        playerOneLabel.setId("playerOneLabel");

        playerOneTextField.setDisable(true);
        playerOneTextField.setEditable(false);
        playerOneTextField.setLayoutX(169.0);
        playerOneTextField.setLayoutY(80.0);
        playerOneTextField.setPrefHeight(40.0);
        playerOneTextField.setPrefWidth(146.0);

        playerTwoLabel.setLayoutX(35.0);
        playerTwoLabel.setLayoutY(139.0);
        playerTwoLabel.setPrefHeight(34.0);
        playerTwoLabel.setPrefWidth(121.0);
        playerTwoLabel.setText("Player O");
        playerTwoLabel.setId("playerTwoLabel");
        
        playerTwoTextField.setDisable(true);
        playerTwoTextField.setEditable(false);
        playerTwoTextField.setLayoutX(169.0);
        playerTwoTextField.setLayoutY(140.0);
        playerTwoTextField.setPrefHeight(40.0);
        playerTwoTextField.setPrefWidth(146.0);

        recordCheck.setLayoutX(35.0);
        recordCheck.setLayoutY(220.0);
        recordCheck.setMnemonicParsing(false);
        recordCheck.setText("Do you want to record ?");
        recordCheck.setId("multiPlayerCheckBox");


        StartBtn.setLayoutX(120.0);
        StartBtn.setLayoutY(430.0);
        StartBtn.setMnemonicParsing(false);
        StartBtn.setId("StartBtn");
        StartBtn.setText("Start Game");
        
        BackBtn.setLayoutX(30.0);
        BackBtn.setLayoutY(540.0);
        BackBtn.setMnemonicParsing(false);
        BackBtn.setId("BackBtn");
        BackBtn.setText("Back");
        BackBtn.setOnAction((ActionEvent event) -> {
            if(!ProjectManger.onlineMode && !ProjectManger.vsComputer)
                ProjectManger.viewPane(ProjectManger.multiNamepane);
        });
        
        SearchTextField.setLayoutX(500.0);
        SearchTextField.setLayoutY(30.0);
        SearchTextField.setId("SearchTextField");

        SearchBtn.setLayoutX(500.0);
        SearchBtn.setLayoutY(80.0);
        SearchBtn.setMnemonicParsing(false);
        SearchBtn.setText("Players");
        SearchBtn.setId("SearchBtn");

        ListItems.setLayoutX(500.0);
        ListItems.setLayoutY(150.0);
        ListItems.setId("ListItems");
        
        getChildren().add(label);
         getChildren().add(HistoryBtn);
         getChildren().add(playerOneLabel);
         getChildren().add(playerOneTextField);
          getChildren().add(playerTwoLabel);
          getChildren().add(playerTwoTextField);
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
            ProjectManger.gamePane.playerOne.setText(playerOneTextField.getText());
            ProjectManger.gamePane.playerTwo.setText(playerTwoTextField.getText());
            ProjectManger.recordHasBeenStarted = false;
        });
        HistoryBtn.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.battleHistory);
            
            
        });
        recordCheck.setOnAction((ActionEvent event) -> {
            if(recordCheck.isSelected())
                ProjectManger.startRecordGame = true;
        });
    }
}
