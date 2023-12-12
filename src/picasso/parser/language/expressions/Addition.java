package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents addition class in the picasso language
 * @author lakpafinjusherpa
 */
public class Addition extends ExpressionTreeNode {
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	public Addition(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the addition
	 * of the function's parameter.
	 * 
	 * @return the color from evaluating the addition of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor leftResult = left.evaluate(x, y);
		RGBColor rightResult = right.evaluate(x, y);
		double red = leftResult.getRed() + rightResult.getRed();
		double green = leftResult.getGreen() + rightResult.getGreen();
		double blue = leftResult.getBlue() + rightResult.getBlue();

		return new RGBColor(red, green, blue);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Addition)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Addition other = (Addition) o;
		if (!other.left.equals(this.left)) {
			return false;
		}

		if (!other.right.equals(this.right)) {
			return false;
		}

		return true;

	}
	@Override
    public String toString(){
        StringBuilder str = new StringBuilder(""); 
        str.append(left); 
        str.append(" + "); 
        str.append(right);
        return str.toString(); 
    }

}
