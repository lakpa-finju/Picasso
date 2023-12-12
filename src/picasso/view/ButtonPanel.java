package picasso.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
public class ButtonPanel extends JPanel {
	private Canvas myView;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Create panel that will update the given picasso.view.
	 * 
	 * @param view the Canvas that the panel's buttons update
	 */
	public ButtonPanel(Canvas view) {
		myView = view;
		textField = new JTextField(20);
		textArea = new JTextArea();
		this.add(textField);
		this.add(textArea);
	}

	/**
	 * Add the given Command as a button with the given button text. When the button
	 * is clicked, the command is executed and the associated canvas is updated.
	 * 
	 * @param buttonText the text for the button
	 * @param action     the action associated with the new button
	 */
	public void add(String buttonText, final Command<Pixmap> action) {
		JButton button = new JButton(buttonText);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.execute(myView.getPixmap());
				myView.refresh();
			}
		});
		add(button);
	}

	/**
	 * Add the given Command as a button with the given button text. When the button
	 * is clicked, the action listener updates the tab panel with new images selected.
	 * 
	 * @param buttonText the text for the button
	 * @param frame Java Frame object from parent class 
	 * @param tabPanel JtabbedPane object for adding new tab images
	 * @param size Dimension object for defining the size of each tab images
	 */
	public void add(String buttonText, Frame frame, JTabbedPane tabPanel, Dimension size) {
		JButton button = new JButton(buttonText);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// only one dialog box needed for an application
				final JFileChooser ourChooser = new JFileChooser(System.getProperties().getProperty("user.dir"));

				ourChooser.setDialogType(JFileChooser.OPEN_DIALOG);
				ourChooser.setMultiSelectionEnabled(true);
				int response = ourChooser.showDialog(null, null);
				if (response == JFileChooser.APPROVE_OPTION) {
					File[] files = ourChooser.getSelectedFiles();
					for (File file : files) {
						Canvas canva = new Canvas(frame, file.getPath());
						canva.setSize(size);
						JPanel page1 = new JPanel();
						page1.add("tab", canva);
						String[] bits = file.getPath().split(File.separator);
						tabPanel.addTab(bits[bits.length - 1], page1);
						frame.add(tabPanel);

					}
				}
			}

		});
		add(button);
	}

	/**
	 * Add the given Command as a button. The button's text will be the given
	 * command's name.
	 * 
	 * @param action the command associated with the new button
	 */
	public void add(NamedCommand<Pixmap> action) {
		add(action.getName(), action);
	}

	/**
	 * Returns the input text from the text box
	 * 
	 * @return the input text from the text box
	 */
	public String getInput() {
		return textField.getText();
	}

	public void enterToEvaluate(ThreadedCommand<Pixmap> evalutorAction) {
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evalutorAction.execute(myView.getPixmap());
				myView.refresh();
			}
		});
	}

	public void addExpressionHistory(String s) {
		textArea.append(s + "\n");
	}
}
