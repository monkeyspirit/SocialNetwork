package versione1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SocialNetwork extends Application {

	//Attributi
	private ArrayList<Category> categories;
	
	/**
	 * Costruttore SocialNetwork
	 */
	public SocialNetwork() {
		this.categories = new ArrayList<Category>();
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle("Bacheca");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}

	//Metodi
	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	public ArrayList<Category> getCategories(){
		return this.categories;
	}


	
	
	public static void main(String[] args) {
		SocialNetwork social = new SocialNetwork();

		SoccerMatch soccer_match = new SoccerMatch();
		social.addCategory(soccer_match);
		EventSoccerMatch evento = new EventSoccerMatch();

		launch(args);

	}

}
