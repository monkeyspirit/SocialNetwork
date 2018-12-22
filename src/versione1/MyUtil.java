package versione1;
import java.time.LocalDate;
import java.util.ArrayList;

public class MyUtil {


	
	
	
	// -----------------METODI CONTROLLO INPUT NUMERICI------------------
	
	
	
	
	//metodo che controlla che l'input di un campo sia un intero
	public static boolean checkInteger(String s) {
		try {
			int val = Integer.parseInt(s);
			if(val<=0)
				return false;
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	//metodo che controlla che il numero di occorrenze di un carattere in una stringa sia 1
	public static boolean numberOfOccurences(char c, String s){
		int count =0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) == c){
				count++;
				if (count >1)
					return false;
			}
		}
		if(count == 0)
			return false;
		else return true;
	}
	//metodo che fornisce l'indice di un carattere nella stringa
	public static int getIndexOfChar(char c, String s) {
		if(numberOfOccurences(c, s) == false)
			return -1;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == c)
				return i; 
		}
		return -1;
	}
	//metodo che controlla se l'input inserito è un float con "," al posto di "."
	public static boolean checkWeirdFloat(String s) {
		int index = getIndexOfChar(',', s);
		if(index == -1 || index == s.length() || index == 0)
			return false;
		String substr1, substr2;
		substr1 = s.substring(0, index);
		substr2 = s.substring(index, s.length());
		if (checkInteger(substr1) == true || checkInteger(substr2) == true)
			return true;
		return false;
	}
	
	//metodo che controlla che l'input sia un float
	public static boolean checkFloat(String s) {
		try {
			float val = Float.parseFloat(s);
			if (val<=0)
				return false;
		}
		catch(NumberFormatException e) {
			if(checkWeirdFloat(s) == true) {
				s = s.replace(',', '.');
				if(checkFloat(s) == true && Float.parseFloat(s)>0) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	//metodo che controlla che l'input inserito sia positivo

	
	
	
	
	
	// -----------------METODI CONTROLLO VALIDITA' DATA INSERITA-----------------------------
	
	

	public static boolean checkDate(LocalDate date) {
		return date.isBefore(LocalDate.now());
	}
	
	
	// -------------------METODI CONTROLLO VALIDITA' STRINGHE DI TESTO-------------------------
	
	public static boolean checkString(String s) {
		if(s.length() <= 1)
			return false;
		return true;
	}



	// ---------METODO PER IMPOSTAZIONE DELLA DURATA IN BASE A MINUTI, ORE Ì, GIORNI -----------

	public static ArrayList<Integer> getArray(int min, int max) {
		ArrayList<Integer> choose = new ArrayList<>();
		for(int i=min; i<=max; i++){
			choose.add(i);
		}
		return choose;
	}



	
	
}
