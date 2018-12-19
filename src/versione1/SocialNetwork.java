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



	public Category findCategoryByName(String name){
		Category find = null;

		for(int i=0; i<categories.size(); i++){
			if ( name.equals(categories.get(i).getName())){
				find = categories.get(i);
			}
		}

		return find;
	}

	public Category findCategoryByIndex(int index){
		return categories.get(index);
	}

	
}
