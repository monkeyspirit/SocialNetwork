package versione1;

import java.util.ArrayList;

public abstract class Category {

	//Attributi
	private ArrayList<Event> events;

	/**
	 * Costruttore Category
	 */
	public Category() {
		this.events =  new ArrayList<>();
	}

	//Metodi
	public abstract String getName();
	public abstract String getDescription();
	
}
