package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import versione1.EventSoccerMatch;
import utilities.FileUtility;
import versione1.SoccerMatch;
import versione1.SocialNetwork;
import versione1.User;
import versione2.ControlThread;

import static versione2.ControlThread.deleteAll;


public class Main extends Application {

    private static Stage guiStage;

    public static Stage getStage() {
        return guiStage;
    }

    /**
     * Il metodo start ha il compito principale di far avviare l'interfaccia grafica e impostare
     * tutti i parametri necessari al suo funzionamento, nel nostro caso si occupa anche di creare le istnze iniziali (in mancanza
     * di un metodo di lettur scrittura da file)
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        guiStage = primaryStage;


        // Creo le istanze principali del mio programma --> quando inseriremo il metodo di lettura
        // e scrittura da file qui ci vorra una struttura di controllo come un case o un if
        // e queste righe saranno in una parte di codice alternativa

        SocialNetwork social = new SocialNetwork();
        social.loadUsersListFromFile(); //NUOVO: carico la lista di utenti da file

        SoccerMatch soccer_match = new SoccerMatch();
        social.addCategory(soccer_match);

        User system = social.getUsers().get(0); //prendo il primo utente di quella lista (non volevo cancellarti l'esempio)

        EventSoccerMatch eventSoccerMatch1 = new EventSoccerMatch("Partita a Mompiano", 5, system.getUsername() );
        eventSoccerMatch1.addPartecipants(system.getUsername());
        EventSoccerMatch eventSoccerMatch2 = new EventSoccerMatch("Partita a Remedello", 2, system.getUsername());
        eventSoccerMatch2.addPartecipants(system.getUsername());
        soccer_match.addEvent(eventSoccerMatch1);
        soccer_match.addEvent(eventSoccerMatch2);

        soccer_match.controlEventState();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        LoginController loginContr = new LoginController();

        loader.setController(loginContr);

        loginContr.setSocialNetwork(social);

        ControlThread t1 = new ControlThread();
        t1.start();
        t1.setSocialNetwork(social);


        // Imposto lo stage e la scene principali
        Parent root =  (Parent) loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Accesso");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }







}
