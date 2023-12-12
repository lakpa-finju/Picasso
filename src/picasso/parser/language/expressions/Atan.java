package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents the arctangent function for Picasso evaluations.
 * 
 * @author Liz Kent
 */
public class Atan extends UnaryFunction {
	
	/**
	 * Constructs the cosine function with the given expression as a parameter
	 * @param param - the given expression
	 */
	public Atan(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates the expression at the given (x,y) by taking the cosine of
     * the parameter.
     * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);

		double red = Math.atan(result.getRed());
		double green = Math.atan(result.getGreen());
		double blue = Math.atan(result.getBlue());

		return new RGBColor(red, green, blue);
	}
}