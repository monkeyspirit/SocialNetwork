package main.model;
import main.model.event.Event;
import main.model.event.StateValue;
import main.model.notifications.Notification;
import main.model.notifications.NotificationsBuilder;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;



/**
 * Classe che si occupa di osservare la lista di eventi per riconoscerne i cambiamenti e aggiornare
 * lo stato del social network in base a questi. Per esempio cambia lo stato di un evento quando i
 * requisiti per la transizione sono verificati
 */
public class ControlThread extends Thread{

    SocialNetwork socialNetwork;

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    @Override
    public void run() {

        while (true) {
            if(socialNetwork != null) {
                controlState();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public Category getCategoryFromEvent(Event event){
        for(Category cat : this.socialNetwork.getCategories()){
            if(cat.getEvents().contains((event)))
                return cat;
        }
        return null;
    }

    public void sendNotificationForCreation(Event event, Notification notification){
        Category cat = getCategoryFromEvent(event);
        for (User user: socialNetwork.getUsers() ) {
            if(!(user.equals(socialNetwork.getLoggedUser())) && user.getCategoryPref().contains(cat.getName())){
                user.addNotification(notification);
            }
        }
    }

    public void sendNotificationAndReminder(Event event, Notification notification){
        User sendTo;
        for(int i=0; i<event.getParticipants().size(); i++){
            sendTo=socialNetwork.findUserByName(event.getParticipants().get(i).getUsername());
            sendTo.addNotification(notification);

            if(event.getState().getStateValue() == StateValue.Chiusa){
                sendTo.addNotification( NotificationsBuilder.buildReminder((String) event.getTitle().getValue(),
                        (LocalDate) event.getDate().getValue(), (LocalTime) event.getTime().getValue(), (String) event.getPlace().getValue(),
                        (Float) event.getIndividualTee().getValue(), event.getParticipants().get(i).getExtra() )  );

            }
        }
    }

    public void sendNotification(Event event, Notification notification){
        if(notification != null) {
            if (event.getState().getStateValue() == StateValue.Aperta)
                sendNotificationForCreation(event, notification);
            else
                sendNotificationAndReminder(event, notification);
        }
    }


    /**
     * Controlla e modifica lo stato degli eventi
     */
    public void controlState(){
        Notification notification;
        for (Category cat : socialNetwork.getCategories()) {
            for(Event event : (ArrayList<Event>) cat.getEvents()){
                    sendNotification(event, event.checkChangeState());
            }
        }
    }


}

