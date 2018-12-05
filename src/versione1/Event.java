package versione1;

import java.util.ArrayList;

public abstract class Event{

	//Attributes of the event class that are the same for all the events
	private ArrayList<Field> fields;
	private Field title = new Field("Titolo","Campo facoltativo che consiste in un nome di fantasia attribuito all’evento");
	private Field numOfPlayers = new Field("Numero di partecipanti", "Campo obbligatorio che stabilisce il numero di persone da coinvolgere nell'evento");
	private Field place = new Field("Luogo", "Campo obbligatorio che indica l’indirizzo del luogo che ospiterà l’evento oppure, se l’evento è itinerante, il luogo di ritrovo dei partecipanti");
	private Field date = new Field("Data","Campo obbligatorio che indica la data in cui l’evento proposto deve svolgersi o, nel caso l’evento non termini nello stesso giorno in cui ha inizio, la data di inizio dell’evento");
	private Field time = new Field("Ora", "Campo obbligatorio che indica l’ora in cui i partecipanti dovranno trovarsi nel luogo “Luogo” in data “Data” per dare inizio all’evento");
	private Field duration = new Field("Durata", "Campo facoltativo che indica la durata in termini di numero (approssimativo) di ore e minuti, per gli eventi che si esauriscono in un sol giorno, o in termini di numero esatto di giorni, per gli eventi che occupano più giorni consecutivi");
	private Field indTee = new Field("Quota individuale","Campo obbligatorio che indica la spesa (o una stima della stessa) che ogni partecipante all’iniziativa dovrà sostenere (si noti che la spesa può anche essere nulla)");
	private Field teeInclude = new Field("Compreso nella quota", "Campo facoltativo che indica tutte le voci di spesa comprese nell’ammontare indicato nella “Quota individuale”");
	private Field endDate = new Field("Data conclusiva","Campo facoltativo che fissa la data di conclusione dell’evento");
	private Field endTime = new Field("Ora conclusiva", "Campo facoltativo che stima l’ora di conclusione dell’evento");
	private Field note = new Field("Note","Campo facoltativo contenente informazioni aggiuntive circa l’evento");

	/*
	Da qualche parte dobbiamo mettere questo pezzo di codice, direi di metterlo nel costruttore, quando si creerà la classe figlia il costruttore della padre si occuperà di
	inserire i campi nell'arrayLit di campi
		fields.add(title);
		fields.add(numOfPlayers);
		fields.add(place);
		fields.add(date);
		fields.add(time);
		fields.add(duration);
		fields.add(indTee);
		fields.add(teeInclude);
		fields.add(endDate);
		fields.add(endTime);
		fields.add(note);
	*/


}
