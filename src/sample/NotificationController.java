package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import versione1.User;


public class NotificationController {

    private User sessionUser;
    private String notification;
    private int notificationIndex;
    private Stage thisStage;
    private ListView notificationListView;

    public void setThisStage(Stage thisStage) { this.thisStage = thisStage; }

    public void setNotification(String notification) { this.notification = notification; }

    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }

    public void setNotificationIndex(int notificationIndex) { this.notificationIndex = notificationIndex; }



    @FXML
    private Label messageLbl;
    @FXML
    private Button removeBtn;

    @FXML
    private void initialize(){
        messageLbl.setText(notification);
    }

    public void removeNotification(){
        sessionUser.removeNotification(notificationIndex);
        // Queste due righe mi servono perchè altrimenti rimangono notifiche eliminate all'interno del listView
        notificationListView.refresh();
        notificationListView.getSelectionModel().clearSelection(); // elimino la selezione precedente che in caso rimarrebbe salvata
        //
        thisStage.close();
    }

    public void setNotificationListView(ListView notificationListView) {
        this.notificationListView = notificationListView;
    }
}
