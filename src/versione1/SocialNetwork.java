package versione1;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SocialNetwork  {

	//Attributi
	private ArrayList<Category> cateogories;
	
	/**
	 * Costruttore SocialNetwork
	 */
	public SocialNetwork() {
		this.cateogories = new ArrayList<Category>();
	}
	
	//Metodi
	public void addCategory(Category category) {
		this.cateogories.add(category);
	}
	
	
	public static void main(String[] args) {
		SocialNetwork social = new SocialNetwork();
		SoccerMatch soccer_match = new SoccerMatch();
		social.addCategory(soccer_match);
		EventSoccerMatch evento = new EventSoccerMatch();
		String messaggio = "Scegli che categoria visualizzare tra le seguenti: \n 1 - " + soccer_match.getName();
		String output = null;
		int input = Integer.parseInt( JOptionPane.showInputDialog(messaggio) );
		if(input == 1) {
			output = evento.getTitle().getName() +"\n"+
				     evento.getNumOfPartecipants().getName() +"\n"+
					 evento.getRegistrationDeadline().getName() +"\n"+
					 evento.getPlace().getName();
					//etc..
		}
		JOptionPane.showMessageDialog(null, output);

	}

}
