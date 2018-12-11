package versione1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SocialNetwork {

	//Attributi
	private ArrayList<Category> categories;
	
	/**
	 * Costruttore SocialNetwork
	 */
	public SocialNetwork() {
		this.categories = new ArrayList<Category>();
	}

	//Metodi
	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	public ArrayList<Category> getCategories(){
		return this.categories;
	}


	
	

}
