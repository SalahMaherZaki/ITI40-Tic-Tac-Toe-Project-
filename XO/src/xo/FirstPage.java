package xo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public  class FirstPage extends Pane {

    protected final Button singleMode;
    protected final Button multiMode;
    protected final Button onlineMode;
    public FirstPage() {

        singleMode = new Button();
        multiMode = new Button();
        onlineMode = new Button();
        

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

       
        singleMode.setLayoutX(240.0);
         multiMode.setLayoutX(240.0);
        onlineMode.setLayoutX(240.0);
        
        singleMode.setLayoutY(400.0);
        multiMode.setLayoutY(470.0);
        onlineMode.setLayoutY(540.0);
        
        singleMode.setId("SingleBtn");
        singleMode.setMnemonicParsing(false);
        singleMode.setPrefHeight(40.0);
        singleMode.setPrefWidth(133.0);
        singleMode.setText("Single Player");
        singleMode.setPadding(new Insets(0.0, 0.0, 0.0, 5.0));
        
        multiMode.setId("twoPlayerBtn");
        multiMode.setMnemonicParsing(false);
        multiMode.setText("Two Players");
        multiMode.setPadding(new Insets(10.0));
        
        onlineMode.setId("overNetworkBtn");
        onlineMode.setMnemonicParsing(false);
        onlineMode.setText("Play Over Network");
        onlineMode.setPadding(new Insets(10.0));
        
         getChildren().add(singleMode);
         getChildren().add(multiMode);
         getChildren().add(onlineMode);
         
      
        singleMode.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.singleNamePane);
            //On Click SingleMode The vsComputer Flag is Going To Be True in This Case
            ProjectManger.vsComputer = true;
            ProjectManger.onlineMode = false;
        });
        multiMode.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.multiNamepane);
            ProjectManger.vsComputer = false;
            ProjectManger.onlineMode = false;
        });
        onlineMode.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.loginPage);
        });
    }
}
