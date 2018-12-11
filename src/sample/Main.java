package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import versione1.EventSoccerMatch;
import versione1.SoccerMatch;
import versione1.SocialNetwork;

import java.io.IOException;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));
        SampleController controller = new SampleController();

        SocialNetwork social = new SocialNetwork();
        SoccerMatch soccer_match = new SoccerMatch();
        social.addCategory(soccer_match);

        loader.setController(controller);

        controller.setSocialNetwork(social);

        System.out.println(social.getCategories().get(0).getName());

        Parent root =  (Parent) loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Bacheca");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Metodo per inizializzare i componenti base del progetto
     */


    public static void main(String[] args) {


        launch(args);

    }

}
