package picasso.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

import picasso.model.Pixmap;
import picasso.util.ThreadedCommand;
import picasso.view.commands.*;

/**
 * Main container for the Picasso application
 *
 * @author Robert Duvall (rcd@cs.duke.edu)
 * @author lakpafinjusherpa
 * 
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	public Frame(Dimension size) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame myFrame = this;
		// multiple images
		JTabbedPane tabPanel = new JTabbedPane();
		
		// create GUI components
		Canvas canvas = new Canvas(myFrame);
		canvas.setSize(size);
		JPanel page = new JPanel();
		page.add(canvas);
		tabPanel.add("normal",page);
	
		// add history panel
		HistoryPanel history = new HistoryPanel(canvas);
		JScrollPane historyScroll = new JScrollPane(history);
		String title = "History";
		Border border = BorderFactory.createTitledBorder(title);
		historyScroll.setBorder(border);

		// add commands
		ButtonPanel commands = new ButtonPanel(canvas);
		commands.add("multi images", this, tabPanel, size);
		commands.add("Open Image", new Reader());
		Evaluator eval = new Evaluator(commands, history);
		commands.add("Open Expression", new ReaderEvaluator(commands, eval));
		ThreadedCommand<Pixmap> evalutorAction = new ThreadedCommand<Pixmap>(canvas, eval);
		commands.add("Evaluate", evalutorAction);
		commands.add("Save", new Writer());
		commands.enterToEvaluate(evalutorAction);
		

		// add our container to Frame and show it
		//getContentPane().add(canvas, BorderLayout.CENTER);
		myFrame.getContentPane().add(commands, BorderLayout.NORTH);
		myFrame.getContentPane().add(tabPanel);
		myFrame.getContentPane().add(historyScroll, BorderLayout.EAST);
		

		pack();
	}
}
