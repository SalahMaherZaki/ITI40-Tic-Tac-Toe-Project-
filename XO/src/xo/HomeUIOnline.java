package xo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.Thread.MIN_PRIORITY;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static xo.HomeUISingle.SearchTextField;
import static xo.HomeUISingle.recordCheck;

public class HomeUIOnline extends Pane {

    protected final Button HistoryBtn;
    protected final Label playerLabel;
    public static TextField playerTextField;
    protected final Label label;
    public static RadioButton radioButton;
    public static RadioButton radioButton0;
    protected final ToggleGroup group;
    public static CheckBox recordCheck;
    protected final Button StartBtn;
    public static Button BackBtn;
    protected static TextField SearchTextField;
    protected static Label SearchBtn;
    public static ListView ListItems;
    //=Server
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    String recieveData, data;
    public String playerOne1;
    public String playerTwo2;
    public HomeUIOnline() {

        HistoryBtn = new Button();
        playerLabel = new Label();
        playerTextField = new TextField();
        label = new Label();
        radioButton = new RadioButton();
        radioButton0 = new RadioButton();
        group = new ToggleGroup();
        recordCheck = new CheckBox();
        StartBtn = new Button();
        BackBtn = new Button();
        SearchTextField = new TextField();
        SearchBtn = new Label();
        ListItems = new ListView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);


         HistoryBtn.setLayoutX(35.0);
        HistoryBtn.setLayoutY(370.0);
        HistoryBtn.setMnemonicParsing(false);
        HistoryBtn.setPrefHeight(70.0);
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

    
        radioButton.setId("x");
        radioButton.setLayoutX(270.0);
        radioButton.setLayoutY(293.0);
        radioButton.setMnemonicParsing(false);
        radioButton.setText("X");
        radioButton.setToggleGroup(group);
        radioButton.setSelected(true);
        
        radioButton0.setId("o");
        radioButton0.setLayoutX(350.0);
        radioButton0.setLayoutY(293.0);
        radioButton0.setMnemonicParsing(false);
        radioButton0.setText("O");
        radioButton0.setToggleGroup(group);

        label.setLayoutX(33.0);
        label.setLayoutY(290.0);
        label.setFont(new Font(18.0));
        label.setText("Note That Sender Player is Always (X)");
        label.setId("markOnline");
       
        recordCheck.setLayoutX(35.0);
        recordCheck.setLayoutY(180.0);
        recordCheck.setMnemonicParsing(false);
        recordCheck.setText("Do you want to record ?");
        recordCheck.setId("singlePlayerCheckBox");
        
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

          SearchTextField.setLayoutX(500.0);
        SearchTextField.setLayoutY(30.0);
        SearchTextField.setPrefWidth(179.0);
        SearchTextField.setId("SearchTextField");
   
        
        SearchBtn.setLayoutX(490.0);
        SearchBtn.setLayoutY(80.0);
        SearchBtn.setMnemonicParsing(false);
        SearchBtn.setText("Online Players");
        SearchBtn.setId("SearchBtn");
        //SearchBtn.setE(true);
        
        ListItems.setLayoutX(500.0);
        ListItems.setLayoutY(150.0);
        ListItems.setId("ListItems");
    
