package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents the clamp function for Picasso evaluations.
 * 
 * @author Trey Custodio
 */
public class Clamp extends UnaryFunction {
	
	/**
	 * Constructs the clamp function with the given expression as a parameter
	 * @param param - the given expression
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
	}
	
	/**
	 * Evaluates the expression at the given coordinates by clamping the
	 * rgb values to the range [-1,1]
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x,y);
		
		double red = RGBColor.clamp(result.getRed());
		double green = RGBColor.clamp(result.getGreen());
		double blue = RGBColor.clamp(result.getBlue());
		
		return new RGBColor(red, green, blue);
	}
}