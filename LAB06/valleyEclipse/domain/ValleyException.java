package domain;

import java.io.Serializable;

public class ValleyException extends Exception implements Serializable{
	public static final String OPTION_OPEN = "Opcion OPEN en construcción";
	public static final String OPTION_SAVE = "File is null";
	public static final String OPTION_IMPORT = "Opcion IMPORT en construcción";
	public static final String OPTION_EXPORT = "Opcion EXPORT en construccion";
	
	public ValleyException(String message) {
		super(message);
	}
}
