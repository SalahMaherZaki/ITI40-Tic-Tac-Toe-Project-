package xo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static xo.LogInPage.playerName;
import static xo.LogInPage.playerPassword;
import static xo.ProjectManger.loginPage;

public class SignUpPage extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final Button Confirm;
    protected final TextField Name;
    protected final TextField Age;
    
    protected final PasswordField Password;
    //=Server
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    String recieveData, data;
        protected final Label image;
    protected final Button back;

    public SignUpPage() {

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        Confirm = new Button();
        Name = new TextField();
        Age = new TextField();
        
        Password = new PasswordField();
         image=new Label();
                 back=new Button();

        setId("AnchorPane");
        setPrefHeight(438.0);
        setPrefWidth(479.0);

        label.setLayoutX(126);
        label.setLayoutY(120);
        label.setMinHeight(16);
        label.setMinWidth(69);

        label4.setLayoutX(119.0);
        label4.setLayoutY(14.0);
        label4.setPrefHeight(53.0);
        label4.setPrefWidth(400.0);
        label4.setText("Sign Up"); 
        label4.setId("signHeader");
        
        label0.setLayoutX(80.0);
        label0.setLayoutY(167.0);
        label0.setPrefHeight(41.0);
        label0.setPrefWidth(80.0);
        label0.setText("Name");
        label0.setId("signName");
        
        Name.setLayoutX(220.0);
        Name.setLayoutY(170.0);
        Name.setPrefHeight(41.0);
        Name.setPrefWidth(180.0);
        Name.setId("signNameText");
        
       
        
        label3.setLayoutX(80.0);
        label3.setLayoutY(257.0);
        label3.setPrefHeight(41.0);
        label3.setPrefWidth(102.0);
        label3.setText("Password");
        label3.setId("signPass");
        
        Password.setLayoutX(220.0);
        Password.setLayoutY(260.0);
        Password.setPrefHeight(41.0);
        Password.setPrefWidth(180.0);
        Password.setId("signPassText");
        
        image.setLayoutX(450.0);
        image.setLayoutY(100.0);
        image.setPrefHeight(400.0);
        image.setPrefWidth(200.0);
        image.setId("signImage");
        
        Confirm.setLayoutX(220.0);
        Confirm.setLayoutY(430.0);
        Confirm.setMnemonicParsing(false);
        Confirm.setPrefHeight(50.0);
        Confirm.setPrefWidth(135.0);
        Confirm.setText("Confirm");
        Confirm.setId("signConfirm");
        
         back.setLayoutX(30.0);
        back.setLayoutY(540.0);
        back.setMnemonicParsing(false);
        back.setPrefHeight(40.0);
        back.setPrefWidth(135.0);
        back.setText("Back");
        back.setId("signBack");
        back.setOnAction((ActionEvent event) -> {
            ProjectManger.viewPane(ProjectManger.loginPage);
        });
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(Confirm);
        getChildren().add(Name);
       
        getChildren().add(Password);
        getChildren().add(image);
        getChildren().add(back);
        //Set The Pane By Default Visible False
        Confirm.setDefaultButton(true);
        this.setVisible(false);
        Confirm.setOnAction((ActionEvent event) -> {
            if(Name.getText().equals("") || Password.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Name or Password is invalid", "ERROR", JOptionPane.ERROR_MESSAGE);
            else
            {
                data = "signup," + Name.getText()+","+Password.getText();
                ps.println(data);
            }
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
            System.out.println("Sorry The Connection Is Disabled");
        }
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        recieveData = dis.readLine();
                        System.out.println("The Received Data  = "+recieveData);
                        StringTokenizer signup = new StringTokenizer(recieveData,",");
                        String replySign = signup.nextToken();
                        if(replySign.equals("signup"))
                        {
                            if(Name.getText().equals(signup.nextToken()))
                            {
                                JOptionPane.showMessageDialog(null, "Successfully Sign Up");
                                ProjectManger.viewPane(ProjectManger.loginPage);
                                Name.setText("");
                                Password.setText("");
                            }
                        }
                        if(replySign.equals("notSignup"))
                        {
                            JOptionPane.showMessageDialog(null, "Sorry Name or Password is Already Exists", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        System.out.println("The Window Is Closed");
                        break;
                    }
                }
            }
        };
            t.start();
    }
}
