package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents the tangent function for Picasso evaluations.
 * 
 * @author Liz Kent
 */
public class Tan extends UnaryFunction {
	
	/**
	 * Constructs the sine function with the given expression as a parameter
	 * @param param - the given expression
	 */
	public Tan(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates the expression at the given (x,y) by taking the tangent of
     * the parameter.
     * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);

		double red = Math.tan(result.getRed());
		double green = Math.tan(result.getGreen());
		double blue = Math.tan(result.getBlue());

		return new RGBColor(red, green, blue);
	}
}