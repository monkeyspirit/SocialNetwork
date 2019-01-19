package versione2;
import versione1.Category;
import versione1.Event;
import versione1.SocialNetwork;
import versione1.User;
import versione2.notifications.Notification;
import versione2.notifications.NotificationsBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static versione2.StateValue.Fallita;

public class ControlThread extends Thread {

    int i;
    SocialNetwork socialNetwork;
    private Notification notificationToSend;
    private Notification reminder;
    private boolean thereIsReminder;


    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    @Override
    public void run() {

        while (true) {

        	if(socialNetwork != null) {
        		for (Category cat : socialNetwork.getCategories()) {
        		     for(Event event : (ArrayList<Event>) cat.getEvents()){
        		         if(controlState(event)){
                             List<String> destinationUser = event.getParticipants();
        		             User sendTo;

        		             for(int i=0; i<destinationUser.size(); i++){
        		                 sendTo = socialNetwork.findUserByName(destinationUser.get(i));
        		                 sendTo.addNotification(notificationToSend);
        		                 if(thereIsReminder){
                                     sendTo.addNotification(reminder);
                                 }
                             }
                         }
                     }
        		}
        	}

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        }


    /**
     * Controlla e modifica lo stato degli eventi
     */
    public boolean controlState(Event event){

        boolean isChanged = false;
        thereIsReminder=false;


        // Per gli eventi aperti:
        switch (event.getState().get(event.getState().size()-1).getStateValue()) {

            case Aperta:

                // se il numero di partecipanti e' uguale al numero richiesto e' chiusa
                if(event.isNumOfParticipantsEqualsMax()){
                    event.getState().add(new versione2.State(StateValue.Chiusa, LocalDate.now()));

                    isChanged = true;
                    notificationToSend = NotificationsBuilder.buildNotificationClosed((String) event.getTitle().getValue());
                    reminder = NotificationsBuilder.buildReminder((String) event.getTitle().getValue(), (LocalDate) event.getDate().getValue(), (LocalTime) event.getTime().getValue(), (String) event.getPlace().getValue(), (Float) event.getIndTee().getValue());
                    thereIsReminder=true;

                }

                // se la data di termine ed e' uguale ad oggi e non abbiamo il numero di partecipanti fallisce
                if(LocalDate.now().equals(event.getRegistrationDeadline().getValue()) && !event.isNumOfParticipantsEqualsMax()) {

                    event.getState().add(new versione2.State(Fallita, LocalDate.now()));
                    isChanged = true;

                    notificationToSend = NotificationsBuilder.buildNotificationFailed((String) event.getTitle().getValue());
                    thereIsReminder=false;
                }

                break;


            case Chiusa:
                //se la data di termine equivale ad oggi
                if(event.getEndDate().getValue() != null && LocalDate.now().equals(event.getEndDate().getValue())) {
                    event.getState().add(new versione2.State(StateValue.Conclusa, LocalDate.now()));
                    isChanged = true;
                    thereIsReminder=false;
                    notificationToSend = NotificationsBuilder.buildNotificationTerminated((String)event.getTitle().getValue());

                }
                break;
        }

        return isChanged;
    }


}
