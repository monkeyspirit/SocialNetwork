package versione1;

import java.util.List;

public class SoccerMatchCategory extends Category<SoccerMatchEvent> {

	public static final String SOCCER_NAME = "Partite di calcio";
	public static final String SOCCER_DESCRIPTION = "Categoria che ha lo scopo di proporre partite di calcio di vario genere.";

	/**
	 * Costruttore SoccerMatchCategory: invoca il costruttore di Category passando come parametri
	 * il nome della categoria e la sua descrizione
	 *
	 */
	public SoccerMatchCategory() {
		super(SOCCER_NAME, SOCCER_DESCRIPTION);
	}



	@Override
	public List<SoccerMatchEvent> getEvents() {
		return super.getEvents();
	}

	@Override
	public void setEvents(List<SoccerMatchEvent> events) {
		super.setEvents(events);
	}

	/**
	 * Metodo per l'aggiunta di un nuovo evento alla list di eventi
	 * @param event
	 */
	@Override
    public void addEvent(SoccerMatchEvent event) {
		super.addEvent(event);
    }
}
