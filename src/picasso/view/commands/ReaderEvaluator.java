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
	
	/**
	 * Constructor
	 * @param mybuttonpanel
	 */
	public ReaderEvaluator(ButtonPanel mybuttonpanel) {
		super(JFileChooser.OPEN_DIALOG);
		buttonpanel = mybuttonpanel; 
	}
	/**
	 * Evaluate an expression for each point in the image.
	 */
	public void execute(Pixmap target) {

		// take the input from the file 
		String fileName = getFileName(); 
		if (fileName != null) {
			File file = new File(fileName); 
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				String st; 
				ExpressionTreeNode expr = null;
				while ((st = br.readLine()) != null) {
					expr = createExpression(st);
					/*
					try {
						System.out.println("try");
						expr = createExpression(st);//results in null
						System.out.println(expr);
						System.out.println(st);
					}
					catch (ParseException e) {
						System.out.println("catch");
						//Show error
						String errorFile = System.getProperty("user.dir") +
								File.separator +"images" + File.separator + "Error_Image.png";
						target.read(errorFile);
						br.close();
						return;//stop execution, wait for next input to run again
					}
					*/
					if (expr != null) {
						System.out.println("expr != null");
						Dimension size = target.getSize();
						for (int imageY = 0; imageY < size.height; imageY++) {
							double evalY = imageToDomainScale(imageY, size.height);
							for (int imageX = 0; imageX < size.width; imageX++) {
								double evalX = imageToDomainScale(imageX, size.width);
								Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
								target.setColor(imageX, imageY, pixelColor);
							}
						}
					}
				}
				br.close(); 
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
