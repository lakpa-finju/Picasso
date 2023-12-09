package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents the sine function for Picasso evaluations.
 * 
 * @author Liz Kent
 */
public class Log extends UnaryFunction {
	
	/**
	 * Constructs the log function with the given expression as a parameter
	 * @param param - the given expression
	 */
	public Log(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates the expression at the given (x,y) by taking the log of
     * the absolute value of the parameter.
     * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);

		double red = Math.log(Math.abs(result.getRed()));
		double green = Math.log(Math.abs(result.getGreen()));
		double blue = Math.log(Math.abs(result.getBlue()));

		return new RGBColor(red, green, blue);
	}
}