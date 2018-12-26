package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import versione1.Category;
import versione1.Event;
import versione1.EventSoccerMatch;

import java.time.LocalDate;

public class EventController {

    public static final String SOCCER_NAME = "Partite di calcio";

    @FXML
    private Label ageLbl, genderLbl, creatorLblEvent,titleLblEvent,numPLblEvent, deadLLblEvent, placeLblEvent, dateLblEvent, timeLblEvent, durLblEvent, indTeeLblEvent, totTeLblEvent, endDateLblEvent,endTimeLblEvent, noteLblEvent, ageLblEvent,genderLblEvent;

    EventSoccerMatch eventSoccerSelected;
    Category catSelected;

    public void setCatSelected(Category catSelected) { this.catSelected = catSelected;  }

    public void setEventSoccerSelected(Event eventSelected) {
        if(catSelected.getName().equals(SOCCER_NAME)) {
            this.eventSoccerSelected = (EventSoccerMatch) eventSelected;
        }
    }

    @FXML
    private void initialize(){


        creatorLblEvent.setText(eventSoccerSelected.getCreator().getUsername());

        if(eventSoccerSelected.getTitle().getValue() != null){
            titleLblEvent.setText((String) eventSoccerSelected.getTitle().getValue());
        }
        else{
            titleLblEvent.setText(" ");
        }

        numPLblEvent.setText(String.valueOf(eventSoccerSelected.getNumOfPartecipants().getValue()));

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

        if(catSelected.getName().equals(SOCCER_NAME)) {
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

    }


}
