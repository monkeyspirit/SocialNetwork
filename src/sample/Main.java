package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.LoginController;
import versione1.*;
import versione2.ControlThread;
import versione5.CinemaCategory;

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

        User system = social.getUsers().get(0); //prendo il primo utente di quella lista

        social.loadAllEventsFromFile(); //carico tutti gli eventi

        //Il blocco seguente serve per creare un nuovo evento e scriverlo su file
//
//        SoccerMatchEvent soccerMatchEvent1 = new SoccerMatchEvent(
//                "Finale a San Siro",
//                10,
//                LocalDate.of(2019, Month.MARCH, 1),
//                "San Siro",
//                LocalDate.of(2019, Month.MARCH, 8),
//                LocalTime.of(21,00),
//                "2",
//                25,
//                "Pulizia spogliatoi, affitto campo, divise nuove",
//                LocalDate.of(2019, Month.FEBRUARY, 15),
//                LocalTime.of(22,45),
//                "18-25",
//                Gender.Maschile,
//                "Finale",
//                "Giacomo"
//        );
//        soccerMatchEvent1.addParticipant("Giacomo"); //posso aggiungere il creatore?
//        soccerMatchEvent1.addParticipant("Maria");
//        soccerMatchEvent1.addParticipant("Pietro");
//
//        social.getSoccerMatchCategory().addEvent(soccerMatchEvent1);
//        social.writeSoccerMatchEventListOnFile();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        LoginController loginContr = new LoginController();

        loader.setController(loginContr);

        loginContr.setSocialNetwork(social);

        ControlThread controlThread = new ControlThread();
        controlThread.start();
        controlThread.setSocialNetwork(social);



        // Imposto lo stage e la scene principali
        Parent root =  (Parent) loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Accesso");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);

        if(!guiStage.isShowing()){
            System.exit(4);
        }

    }







}
