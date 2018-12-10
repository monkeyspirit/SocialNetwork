package versione1;

import java.util.ArrayList;

public abstract class Category {

	//Attributi
	private ArrayList<Event> events;
	private String name;
	private String description;

	/**
	 * Costruttore Category
	 */
	public Category(String name, String description) {
		this.events =  new ArrayList<>();
		this.name = name;
		this.description = description;
	}

	//Metodi
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public ArrayList<Event> getEvents() {
		return this.events;
	}
	
	public void addEvent(Event event) {
		this.events.add(event);
	}
	
}
