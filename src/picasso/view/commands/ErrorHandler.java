package picasso.view.commands;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import picasso.model.Pixmap;

/**
 * Handles displaying error messages to the user
 * @author Trey Custodio
 */
public class ErrorHandler {
	
	//variables representing the user's path
	
	/** The user's path to the image error file */
	final static String IMAGE_ERROR_PATH = System.getProperty("user.dir") +
			File.separator +"images" + File.separator + "Error_Image.png";
	
	/** The user's path to the input error file */
	final static String INPUT_ERROR_PATH = System.getProperty("user.dir") +
			File.separator +"images" + File.separator + "Error_Input.png";
	
	
	//methods to display the error message
	
	/** Displays the pop up error message for invalid images
	 * @param target - the current Pixmap
	 */
	public static void displayImageError(Pixmap target) {
		ImageIcon icon = new ImageIcon(IMAGE_ERROR_PATH);
		JOptionPane.showMessageDialog(null, null, "Image Error", JOptionPane.ERROR_MESSAGE, icon);
	}
	
	/**
	 * Displays the pop up error message for invalid input
	 * @param target - the current Pixmap
	 */
	public static void displayInputError(Pixmap target) {
		displayInputError(target, null);
	}

	/**
	 * Displays the pop up error message for invalid input
	 * @param target - the current Pixmap
	 * @param message TODO
	 */
	public static void displayInputError(Pixmap target, String message) {
		ImageIcon icon = new ImageIcon(INPUT_ERROR_PATH);
		JOptionPane.showMessageDialog(null, message, "Expression Error", JOptionPane.ERROR_MESSAGE, icon);
	}
	
}
