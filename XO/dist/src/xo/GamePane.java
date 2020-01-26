package xo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class GamePane extends Pane {

    public static Label playerOne;
    public static Label playerTwo;
    protected final Label label;
    public static Label scoreOne;
    public static Label scoreTwo;
    protected final Line line;
    protected final Line line0;
    protected final Line line1;
    protected final Line line2;
    protected final Button exit;
    protected final Button btn11;
    protected final Button btn21;

    public static Button newGame;

    protected final Button btn31;
    protected final Button btn12;
    protected final Button btn22;
    protected final Button btn32;
    protected final Button btn13;
    protected final Button btn23;
    protected final Button btn33;
    //Video Reference
    public static VedioPane video;
    public static videoOffline videoOffline;
    //Flags Section
    public static boolean isGameEnd = false;
    boolean isFirstPlayerTurn = true;

    //Colors
    public static Color xForeground = Color.rgb(166,242,0);
    public static Color oForeground = Color.rgb(255,83,190);

    //Vs Pc Flags
    public static int XOCounter = 0;
    Random random = new Random();
    int randomNumber;
    Button[] buttons;
    boolean isDraw = true;

    //Records Section The Flag Is Into Project
    int recrodCounter = 0;
    String savedRecord;
    LinkedHashMap<String, String> myMap = new LinkedHashMap<>();

    //Vs Computer Section
    Socket mySocket;
    DataInputStream dis;
    public static PrintStream ps;
    String recieveData, data;

    //Online Mode
    String onlineData;
    String Data;
    public static String gameChar, Player1, Player2, sendTheXOToAnotherOne;

    //Online Check If The Players Both Wants To Play Again
    public static boolean again1 = false,again2 = false;
    public GamePane() {

        playerOne = new Label();
        playerTwo = new Label();
        label = new Label();
        scoreOne = new Label();
        scoreTwo = new Label();
        line = new Line();
        line0 = new Line();
        line1 = new Line();
        line2 = new Line();
        exit = new Button();
        btn11 = new Button();
        btn21 = new Button();
        btn31 = new Button();
        btn12 = new Button();
        btn22 = new Button();
        btn32 = new Button();
        btn13 = new Button();
        btn23 = new Button();
        btn33 = new Button();

        newGame = new Button();
        isDraw = true;
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        playerOne.setLayoutX(130.0);
        playerOne.setLayoutY(20.0);
        playerOne.setPrefHeight(35.0);
        playerOne.setPrefWidth(130.0);
        playerOne.setText("Player One");
        playerOne.setId("gamePanePlayerOne");
        
        scoreOne.setLayoutX(150.0);
        scoreOne.setLayoutY(70.0);
        scoreOne.setText("0");
        scoreOne.setId("scoreOne");
        
        playerTwo.setLayoutX(550.0);
        playerTwo.setLayoutY(20.0);
        playerTwo.setPrefHeight(35.0);
        playerTwo.setPrefWidth(130.0);
        playerTwo.setText("Player Two");
        playerTwo.setId("gamePanePlayerTwo");
        
        scoreTwo.setLayoutX(550.0);
        scoreTwo.setLayoutY(70.0);
        scoreTwo.setText("0");
        scoreTwo.setId("scoreTwo");
        
        label.setLayoutX(310.0);
        label.setLayoutY(45.0);
        label.setPrefHeight(45.0);
        label.setPrefWidth(100.0);
        label.setText("Score");
        label.setId("score");
        
        

        

        line.setEndX(210.0);
        line.setLayoutX(330.0);
        line.setLayoutY(255.0);
        line.setStartX(-117.0);
        line.setStrokeWidth(5.0);
        line.setId("line");

        line0.setEndX(211.0);
        line0.setLayoutX(330.0);
        line0.setLayoutY(380.0);
        line0.setStartX(-117.0);
        line0.setStrokeWidth(5.0);
        line0.setId("line0");
         
       
        
        line1.setEndX(-29.0);
        line1.setEndY(183.0);
        line1.setLayoutX(340.0);
        line1.setLayoutY(275.0);
        line1.setStartX(-29.0);
        line1.setStartY(-78.0);
        line1.setStrokeWidth(5.0);
        line1.setId("line1");

        line2.setEndX(102.0);
        line2.setEndY(185.0);
        line2.setLayoutX(340.0);
        line2.setLayoutY(275.0);
        line2.setStartX(102.0);
        line2.setStartY(-79.0);
        line2.setStrokeWidth(5.0);
        line2.setId("line2");

        exit.setLayoutX(30.0);
        exit.setLayoutY(540.0);
        exit.setMnemonicParsing(false);
        exit.setPrefHeight(50.0);
        exit.setPrefWidth(84.0);
        exit.setText("Exit");
        exit.setId("gamePaneExit");
        
        newGame.setLayoutX(440.0);
        newGame.setLayoutY(339.0);
        newGame.setMnemonicParsing(false);
        newGame.setPrefHeight(50.0);
        newGame.setPrefWidth(150.0);
        newGame.setText("New Game");
        newGame.setId("gamePaneNew");
        
        btn11.setLayoutX(212.0);
        btn11.setLayoutY(183.0);
        btn11.setMnemonicParsing(false);
        btn11.setPrefHeight(67.0);
        btn11.setPrefWidth(100.0);
        btn11.setText("");
        btn11.setId("btn11");

        btn21.setLayoutX(212.0);
        btn21.setLayoutY(257.0);
        btn21.setMnemonicParsing(false);
        btn21.setPrefHeight(115.0);
        btn21.setPrefWidth(100.0);
        btn21.setText("");
        btn21.setId("btn21");
         
        btn31.setLayoutX(212.0);
        btn31.setLayoutY(377.0);
        btn31.setMnemonicParsing(false);
        btn31.setPrefHeight(67.0);
        btn31.setPrefWidth(100.0);
        btn31.setText("");
         btn31.setId("btn31");
         
        btn12.setLayoutX(315.0);
        btn12.setLayoutY(183.0);
        btn12.setMnemonicParsing(false);
        btn12.setPrefHeight(67.0);
        btn12.setPrefWidth(126.0);
        btn12.setText("");
         btn12.setId("btn12");

        btn22.setLayoutX(315.0);
        btn22.setLayoutY(259.0);
        btn22.setMnemonicParsing(false);
        btn22.setPrefHeight(115.0);
        btn22.setPrefWidth(126.0);
        btn22.setText("");
         btn22.setId("btn22");

        btn32.setLayoutX(317.0);
        btn32.setLayoutY(377.0);
        btn32.setMnemonicParsing(false);
        btn32.setPrefHeight(67.0);
        btn32.setPrefWidth(119.0);
        btn32.setText("");
         btn32.setId("btn32");

        btn13.setLayoutX(446.0);
        btn13.setLayoutY(183.0);
        btn13.setMnemonicParsing(false);
        btn13.setPrefHeight(67.0);
        btn13.setPrefWidth(100.0);
        btn13.setText("");
        btn13.setId("btn13");

        btn23.setLayoutX(446.0);
        btn23.setLayoutY(259.0);
        btn23.setMnemonicParsing(false);
        btn23.setPrefHeight(115.0);
        btn23.setPrefWidth(100.0);
        btn23.setText("");
        btn23.setId("btn23");

        btn33.setLayoutX(446.0);
        btn33.setLayoutY(377.0);
        btn33.setMnemonicParsing(false);
        btn33.setPrefHeight(67.0);
        btn33.setPrefWidth(100.0);
        btn33.setText("");
         btn33.setId("btn33");
        

        getChildren().add(playerOne);
        getChildren().add(playerTwo);
        getChildren().add(label);
        getChildren().add(scoreOne);
        getChildren().add(scoreTwo);
        getChildren().add(line);
        getChildren().add(line0);
        getChildren().add(line1);
        getChildren().add(line2);
        getChildren().add(exit);
        getChildren().add(btn11);
        getChildren().add(btn21);
        getChildren().add(btn31);
        getChildren().add(btn12);
        getChildren().add(btn22);
        getChildren().add(btn32);
        getChildren().add(btn13);
        getChildren().add(btn23);
        getChildren().add(btn33);
        startNewGame();
        this.setVisible(false);
        exit.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.firstPage);
            startNewGame();
            scoreOne.setText("0");
            scoreTwo.setText("0");
            if(ProjectManger.onlineMode)
            {
                LogInPage.playerName.clear();
                LogInPage.playerPassword.clear();
                ProjectManger.isInGame = false;
                ProjectManger.onlineMode = false;
                data = "remove," + HomeUIOnline.playerTextField.getText();
                ps.println(data);
                //==================================
                if(HomeUIOnline.playerTextField.getText().equals(Player1))
                {
                    String playAgainReject1 = "exitContext,"+Player2;
                    ps.println(playAgainReject1);
                }
                if(HomeUIOnline.playerTextField.getText().equals(Player2))
                {
                    String playAgainReject2 = "exitContext,"+Player1;
                    ps.println(playAgainReject2);
                }
            }
        });
        newGame.setOnAction((ActionEvent event) -> {
            startNewGame();
        });

        //=====================================
        //Call Action Performed On Every Time
        btn11.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn11, "btn11");
            }
        });
        btn12.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn12, "btn12");
            }
        });
        btn13.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn13, "btn13");
            }
        });
        btn21.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn21, "btn21");
            }
        });
        btn22.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn22, "btn22");
            }
        });
        btn23.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn23, "btn23");
            }
        });
        btn31.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn31, "btn31");
            }
        });
        btn32.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn32, "btn32");
            }
        });
        btn33.setOnAction((ActionEvent event) -> {
            if (ProjectManger.recordHasBeenStarted) {
                btn11.setDisable(false);
            } else {
                actionPerformed(btn33, "btn33");
            }
        });
        //===================================================
        //Server Response To The Game Pane
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
                        
                        if (!(recieveData.equals(null))) {
                            StringTokenizer serverToken = new StringTokenizer(recieveData, ",");
                            String replyReply = serverToken.nextToken();
                            if (replyReply.equals("gameStartRequest")) {
                                gameChar = serverToken.nextToken();
                                Player1 = serverToken.nextToken();
                                Player2 = serverToken.nextToken();
                            }
                            if (replyReply.equals("gameActionReply")) {
                                //Handle The Player Turn
                                isFirstPlayerTurn = !isFirstPlayerTurn;
                                String printOnTheAnotherOne = serverToken.nextToken();
                                String btnName = serverToken.nextToken();
                                String gameCharReply = serverToken.nextToken();
                                if (HomeUIOnline.playerTextField.getText().equals(printOnTheAnotherOne)) {
                                    printOnlineData(btnName, gameCharReply);
                                }
                            }
                            if(replyReply.equals("loserBaby"))
                            {
                                String loser = serverToken.nextToken();
                                if(HomeUIOnline.playerTextField.getText().equals(loser))
                                {
                                    Platform.runLater(() -> colorLoser());
                                    Thread.sleep(50);
                                    
                                    Platform.runLater(() -> scoreLoser(loser));
                                    Thread.sleep(50);
                                }
                            }
                            if(replyReply.equals("drawBaby"))
                            {
                                String anotherPlayer = serverToken.nextToken();
                                if(HomeUIOnline.playerTextField.getText().equals(anotherPlayer))
                                {
                                    Platform.runLater(() -> video = new VedioPane("D:\\Java\\Project\\XO\\src\\videos\\nowin.mp4"));
                                    Platform.runLater(() -> XO.btn.fire());
                                    Thread.sleep(50);
                                    
                                }
                            }
                            //Handle The Requests
                            //System.out.println("The Reply From Server About The Play Again"+replyReply);
                            if(replyReply.equals("playAgain1"))
                            {
                                again2 = true;
                                if(HomeUIOnline.playerTextField.getText().equals(Player2))
                                {
                                    if(again1 && again2)
                                    {
                                        Platform.runLater(() ->newGame.fire());
                                        Thread.sleep(50);
                                        String playAgain = "playOnePlayBack,"+Player1;
                                        ps.println(playAgain);
                                    }
                                    /*else if(again1 || again2)
                                    {
                                        Platform.runLater(() -> new Alert(AlertType.CONFIRMATION, "Please Wait For "+ Player2+" To Accapt The Request").show());
                                        Thread.sleep(450);
                                    }*/
                                }
                            }
                            if(replyReply.equals("playAgain2"))
                            {
                                again1 = true;
                                if(HomeUIOnline.playerTextField.getText().equals(Player1))
                                {
                                    if(again1 && again2)
                                    {
                                        Platform.runLater(() ->newGame.fire());
                                        Thread.sleep(50);
                                        String playAgain = "playAgainAccapt1,"+GamePane.playerTwo.getText();
                                        ps.println(playAgain);
                                    }
                                    else if(again1 || again2)
                                    {
                                        Platform.runLater(() -> new Alert(AlertType.CONFIRMATION, "Please Wait For "+ Player1+" To Accapt The Request").show());
                                        Thread.sleep(50);
                                    }
                                }
                            }
                            //======================================
                            //Play Back Request
                            if(replyReply.equals("firstPlayerPlayBack"))
                            {
                                again2 = true;
                                if(HomeUIOnline.playerTextField.getText().equals(Player1))
                                {
                                    if(again1 && again2)
                                    {
                                        Platform.runLater(() ->newGame.fire());
                                        Thread.sleep(50);
                                    }
                                }
                            }
                            //=============================================
                            // Reject Play Again
                            if(replyReply.equals("againReject1"))
                            {
                                ProjectManger.isInGame = false;
                                again1 = again2 = false;
                                if(HomeUIOnline.playerTextField.getText().equals(Player2))
                                {
                                    Platform.runLater(() -> new Alert(AlertType.CONFIRMATION, "Sorry The Another Player Has Been Rejected To Play Again").show());
                                    Platform.runLater(() ->VedioPane.fireBack.fire());
                                    Thread.sleep(50);
                                }
                            }
                            if(replyReply.equals("againReject2"))
                            {
                                ProjectManger.isInGame = false;
                                again1 = again2 = false;
                                if(HomeUIOnline.playerTextField.getText().equals(Player1))
                                {
                                    Platform.runLater(() -> new Alert(AlertType.CONFIRMATION, "Sorry The Another Player Has Been Rejected To Play Again").show());
                                    Platform.runLater(() ->VedioPane.fireBack.fire());
                                    Thread.sleep(50);
                                    
                                }
                            }
                            if(replyReply.equals("exitAnotherPlayer"))
                            {
                                String anotherPlayer = serverToken.nextToken();
                                if(HomeUIOnline.playerTextField.getText().equals(anotherPlayer))
                                {
                                    
                                    
                                    ProjectManger.viewPane(ProjectManger.firstPage);
                                    Platform.runLater(() ->scoreOne.setText("0"));
                                    Platform.runLater(() ->scoreTwo.setText("0"));
                                    Platform.runLater(() ->LogInPage.playerName.clear());
                                    Platform.runLater(() ->LogInPage.playerPassword.clear());
                                    
                                    ProjectManger.isInGame = false;
                                    ProjectManger.onlineMode = false;
                                    data = "remove," + HomeUIOnline.playerTextField.getText();
                                    ps.println(data);
                                    Platform.runLater(() ->startNewGame());
                                }
                            }
                        }
                    } catch (IOException ex) {
                        Platform.runLater(() -> new Alert(AlertType.ERROR, "Sorry The Server Has Been Closed \n Please Try To Access The Game Later").show());
                        Platform.runLater(() -> exit.fire());
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex1) {
                            System.out.println("HELLLOOOOOOOOOOOOOOOOOOOOOO FROM EXIT EXCEPTION");
                        }
                        break;
                    } catch (InterruptedException ex) {
                        System.out.println("Sorry The Connection Is Disabled");
                    }
                }
            }
        };
            t.start();
    }

    private void colorWinner(Button b1, Button b2, Button b3) {
        b1.setStyle("-fx-background-color: lightblue;-fx-font-size: 3em bold;");
        b2.setStyle("-fx-background-color: lightblue;-fx-font-size: 3em bold;");
        b3.setStyle("-fx-background-color: lightblue;-fx-font-size: 3em bold;");
        if(!ProjectManger.onlineMode)
        {
            videoOffline = new videoOffline("D:\\Java\\Project\\XO\\src\\videos\\winners.mp4");
            XO.btnOffline.fire();
        }
        else
        {
            video = new VedioPane("D:\\Java\\Project\\XO\\src\\videos\\winners.mp4");
            XO.btn.fire();
        }
        isDraw = false;
        if (ProjectManger.startRecordGame) {
            startRecordGame();
        }
        if(ProjectManger.onlineMode)
        {
            if(HomeUIOnline.playerTextField.getText().equals(Player1))
            {
                String sendToLoser = "loser,"+Player2;
                ps.println(sendToLoser);
            }
            if(HomeUIOnline.playerTextField.getText().equals(Player2))
            {
                String sendToLoser = "loser,"+Player1;
                ps.println(sendToLoser);
            }
        }
    }

    private void colorLoser() {
        video = new VedioPane("D:\\Java\\Project\\XO\\src\\videos\\losers.mp4");
        XO.btn.fire();
        isDraw = false;
        //Here Closed The Game for Another Player
        isGameEnd = true;
        if (ProjectManger.startRecordGame) {
            startRecordGame();
        }
    }
    public void scoreLoser(String loser)
    {
        if(loser.equals(Player2))
            scoreOne.setText(Integer.valueOf(scoreOne.getText()) + 1 + "");
        if(loser.equals(Player1))
            scoreTwo.setText(Integer.valueOf(scoreTwo.getText()) + 1 + "");
    }
    private void checkIfGameEnd() {
        String s11 = btn11.getText();
        String s12 = btn12.getText();
        String s13 = btn13.getText();
        String s21 = btn21.getText();
        String s22 = btn22.getText();
        String s23 = btn23.getText();
        String s31 = btn31.getText();
        String s32 = btn32.getText();
        String s33 = btn33.getText();

        if (s11.equals(s12) && s11.equals(s13) && !s11.equals("")) {
            isGameEnd = true;
            colorWinner(btn11, btn12, btn13);
        }

        if (s21.equals(s22) && s21.equals(s23) && !s21.equals("")) {
            isGameEnd = true;
            colorWinner(btn21, btn22, btn23);
        }

        if (s31.equals(s32) && s31.equals(s33) && !s31.equals("")) {
            isGameEnd = true;
            colorWinner(btn31, btn32, btn33);
        }

        if (s11.equals(s21) && s11.equals(s31) && !s11.equals("")) {
            isGameEnd = true;
            colorWinner(btn11, btn21, btn31);
        }

        if (s12.equals(s22) && s12.equals(s32) && !s12.equals("")) {
            isGameEnd = true;
            colorWinner(btn12, btn22, btn32);
        }

        if (s13.equals(s23) && s13.equals(s33) && !s13.equals("")) {
            isGameEnd = true;
            colorWinner(btn13, btn23, btn33);
        }

        if (s11.equals(s22) && s11.equals(s33) && !s11.equals("")) {
            isGameEnd = true;
            colorWinner(btn11, btn22, btn33);
        }

        if (s13.equals(s22) && s13.equals(s31) && !s13.equals("")) {
            isGameEnd = true;
            colorWinner(btn13, btn22, btn31);
        }
        if (XOCounter >= 9) {
            if (isDraw == true) {
                if(!ProjectManger.onlineMode)
                {
                    videoOffline = new videoOffline("D:\\Java\\Project\\XO\\src\\videos\\nowin.mp4");
                    XO.btnOffline.fire();
                }
                else
                {
                    video = new VedioPane("D:\\Java\\Project\\XO\\src\\videos\\nowin.mp4");
                    XO.btn.fire();
                    if(HomeUIOnline.playerTextField.getText().equals(Player1))
                    {
                        String sendToLoser = "drawKid,"+Player2;
                        ps.println(sendToLoser);
                    }
                    if(HomeUIOnline.playerTextField.getText().equals(Player2))
                    {
                        String sendToLoser = "drawKid,"+Player1;
                        ps.println(sendToLoser);
                    }
                } 
                if (ProjectManger.startRecordGame) {
                    startRecordGame();
                }
            }
            isGameEnd = true;
            isFirstPlayerTurn = true;
            XOCounter = 0;
        }
        if (isGameEnd == true && isDraw == false && ProjectManger.vsComputer == false) {
            if (isFirstPlayerTurn) {
                scoreTwo.setText(Integer.valueOf(scoreTwo.getText()) + 1 + "");
            } else {
                scoreOne.setText(Integer.valueOf(scoreOne.getText()) + 1 + "");
            }
            XOCounter = 0;
        }
        if (isGameEnd == true && isDraw == false && ProjectManger.vsComputer == true) {
            if (isFirstPlayerTurn) {
                scoreOne.setText(Integer.valueOf(scoreOne.getText()) + 1 + "");
            } else {
                scoreTwo.setText(Integer.valueOf(scoreTwo.getText()) + 1 + "");
            }
            XOCounter = 0;
        }
        if (isGameEnd == true && isDraw == true) {
            if (isFirstPlayerTurn) {
                scoreOne.setText(Integer.valueOf(scoreOne.getText()) + "");
            } else {
                scoreTwo.setText(Integer.valueOf(scoreTwo.getText()) + "");
            }
            XOCounter = 0;
        }
    }

    private void startNewGame() {
        //THIS FOR Online Player if caused A Bug Check it
        isFirstPlayerTurn = true;
        isGameEnd = false;
        isDraw = true;
        again1 = false;
        again2 = false;
        btn11.setText("");
        btn12.setText("");
        btn13.setText("");
        btn21.setText("");
        btn22.setText("");
        btn23.setText("");
        btn31.setText("");
        btn32.setText("");
        btn33.setText("");
        btn11.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn12.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn13.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn21.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn22.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn23.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn31.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn32.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");
        btn33.setStyle("-fx-background-color: none;-fx-font-size: 4em bold;");

    }

    private void actionPerformed(Button clickedButton, String btnname) {

        if (ProjectManger.vsComputer == false && ProjectManger.onlineMode == false) {
            if (isGameEnd == false && clickedButton.getText().equals("")) {
                if (isFirstPlayerTurn) {
                    clickedButton.setTextFill(xForeground);
                    clickedButton.setText("X");
                    myMap.put(btnname, "X");
                    isFirstPlayerTurn = false;
                    XOCounter++;
                    
                } else {
                    clickedButton.setTextFill(oForeground);
                    clickedButton.setText("O");
                    myMap.put(btnname, "O");
                    isFirstPlayerTurn = true;
                    XOCounter++;
                }
                checkIfGameEnd();

            }
        }
        if (ProjectManger.vsComputer == true && clickedButton.getText().equals("") && ProjectManger.onlineMode == false) {

                buttons = new Button[]{btn11, btn12, btn13, btn21, btn22, btn23, btn31, btn32, btn33};
                XOCounter++;
                isFirstPlayerTurn = true;
                clickedButton.setTextFill(xForeground);
                clickedButton.setText("X");
                myMap.put(btnname,"X");
                for (Map.Entry<String,String> entry : myMap.entrySet()) {
                    System.out.println(entry.getKey()+"," + entry.getValue());
                }
                checkIfGameEnd();
            if (isGameEnd == false){
                XOCounter++;
                isFirstPlayerTurn = false;
                for (;;) {
                    randomNumber = random.nextInt(9);
                    if (buttons[randomNumber].getText().equals("")) {
                            buttons[randomNumber].setTextFill(oForeground);
                            buttons[randomNumber].setText("O");
                            myMap.put(buttons[randomNumber].getId(), "O");
                            System.out.println("Helloooooooooooooooo FROM     O");
                        break;
                    }
                }
                checkIfGameEnd();
            }
        }
        if (ProjectManger.onlineMode == true && isGameEnd == false && clickedButton.getText().equals("")) {
            String checkXO1, checkXO2;
            if (gameChar.equals("O")) {
                checkXO1 = "O";
                checkXO2 = "X";
            } else {
                checkXO1 = "X";
                checkXO2 = "O";
            }
            //I'll Handle it On The Reply Action Section
            if (HomeUIOnline.playerTextField.getText().equals(Player1) && isFirstPlayerTurn) {
                if (checkXO1.equals("O")) 
                    clickedButton.setTextFill(oForeground);
                else
                    clickedButton.setTextFill(xForeground);
                clickedButton.setText(checkXO1);
                myMap.put(btnname, checkXO1);
                //Reply To The Another Player
                sendTheXOToAnotherOne = "XOActionReply," + Player2 + "," + btnname + "," + checkXO1;
                ps.println(sendTheXOToAnotherOne);
                
            }
            if (HomeUIOnline.playerTextField.getText().equals(Player2) && !isFirstPlayerTurn) {
                if (checkXO2.equals("O")) 
                    clickedButton.setTextFill(oForeground);
                else
                    clickedButton.setTextFill(xForeground);
                clickedButton.setText(checkXO2);
                myMap.put(btnname, checkXO2);
                //Reply To The Another Player
                sendTheXOToAnotherOne = "XOActionReply," + Player1 + "," + btnname + "," + checkXO2;
                ps.println(sendTheXOToAnotherOne);
            }
            XOCounter ++;
            checkIfGameEnd();
        }
    }

    private void startRecordGame() {
        FileOutputStream fos;
        Path games = Paths.get("C:\\XORecords");
        try {
            Files.createDirectories(games); // Check If Exist Or Not If Not Exists Create 
            recrodCounter = new File(games.toString()).list().length + 1;
            String selectedFile = "C:\\XORecords\\xogame" + recrodCounter + ".txt";
            savedRecord = "";
            for (Map.Entry<String,String> entry : myMap.entrySet()) {
                savedRecord += entry.getKey() + "," + entry.getValue() + ",";
            }
            byte[] b = savedRecord.getBytes();
            fos = new FileOutputStream(selectedFile);
            fos.write(b);
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(GamePane.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void printOnlineData(String btnName, String gameCharReply) throws InterruptedException {
        XOCounter++;
        if (btnName.equals("btn11")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn11.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn11.setTextFill(xForeground));
            Platform.runLater(() -> btn11.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn12")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn12.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn12.setTextFill(xForeground));
            Platform.runLater(() -> btn12.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn13")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn13.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn13.setTextFill(xForeground));
            Platform.runLater(() -> btn13.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn21")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn21.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn21.setTextFill(xForeground));
            Platform.runLater(() -> btn21.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn22")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn22.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn22.setTextFill(xForeground));
            Platform.runLater(() -> btn22.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn23")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn23.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn23.setTextFill(xForeground));
            Platform.runLater(() -> btn23.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn31")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn31.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn31.setTextFill(xForeground));
            Platform.runLater(() -> btn31.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn32")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn32.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn32.setTextFill(xForeground));
            Platform.runLater(() -> btn32.setText(gameCharReply));
            Thread.sleep(100);
        }
        if (btnName.equals("btn33")) {
            if (gameCharReply.equals("O")) {
                Platform.runLater(() -> btn33.setTextFill(oForeground));
            }
            else
                Platform.runLater(() -> btn33.setTextFill(xForeground));
            Platform.runLater(() -> btn33.setText(gameCharReply));
            Thread.sleep(100);
        }
    }
}
