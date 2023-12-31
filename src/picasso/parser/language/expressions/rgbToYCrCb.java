package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the abs function in the Picasso language.
 * 
 * @author Linh Nguyen
 *
 * 
 */
public class rgbToYCrCb extends UnaryFunction {

	/**
	 * Create a floor expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to floor
	 */
	public rgbToYCrCb(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the floor of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the floor of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = result.getRed() * 0.2989 + result.getGreen() * 0.5866 + result.getBlue() * 0.1145;
		double green = result.getRed() * -0.1687 + result.getGreen() * -0.3312 + result.getBlue() * 0.5;
		double blue = result.getRed() * 0.5000 + result.getGreen() * -0.4183 + result.getBlue() * -0.0816;

		return new RGBColor(red, green, blue);
	}
}
