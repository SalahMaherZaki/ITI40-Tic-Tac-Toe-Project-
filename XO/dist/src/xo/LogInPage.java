package xo;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static javax.management.Query.value;
import javax.swing.JOptionPane;
import static xo.HomeUIOnline.ListItems;

public class LogInPage extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Button login;
    public static TextField playerName;
    public static PasswordField playerPassword;
    protected final Button signUp;
        protected final Button back;

    //=Server
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    String recieveData, data;
    public LogInPage() {

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        login = new Button();
        playerName = new TextField();
        playerPassword = new PasswordField();
        signUp = new Button();
        back = new Button();

        setId("AnchorPane");
        setPrefHeight(438.0);
        setPrefWidth(479.0);

        label2.setLayoutX(180.0);
        label2.setLayoutY(10.0);
        label2.setText("Log In");
        label2.setId("headerLogin");
        
        label0.setLayoutX(80.0);
        label0.setLayoutY(167.0);
        label0.setPrefHeight(41.0);
        label0.setPrefWidth(100.0);
        label0.setText("Name");
        label0.setId("loginName");
        
        playerName.setLayoutX(220.0);
        playerName.setLayoutY(170.0);
        playerName.setPrefHeight(41.0);
        playerName.setPrefWidth(180.0);
        playerName.setId("textLoginName");
        
        label1.setLayoutX(80.0);
        label1.setLayoutY(257.0);
        label1.setPrefHeight(41.0);
        label1.setPrefWidth(120.0);
        label1.setText("Password");
        label1.setId("loginPassword");
        
        playerPassword.setLayoutX(220.0);
        playerPassword.setLayoutY(260.0);
        playerPassword.setPrefHeight(41.0);
        playerPassword.setPrefWidth(180.0);
        playerPassword.setId("textLoginPassword");
        
        

        login.setLayoutX(200.0);
        login.setLayoutY(340.0);
        login.setMnemonicParsing(false);
        login.setPrefHeight(53.0);
        login.setPrefWidth(120.0);
        login.setText("Login");
        login.setId("loginBtn");
        
        signUp.setLayoutX(200.0);
        signUp.setLayoutY(420.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(41.0);
        signUp.setPrefWidth(130.0);
        signUp.setText("Sign Up");
        signUp.setId("signBtn");
        
        back.setLayoutX(30.0);
        back.setLayoutY(540.0);
        back.setMnemonicParsing(false);
        back.setPrefHeight(50.0);
        back.setPrefWidth(170.0);
        back.setText("Back");
        back.setId("loginBack");
        
         label.setLayoutX(400);
        label.setLayoutY(150);
        label.setMinHeight(350);
        label.setMinWidth(300);
        label.setId("loginImage");
        
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(login);
        getChildren().add(playerName);
        getChildren().add(playerPassword);
        getChildren().add(signUp);
        getChildren().add(back);
        login.setOnAction((ActionEvent event) -> {
            if(playerName.getText().equals("") || playerName.getText().contains(";") || playerPassword.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Name or Password is invalid", "ERROR", JOptionPane.ERROR_MESSAGE);
            else
            {
                data = "login," + playerName.getText()+","+playerPassword.getText();
                try {
                    ps.println(data);
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR, "Sorry The Server Is Offline").show();
                }
                
            }
        });
        //Set This Pane By Default Visible False
        this.setVisible(false);
        signUp.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.signupPage);
            playerName.setText("");
            playerPassword.setText("");
        });
        back.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.firstPage);
          });
                //===================================================
        //Server Response
        try {
            mySocket = new Socket(XO.serverIP, 5005);
            if(!mySocket.isClosed())
            {
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
            }
            else{
                System.out.println("Sorry The Server Is Closed");
            }

        } catch (IOException ex) {
            System.out.println("Sorry The Server IS offline");
        }
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        recieveData = dis.readLine();
                        StringTokenizer tokens = new StringTokenizer(recieveData,",");
                        System.out.println("The Received Data From Server  = "+recieveData);
                        //To Handle The Request From The Server To Refresh Data In Each Iterate
                        if (recieveData.equals("first")) {
                            Platform.runLater(() -> ListItems.getItems().clear());
                            Thread.sleep(50);
                        }
                        if(tokens.nextToken().equals("logedIn"))
                        {
                            String pName = tokens.nextToken();
                            System.out.println(pName);
                            if(pName.equals(playerName.getText()))
                            {
                                ProjectManger.viewPane(ProjectManger.homeOnline);
                                HomeUIOnline.playerTextField.setText(playerName.getText());
                                ProjectManger.onlineMode = true;
                                ProjectManger.testFlag = true;
                                
                            }
                        }
                        if(recieveData.equals("notLogedIn"))
                        {
                            JOptionPane.showMessageDialog(null, "Name or Password is invalid", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        System.out.println("The Window Is Closed");
                        break;
                    } catch (InterruptedException ex) {
                        new Alert(Alert.AlertType.ERROR, "Sorry The Server Is Offline").show();
                    }
                }
            }
        };
        t.start();
    }
}
