package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.StringToken;
import picasso.parser.language.math.ComplexNumber;
import picasso.parser.language.math.Wrapper;

/**
 * This class represents the sine function for Picasso evaluations.
 * 
 * @author Liz Kent
 */
public class Julia extends ExpressionTreeNode {
	
    private ComplexNumber c;
	/**
	 * Constructs the sine function with the given expression as a parameter
	 * @param param - the given expression
	 */
	public Julia(StringToken real, StringToken imaginary) {
		c.real = ;
	}
	
	/**
	 * Evaluates the expression at the given (x,y) by taking the sine of
     * the parameter.
     * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);

		double red = Math.sin(result.getRed());
		double green = Math.sin(result.getGreen());
		double blue = Math.sin(result.getBlue());

		return new RGBColor(red, green, blue);
	}
}