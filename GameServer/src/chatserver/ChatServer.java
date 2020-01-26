package ChatServer;
import java.io.DataInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.S;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;
public class ChatServer {
    ServerSocket serverSocket;
    public ChatServer()
    {
        try {
            serverSocket = new ServerSocket(5005);
            while (true) {
                Socket s = serverSocket.accept();
                new ChatHandler(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        new ChatServer();   
    }
}
class ChatHandler extends Thread
{
    DataInputStream dis;
    PrintStream ps;
    static Vector <ChatHandler> clientsVectors  = new Vector<ChatHandler>();
    static ArrayList<String> playerNames = new ArrayList<String>();
    
    public ChatHandler(Socket s)
    {
        try {
            dis = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
            clientsVectors.add(this);
            start();
        } catch (IOException ex) {
            System.out.println("Waiting For Data To Get it");
        }
    }
    public void run()
    {
        while (true) {            
            String str = null;
            try {
                str = dis.readLine();
                System.out.println(str);
                //Send The Msg TO All
            StringTokenizer strToken = new StringTokenizer(str, ",");
            System.out.println(strToken.countTokens());
            //Check If The Test Is Login Test  
            String checkDirection = strToken.nextToken();
            //===============================
            //Login Section
            if(checkDirection.equals("login"))
            {
                try {
                    String playerName = strToken.nextToken();
                    
                    String playerPass = strToken.nextToken();
                    Connection con = Database.getDBConnection();
                    PreparedStatement pname = con.prepareStatement("SELECT playername FROM onlinemode WHERE playername =? ");
                    pname.setString(1, playerName);
                    ResultSet s1 = pname.executeQuery();
                    if(s1.next())
                    {
                        //s1.next();
                        if (s1.getString(1).equals(playerName)) {
                            PreparedStatement ppass = con.prepareStatement("SELECT pass FROM onlinemode WHERE playername =? ");
                            ppass.setString(1, playerName);
                            ResultSet s2 = ppass.executeQuery();
                            s2.next();
                            if (s2.getString(1).equals(playerPass)) {
                                //Add Online User On Login
                                playerNames.add(playerName);
                                isValidLogin(true,playerName);
                                for(String s : playerNames)
                                {
                                    SendMessageToAll(s);
                                }
                            }
                            else
                                isValidLogin(false,playerName);
                        }
                    }
                    else
                        isValidLogin(false,playerName);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //=====================================
            //SignUp Section
            if(checkDirection.equals("signup"))
            {
                try {
                    String playerName = strToken.nextToken();
                    String playerPass = strToken.nextToken();
                    Connection con = Database.getDBConnection();
                    //Insert Into DataBase
                    PreparedStatement pname = con.prepareStatement("SELECT playername FROM onlinemode WHERE playername =? ");
                    pname.setString(1, playerName);
                    ResultSet s1 = pname.executeQuery();
                    
                    if (s1.isBeforeFirst())
                    {
                        isValidSignup(false,playerName);
                    }
                    else
                    {
                        PreparedStatement pstmt = con.prepareStatement("insert into onlinemode (playername, pass) values (?,?)");
                        pstmt.setString(1, playerName);
                        pstmt.setString(2, playerPass);
                        int i = pstmt.executeUpdate();
                        if (i > 0) {
                            isValidSignup(true,playerName);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //====================================
            //Remove Online Players
            if(checkDirection.equals("remove"))
            {
                String playerName = null;
                if(strToken.hasMoreTokens())
                     playerName = strToken.nextToken();
                playerNames.remove(playerName);
                //=======================================
                //Remove Logout Players
                for(String s : playerNames)
                {
                    if(s.equals(playerNames.get(0)))
                        SendMessageToAll("first");
                    SendMessageToAll(s);
                }
            }
            if(checkDirection.equals("requestPlay"))
            {
                String playerName = strToken.nextToken();
                //The Request Sender
                String requestSender = strToken.nextToken();
                //=======================================
                //Send Request To The Another Player
                sendPlayRequest(playerName,requestSender);
            }
            if(checkDirection.equals("accaptedRequest"))
            {
                String playerName = strToken.nextToken();
                String playerReply = strToken.nextToken();
                //Send The Message To The Sender By Name
                sendAccaptedRequest(playerName,playerReply);
            }
            if(checkDirection.equals("gameStart"))
            {
                String gameChar = strToken.nextToken();
                String player1 = strToken.nextToken();
                String player2 = strToken.nextToken();
                //Send The Message To The Sender By Name
                gameStartRequest(player1,player2,gameChar);
            }
            //Handle The Game Reply From Client To Print It At The Another Client
            if(checkDirection.equals("XOActionReply"))
            {
                String printOnTheAnotherOne = strToken.nextToken();
                String btnName = strToken.nextToken();
                String gameCharReply = strToken.nextToken();
                gameActionReply(printOnTheAnotherOne,btnName,gameCharReply);
                
            }
            //Loser Baby
            if(checkDirection.equals("loser"))
            {
                String helloLoser = strToken.nextToken();
                loserMethod(helloLoser);
            }
            //draw Baby
            if(checkDirection.equals("drawKid"))
            {
                String helloLoser = strToken.nextToken();
                drawMethodWay(helloLoser);
            }
            //=======================================
            //Play Again Requests Accapt
            if(checkDirection.equals("playAgainAccapt1"))
            {
                String playerTwo = strToken.nextToken();
                playAgain1(playerTwo);
            }
            if(checkDirection.equals("playAgainAccapt2"))
            {
                String playerOne = strToken.nextToken();
                playAgain2(playerOne);
            }
            //======================================
            //Play Again Reject
            if(checkDirection.equals("playAgainReject1"))
            {
                String playerTwo = strToken.nextToken();
                playAgainReject1(playerTwo);
            }
            if(checkDirection.equals("playAgainReject2"))
            {
                String playerOne = strToken.nextToken();
                playAgainReject2(playerOne);
            }
            //This When The Player In The Game Right Now
            if(checkDirection.equals("rejectAccaptedRequest"))
            {
                String sender = strToken.nextToken();
                rejectRequestPlay(sender);
            }
            if(checkDirection.equals("exitContext"))
            {
                String anotherPlayer = strToken.nextToken();
                exitRequest(anotherPlayer);
            }
            if(checkDirection.equals("playOnePlayBack"))
            {
                String playerOne = strToken.nextToken();
                playBack(playerOne);
            }
            } catch (IOException ex) {
                System.out.println("Waiting For Data");
            }
        }
    }

    void SendMessageToAll(String msg)
    {
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(msg);
        }
    }
    public void isValidLogin(boolean flag,String playerName)
    {
        if(flag == true)
        {
            for(ChatHandler ch : clientsVectors) {
                ch.ps.println("logedIn," + playerName);
            }
            //This For Handle To Refresh The ListView On Login To The Server
            for(String s : playerNames)
            {
                if(s.equals(playerNames.get(0)))
                    SendMessageToAll("first");
            }
        }
        else
        {
            for(ChatHandler ch : clientsVectors) {
                ch.ps.println("notLogedIn,"+playerName);
            }
        }
    }
    public void isValidSignup(boolean flag,String playerName)
    {
        if(flag == true)
        {
            for(ChatHandler ch : clientsVectors) {
                ch.ps.println("signup,"+playerName);
            }
        }
        else
        {
            for(ChatHandler ch : clientsVectors) {
                ch.ps.println("notSignup");
            }
        }
    }
    public void sendPlayRequest(String playerName,String requestSender)
    {
        String sendReplyRequest = "reply," + playerName + ","+requestSender;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(sendReplyRequest);
        }
    }
    public void sendAccaptedRequest(String sender,String playerReply)
    {
        String sendAccaptedRequest = "accaptedRequest," + sender +","+playerReply;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(sendAccaptedRequest);
        }
    }
    public void rejectRequestPlay(String sender)
    {
        String sendRejectRequest = "rejectRequestPlay," + sender;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(sendRejectRequest);
        }
    }
    public void gameStartRequest(String player1,String player2,String gameChar)
    {
        String onlineRequest = "gameStartRequest,"+gameChar+","+player1+","+player2;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(onlineRequest);
        }
    }
    public void gameActionReply(String printOnTheAnotherOne,String btnName,String gameCharReply)
    {
        String actionReply = "gameActionReply," + printOnTheAnotherOne+","+btnName +","+gameCharReply;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(actionReply);
        }
    }
    public void loserMethod(String loser)
    {
        String loserReply = "loserBaby," +loser;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(loserReply);
        }
    }
    
    public void drawMethodWay(String anotherOne)
    {
        String drawPlayer = "drawBaby," +anotherOne;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(drawPlayer);
        }
    }
    public void playAgain1(String pTwo)
    {
        String playAgain1 = "playAgain1," +pTwo;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(playAgain1);
        }
    }
    public void playAgain2(String pOne)
    {
        String playAgain2 = "playAgain2," +pOne;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(playAgain2);
        }
    }
    public void playAgainReject1(String pTwo)
    {
        String playAgainReject1 = "againReject1," +pTwo;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(playAgainReject1);
        }
    }
    public void playAgainReject2(String pOne)
    {
        String playAgainReject2 = "againReject2," +pOne;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(playAgainReject2);
        }
    }
    public void exitRequest(String anotherPlayer)
    {
        String exitRequest = "exitAnotherPlayer," +anotherPlayer;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(exitRequest);
        }
    }
    public void playBack(String firstPlayer)
    {
        String exitRequest = "firstPlayerPlayBack," +firstPlayer;
        for(ChatHandler ch : clientsVectors) {
            ch.ps.println(exitRequest);
        }
    }
}
class Database {
    private static final Logger logger = Logger.getLogger(Database.class.getName());
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/XOGAME";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Elkholy@123";
    private Database() {}
    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException exception) {
            logger.log(Level.SEVERE, exception.getMessage());
        }
        return connection;
    }
}