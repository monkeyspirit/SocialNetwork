package versione1;

public class SoccerMatch extends Category {

	public static final String SOCCER_NAME = "Partite di calcio";
	public static final String SOCCER_DESCRIPTION = "Categoria che ha lo scopo di proporre partite di calcio di vario genere. Ogni partita";


	public SoccerMatch() {
		super();
	}

	@Override
	public String getName() {
		return SOCCER_NAME;
	}

	@Override
	public String getDescription() {
		return SOCCER_DESCRIPTION;
	}

}
