package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import versione1.Event;
import versione1.SoccerMatchEvent;
import versione1.SocialNetwork;
import versione1.User;
import versione2.StateValue;

import javax.swing.*;

public class EventController {

    public static final String SOCCER_NAME = "Partite di calcio";

    @FXML
    private Label placesAvbLbl, stateLblEvent, ageLbl, genderLbl, creatorLblEvent,titleLblEvent,numPLblEvent, deadLLblEvent, placeLblEvent, dateLblEvent, timeLblEvent, durLblEvent, indTeeLblEvent, totTeLblEvent, endDateLblEvent,endTimeLblEvent, noteLblEvent, ageLblEvent,genderLblEvent;
    @FXML
    private Button subScribeBtn;
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

        subScribeBtn.setDisable(eventSoccerSelected.isUserAlreadyRegistered(sessionUsername) ||eventSoccerSelected.getStateValue().equals(StateValue.Chiusa) == true);

        creatorLblEvent.setText(eventSoccerSelected.getCreator());

        stateLblEvent.setText(String.valueOf(eventSoccerSelected.getStateValue()));

        if(eventSoccerSelected.getTitle().getValue() != null){
            titleLblEvent.setText((String) eventSoccerSelected.getTitle().getValue());
        }
        else{
            titleLblEvent.setText(" ");
        }

        numPLblEvent.setText(String.valueOf(eventSoccerSelected.getNumOfParticipants().getValue()));

        if(eventSoccerSelected.getRegistrationDeadline().getValue() != null){
            deadLLblEvent.setText(String.valueOf(eventSoccerSelected.getRegistrationDeadline().getValue()));
        }
        else{
            deadLLblEvent.setText(" ");
        }

        if(eventSoccerSelected.getPlace().getValue() != null){
            placeLblEvent.setText((String) eventSoccerSelected.getPlace().getValue());
        }
        else{
            placeLblEvent.setText(" ");
        }

        if(eventSoccerSelected.getDate().getValue()!= null){
            dateLblEvent.setText(String.valueOf(eventSoccerSelected.getDate().getValue()));
        }
        else{
            dateLblEvent.setText(" ");
        }

        if(eventSoccerSelected.getTime().getValue() != null){
            timeLblEvent.setText(String.valueOf(eventSoccerSelected.getTime().getValue()));
        }
        else{
            timeLblEvent.setText(" ");
        }

        if(eventSoccerSelected.getDuration().getValue() != null){
            durLblEvent.setText(String.valueOf(eventSoccerSelected.getDuration().getValue()));
        }
        else{
            durLblEvent.setText(" ");
        }

        if(eventSoccerSelected.getIndTee().getValue() != null){
            indTeeLblEvent.setText(String.valueOf(eventSoccerSelected.getIndTee().getValue()));
        }
        else{
            indTeeLblEvent.setText(" ");
        }

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

        int postiDisponibili = (int) eventSoccerSelected.getNumOfParticipants().getValue() - eventSoccerSelected.getParticipants().size();
        placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);

    }

    public void subScribe(){

            if (!eventSoccerSelected.isUserAlreadyRegistered(sessionUsername)) {
                    eventSoccerSelected.addParticipant(sessionUsername);

                    socialNetwork.updateUserandEventsListFile();
                    subScribeBtn.setDisable(true);
                    int postiDisponibili = (int) eventSoccerSelected.getNumOfParticipants().getValue() - eventSoccerSelected.getParticipants().size();
                    placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);
            }
            else {
                JOptionPane.showMessageDialog(null, "Attenzione: risulti precedentemente iscritto");
            }

            for (int i = 0; i < eventSoccerSelected.getParticipants().size(); i++) {
                System.out.println(eventSoccerSelected.getParticipants().get(i));
            }

    }


}
