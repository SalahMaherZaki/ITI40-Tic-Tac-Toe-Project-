package xo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;


public  class SingleNamePane extends Pane {

    protected final Label label;
    protected final TextField singleUserName;
    protected final RadioButton radioButton;
    protected final RadioButton radioButton0;
    protected final Label label0;
    protected final Label label1;
    protected final Button singleOfflineStart;
    protected final ToggleGroup group;
     protected final Button backToFirstPage;
    protected final Label singleImage;
    public SingleNamePane() {

        label = new Label();
        singleUserName = new TextField();
        radioButton = new RadioButton();
        radioButton0 = new RadioButton();
        label0 = new Label();
        label1 = new Label();
        singleOfflineStart = new Button();
        group = new ToggleGroup();
        backToFirstPage=new Button();
        singleImage = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        
        label.setId("singleNameHeader");
        label.setLayoutX(200.0);
        label.setLayoutY(30.0);
        label.setText("You VS Pc");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        
        singleUserName.setId("singleName");
        singleUserName.setLayoutX(250.0);
        singleUserName.setLayoutY(180.0);
        singleUserName.setPrefHeight(50.0);
        singleUserName.setPrefWidth(200.0);

        radioButton.setId("x");
        radioButton.setLayoutX(270.0);
        radioButton.setLayoutY(293.0);
        radioButton.setMnemonicParsing(false);
        radioButton.setText("X");
        radioButton.setToggleGroup(group);
        radioButton.setSelected(true);
        
        radioButton0.setId("o");
        radioButton0.setLayoutX(397.0);
        radioButton0.setLayoutY(293.0);
        radioButton0.setMnemonicParsing(false);
        radioButton0.setText("O");
        radioButton0.setToggleGroup(group);
       
        label0.setId("enterName");
        label0.setLayoutX(80.0);
        label0.setLayoutY(177.0);
        label0.setPrefHeight(50.0);
        label0.setPrefWidth(200.0);
        label0.setText("Enter Your Name :");
        
        label1.setId("chooseMark");
        label1.setLayoutX(80.0);
        label1.setLayoutY(280.0);
        label1.setPrefHeight(50.0);
        label1.setPrefWidth(200.0);
        label1.setText("Choose your mark :");
        
        singleOfflineStart.setId("singleGameBtn");
        singleOfflineStart.setText("Start");
        singleOfflineStart.setMnemonicParsing(false);
         singleOfflineStart.setLayoutX(240.0);
        singleOfflineStart.setLayoutY(400.0);
        
        backToFirstPage.setId("backToFirstPage");
        backToFirstPage.setText("Back");
         backToFirstPage.setMnemonicParsing(false);
         backToFirstPage.setLayoutX(30.0);
        backToFirstPage.setLayoutY(540.0);
        
        singleImage.setId("singleImage");
        singleImage.setLayoutX(420.0);
        singleImage.setLayoutY(340.0);
        singleImage.setPrefHeight(250.0);
        singleImage.setPrefWidth(250.0);
        
        getChildren().add(label);
         getChildren().add(singleUserName);
         getChildren().add(radioButton);
         getChildren().add(radioButton0);
         getChildren().add(label0);
         getChildren().add(label1);
         getChildren().add(singleOfflineStart);
         getChildren().add(backToFirstPage);
         getChildren().add(singleImage);
        
        //Set The Pane By Default Visible False
        this.setVisible(false);
        singleOfflineStart.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.homeSingle);

            ProjectManger.viewPane(ProjectManger.homeSingle);
            HomeUISingle.SearchTextField.setDisable(true);
            HomeUISingle.SearchBtn.setDisable(true);
            HomeUISingle.ListItems.setDisable(true);
            HomeUISingle.playerTextField.setText("");
                
            if(radioButton.isSelected())
            {
                ProjectManger.homeSingle.playerTextField.setText(singleUserName.getText()); 
                ProjectManger.homeSingle.playerLabel.setText("Player X");
            }
            else{
                ProjectManger.homeSingle.playerTextField.setText(singleUserName.getText());
                ProjectManger.homeSingle.playerLabel.setText("Player O");
            }
           
        });
        backToFirstPage.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.firstPage);});
        
        
    }
}
