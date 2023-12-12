package picasso.view.commands;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import picasso.model.Pixmap;

public class ErrorHandler {
	
	
	public static void displayError(Pixmap target) {
		
		String errorFile = System.getProperty("user.dir") +
				File.separator +"images" + File.separator + "Error_Image.png";
		ImageIcon err = new ImageIcon(errorFile);
		JOptionPane.showMessageDialog(null, null, "Image Error", JOptionPane.ERROR_MESSAGE, err);
		/*
		String errorFile = System.getProperty("user.dir") +
				File.separator +"images" + File.separator + "Error_Image.png";
		target.read(errorFile);
		*/
	}
}
