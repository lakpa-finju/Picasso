package picasso.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import picasso.model.Pixmap;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	public Frame(Dimension size) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create GUI components
		Canvas canvas = new Canvas(this);
		canvas.setSize(size);

		// add commands to test here
		ButtonPanel commands = new ButtonPanel(canvas);
		commands.add("Open Image", new Reader());
		commands.add("Open Expression", new ReaderEvaluator(commands));
		ThreadedCommand<Pixmap> evalutorAction = new ThreadedCommand<Pixmap>(canvas, new Evaluator(commands));
		commands.add("Evaluate", evalutorAction);
		commands.add("Save", new Writer());
		commands.enterToEvaluate(evalutorAction); 
		
		// add our container to Frame and show it
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(commands, BorderLayout.NORTH);
		pack();
	}
}
