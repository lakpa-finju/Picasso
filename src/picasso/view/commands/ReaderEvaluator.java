package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Scanner;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.ParseException;
import picasso.util.Command;
import picasso.view.ButtonPanel;
import javax.swing.JFileChooser;
import picasso.util.FileCommand;

import java.io.*;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 * @author Sara Sprenkle
 */

public class ReaderEvaluator extends FileCommand<Pixmap> implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;

	private ButtonPanel buttonpanel;
	private Evaluator evaluator;

	/**
	 * Constructor
	 * 
	 * @param mybuttonpanel
	 */
	public ReaderEvaluator(ButtonPanel mybuttonpanel, Evaluator eval) {
		super(JFileChooser.OPEN_DIALOG);
		buttonpanel = mybuttonpanel;
		evaluator = eval;
		
	}


	/**
	 * Evaluate an expression for each point in the image.
	 */
	// for merge
	public void execute(Pixmap target) {

		// take the input from the file
		String fileName = getFileName();
		if (fileName != null) {
			File file = new File(fileName);
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				String st;
				boolean stop = false;
				while ( ((st = br.readLine()) != null) && !stop) {
					evaluator.execute(target, st, stop);
				}
				br.close();
				//handle these exceptions appropriately
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}

	/**
	 * 
	 * A place holder for a more interesting way to build the expression.
	 */
	private ExpressionTreeNode createExpression(String s) {
		// Note, when you're testing, you can use the ExpressionTreeGenerator to
		// generate expression trees from strings, or you can create expression
		// objects directly (as in the commented statement below).

		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		return expTreeGen.makeExpression(s);

	}

}
