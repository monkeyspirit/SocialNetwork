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

	/**
	 * Cerca un evento a partire dal nome
	 * @param eventNameToFind il nome dell'evento da cercare
	 * @return l'evento, se questo è stato trovato
	 */
	public Event findEventByName(String eventNameToFind){
		for (Event event : events) {
			if(eventNameToFind.equalsIgnoreCase((String)event.getTitle().getValue()))
				return event;
		}
	    return null;
    }

	/**
	 * Controlla se un evento con un certo nome è già presente nella lista degli eventi
	 * @param event l'evento da cercare
	 * @return true se l'evento è già presente, false altrimenti
	 */
	public boolean doesEventAlreadyExist(String event){

		for (Event e:events) {
			if(event.equalsIgnoreCase((String) e.getTitle().getValue()))
				return true;
		}
		return false;
    }

	public void controlEventState(){
		for(int i=0; i<events.size(); i++){
			events.get(i).controlState();
		}
	}
}
