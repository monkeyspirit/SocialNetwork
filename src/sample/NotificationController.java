package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import versione1.User;
import versione2.notifications.Notification;


public class NotificationController {

    private User sessionUser;
    private String notification;
    private Stage thisStage;

    public void setThisStage(Stage thisStage) { this.thisStage = thisStage; }

    public void setNotification(String notification) { this.notification = notification; }
    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }

    @FXML
    private Label messageLbl;
    @FXML
    private Button removeBtn;

    @FXML
    private void initialize(){
        messageLbl.setText(notification);
    }

}
