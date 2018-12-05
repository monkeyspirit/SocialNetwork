package versione1;

import java.util.ArrayList;

public abstract class Category {
	private String name;
	private String description;
	private ArrayList<Event> events = new ArrayList<>();
	public abstract String getName();
	public abstract String getDescription();
	
}
