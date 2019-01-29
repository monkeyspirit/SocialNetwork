package sample.controller;

import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import versione1.*;
import versione2.StateValue;
import versione2.notifications.NotificationsBuilder;
import versione5.CinemaEvent;
import versione5.FilmType;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EventCreateController {

    public static final String SOCCER_NAME = "Partite di calcio";
    public static final String SOCCER_DESCRIPTION = "Categoria che ha lo scopo di proporre partite di calcio di vario genere.";

    public static final String CINEMA_NAME = "Cinema";
    public static final String CINEMA_DESCRIPTION = "Categoria che ha lo scopo di proporre la visione di film di vario genere.";


    // Messaggi di errore
    public static final String MISS_CATEGORY_MSG =  "Inserire una categoria";
    public static final String MISS_NUMBER_OF_PARTECIPANTS_MSG = "Inserire numero partecipanti";
    public static final String ERROR_NUMBER_REQUIRED = "Inserire un numero";
    public static final String MISS_DEADLINE_MSG = "Inserire la data di termine";
    public static final String MISS_PLACE_MSG = "Inserire il luogo";
    public static final String MISS_DATE_MSG = "Inserire la data in cui si tiene l'evento";
    public static final String MISS_TIME_MSG = "Inserire l'orario";
    public static final String ERROR_TIME_BECAUSE_ERROR_DATE_MSG = "L'orario inserito non risulta valido in quanto la data inserita non risulta valida";
    public static final String MISS_INDIVIDUAL_TEE_MSG = "Inserire la quota individuale";
    public static final String MISS_AGE_MSG = "Inserire la fascia di eta'";
    public static final String MISS_GENDER_MSG = "Inserire il genere";
    public static final String ERROR_ENDTIME_BEFORE_TIME_IF_DATE_EQUAL_ENDDATE_MSG = "L'orario di conclusione non risulta valido in quanto precedente all'orario di inizio";
    public static final String MISS_DURATION_OR_ENDDATE_MSG = "Inserire o la durata o il giorno e l'ora di termine";
    public static final String ALREADY_EXIST_EVENT_WITH_THIS_TITLE_MSG = "Esiste gia' un evento con quest titolo";
    public static final String ERR_EXTRATEE= "Errore nelle voci di spesa extra.";

    public static final String MISS_GENDER_FILM = "Manca il genere del film.";




    // ~~~~~ newEvent Stage ~~~~~~~~~~~

    @FXML
    private Label extraTeeLbl, minusLbl, typeOfFilmLbl, noteLbl, retiredDeadLLbl, extraParLbl, titleLbl,durLbl, durUnitLbl, catLbl, numPLbl, deadLLbl, placeLbl,dateLbl,timeLbl,indTeeLbl, endDateLbl, endTimeLbl, ageLbl,totTeLbl, genderLbl;
    @FXML
    private TextField titleTxtF, numPTxtF, placeTxtF, indTeeTxtF, extraNumParTxt, pastiExtraTF, gadgetExtraTF, rinfrescoExtraTF;
    @FXML
    private Button createBtn;
    @FXML
    private ChoiceBox<String> catCB;
    @FXML
    private DatePicker deadLineDP, dateDP,endDateDP, retiredDeadLineDP;
    @FXML
    private ChoiceBox<Integer> minAgeCB, maxAgeCB, durBigCB,durLitCB;
    @FXML
    private ChoiceBox<Gender> genderCB;
    @FXML
    private JFXTimePicker endTimeTP, timeTP;
    @FXML
    private TextArea noteTxtA, totTeeTxtA;
    @FXML
    private CheckBox isTeeActiveCkB, pastiCheckBox,gadgetCheckBox,rinfrescoCheckBox;
    @FXML
    private ListView<CheckBox> typeOfFilmListView, inviteParticipantsListView;


    // Asterischi campi di soccerMatch
    @FXML
    private Label segnoAge, segnoGen;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private AgeGroup ageGroup;

    private List<CheckBox> userCheckList;
    private List<CheckBox> typeOfFilmCheckList;

    private List<String> selectedUserToInvite;
    private List<String> typeOfFilmIns;

    private ArrayList<Integer> ageRangeMin;

    private String[] errorMsg = new  String[13];


    // ~~~~~~ Campi FACOLTATIVI ~~~~~~~~

    private String titleIns;
    private int extraNumIns;
    private String totTeeIns;
    private LocalDate retiredDeadLineIns;
    private LocalDate endDateIns;
    private LocalTime endTimeIns;
    private String noteIns;
    private int durH, durM, durD;
    private String durationIns;

    private boolean extraNumIsVal = false;
    private boolean endDateIsVal = false;
    private boolean endTimeIsVal = false;



    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // ~~~~~~ Campi OBBLIGATORI ~~~~~~

    private String categoryIns;
    private int numParIns;
    private LocalDate deadLineIns;
    private String placeIns;
    private LocalDate dateIns;
    private LocalTime timeIns;
    private float indTeeIns;
    private int minAge, maxAge;
    private String ageRangeIns;
    private Gender genderIns;


    private boolean catIsVal = false;
    private boolean titIsVal = false;
    private boolean numIsVal = false;
    private boolean deadLineIsVal = false;
    private boolean placeIsVal = false;
    private boolean dateIsVal = false;
    private boolean timeIsVal = false;
    private boolean indTeeIsVal = false;
    private boolean ageIsVal= false;
    private boolean genderIsVal = false;
    private boolean durIsVal = false;

    private boolean typeOfFilmIsVal = false;

    private float extraPastiTeeIns, extraRinfrescoTeeINs, extraGadgetTeeIns;

    private boolean extraTeeIsVal=false;

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    private SocialNetwork socialNetwork;
    private Stage thisStage;
    private String creator;


    // -------- Costanti da sistemare --------

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


    public static final String FILMTYPE_DESCRIPTION = "Indica la tipologia del film proposto, può elencare più generi";


    // ~~~~~~~~ Metodi ~~~~~~~~~~~~~


    public void setThisStage(Stage thisStage) { this.thisStage = thisStage; }

    public void setSocialNetwork(SocialNetwork socialNetwork) { this.socialNetwork = socialNetwork; }

    public void setCreator(String creator) { this.creator = creator;  }

    /**
     * Il metodo serve per inizializzare la finestra, in particolare per impostare gli observable list nei
     * choiceBox e per rendere determinati choiceBox abilitati o meno in base alla scelta della categoria.
     * Se si seleziona Partita di Calcio viene abilitata:
     * - la selezione della fascia di età
     * - la sezione del genere
     *
     * Poichè non ci deve essere inconsistenza tra fascia di età minima e massima il choiceBox della massima
     * viene abilitato solo dopo aver selezionato la minima e con valori suoeriori (maggiore stretto) dalla
     * minima selezionata.
     *
     * @throws IOException
     */
    @FXML
    private void initialize() throws IOException {


        // Metto i tollpic sui campi sempre presenti

        initializeToolPic(titleLbl, TITLE_DESCRIPTION);
        initializeToolPic(numPLbl, NUMPLAY_DESCRIPTION);
        initializeToolPic(extraParLbl, EXTRA_PARTECIPANTS_DESCRIPTION);
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

        userCheckList = new ArrayList<>();

        for(String username : socialNetwork.getUserThatPlayOtherCreatorEvents(creator)){

            CheckBox userCheck = new CheckBox();
            userCheck.setText(username);
            userCheckList.add(userCheck);
        }


        // Questa parte serve per creare il check box nella list view

        inviteParticipantsListView.setItems(FXCollections.observableList(userCheckList));


        ageGroup = new AgeGroup();
        ageRangeMin = ageGroup.getNumeri();

        List<String> catName = new ArrayList<>();
        for(Category category : socialNetwork.getCategories()){
            catName.add(category.getName());
        }

        catCB.setItems(FXCollections.observableArrayList(catName));

        // Imposto i giorni precedenti ad oggi non selezionabili su datePicker

        deadLineDP.setDayCellFactory(picker-> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty){
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now().plusDays(1)));
            }
        });

        deadLineDP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                retiredDeadLineDP.setDisable(false);
                dateDP.setDisable(false);
                timeTP.setDisable(false);

                retiredDeadLineDP.setDayCellFactory(picker-> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty){
                        super.updateItem(date, empty);
                        setDisable(empty || (date.isBefore(LocalDate.now().plusDays(1)) || date.isAfter(deadLineDP.getValue())));
                    }
                });



                dateDP.setDayCellFactory(picker-> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty){
                        super.updateItem(date, empty);
                        setDisable(empty || date.isBefore(deadLineDP.getValue().plusDays(1)));
                    }
                });

                dateDP.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        endDateDP.setDisable(false);
                        endTimeTP.setDisable(false);

                        endDateDP.setDayCellFactory(picker-> new DateCell() {
                            @Override
                            public void updateItem(LocalDate date, boolean empty){
                                super.updateItem(date, empty);
                                setDisable(empty || date.isBefore(dateDP.getValue()));
                            }
                        });
                    }
                });


            }
        });




        /**
         * ActionListeren che permette di ottenere la lista degli eventi della categoria selezionata sulla
         * ListView delle categorie
         */
        catCB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                switch(catCB.getSelectionModel().getSelectedItem()){

                    case(SOCCER_NAME): { //Se si seleziona il vuoto così non da errore

                        durBigCB.setDisable(false);
                        durLitCB.setDisable(true);

                        durUnitLbl.setText("Giorno/i");
                        durBigCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(1, 10)));

                        // Imposto ltri valori invisibili

                        typeOfFilmLbl.setVisible(false);
                        typeOfFilmListView.setVisible(false);
                        extraTeeLbl.setVisible(false);
                        pastiCheckBox.setVisible(false);
                        rinfrescoCheckBox.setVisible(false);
                        gadgetCheckBox.setVisible(false);
                        pastiExtraTF.setVisible(false);
                        gadgetExtraTF.setVisible(false);
                        rinfrescoExtraTF.setVisible(false);

                        //

                        genderLbl.setVisible(true);
                        ageLbl.setVisible(true);
                        segnoAge.setVisible(true);
                        segnoGen.setVisible(true);
                        minusLbl.setVisible(true);


                        minAgeCB.setVisible(true);
                        maxAgeCB.setVisible(true);

                        genderCB.setVisible(true);

                        minAgeCB.setDisable(false);
                        minAgeCB.setItems(FXCollections.observableArrayList(ageRangeMin));

                        genderCB.setDisable(false);


                        Tooltip tooltipCategory = new Tooltip();
                        tooltipCategory.setText(SOCCER_DESCRIPTION);
                        catLbl.setTooltip(tooltipCategory);


                        // Metto i tollpic sui campi NON sempre presenti

                        initializeToolPic(ageLbl, AGERANGE_DESCRIPTION);
                        initializeToolPic(genderLbl, GENDER_DESCRIPTION);


                        //
                        break;
                    }

                    case(CINEMA_NAME): {

                        durBigCB.setDisable(false);
                        durLitCB.setDisable(true);

                        durUnitLbl.setText("Giorno/i");
                        durBigCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(1, 10)));

                        //Prima imposto gli altri valori invisibili

                        genderLbl.setVisible(false);
                        ageLbl.setVisible(false);
                        segnoAge.setVisible(false);
                        segnoGen.setVisible(false);
                        minusLbl.setVisible(false);


                        minAgeCB.setVisible(false);
                        maxAgeCB.setVisible(false);

                        genderCB.setVisible(false);

                        //

                        typeOfFilmLbl.setVisible(true);
                        typeOfFilmListView.setVisible(true);
                        extraTeeLbl.setVisible(true);
                        pastiCheckBox.setVisible(true);
                        rinfrescoCheckBox.setVisible(true);
                        gadgetCheckBox.setVisible(true);
                        pastiExtraTF.setVisible(true);
                        gadgetExtraTF.setVisible(true);
                        rinfrescoExtraTF.setVisible(true);


                        // Imposto i generi dei film

                        typeOfFilmCheckList = new ArrayList<>();



                        for(FilmType type : FilmType.values() ){

                            CheckBox filmTypeCheck = new CheckBox();
                            filmTypeCheck.setText(type.toString());
                            typeOfFilmCheckList.add(filmTypeCheck);
                        }


                        // Questa parte serve per creare il check box nella list view

                        typeOfFilmListView.setItems(FXCollections.observableList(typeOfFilmCheckList));

                        Tooltip tooltipCategory = new Tooltip();
                        tooltipCategory.setText(CINEMA_DESCRIPTION);
                        catLbl.setTooltip(tooltipCategory);


                        // Metto i tollpic sui campi NON sempre presenti

                        initializeToolPic(typeOfFilmLbl, FILMTYPE_DESCRIPTION);

                        break;
                    }
                }


            }
        });




        /**
         * ActionListeren che permette una volta selezionato il minimo dell'età di selezionare il max, in questo modo lo popolo con i
         * numeri maggiorni stretti del minimo, dopo i 20 anni si va di decina in decina per facilitare
         * la scelta all'utente
         */
        minAgeCB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                maxAgeCB.setDisable(false);
                ArrayList<Integer> ageRangeMax = ageGroup.getMinOf(minAgeCB.getSelectionModel().getSelectedItem());
                maxAgeCB.setItems(FXCollections.observableArrayList(ageRangeMax));
            }
        });


        /**
         * ActionListener che controlla la selezione della data finale, se tale valore è uguale alla data iniziale
         * allora i ChoiceBox si settano per attendere la durata in ore e minuti, se la data finale è diversa dalla data iniziale
         * invece i ChoiceBox della durata si disattivano perchè in questo modo l'utente inserisce solo i valori esatti
         */
        endDateDP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if((endDateDP.getValue().isEqual(dateDP.getValue())) && (endTimeTP.getValue() == null)) {
                    durBigCB.setDisable(false);
                    durBigCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(0, 23)));
                    durLitCB.setDisable(false);
                    if(durBigCB.getValue()!= null && durBigCB.getValue().equals(0)){
                        durLitCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(1, 59)));
                    }
                    else{
                        durLitCB.setItems(FXCollections.observableArrayList(MyUtil.getArray(0, 59)));
                    }

                    durUnitLbl.setText("Ore:Minuti");
                    endTimeTP.setDisable(false);
                }
                else {
                    durBigCB.setDisable(true);
                    durLitCB.setDisable(true);
                    durUnitLbl.setText(" ");
                    endTimeTP.setDisable(false);
                }
            }
        });

        /**
         * ActionListener che nel caso in cui venga scelta l'ora finale disattiva i ChoiceBox della durata
         */
        endTimeTP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                durBigCB.setDisable(true);
                durLitCB.setDisable(true);
                durUnitLbl.setText(" ");
            }
        });


        isTeeActiveCkB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isTeeActiveCkB.isSelected()){
                    indTeeTxtF.setDisable(false);
                    indTeeIsVal=false;
                }
                else{
                    indTeeIns=0;
                    indTeeTxtF.setDisable(true);
                    indTeeIsVal=true;
                }
            }
        });

        //Aggiungo gli elementi dell'Enum <Gender> al Choice Box del genere
        genderCB.setItems(FXCollections.observableArrayList(Gender.Maschile, Gender.Femminile));

    }


    /**
     * Metodo per la creazione effettiva dell'evento, il metotodo applicato su thisStage chiude la finestra corrente una volta
     * inseriti tutti i campi e effettuati i controlli. I campi obbligatori sono:
     * - numero dei partecipanti
     * - termine ultimo iscrizione
     * - luogo
     * - data
     * - ora
     * - quota individuale
     * Gli altri campi sono facolatitivi e quindi possono anche essere omessi.
     * In oltre si ha l'obbligo prima di creare l'evento selezionare a quale categoria appartenga.
     * Ogni volta che viene selezionato qualcosa che non va bene o si commettono erroi viene stampato un msg
     * errore relativo a cosa succede e diventa rossa la label.
     *
     * Un'aggiunta e' relativa alla durata e alla data di termine, poiche' non puo' esistere un evento
     * che inizia ma non finisce l'utente e' pregato di inserire almeno uno dei due.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void createEvent(ActionEvent actionEvent) throws IOException {


        // OBBLIGATORIO
        // categoria

        // Acquisisco la categoria dal choiceBox, e se la categoria non viene selezionata non vado avanti
        // Nel caso in cui non sia selezionata diventa rossa la label e compare un msg di errore
        if (catCB.getSelectionModel().getSelectedItem() == null) {
            catLbl.setTextFill(Color.RED);
            catIsVal = false;
            JOptionPane.showMessageDialog(null, MISS_CATEGORY_MSG);
        } else {
            categoryIns = catCB.getSelectionModel().getSelectedItem();
            catLbl.setTextFill(Color.BLACK);
            catIsVal = true;


            // Acquisisco se c'e' il titolo
            // FACOLTATIVO
            //titolo
            if(titleTxtF.getText()!=null && socialNetwork.findCategoryByName(categoryIns).doesEventAlreadyExist(titleTxtF.getText()) == true){
                errorMsg[0] = ALREADY_EXIST_EVENT_WITH_THIS_TITLE_MSG;
                titleLbl.setTextFill(Color.RED);
                titIsVal = false;
            }
            else if (titleTxtF.getText()!=null && socialNetwork.findCategoryByName(categoryIns).doesEventAlreadyExist(titleTxtF.getText()) == false){
                titleIns = titleTxtF.getText();
                errorMsg[0] = null;
                titleLbl.setTextFill(Color.BLACK);
                titIsVal = true;

            }


            // Acquisisco il numero di partecipanti, controllo sia inserito e che sia un valore numerico
            // OBBLIGATORIO
            // numero partecipanti

            if (numPTxtF.getText().isEmpty() || !MyUtil.checkInteger(numPTxtF.getText())) {
                numPLbl.setTextFill(Color.RED);
                numIsVal = false;
                errorMsg[1] = MISS_NUMBER_OF_PARTECIPANTS_MSG;
            } else {
                String numPS = numPTxtF.getText();
                numPLbl.setTextFill(Color.BLACK);
                numParIns = Integer.parseInt(numPS);
                numIsVal = true;
                errorMsg[1] = null;
            }

            // Acquisisco il numero di partecipanti tollerati in più
            // FACOLTATIVO
            // tolleranza partecipanti
            if(extraNumParTxt.getText().isEmpty()){
                extraParLbl.setTextFill(Color.BLACK);
                extraNumIns = 0;
                extraNumIsVal = true;
                errorMsg[11]= null;
            }
            else{
                if(!MyUtil.checkInteger(extraNumParTxt.getText())){
                    extraParLbl.setTextFill(Color.RED);
                    extraNumIsVal = false;
                    errorMsg[11] = ERROR_NUMBER_REQUIRED;
                }
                else {
                    extraParLbl.setTextFill(Color.BLACK);
                    extraNumIns = Integer.parseInt(extraNumParTxt.getText());
                    extraNumIsVal = true;
                    errorMsg[11]= null;
                }
            }


            // Acquisco il campo data e controllo che non sia una data precedente ad oggi e vuota
            // OBBLIGATORIO
            // termine ultimo iscrizione

            if (deadLineDP.getValue() == null) {
                deadLLbl.setTextFill(Color.RED);
                deadLineIsVal = false;
                errorMsg[2] = MISS_DEADLINE_MSG;
            } else {
                deadLineIns = deadLineDP.getValue();
                deadLLbl.setTextFill(Color.BLACK);
                deadLineIsVal = true;
                errorMsg[2] = null;
            }

            // Acquisisco il termine di ritiro iscrizione
            // FACOLTATIVO
            // termine ultimo ritiro iscrizione

            if(retiredDeadLineDP.getValue() == null){
                retiredDeadLineIns = deadLineIns;
            }
            else{
                retiredDeadLineIns = retiredDeadLineDP.getValue();
            }

            // Acquisisco il luogo controllando che sia inserito e che sia una stringa
            // OBBLIGATORIO
            //luogo

            if (placeTxtF.getText().isEmpty() || !MyUtil.checkString(placeTxtF.getText())) {
                placeLbl.setTextFill(Color.RED);
                placeIsVal = false;
                errorMsg[3] = MISS_PLACE_MSG;
            } else {
                placeIns = placeTxtF.getText();
                placeLbl.setTextFill(Color.BLACK);
                placeIsVal = true;
                errorMsg[3] = null;
            }

            // Acquisisco la data dell'evento, controllo che il campo non sia vuoto, non sia prima il termine ultimo + 1
            // OBBLIGATORIO
            //data

            if ((dateDP.getValue() == null)) {
                dateLbl.setTextFill(Color.RED);
                dateIsVal = false;
                errorMsg[4] = MISS_DATE_MSG;
            } else {
                dateIns = dateDP.getValue();
                dateLbl.setTextFill(Color.BLACK);
                dateIsVal = true;
                errorMsg[4] = null;
            }

            // Acquisisco l ora a cui si verifichera
            // OBBLIGATORIO
            //ora

            if (timeTP.getValue() == null || dateIsVal == false) {
                timeLbl.setTextFill(Color.RED);
                timeIsVal = false;

                if(timeTP.getValue() == null){
                    errorMsg[5] = MISS_TIME_MSG;
                }
                else {
                    errorMsg[5] = ERROR_TIME_BECAUSE_ERROR_DATE_MSG;
                }

            } else {
                timeIns = timeTP.getValue();
                timeLbl.setTextFill(Color.BLACK);
                timeIsVal = true;
                errorMsg[5] = null;
            }

            // Acquisisco la quota individuale e controllo non sia vuota, in caso la converto in decimale, ma prima controllo
            // che il CheckBox sia selezionato o meno
            // OBBLIGATORIO
            //quota individuale

            if(isTeeActiveCkB.isSelected()){
                if (indTeeTxtF.getText().isEmpty() || !MyUtil.checkFloat(indTeeTxtF.getText())) {
                    indTeeLbl.setTextFill(Color.RED);
                    indTeeIsVal = false;
                    errorMsg[6] = MISS_INDIVIDUAL_TEE_MSG;
                } else {
                    String indTeeS = indTeeTxtF.getText();

                    indTeeIns = Float.parseFloat(indTeeS);
                    indTeeLbl.setTextFill(Color.BLACK);
                    indTeeIsVal = true;
                    errorMsg[6] = null;
                }
            }
            else{
                indTeeIns=0;
                indTeeIsVal = true;
                errorMsg[6] = null;
            }


            // Indicala voci di spesa
            // FACOLTATIVO
            // voci della quota
            totTeeIns = totTeeTxtA.getText();


            // Acquisisco se c e la data di termine e controllo che non sia prima della data dell evento
            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // data conclusiva

            if (endDateDP.getValue() != null && dateIsVal == true) {
                endDateIns = endDateDP.getValue();
                endDateLbl.setTextFill(Color.BLACK);
                endDateIsVal = true;
            }
            else {
                endDateIsVal = true;
                if(durBigCB.getValue() != null){
                    durD = durBigCB.getValue();
                    durationIns = String.valueOf(durD);

                }

            }


            // Acquisisco se c e l ora di termine e controllo non sia prima dell ora impostata nello stesso giorno
            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // ora conclusiva
            if (endTimeTP.getValue() != null && timeIsVal == true) {

                if (endTimeTP.getValue().isBefore(timeIns) && endDateIns.isEqual(dateIns)) {

                    endTimeLbl.setTextFill(Color.RED);
                    endTimeIsVal = false;
                    errorMsg[7] = ERROR_ENDTIME_BEFORE_TIME_IF_DATE_EQUAL_ENDDATE_MSG;

                } else {

                    endTimeIns = endTimeTP.getValue();
                    endTimeLbl.setTextFill(Color.BLACK);
                    endTimeIsVal = true;
                    errorMsg[7] = null;

                }
            }

            else {

                endTimeIsVal = true;

                if( durBigCB.getValue()!= null && durLitCB.getValue() != null){
                    durH = durBigCB.getValue();
                    durM = durLitCB.getValue();
                    durationIns = durH+":"+durM;
                }

            }

            // Acquisisco l eta controllando che l'utente la inserisca se ha scelto partita di calcio
            // OBBLIGATORIO -> se partita di calcio
            //fascia età
            // Acquisisco il genere se partita di calcio
            // OBBLIGATORIO -> se partita di calcio
            // genere

            switch (categoryIns){
                case (SOCCER_NAME): {

                    if (minAgeCB.getValue() != null && maxAgeCB.getValue() != null) {
                        minAge = minAgeCB.getValue();
                        maxAge = maxAgeCB.getValue();
                        ageGroup.setRange(minAge, maxAge);
                        ageRangeIns = ageGroup.getRange();
                        ageLbl.setTextFill(Color.BLACK);
                        ageIsVal = true;
                        errorMsg[8] = null;
                    } else {
                        ageLbl.setTextFill(Color.RED);
                        ageIsVal = false;
                        errorMsg[8] = MISS_AGE_MSG;
                    }


                    genderIns = genderCB.getSelectionModel().getSelectedItem();
                    if (genderCB.getSelectionModel().getSelectedItem() == null) {
                        genderLbl.setTextFill(Color.RED);
                        genderIsVal = false;
                        errorMsg[9] = MISS_GENDER_MSG;
                    } else {
                        genderLbl.setTextFill(Color.BLACK);
                        genderIsVal = true;
                        errorMsg[9] = null;
                    }

                    break;
                }

                case (CINEMA_NAME): {

                    boolean empty = true;

                    typeOfFilmIns = new ArrayList<>();

                    for(CheckBox check: typeOfFilmCheckList){
                        if(check.isSelected()){
                            empty = false;
                            typeOfFilmIns.add(check.getText());
                        }
                    }


                    if(empty){
                        typeOfFilmLbl.setTextFill(Color.RED);
                        typeOfFilmIsVal = false;
                        errorMsg[8] = MISS_GENDER_FILM;

                    }
                    else {
                        typeOfFilmIsVal = true;
                        errorMsg[8] = null;
                        typeOfFilmLbl.setTextFill(Color.BLACK);
                    }


                    // CheckBox voci di spesa aggiuntiva

                    boolean pasti = false;
                    boolean rinfresco = false;
                    boolean gadget = false;

                    if(pastiCheckBox.isSelected()){
                        if(MyUtil.checkFloat(pastiExtraTF.getText())){
                            extraPastiTeeIns=Float.parseFloat(pastiExtraTF.getText());
                            errorMsg[9] = null;
                            extraTeeLbl.setTextFill(Color.BLACK);
                            pasti = true;
                        }
                        else{
                            errorMsg[9] = ERR_EXTRATEE;
                            extraTeeLbl.setTextFill(Color.RED);
                            pasti = false;
                        }
                    }
                    else{
                        extraPastiTeeIns=0;
                        errorMsg[9] = null;
                        extraTeeLbl.setTextFill(Color.BLACK);
                        pasti = true;
                    }

                    if(gadgetCheckBox.isSelected()){
                        if(MyUtil.checkFloat(gadgetExtraTF.getText())){
                            extraGadgetTeeIns=Float.parseFloat(gadgetExtraTF.getText());
                            errorMsg[9] = null;
                            extraTeeLbl.setTextFill(Color.BLACK);
                            gadget=true;
                        }
                        else{
                            errorMsg[9] = ERR_EXTRATEE;
                            extraTeeLbl.setTextFill(Color.RED);
                            gadget=false;
                        }
                    }
                    else{
                        extraGadgetTeeIns=0;
                        extraTeeLbl.setTextFill(Color.BLACK);
                        errorMsg[9] = null;
                        gadget=true;
                    }

                    if(rinfrescoCheckBox.isSelected()){
                        if(MyUtil.checkFloat(rinfrescoExtraTF.getText())){
                            extraRinfrescoTeeINs=Float.parseFloat(rinfrescoExtraTF.getText());
                            errorMsg[9] = null;
                            extraTeeLbl.setTextFill(Color.BLACK);
                            rinfresco = true;
                        }
                        else{
                            errorMsg[9] = ERR_EXTRATEE;
                            extraTeeLbl.setTextFill(Color.RED);
                            rinfresco = false;
                        }
                    }
                    else{
                        extraRinfrescoTeeINs = 0;
                        errorMsg[9] = null;
                        extraTeeLbl.setTextFill(Color.BLACK);
                        rinfresco = true;
                    }

                    if(pasti && gadget && rinfresco){
                        extraTeeIsVal = true;
                    }
                    else{
                        extraTeeIsVal = false;
                    }

                }
            }



            // FACOLTATIVO -> ma se c e devo controllare la coerenza
            // durata --> guardare end time e end date
            if( (endDateDP.getValue() != null && endTimeTP.getValue() == null ) && durationIns == null ){
                errorMsg[10] = MISS_DURATION_OR_ENDDATE_MSG;
                durLbl.setTextFill(Color.RED);
                durIsVal = false;
            }
            else {
                errorMsg[10] = null;
                durLbl.setTextFill(Color.BLACK);
                durIsVal = true;
            }


            // FACOLTATIVO
            // noteIns
            noteIns = noteTxtA.getText();

            System.out.println(categoryIns);

           switch (categoryIns) {

                case (SOCCER_NAME): {

                    if (catIsVal && titIsVal && numIsVal && extraNumIsVal && deadLineIsVal && placeIsVal && dateIsVal && timeIsVal && endDateIsVal && endTimeIsVal && indTeeIsVal && ageIsVal && genderIsVal && durIsVal) {

                        if(endDateIns == null &&  durationIns !=null) {
                            endDateIns = dateIns.plusDays(Integer.parseInt(durationIns));
                        }
                        else if(endDateIns == null &&  durationIns == null){
                            endDateIns = dateIns.plusDays(1);
                        }
                        else{
                            if(endTimeIns == null && durationIns !=null){
                                endTimeIns = timeIns.plusHours(durH).plusMinutes(durM);

                            }
                        }

                        SoccerMatchEvent match = new SoccerMatchEvent(titleIns, numParIns, extraNumIns, deadLineIns, retiredDeadLineIns,  placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, ageRangeIns, genderIns, StateValue.Creata, LocalDate.now(), noteIns, creator);
                        match.addParticipant(creator);

                        selectedUserToInvite = new ArrayList<>(); // array di stringhe degli utenti a cui inviare la notifica

                        for(CheckBox check: userCheckList){
                            if(check.isSelected()){
                                selectedUserToInvite.add(check.getText());
                            }
                        }

                        for(String username : selectedUserToInvite){
                            User sendTo = socialNetwork.findUserByName(username);
                            sendTo.addNotification( NotificationsBuilder.buildNotificationInvite(titleIns));
                        }

                        socialNetwork.getSoccerMatchCategory().addEvent(match);

                        socialNetwork.writeSoccerMatchEventListOnFile();
                        socialNetwork.updateUsersListFile();

                        thisStage.close();

                    }
                    else {

                        System.out.println("cat:"+catIsVal +" tit:"+titIsVal+" num:"+ numIsVal+" extra:"+extraNumIsVal+" dead:"+deadLineIsVal+" place:"+placeIsVal +" date:"+ dateIsVal +" time:" +timeIsVal +" endd:" +endDateIsVal +" endt:" +endTimeIsVal +"indT:"+indTeeIsVal +" age:"+ ageIsVal +" gender:"+ genderIsVal + " dur:"+ durIsVal);


                            String msgPopUp = "";
                        for(int i=1; i< errorMsg.length; i++){
                            if(errorMsg[i] != null){
                                msgPopUp += errorMsg[i] + "\n";
                            }
                        }
                        JOptionPane.showMessageDialog(null, msgPopUp);
                    }

                    break;
                }

                case (CINEMA_NAME): {

                    if (catIsVal && titIsVal && numIsVal && extraNumIsVal && deadLineIsVal && placeIsVal && dateIsVal && timeIsVal && endDateIsVal && endTimeIsVal && indTeeIsVal && typeOfFilmIsVal && durIsVal && extraTeeIsVal) {

                        if(endDateIns == null &&  durationIns !=null) {
                            endDateIns = dateIns.plusDays(Integer.parseInt(durationIns));
                        }
                        else if(endDateIns == null &&  durationIns == null){
                            endDateIns = dateIns.plusDays(1);
                        }
                        else{
                            if(endTimeIns == null && durationIns !=null){
                                endTimeIns = timeIns.plusHours(durH).plusMinutes(durM);

                            }
                        }

                        CinemaEvent filmView = new CinemaEvent(titleIns, numParIns, extraNumIns, deadLineIns, retiredDeadLineIns,  placeIns, dateIns, timeIns, durationIns, indTeeIns, totTeeIns, endDateIns, endTimeIns, StateValue.Creata, LocalDate.now(), noteIns, creator, typeOfFilmIns, extraPastiTeeIns,  extraRinfrescoTeeINs, extraGadgetTeeIns);
                        filmView.addParticipant(creator);

                        selectedUserToInvite = new ArrayList<>(); // array di stringhe degli utenti a cui inviare la notifica

                        for(CheckBox check: userCheckList){
                            if(check.isSelected()){
                                selectedUserToInvite.add(check.getText());
                            }
                        }

                        for(String username : selectedUserToInvite){
                            User sendTo = socialNetwork.findUserByName(username);
                            sendTo.addNotification( NotificationsBuilder.buildNotificationInvite(titleIns));
                        }

                        socialNetwork.getCinemaCategory().addEvent(filmView);

                       // socialNetwork.writeSoccerMatchEventListOnFile();
                        socialNetwork.updateUsersListFile();

                        thisStage.close();

                    }
                    else {
                        String msgPopUp = "";
                        for(int i=1; i< errorMsg.length; i++){
                            if(errorMsg[i] != null){
                                msgPopUp += errorMsg[i] + "\n";
                            }
                        }
                        JOptionPane.showMessageDialog(null, msgPopUp);
                    }

                    break;
                }
            }
        }
    }



    public void initializeToolPic( Label label, String DESCRIPTION){
        Tooltip tooltip = new Tooltip();
        tooltip.setText(DESCRIPTION);
        label.setTooltip(tooltip);

    }
}