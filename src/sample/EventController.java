package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import versione1.Event;
import versione1.SoccerMatchEvent;
import versione1.SocialNetwork;
import versione1.User;
import versione2.StateValue;
import versione2.notifications.Notification;
import versione2.notifications.NotificationsBuilder;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class EventController {

    public static final String SOCCER_NAME = "SoccerMatchEvent";

    @FXML
    private Label extranumPLblEvent,retiredDeadLLblEvent, placesAvbLbl, stateLblEvent, ageLbl, genderLbl, creatorLblEvent,titleLblEvent,numPLblEvent, deadLLblEvent, placeLblEvent, dateLblEvent, timeLblEvent, durLblEvent, indTeeLblEvent, totTeLblEvent, endDateLblEvent,endTimeLblEvent, noteLblEvent, ageLblEvent,genderLblEvent;
    @FXML
    private Button subScribeBtn, retiredParBtn, retiredEventBtn;
    private SocialNetwork socialNetwork;
    private SoccerMatchEvent eventSoccerSelected;
    private String sessionUsername;
    private User sessionUser;


    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public void setSessionUsername(String sessionUsername) { this.sessionUsername = sessionUsername; }

    public void setEventSelected(Event eventSelected) {
            this.eventSoccerSelected = (SoccerMatchEvent) eventSelected;

    }

    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }



    @FXML
    /**
     * Inizializzo tutti i parametri realtivi ai campi degli eventi
     * Creo i bottoni per l'iscrizione all'evento
     */
    private void initialize(){


        if(eventSoccerSelected.getStateValue().equals(StateValue.Aperta)){

                if (eventSoccerSelected.isUserAlreadyRegistered(sessionUsername)) {
                    if (eventSoccerSelected.isUserCreator(sessionUsername)) {
                        retiredParBtn.setDisable(true);
                        subScribeBtn.setDisable(true);
                        retiredEventBtn.setVisible(true);
                        retiredEventBtn.setDisable(false);
                    } else {
                        subScribeBtn.setDisable(true);
                        if(LocalDate.now().isAfter(eventSoccerSelected.getRetireDeadline().getValue())){
                            retiredParBtn.setDisable(true);
                        }
                        else{
                            retiredParBtn.setDisable(false);
                        }
                    }
                } else {
                    if (eventSoccerSelected.isNumOfTotalParticipantsEqualsMaxPlusTolerance()) {
                        retiredEventBtn.setDisable(true);
                        subScribeBtn.setDisable(true);
                    } else {
                        subScribeBtn.setDisable(false);
                        retiredEventBtn.setDisable(true);
                    }
                }


        }
        else {
            subScribeBtn.setDisable(true);
            retiredEventBtn.setDisable(true);
            retiredParBtn.setDisable(true);
        }




        creatorLblEvent.setText(eventSoccerSelected.getCreator());

        stateLblEvent.setText(String.valueOf(eventSoccerSelected.getStateValue()));

        if(eventSoccerSelected.getTitle().getValue() != null){
            titleLblEvent.setText((String) eventSoccerSelected.getTitle().getValue());
        }
        else{
            titleLblEvent.setText(" ");
        }



        numPLblEvent.setText(String.valueOf(eventSoccerSelected.getNumOfParticipants().getValue()));

        extranumPLblEvent.setText(String.valueOf(eventSoccerSelected.getExtraParticipants().getValue()));

        deadLLblEvent.setText(String.valueOf(eventSoccerSelected.getRegistrationDeadline().getValue()));

        retiredDeadLLblEvent.setText(String.valueOf(eventSoccerSelected.getRetireDeadline().getValue()));

        placeLblEvent.setText((String) eventSoccerSelected.getPlace().getValue());

        dateLblEvent.setText(String.valueOf(eventSoccerSelected.getDate().getValue()));

        timeLblEvent.setText(String.valueOf(eventSoccerSelected.getTime().getValue()));

        if(eventSoccerSelected.getDuration().getValue() != null){
            durLblEvent.setText(String.valueOf(eventSoccerSelected.getDuration().getValue()));
        }
        else{
            durLblEvent.setText(" ");
        }

        indTeeLblEvent.setText(String.valueOf(eventSoccerSelected.getIndTee().getValue()));

        if(eventSoccerSelected.getTeeInclude().getValue() != null){
            totTeLblEvent.setText(String.valueOf(eventSoccerSelected.getTeeInclude().getValue()));
        }
        else{
            totTeLblEvent.setText(" ");
        }

        if(eventSoccerSelected.getEndDate().getValue() != null){
            endDateLblEvent.setText(String.valueOf(eventSoccerSelected.getEndDate().getValue()));
        }
        else{
            endDateLblEvent.setText(" ");
        }

        if( eventSoccerSelected.getEndTime().getValue() != null){
            endTimeLblEvent.setText(String.valueOf( eventSoccerSelected.getEndTime().getValue()));
        }
        else{
            endTimeLblEvent.setText(" ");
        }

        if(eventSoccerSelected.getNote().getValue() != null){
            noteLblEvent.setText((String) eventSoccerSelected.getNote().getValue());
        }
        else{
            noteLblEvent.setText(" ");
        }


        if(eventSoccerSelected.getType().equals(SOCCER_NAME)) {
            ageLbl.setText("Fascia d'età");
            genderLbl.setText("Genere");
            ageLblEvent.setText((String) eventSoccerSelected.getAgeRange().getValue());
            if(eventSoccerSelected.getGender().getValue() != null){
                genderLblEvent.setText(String.valueOf(eventSoccerSelected.getGender().getValue()));
            }
           else{
                genderLblEvent.setText(" ");
            }
        }

        int postiDisponibili = (eventSoccerSelected.getNumOfParticipants().getValue() + eventSoccerSelected.getExtraParticipants().getValue() )- eventSoccerSelected.getParticipants().size();
        placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);

    }


    /**
     * Il metodo viene associato al pulsante iscrivi ed iscrive l'utente all'evento se ci sono posti disponibili
     */
    public void subScribe(){

        eventSoccerSelected.addParticipant(sessionUsername);
        socialNetwork.updateUserandEventsListFile();
        subScribeBtn.setDisable(true);
        retiredParBtn.setDisable(false);
        int postiDisponibili = (eventSoccerSelected.getNumOfParticipants().getValue() + eventSoccerSelected.getExtraParticipants().getValue() )- eventSoccerSelected.getParticipants().size();
        placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);


    }


    /**
     * Il metodo viene associato al pulsante DISiscrivi ed DISiscrive l'utente all'evento
     */
    public void disScribe(){

            eventSoccerSelected.removeParticipant(sessionUsername);
            socialNetwork.updateUserandEventsListFile();
            subScribeBtn.setDisable(false);
            retiredParBtn.setDisable(true);
            int postiDisponibili = (eventSoccerSelected.getNumOfParticipants().getValue() + eventSoccerSelected.getExtraParticipants().getValue() )- eventSoccerSelected.getParticipants().size();
            placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);


    }


    /**
     * Il metodo viene associato al pulsante ritira evento e ritira l'evento dalla bacheca
     */
    public void retiredEvent(){

        eventSoccerSelected.setState(new versione2.State(StateValue.DaRitirare, LocalDate.now()));
        subScribeBtn.setDisable(true);
        retiredEventBtn.setDisable(true);
        retiredParBtn.setDisable(true);
    }




}
