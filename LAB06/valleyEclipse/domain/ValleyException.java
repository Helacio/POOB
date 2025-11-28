package domain;

public class ValleyException extends Exception{
	public static final String OPTION_OPEN = "Opcion OPEN en construcción";
	public static final String OPTION_SAVE = "Opcion SAVE en construccion";
	public static final String OPTION_IMPORT = "Opcion IMPORT en construcción";
	public static final String OPTION_EXPORT = "Opcion EXPORT en construccion";
	
	public ValleyException(String message) {
		super(message);
	}
}
