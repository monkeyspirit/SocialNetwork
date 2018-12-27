package versione1;

import java.util.ArrayList;

public abstract class Category {

	//Attributi
	private ArrayList<Event> events;
	private String name;
	private String description;

	/**
	 * Costruttore Category: inizializza il nome e la descrizione della categoria (figlia)
	 * e l'arratlist degli eventi associati alla categoria creata
	 * @param name
	 * @param description
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

	public String toString() { return this.name; }

	public void controlEventState(){
		for(int i=0; i<events.size(); i++){
			events.get(i).controlState();
		}
	}

}
