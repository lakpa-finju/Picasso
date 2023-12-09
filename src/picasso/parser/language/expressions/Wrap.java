/**
 * 
 */
package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the wrap function in the picasso language
 * 
 * @author lakpafinjusherpa
 */
public class Wrap extends UnaryFunction {

	/**
	 * creates a wrap expression that takes as a parameter the given expression
	 * @param param the expression to wrap
	 */
	public Wrap(ExpressionTreeNode param) {
		super(param);
	}
	/**
	 * A wrapper function for doubles. 
	 * Specs: takes any number not within upperBound and
	 * lowerBound, removes the amount over the 
	 * limit in either direction and returns a number within range.
	 * Ex. For range -1.0 to 1.0, input of -1.25 should return .75. 1.5 should 
	 * return -.5.
	 * 
	 * @param n number to be wrapped
	 * @param lowerBound lower bound of range, inclusive
	 * @param upperBound upper bound of range, inclusive
	 */
	public double wrap(double n, double lowerBound, double upperBound){
        if ((n- lowerBound) < 0){
            double num = Math.abs((n - lowerBound)) % (upperBound - lowerBound) + lowerBound;
            return num * -1;
        }
        else{ 
		    return (n - lowerBound) % (upperBound - lowerBound) + lowerBound;
        }
	}

	/**
	 * A wrapper function for RGBColors. 
	 * See wrap function for doubles for how numbers should wrap
	 * 
	 * @param input RGBColor to be wrapped
	 * @param lowerBound lower bound of range, inclusive
	 * @param upperBound upper bound of range, inclusive
	 */
	public RGBColor wrap(RGBColor input, double lowerBound, double upperBound){
		double red = input.getRed();
		double green = input.getGreen();
		double blue = input.getBlue();

		red = wrap(red, -1.0, 1.0);
		green = wrap(green, -1.0, 1.0);
		blue = wrap(blue, -1.0, 1.0);

		return new RGBColor(red, green, blue);

	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the wrap of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the wrao of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = wrap(result.getRed(),-1,1);
		double green = wrap(result.getGreen(),-1,1);
		double blue = wrap(result.getBlue(),-1,1);

		return new RGBColor(red, green, blue);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Wrap)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		return true;

	}

}
