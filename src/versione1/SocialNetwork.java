package versione1;

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


	public Category findCategoryByIndex(int index){
		return categories.get(index);
	}


	
	

}
