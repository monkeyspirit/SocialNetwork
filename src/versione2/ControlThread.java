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


    SocialNetwork socialNetwork;
    private Notification notificationToSend;
    private Notification reminder;
    private boolean thereIsReminder;
    private List<String> destinationUser;



    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    @Override
    public void run() {

        while (true) {

            if(socialNetwork != null) {
                for (Category cat : socialNetwork.getCategories()) {
                    for(Event event : (ArrayList<Event>) cat.getEvents()){


                        if(event.getLastState().equals(StateValue.Creata) && controlState(event)) {
                            for (User user: socialNetwork.getUsers() ) {
                                if(user.getCategoryPref().contains(cat.getName())){
                                    user.addNotification(notificationToSend);
                                }
                            }
                        }
                        else if(controlState(event)){
                            User sendTo;
                            destinationUser = event.getParticipants();

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


            case Creata: {
                event.getState().add(new versione2.State(StateValue.Aperta, LocalDate.now()));
                isChanged = true;
                thereIsReminder = false;
                notificationToSend = NotificationsBuilder.buildNotificationNewEvent ((String) event.getTitle().getValue());


                break;
            }

            case Aperta: {

                // se il numero di partecipanti e' uguale al numero massimo e non ci si può più ritirare --> CHIUSA
                if (event.isNumOfTotalParticipantsEqualsMaxPlusTolerance() && LocalDate.now().isAfter(event.getRetireDeadline().getValue())) {
                    event.getState().add(new versione2.State(StateValue.Chiusa, LocalDate.now()));

                    isChanged = true;
                    notificationToSend = NotificationsBuilder.buildNotificationClosed((String) event.getTitle().getValue());
                    reminder = NotificationsBuilder.buildReminder((String) event.getTitle().getValue(), (LocalDate) event.getDate().getValue(), (LocalTime) event.getTime().getValue(), (String) event.getPlace().getValue(), (Float) event.getIndTee().getValue());
                    thereIsReminder = true;

                }

                // se il numero dei partecipanti è tra il numero minimo e la tolleranza e siamo oltre alla data di termine iscrizione --> CHIUSA
                if (event.isNumOfParticipantsMore() && LocalDate.now().isAfter(event.getRegistrationDeadline().getValue())) {
                    event.getState().add(new versione2.State(StateValue.Chiusa, LocalDate.now()));

                    isChanged = true;
                    notificationToSend = NotificationsBuilder.buildNotificationClosed((String) event.getTitle().getValue());
                    reminder = NotificationsBuilder.buildReminder((String) event.getTitle().getValue(), (LocalDate) event.getDate().getValue(), (LocalTime) event.getTime().getValue(), (String) event.getPlace().getValue(), (Float) event.getIndTee().getValue());
                    thereIsReminder = true;
                }

                // se la data di termine e' oltre oggi e non abbiamo il numero di partecipanti --> FALLITA
                if (LocalDate.now().isAfter(event.getRegistrationDeadline().getValue()) && !event.isNumOfParticipantsMore()) {

                    event.getState().add(new versione2.State(Fallita, LocalDate.now()));
                    isChanged = true;

                    notificationToSend = NotificationsBuilder.buildNotificationFailed((String) event.getTitle().getValue());
                    thereIsReminder = false;
                }break;
            }


            case Chiusa: {
                //se la data di termine è oltre oggi --> CONCLUSA
                if (event.getEndDate().getValue() != null && LocalDate.now().isAfter(event.getEndDate().getValue())) {
                    event.getState().add(new versione2.State(StateValue.Conclusa, LocalDate.now()));
                    isChanged = true;
                    thereIsReminder = false;
                    notificationToSend = NotificationsBuilder.buildNotificationTerminated((String) event.getTitle().getValue());

                }
                break;
            }

            case DaRitirare: {
                event.getState().add(new versione2.State(StateValue.Ritirata, LocalDate.now()));
                isChanged = true;
                thereIsReminder = false;
                notificationToSend = NotificationsBuilder.buildNotificationRetiredEvent((String) event.getTitle().getValue());

                break;
            }


        }

        return isChanged;
    }


}
