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
import javafx.scene.text.Font;

public  class MultiNamesPane extends Pane {

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Button MultiOfflineStart;
    protected final TextField firstNameOffline;
    protected final TextField secondNameOffline;
    protected final Button backToFirstPage;
    protected final RadioButton radioButton;
    protected final RadioButton radioButton0;
    protected final ToggleGroup group;
    public static String firstKick;

    public MultiNamesPane(){

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        MultiOfflineStart = new Button();
        firstNameOffline = new TextField();
        secondNameOffline = new TextField();
        radioButton = new RadioButton();
        radioButton0 = new RadioButton();
        group = new ToggleGroup();
        backToFirstPage= new Button();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        
        label.setId("multiNameHeader");
        label.setLayoutX(50.0);
        label.setLayoutY(30.0);
        label.setText("Player1 VS Player2");
        label.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        label.setPadding(new Insets(10.0, 0.0, 10.0, 150.0));    

       label0.setId("multiFirstPlayer");
        label0.setLayoutX(50.0);
        label0.setLayoutY(180.0);
        label0.setText("First Player Name :");

        label1.setId("multiSecondPlayer");
        label1.setLayoutX(50.0);
        label1.setLayoutY(260.0);
        label1.setText("Second Player Name :");

    
        label2.setId("multiFirstMark");
        label2.setLayoutX(50.0);
        label2.setLayoutY(330.0);
        label2.setText("First Player  Mark ");
     
        firstNameOffline.setId("firstPlayerName");
        firstNameOffline.setLayoutX(300.0);
        firstNameOffline.setLayoutY(170.0);
        firstNameOffline.setPrefHeight(45.0);
        firstNameOffline.setPrefWidth(280.0);

        secondNameOffline.setId("secondPlayerName");
        secondNameOffline.setLayoutX(300.0);
        secondNameOffline.setLayoutY(250.0);
        secondNameOffline.setPrefHeight(45.0);
        secondNameOffline.setPrefWidth(280.0);

        radioButton.setId("x");
        radioButton.setLayoutX(300.0);
        radioButton.setLayoutY(330.0);
        radioButton.setMnemonicParsing(false);
        radioButton.setText("X");
        radioButton.setToggleGroup(group);
        radioButton.setSelected(true);
        
        radioButton0.setId("o");
        radioButton0.setLayoutX(450.0);
        radioButton0.setLayoutY(330.0);
        radioButton0.setMnemonicParsing(false);
        radioButton0.setText("O");
        radioButton0.setToggleGroup(group);

        MultiOfflineStart.setId("twoPlayerGameBtn");
        MultiOfflineStart.setMnemonicParsing(false);
        MultiOfflineStart.setLayoutX(250.0);
        MultiOfflineStart.setLayoutY(450.0);
        MultiOfflineStart.setText("Start");
        
         backToFirstPage.setId("backToFirstPage");
         backToFirstPage.setMnemonicParsing(false);
         backToFirstPage.setLayoutX(30.0);
        backToFirstPage.setLayoutY(540.0);
        backToFirstPage.setText("Back");
        
        getChildren().add(label);
         getChildren().add(label0);
         getChildren().add(label1);
         getChildren().add(label2);
         getChildren().add(MultiOfflineStart);
         getChildren().add(firstNameOffline);
         getChildren().add(secondNameOffline);
         getChildren().add(radioButton);
         getChildren().add(radioButton0);
         getChildren().add(backToFirstPage);
        //Set The Pane By Default Visible False
        this.setVisible(false);
        MultiOfflineStart.setOnAction((ActionEvent event) -> {
            
//            try {
//                Connection conn = Database.getDBConnection();
//                ProjectManger.viewPane(ProjectManger.homeMulti);
//                String query ="INSERT INTO MULTIMODE(nameplayer1,nameplayer2) VALUES ( '"+firstNameOffline.getText()+"','"+secondNameOffline.getText()+"')";
//                Statement statement =conn.createStatement();
//                statement.execute(query);
//            } catch (SQLException ex) {
//                Logger.getLogger(MultiNamesPane.class.getName()).log(Level.SEVERE, null, ex);
//            }
            ProjectManger.viewPane(ProjectManger.homeMulti);
            ProjectManger.homeMulti.SearchTextField.setDisable(true);
            ProjectManger.homeMulti.SearchBtn.setDisable(true);
            ProjectManger.homeMulti.ListItems.setDisable(true);
            
            if(radioButton.isSelected())
            {
                firstKick = "X";
                ProjectManger.homeMulti.playerOneTextField.setText(firstNameOffline.getText());
                ProjectManger.homeMulti.playerTwoTextField.setText(secondNameOffline.getText());
            }
            else{
                firstKick = "O";
                ProjectManger.homeMulti.playerOneTextField.setText(secondNameOffline.getText());
                ProjectManger.homeMulti.playerTwoTextField.setText(firstNameOffline.getText());
                
            }
        });
        backToFirstPage.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.firstPage);});
    }

}