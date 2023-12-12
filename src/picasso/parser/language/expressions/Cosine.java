package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents the cosine function for Picasso evaluations.
 * 
 * @author Liz Kent
 */
public class Cosine extends UnaryFunction {
	
	/**
	 * Constructs the cosine function with the given expression as a parameter
	 * @param param - the given expression
	 */
	public Cosine(ExpressionTreeNode param) {
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

		double red = Math.cos(result.getRed());
		double green = Math.cos(result.getGreen());
		double blue = Math.cos(result.getBlue());

		return new RGBColor(red, green, blue);
	}
}