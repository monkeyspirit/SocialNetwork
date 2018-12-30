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



	public Event findEventByName(String event){
	    Event find = null;

	    for(int i=0; i<events.size(); i++){
	        if(event.equals(events.get(i).getTitle().getValue())){
	            find = events.get(i);
            }
        }

	    return find;
    }

    public boolean arlExistEvent(String event){
        boolean find = false;

        for(int i=0; i<events.size(); i++){
            if(event.equalsIgnoreCase((String) events.get(i).getTitle().getValue())){
                find = true;
            }
        }

        return find;
    }

	public void controlEventState(){
		for(int i=0; i<events.size(); i++){
			events.get(i).controlState();
		}
	}
}
