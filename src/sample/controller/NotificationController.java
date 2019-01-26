package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import versione1.Event;
import versione1.SocialNetwork;
import versione1.User;
import versione2.notifications.Notification;
import versione2.notifications.NotificationType;


public class NotificationController {

    private SocialNetwork socialNetwork;
    private User sessionUser;
    private Notification notification;
    private int notificationIndex;
    private Stage thisStage;
    private ListView notificationListView;
    private Event event;

    public static final String MSG_INVITE ="Per iscriversi all'evento basta premere sul bottono \"Accetta invito\", nel caso non si fosse sicuri di accettare o meno basta chiudere la finstra.";
    public static final String MSG_REMOVE ="Per rimuove la notifica dalla lista delle notifiche basta premere il pulsante \"Rimuovi\".";

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public void setThisStage(Stage thisStage) { this.thisStage = thisStage; }

    public void setNotification(Notification notification) { this.notification = notification; }

    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }

    public void setNotificationIndex(int notificationIndex) { this.notificationIndex = notificationIndex; }



    @FXML
    private Label messageAllertLbl, inviteLbl, removeLbl, messageReminderLbl;
    @FXML
    private Button removeBtn, acceptBtn;

    @FXML
    private void initialize(){

        if (notification.getNotificationType().equals(NotificationType.Alert) || notification.getNotificationType().equals(NotificationType.Invite) ){
            messageAllertLbl.setText(notification.getNotificationMessage());
        }
        else if(notification.getNotificationType().equals(NotificationType.Reminder)){
            messageReminderLbl.setText(notification.getNotificationMessage());
        }

        if (notification.getNotificationType().equals(NotificationType.Invite)) {
            acceptBtn.setVisible(true);
            inviteLbl.setText(MSG_INVITE);
            event = socialNetwork.findEventByEventName(notification.getEventName());
            if(event.isNumOfTotalParticipantsEqualsMaxPlusTolerance() || event.isUserAlreadyRegistered(sessionUser.getUsername())){
                acceptBtn.setDisable(true);
            }
            else {
                acceptBtn.setDisable(false);
            }
        }

        removeLbl.setText(MSG_REMOVE);


    }

    public void removeNotification(){
        sessionUser.removeNotification(notificationIndex);
        // Queste due righe mi servono perchè altrimenti rimangono notifiche eliminate all'interno del listView
        notificationListView.refresh();
        notificationListView.getSelectionModel().clearSelection(); // elimino la selezione precedente che in caso rimarrebbe salvata
        //

        socialNetwork.updateUsersListFile();

        thisStage.close();
    }

    public void setNotificationListView(ListView notificationListView) {
        this.notificationListView = notificationListView;
    }

    public void subscribeUser(){

        (socialNetwork.findEventByEventName(notification.getEventName())).addParticipant(sessionUser.getUsername());
        acceptBtn.setDisable(true);
        thisStage.close();
    }

}
