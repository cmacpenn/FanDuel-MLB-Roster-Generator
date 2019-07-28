import java.io.File;

/**
 * Exception thrown when the wrong type of file is provided
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class FileFormatException extends Exception {
	
	// Variables
	File f;
	String message;
	
	FileFormatException(File f, String message){
		this.f = f;
		this.message = message;
	}
	
	public String getMessage() {
		return "Error in " + f.toString() + ". " + message;
	}

}
