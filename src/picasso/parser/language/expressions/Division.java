package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents the division operation.
 * 
 * @author Liz Kent
 */
public class Division extends ExpressionTreeNode {
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	public Division(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by producing a color
     * based on the function's parameter.
	 * 
	 * @return the color from evaluating the division of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor leftResult = left.evaluate(x, y);
		RGBColor rightResult = right.evaluate(x, y);
		double red = leftResult.getRed() / rightResult.getRed();
		double green = leftResult.getGreen() / rightResult.getGreen();
		double blue = leftResult.getBlue() / rightResult.getBlue();

		return new RGBColor(red, green, blue);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Division)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Division other = (Division) o;
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
        str.append(" / "); 
        str.append(right);
        return str.toString(); 
    }

}

