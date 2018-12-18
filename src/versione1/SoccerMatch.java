package versione1;

public class SoccerMatch extends Category {

	public static final String SOCCER_NAME = "Partite di calcio";
	public static final String SOCCER_DESCRIPTION = "Categoria che ha lo scopo di proporre partite di calcio di vario genere.";

	/**
	 * Costruttore SoccerMatch: invoca il costruttore di Category passando come parametri
	 * il nome della categoria e la sua descrizione
	 *
	 */
	public SoccerMatch() {
		super(SOCCER_NAME, SOCCER_DESCRIPTION);
	}



    public void addEvent(EventSoccerMatch event) {
		super.getEvents().add(event);
    }
}
