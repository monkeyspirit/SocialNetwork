package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import versione1.Event;
import versione1.SoccerMatchEvent;
import versione1.SocialNetwork;
import versione1.User;
import versione2.StateValue;
import versione5.CinemaEvent;

import java.time.LocalDate;

public class EventController {

    public static final String SOCCER_NAME = "SoccerMatchEvent";
    public static final String CINEMA_NAME = "CinemaEvent";

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
    public static final String CREATOR_DESCRIPTION = "Creatore dell'evento";
    public static final String STATE_DESCRIPTION = "Stato in cui si trova l'evento";
    public static final String FILMTYPE_DESCRIPTION = "Indica la tipologia del film proposto, può elencare più generi";

    @FXML
    private Label creatorLbl, stateLbl, vociSpeseExtra, gadgetValueVentLbl, rinfrescoValueVentLbl, pastiValueVentLbl, extranumPLblEvent,retiredDeadLLblEvent, placesAvbLbl, stateLblEvent, ageLbl, genderLbl, creatorLblEvent,titleLblEvent,numPLblEvent, deadLLblEvent, placeLblEvent, dateLblEvent, timeLblEvent, durLblEvent, indTeeLblEvent, totTeLblEvent, endDateLblEvent,endTimeLblEvent, noteLblEvent, ageLblEvent,genderLblEvent;
    @FXML
    private Button subScribeBtn, retiredParBtn, retiredEventBtn;
    @FXML
    private Label titleLbl, numPLbl, extraNumPLbl, deadLLbl, retiredDeadLLbl, placeLbl, dateLbl, timeLbl, durLbl, indTeeLbl, totTeLbl, endDateLbl, endTimeLbl, noteLbl,codificaDurataLbl;
    @FXML
    private CheckBox gadgetEventCheckB, rinfrescoEventCheckB, pastiEventCheckB;

    private SocialNetwork socialNetwork;
    private Event event;
    private String sessionUsername;
    private User sessionUser;


    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public void setSessionUsername(String sessionUsername) { this.sessionUsername = sessionUsername; }

    public void setEventSelected(Event eventSelected) {
            this.event = eventSelected;

    }

    public void setSessionUser(User sessionUser) { this.sessionUser = sessionUser; }



