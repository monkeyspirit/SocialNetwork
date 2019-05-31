package main.fileUtilities;

/**
 * Classe di supporto per la costruzione della stringa corrispondente al path di un file
 */
public class PathBuilder {

    /**
     * Costruisce il path per il file: path/fileName.json
     * @param path percorso fino alla cartella in cui il file e' contenuto
     * @param fileName nome del file
     * @return path/fileName.json
     */
    public static String buildJsonFilePath(String path, String fileName) {
        return path + fileName + ".json";
    }

    /**
     * Costruisce il path per il file: path/user_fileName.json
     * @param path percorso fino alla cartella in cui il file e' contenuto
     * @param fileName nome del file
     * @param user nome dell'utente
     * @return path/user_fileName.json
     */
    public static String buildJsonFilePath(String path, String fileName, String user) {
        return path + fileName + '_' + user + ".json";
    }
}
