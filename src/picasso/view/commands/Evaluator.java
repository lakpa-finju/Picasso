package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.EmptyStackException;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import picasso.view.ButtonPanel;
import picasso.view.HistoryPanel;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 * @author Sara Sprenkle
 */

public class Evaluator implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	
	private ButtonPanel buttonpanel; 
	private HistoryPanel historypanel; 
	
	/**
	 * Constructor
	 * @param mybuttonpanel
	 */
	public Evaluator(ButtonPanel mybuttonpanel, HistoryPanel myhistorypanel) {
		buttonpanel = mybuttonpanel; 
		historypanel = myhistorypanel; 
	}
	/**
	 * Evaluate an expression for each point in the image.
	 * @param target - the current Pixmap
	 */
	public void execute(Pixmap target) {
		String input = buttonpanel.getInput(); 
		execute(target, input);
	}
	/**
	 * Evaluate an expression for each point in the image.
	 * @param target - the current Pixmap
	 * @param expression - the expression to evaluate
	 * @param stop - controls while loop progression in ReaderEvaluator
	 */
	public boolean execute(Pixmap target, String expression) {
		
		
		ExpressionTreeNode expr = null;
		
		try {
			expr = createExpression(expression);
			if (expr != null) {
				historypanel.addExpressionHistory(expression); 
			}
			else {
				//does not display an error for anticipated null expressions
				return false;
			}
		}
		//Show error message and stop execution
		catch(ParseException e){
			
			String message = e.getMessage().substring(15);
			ErrorHandler.displayInputError(target, message);
			return true;
		}
		
		// evaluate it for each pixel
		Dimension size = target.getSize();
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);
				Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
				target.setColor(imageX, imageY, pixelColor);
			}
		}
		return false;
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
