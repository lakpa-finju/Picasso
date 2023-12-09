package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public class Exponentiation extends ExpressionTreeNode {
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	public Exponentiation(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the
     * exponent of the parameter. The left ETN is the base, and the right
     * ETN is the power.
	 * 
	 * @return the color from evaluating the exponentiation of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor leftResult = left.evaluate(x, y);
		RGBColor rightResult = right.evaluate(x, y);
		double red = Math.pow(leftResult.getRed(),rightResult.getRed());
		double green = Math.pow(leftResult.getGreen(), rightResult.getGreen());
		double blue = Math.pow(leftResult.getBlue(), rightResult.getBlue());

		return new RGBColor(red, green, blue);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Exponentiation)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Exponentiation other = (Exponentiation) o;
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
        str.append(" ^ "); 
        str.append(right);
        return str.toString(); 
    }

}