          getChildren().add(label);
         //getChildren().add(radioButton);
         //getChildren().add(radioButton0);
           getChildren().add(HistoryBtn);
         getChildren().add(playerLabel);
         getChildren().add(playerTextField);
         getChildren().add(recordCheck);
         getChildren().add(BackBtn);
         //getChildren().add(SearchTextField);
         getChildren().add(SearchBtn);
         getChildren().add(ListItems);
        //Set The Pane By Default Visible False
        this.setVisible(false);
        HistoryBtn.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.battleHistory);
        });
        BackBtn.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.firstPage);
            LogInPage.playerName.setText("");
            LogInPage.playerPassword.setText("");

            try {
                Platform.runLater(() -> HomeUIOnline.ListItems.getItems().clear());
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println("Window Has Been Closed");
            }
            // هخلص دي لما اصحي بكرا
            data = "remove," + playerTextField.getText();
            try {
                    ps.println(data);
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Sorry The Server Is Offline").show();
                }
        });
        recordCheck.setOnAction((ActionEvent event) -> {
            if (recordCheck.isSelected()) {
                ProjectManger.startRecordGame = true;
            }
        });
        ListItems.setOnMouseClicked((MouseEvent event) -> {
            //Name
            if (event.getClickCount() == 2) {
                String nameSelected = ListItems.getSelectionModel().getSelectedItems().toString();
                if(!nameSelected.equals("["+HomeUIOnline.playerTextField.getText()+"]") && !nameSelected.equals("["+"]"))
                {
                    int input = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Play With" + nameSelected);
                    if (input == 0) {
                        //In This Case The User Clicked Yes
                        data = "requestPlay," + nameSelected + "," + playerTextField.getText();
                        ps.println(data);
                    } else {
                        System.out.println("Canceled");
                    }
                }
            }
        });

        try {
            mySocket = new Socket(XO.serverIP, 5005);
            if (!mySocket.isClosed()) {
                dis = new DataInputStream(mySocket.getInputStream());
                ps = new PrintStream(mySocket.getOutputStream());
            } else {
                System.out.println("Sorry The Server Is Closed");
            }

        } catch (IOException ex) {
            System.out.println("Sorry The Connection Is Disabled");
        }
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        recieveData = dis.readLine();
                        //if received equals first So Clear The List Because In Threads On Click Back Button
                        // The Thread Duplicates The Input So I've Solved It Using This Condition
                        // That Makes The Server Response SomeThing Like Alert Called First
                        //To Me Before Sending The First Element
                        // Every Time The Thread Duplicates The First Element So Clear The List
                        
                        if (recieveData.equals("first")) {
                            Platform.runLater(() -> ListItems.getItems().clear());
                            Thread.sleep(50);
                        }
                        if (!(recieveData.equals("logedIn")) && !(recieveData.equals("signup"))&& !(recieveData.equals("exitContext"))&& !(recieveData.equals("notSignup"))&&!(recieveData.equals("notLogedIn")) && !(recieveData.contains(",")) && !(recieveData.contains("first"))) {
                            Platform.runLater(() -> ListItems.getItems().add(recieveData));
                            Thread.sleep(100);
                        }

                        //For Quick Solution For This Problem
                        if (recieveData.contains(",")) {
                            StringTokenizer strToken = new StringTokenizer(recieveData, ",");
                            String requestType = strToken.nextToken();
                            String me = strToken.nextToken();
                            //The The Reply Type
                            if (requestType.equals("reply")) {
                                String sender = strToken.nextToken(); // i've Puted It Here To Avoid The Exepction Of Tokenizer
                                
                                if (me.equals("[" + playerTextField.getText() + "]")) {
                                    playerOne1 = sender;
                                    playerTwo2 = playerTextField.getText();
                                    if(!ProjectManger.isInGame)
                                    {
                                        int input = JOptionPane.showConfirmDialog(null, "The Player " + sender + " Wants To Play With You Do You Want Accapt");
                                        if (input == 0) {
                                            ProjectManger.isInGame = true;
                                            ProjectManger.viewPane(ProjectManger.gamePane);
                                            // Replace With The New Name
                                            Platform.runLater(() -> ProjectManger.gamePane.playerOne.setText(sender));
                                            Platform.runLater(() -> ProjectManger.gamePane.playerTwo.setText(playerTextField.getText()));
                                            Thread.sleep(50);
                                            ProjectManger.onlineMode = true;
                                            //Reply To The Player To Redirect Him Too
                                            data = "accaptedRequest," + sender + ","+me;
                                            ps.println(data);
                                            //Here To Send To Server That Game Has Been Started
                                            if(radioButton0.isSelected())
                                            {
                                                data = "gameStart,O," +playerOne1 +","+playerTwo2;
                                                ps.println(data);
                                            }
                                            else
                                            {   
                                                data = "gameStart,X," +playerOne1 +","+playerTwo2;
                                                ps.println(data);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        data = "rejectAccaptedRequest," + playerOne1;
                                        ps.println(data);
                                    }
                                }
                            }
                            // Here i'll Handle If The Request Accapted And Equals Me So it Will Direct Me
                            //The User Which I've Sent Him Request Has Been Accapted It
                            if (requestType.equals("accaptedRequest")) {
                                if (me.equals(playerTextField.getText())) {
                                    ProjectManger.isInGame = true;
                                    String playerAccaptedMyRequest = strToken.nextToken();
                                    ProjectManger.viewPane(ProjectManger.gamePane);
                                    // Replace With The New Name
                                    Platform.runLater(() -> ProjectManger.gamePane.playerOne.setText(playerTextField.getText()));
                                    Platform.runLater(() -> ProjectManger.gamePane.playerTwo.setText(playerAccaptedMyRequest));
                                    Thread.sleep(50);
                                    ProjectManger.onlineMode = true;
                                }
                            }
                            if(requestType.equals("rejectRequestPlay"))
                            {
                                if(playerTextField.getText().equals(me))
                                {
                                    Platform.runLater(() -> new Alert(Alert.AlertType.CONFIRMATION, "Sorry This Player IS Playing Another Game Right Now !!!!!").show());
                                    Thread.sleep(50);
                                }
                            }
                        }

                    } catch (IOException ex) {
                        System.out.println("The Window Is Closed");
                        break;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HomeUIOnline.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
            t.start();
    }
}
