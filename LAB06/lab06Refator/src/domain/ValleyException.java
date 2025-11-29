package domain;

import java.io.Serializable;

public class ValleyException extends Exception implements Serializable{
	public static final String OPTION_OPEN = "File can´t open";
	public static final String OPTION_SAVE = "File can´t save";
	public static final String OPTION_IMPORT = "File can´t import";
	public static final String OPTION_EXPORT = "File can´t export";
	
	public ValleyException(String message) {
		super(message);
	}
}
