package xo;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProjectManger {
 
    static GamePane gamePane = new GamePane();
    static FirstPage firstPage = new FirstPage();
    static SingleNamePane singleNamePane = new SingleNamePane();
    static MultiNamesPane multiNamepane = new MultiNamesPane();
    static LogInPage loginPage = new LogInPage();
    static SignUpPage signupPage = new SignUpPage();
    
    static HomeUISingle homeSingle = new HomeUISingle();
    static HomeUIMulti homeMulti = new HomeUIMulti();
    static HomeUIOnline homeOnline = new HomeUIOnline();
    
    // Next Task i'll move this in the HomeUiMult
    static BattleHistoryUI battleHistory = new BattleHistoryUI();
    static boolean videoFlag;
    // Check If Playing Vs Computer
    static boolean  vsComputer;
    
    //Record Game When Checked Set It True To The GamePane Section
    static boolean startRecordGame ;  //Here To Record Data In The Files
    static boolean recordHasBeenStarted;
    
    //Online Section
    public static boolean onlineMode = false;
    public static boolean testFlag;
    
    //Online Flag Game
    public static boolean isInGame = false;
    
    public static void viewPane(Pane pane)
    {
        gamePane.setVisible(false);
        firstPage.setVisible(false);
        singleNamePane.setVisible(false);
        multiNamepane.setVisible(false);
        loginPage.setVisible(false);
        signupPage.setVisible(false);
        homeSingle.setVisible(false);
        homeMulti.setVisible(false);
        homeOnline.setVisible(false);
        battleHistory.setVisible(false);
        pane.setVisible(true);
    }
}
