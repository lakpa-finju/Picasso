package picasso.view.commands;

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
		//Called when you press open image or open expression
		System.out.println("Open file");
		String fileName = getFileName();
		System.out.println(fileName);
		if (fileName != null) {
			System.out.println("target.read");
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			if (suffix.equals(".png") || suffix.equals(".jpg")) {
				target.read(fileName);
			}
			else {
				System.out.println("error");
				String errorFile = System.getProperty("user.dir") +
						"\\images\\Error_Image.png";
				
				target.read(errorFile);
			}
		}
		
		
		
	}
}
