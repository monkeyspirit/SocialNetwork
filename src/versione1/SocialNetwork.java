package versione1;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SocialNetwork  {

	//Attributi
	private ArrayList<Category> cateogories;
	
	/**
	 * Costruttore SocialNetwork
	 */
	public SocialNetwork() {
		this.cateogories = new ArrayList<Category>();
	}
	
	//Metodi
	public void addCategory(Category category) {
		this.cateogories.add(category);
	}
	
	public ArrayList<Category> getCategories(){
		return this.cateogories;
	}
	
	
	public static void main(String[] args) {
		

	}

}
