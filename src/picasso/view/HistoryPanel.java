package picasso.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import picasso.model.Pixmap;
import picasso.util.Command;
import picasso.util.NamedCommand;
import picasso.util.ThreadedCommand;
import picasso.view.commands.Evaluator;

/**
 * The collection of commands represented as buttons that apply to the active
 * image.
 * 
 * @author Robert C Duvall
 */
@SuppressWarnings("serial")
public class HistoryPanel extends JPanel {
	private Canvas myView;
	private JTextArea textArea; 

	/**
	 * Create panel that will update the given picasso.view.
	 * 
	 * @param view the Canvas that the panel's buttons update
	 */
	public HistoryPanel(Canvas view) {
		myView = view; 
		textArea = new JTextArea(); 
		this.add(textArea); 
		textArea.setLineWrap(true);

	}
	public void addExpressionHistory(String s) {
		textArea.append(s+ "\n"); 
	}
}