    @FXML
    /**
     * Inizializzo tutti i parametri realtivi ai campi degli eventi
     * Creo i bottoni per l'iscrizione all'evento
     */
    private void initialize(){

        // Metto i tollpic sui campi sempre presenti
        initializeToolPic(creatorLbl, CREATOR_DESCRIPTION);
        initializeToolPic(stateLbl, STATE_DESCRIPTION);
        initializeToolPic(titleLbl, TITLE_DESCRIPTION);
        initializeToolPic(numPLbl, NUMPLAY_DESCRIPTION);
        initializeToolPic(extraNumPLbl, EXTRA_PARTECIPANTS_DESCRIPTION);
        initializeToolPic(deadLLbl, REGDEADLINE_DESCRIPTION);
        initializeToolPic(retiredDeadLLbl, RETIRED_DEADLINE_DESCRIPTION);
        initializeToolPic(placeLbl, PLACE_DESCRIPTION);
        initializeToolPic(durLbl, DURATION_DESCRIPTION);
        initializeToolPic(dateLbl, DATE_DESCRIPTION);
        initializeToolPic(timeLbl, TIME_DESCRIPTION);
        initializeToolPic(indTeeLbl,INDTEE_DESCRIPTION);
        initializeToolPic(totTeLbl, TEEINC_DESCRIPTION);
        initializeToolPic(endDateLbl, ENDDATE_DESCRIPTION);
        initializeToolPic(endTimeLbl, ENDTIME_DESCRIPTION);
        initializeToolPic(noteLbl, NOTE_DESCRIPTION);

        //

        if(event.getStateValue().equals(StateValue.Aperta)){

                if (event.isUserAlreadyRegistered(sessionUsername)) {

                    switch(event.getType()){
                        case (CINEMA_NAME): {

                            boolean[] settings = event.extraCheckBoxSettings(sessionUsername);

                            gadgetEventCheckB.setSelected(settings[0]);
                            rinfrescoEventCheckB.setSelected(settings[1]);
                            pastiEventCheckB.setSelected(settings[2]);

                            gadgetEventCheckB.setDisable(true);
                            rinfrescoEventCheckB.setDisable(true);
                            pastiEventCheckB.setDisable(true);
                            break;
                        }
                    }

                    if (event.isUserCreator(sessionUsername)) {
                        retiredParBtn.setDisable(true);
                        subScribeBtn.setDisable(true);
                        retiredEventBtn.setVisible(true);
                        retiredEventBtn.setDisable(false);
                    } else {
                        subScribeBtn.setDisable(true);
                        if(LocalDate.now().isAfter(event.getRetireDeadline().getValue())){
                            retiredParBtn.setDisable(true);
                        }
                        else{
                            retiredParBtn.setDisable(false);
                        }
                    }
                } else {
                    if (event.isNumOfTotalParticipantsEqualsMaxPlusTolerance()) {
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

            switch(event.getType()){
                case (CINEMA_NAME): {

                    boolean[] settings = event.extraCheckBoxSettings(sessionUsername);

                    gadgetEventCheckB.setSelected(settings[0]);
                    rinfrescoEventCheckB.setSelected(settings[1]);
                    pastiEventCheckB.setSelected(settings[2]);

                    gadgetEventCheckB.setDisable(true);
                    rinfrescoEventCheckB.setDisable(true);
                    pastiEventCheckB.setDisable(true);
                    break;
                }
            }
        }



        creatorLblEvent.setText(event.getCreator());

        stateLblEvent.setText(String.valueOf(event.getStateValue()));

        if(event.getTitle().getValue() != null){
            titleLblEvent.setText((String) event.getTitle().getValue());
        }
        else{
            titleLblEvent.setText(" ");
        }



        numPLblEvent.setText(String.valueOf(event.getNumOfParticipants().getValue()));

        extranumPLblEvent.setText(String.valueOf(event.getExtraParticipants().getValue()));

        deadLLblEvent.setText(String.valueOf(event.getRegistrationDeadline().getValue()));

        retiredDeadLLblEvent.setText(String.valueOf(event.getRetireDeadline().getValue()));

        placeLblEvent.setText((String) event.getPlace().getValue());

        dateLblEvent.setText(String.valueOf(event.getDate().getValue()));

        timeLblEvent.setText(String.valueOf(event.getTime().getValue()));

        if(event.getDuration().getValue() != null){
            durLblEvent.setText(String.valueOf(event.getDuration().getValue()));
            if( String.valueOf(event.getDuration().getValue()).contains(":")){
                codificaDurataLbl.setText("Ore:Minuti");
            }
            else{
                codificaDurataLbl.setText("Giorni");
            }
        }
        else{
            durLblEvent.setText(" ");
        }

        indTeeLblEvent.setText(String.valueOf(event.getIndTee().getValue()));

        if(event.getTeeInclude().getValue() != null){
            totTeLblEvent.setText(String.valueOf(event.getTeeInclude().getValue()));
        }
        else{
            totTeLblEvent.setText(" ");
        }

        if(event.getEndDate().getValue() != null){
            endDateLblEvent.setText(String.valueOf(event.getEndDate().getValue()));
        }
        else{
            endDateLblEvent.setText(" ");
        }

        if( event.getEndTime().getValue() != null){
            endTimeLblEvent.setText(String.valueOf( event.getEndTime().getValue()));
        }
        else{
            endTimeLblEvent.setText(" ");
        }

        if(event.getNote().getValue() != null){
            noteLblEvent.setText((String) event.getNote().getValue());
        }
        else{
            noteLblEvent.setText(" ");
        }


        switch(event.getType()){
            case (SOCCER_NAME): {

                ageLbl.setText("Fascia d'età");
                genderLbl.setText("Genere");

                initializeToolPic(ageLbl, AGERANGE_DESCRIPTION);
                initializeToolPic(genderLbl, GENDER_DESCRIPTION);

                ageLblEvent.setText((String) ((SoccerMatchEvent) event).getAgeRange().getValue());
                genderLblEvent.setText(String.valueOf(((SoccerMatchEvent) event).getGender().getValue()));
                break;
            }

            case (CINEMA_NAME): {
                vociSpeseExtra.setVisible(true);
                gadgetEventCheckB.setVisible(true);
                rinfrescoEventCheckB.setVisible(true);
                pastiEventCheckB.setVisible(true);

                gadgetValueVentLbl.setVisible(true);
                pastiValueVentLbl.setVisible(true);
                rinfrescoValueVentLbl.setVisible(true);


                gadgetValueVentLbl.setText(String.valueOf(((CinemaEvent) event).getGadgetExtra().getValue())+" €");
                pastiValueVentLbl.setText(String.valueOf(((CinemaEvent) event).getPastiExtra().getValue())+" €");
                rinfrescoValueVentLbl.setText(String.valueOf(((CinemaEvent) event).getRinfreschiExtra().getValue())+" €");

                genderLbl.setText("Genere");
                initializeToolPic(genderLbl, FILMTYPE_DESCRIPTION);

                String gender = "";
                for(String type: ((CinemaEvent) event).getTypes().getValue()){
                    gender = gender + type +", ";
                }
                genderLblEvent.setText(gender);
                break;

            }
        }





    int postiDisponibili = (event.getNumOfParticipants().getValue() + event.getExtraParticipants().getValue() )- event.getParticipants().size();
        placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);

    }


    /**
     * Il metodo viene associato al pulsante iscrivi ed iscrive l'utente all'evento se ci sono posti disponibili
     */
    public void subScribe(){


        float[] extra = {0, 0 ,0};

        if((event.getType()).equalsIgnoreCase(CINEMA_NAME)){


            gadgetEventCheckB.setDisable(true);
            rinfrescoEventCheckB.setDisable(true);
            pastiEventCheckB.setDisable(true);


            if(gadgetEventCheckB.isSelected()){
                extra[0]=((CinemaEvent) event).getGadgetExtra().getValue();
            }
            if(rinfrescoEventCheckB.isSelected()){
                extra[1]=((CinemaEvent) event).getRinfreschiExtra().getValue();
            }
            if(pastiEventCheckB.isSelected()){
                extra[2]=((CinemaEvent) event).getPastiExtra().getValue();
            }

            event.addParticipant(sessionUsername, extra);

        }
        else{
            event.addParticipant(sessionUsername);
        }

        socialNetwork.updateUserAndEventsListFile();
        subScribeBtn.setDisable(true);
        retiredParBtn.setDisable(false);

        int postiDisponibili = (event.getNumOfParticipants().getValue() + event.getExtraParticipants().getValue() )- event.getParticipants().size();
        placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);


    }


    /**
     * Il metodo viene associato al pulsante DISiscrivi ed DISiscrive l'utente all'evento
     */
    public void disScribe(){

        switch(event.getType()){
            case (CINEMA_NAME): {

                gadgetEventCheckB.setDisable(false);
                rinfrescoEventCheckB.setDisable(false);
                pastiEventCheckB.setDisable(false);
                break;
            }
        }

        event.removeParticipant(sessionUsername);
        socialNetwork.updateUserAndEventsListFile();
        subScribeBtn.setDisable(false);
        retiredParBtn.setDisable(true);
        int postiDisponibili = (event.getNumOfParticipants().getValue() + event.getExtraParticipants().getValue() )- event.getParticipants().size();
        placesAvbLbl.setText("Posti disponibili: "+postiDisponibili);


    }


    /**
     * Il metodo viene associato al pulsante ritira evento e ritira l'evento dalla bacheca
     */
    public void retiredEvent(){

        event.setState(new versione2.State(StateValue.DaRitirare, LocalDate.now()));
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
