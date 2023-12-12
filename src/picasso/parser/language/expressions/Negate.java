package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public class Negate extends ExpressionTreeNode {
	ExpressionTreeNode right;

	public Negate(ExpressionTreeNode right) {
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the subtraction
     * of the parameter.
	 * 
	 * @return the color from evaluating the subtraction of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor rightResult = right.evaluate(x, y);
		double red = -1 * rightResult.getRed();
		double green = -1 * rightResult.getGreen();
		double blue = -1 * rightResult.getBlue();

		return new RGBColor(red, green, blue);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Negate)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Negate other = (Negate) o;

		if (!other.right.equals(this.right)) {
			return false;
		}

		return true;

	}
	@Override
    public String toString(){
        StringBuilder str = new StringBuilder(""); 
        str.append(" ! "); 
        str.append(right);
        return str.toString(); 
    }

}
