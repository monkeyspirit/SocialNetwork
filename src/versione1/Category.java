package versione1;

import java.util.ArrayList;
import java.util.List;

public abstract class Category <T extends Event> {

	//Attributi
	private List<T> events;
	private String name;
	private String description;

	/**
	 * Costruttore Category: inizializza il nome e la descrizione della categoria (figlia)
	 * e la list degli eventi associati alla categoria creata
	 * @param name
	 * @param description
	 */
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
		this.events = new ArrayList<>();
	}

	//Metodi

	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public List<T> getEvents() {
		return this.events;
	}

	public void setEvents(List<T> events) {
		this.events = events;
	}

	public void addEvent(T event) {
		this.events.add(event);
	}

	/**
	 * Cerca un evento a partire dal nome
	 * @param eventNameToFind il nome dell'evento da cercare
	 * @return l'evento, se questo è stato trovato
	 */
	public T findEventByName(String eventNameToFind){
		for (T event : events) {
			if(eventNameToFind.equalsIgnoreCase((String)event.getTitle().getValue()))
				return event;
		}
	    return null;
    }
    //----------Sostituiscilo ai metodi in social Network

	/**
	 * Controlla se un evento con un certo nome è già presente nella lista degli eventi
	 * @param event l'evento da cercare
	 * @return true se l'evento è già presente, false altrimenti
	 */
	public boolean doesEventAlreadyExist(String event){

		for (T e : events) {
			if(event.equalsIgnoreCase((String) e.getTitle().getValue()))
				return true;
		}
		return false;
    }


}
