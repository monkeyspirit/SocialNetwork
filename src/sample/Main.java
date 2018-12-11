package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import versione1.EventSoccerMatch;
import versione1.SoccerMatch;
import versione1.SocialNetwork;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root =  FXMLLoader.load(getClass().getResource("sample.fxml"));



        primaryStage.setTitle("Bacheca");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    /**
     * Metodo per inizializzare i componenti base del progetto
     */
    public static void initialized(){

        SocialNetwork social = new SocialNetwork();
        SoccerMatch soccer_match = new SoccerMatch();
        social.addCategory(soccer_match);
        EventSoccerMatch evento = new EventSoccerMatch();

    }

    public static void main(String[] args) {

        initialized();

        launch(args);

    }

}
