package picasso.view.commands;

import java.io.File;

import javax.swing.JFileChooser;


import picasso.model.Pixmap;
import picasso.util.FileCommand;

/**
 * Open the chosen image file and display in the Pixmap target.
 * 
 * @author Robert C Duvall
 */
public class Reader extends FileCommand<Pixmap> {
	
	//protected static String[] extensions = {".png", ".jpg", ".gif"};
	
	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public Reader() {
		super(JFileChooser.OPEN_DIALOG);
	}

	/**
	 * Displays the image file on the given target.
	 * If the user selects an invalid image, an error
	 * message will be displayed instead.
	 */
	public void execute(Pixmap target) {
		String fileName = getFileName();
		if (fileName != null) {
			target.read(fileName);
			target.setSize(600, 600);
		}
		
		
		
	}
}
