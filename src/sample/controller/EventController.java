package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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

    public static final String TITLE_DESCRIPTION = "Campo facoltativo che consiste in un nome di fantasia attribuito all'evento";
    public static final String NUMPLAY_DESCRIPTION = "Campo obbligatorio che stabilisce il numero di persone da coinvolgere nell'evento";
    public static final String EXTRA_PARTECIPANTS_DESCRIPTION ="Campo facoltativo che indica quanti partecipanti siano eventualmente accettabili in esubero rispetto al \"Numero di partecipanti\"";
    public static final String REGDEADLINE_DESCRIPTION = "Campo obbligatorio che inidica l'ultima data possibile per iscriversi";
    public static final String RETIRED_DEADLINE_DESCRIPTION ="Campo facoltativo che indica la data entro cui a ogni fruitore che ha aderito all’evento è concesso di cancellare la sua iscrizione e al fruitore che ha proposto l’evento di ritirare la proposta";
    public static final String PLACE_DESCRIPTION = "Campo obbligatorio che indica l'indirizzo del luogo che ospitera'  l'evento oppure, se l'evento e' itinerante, il luogo di ritrovo dei partecipanti";
    public static final String DATE_DESCRIPTION = "Campo obbligatorio che indica la data in cui l'evento proposto deve svolgersi o, nel caso l'evento non termini nello stesso giorno in cui ha inizio, la data di inizio dell'evento";
    public static final String TIME_DESCRIPTION = "Campo obbligatorio che indica l'ora in cui i partecipanti dovranno trovarsi nel luogo 'Luogo' in data 'Data' per dare inizio all'evento";
    public static final String DURATION_DESCRIPTION =  "Campo facoltativo che indica la durata in termini di numero (approssimativo) di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano piu' giorni consecutivi";
    public static final String INDTEE_DESCRIPTION = "Campo obbligatorio che indica la spesa (o una stima della stessa) che ogni partecipante all'iniziativa dovra'  sostenere (si noti che la spesa puo' anche essere nulla)";
    public static final String TEEINC_DESCRIPTION = "Campo facoltativo che indica tutte le voci di spesa comprese nell'ammontare indicato nella 'Quota individuale'";
    public static final String ENDDATE_DESCRIPTION = "Campo facoltativo che fissa la data di conclusione dell'evento";
    public static final String ENDTIME_DESCRIPTION = "Campo facoltativo che stima l'ora di conclusione dell'evento";
    public static final String NOTE_DESCRIPTION = "Campo facoltativo contenente informazioni aggiuntive circa l'evento";

    public static final String GENDER_DESCRIPTION = "Sesso dei partecipanti";
    public static final String AGERANGE_DESCRIPTION = "limite inferiore e superiore di eta' dei partecipanti";

    @FXML
    private Label extranumPLblEvent,retiredDeadLLblEvent, placesAvbLbl, stateLblEvent, ageLbl, genderLbl, creatorLblEvent,titleLblEvent,numPLblEvent, deadLLblEvent, placeLblEvent, dateLblEvent, timeLblEvent, durLblEvent, indTeeLblEvent, totTeLblEvent, endDateLblEvent,endTimeLblEvent, noteLblEvent, ageLblEvent,genderLblEvent;
    @FXML
    private Button subScribeBtn, retiredParBtn, retiredEventBtn;
    @FXML
    private Label titleLbl, numPLbl, extraNumPLbl, deadLLbl, retiredDeadLLbl, placeLbl, dateLbl, timeLbl, durLbl, indTeeLbl, totTeLbl, endDateLbl, endTimeLbl, noteLbl;

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

        // Metto i tollpic sui campi sempre presenti

        initializeToolPic(titleLbl, TITLE_DESCRIPTION);
        initializeToolPic(numPLbl, NUMPLAY_DESCRIPTION);
        initializeToolPic(extraNumPLbl, EXTRA_PARTECIPANTS_DESCRIPTION);
        initializeToolPic(deadLLbl, REGDEADLINE_DESCRIPTION);
        initializeToolPic(retiredDeadLLbl, RETIRED_DEADLINE_DESCRIPTION);
        initializeToolPic(placeLbl, PLACE_DESCRIPTION);
        initializeToolPic(durLbl, DURATION_DESCRIPTION);
        initializeToolPic(dateLbl, DATE_DESCRIPTION);
        initializeToolPic(timeLbl, TIME_DESCRIPTION);
        initializeToolPic(indTeeLbl,TEEINC_DESCRIPTION);
        initializeToolPic(endDateLbl, ENDDATE_DESCRIPTION);
        initializeToolPic(endTimeLbl, ENDTIME_DESCRIPTION);
        initializeToolPic(noteLbl, NOTE_DESCRIPTION);

        //

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

            initializeToolPic(ageLbl, AGERANGE_DESCRIPTION);
            initializeToolPic(genderLbl, GENDER_DESCRIPTION);

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
        socialNetwork.updateUserAndEventsListFile();
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
        socialNetwork.updateUserAndEventsListFile();
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


    public void initializeToolPic( Label label, String DESCRIPTION){
        Tooltip tooltip = new Tooltip();
        tooltip.setText(DESCRIPTION);
        label.setTooltip(tooltip);

    }




}
