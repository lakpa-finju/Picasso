package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

public class PerlinColor extends ExpressionTreeNode {
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	public PerlinColor(ExpressionTreeNode left, ExpressionTreeNode right) {
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
		
		double red = ImprovedNoise.noise(leftResult.getRed() + 0.3, rightResult.getRed() + 0.3, 0);
		double blue = ImprovedNoise.noise(leftResult.getBlue() + 0.1, rightResult.getBlue() + 0.1, 0);
		double green = ImprovedNoise.noise(leftResult.getGreen() - 0.8, rightResult.getGreen() - 0.8, 0);
		return new RGBColor(red, green, blue);
		
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof PerlinColor)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		PerlinColor other = (PerlinColor) o;
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
        StringBuilder str = new StringBuilder("perlinColor("); 
        str.append(left); 
        str.append(","); 
        str.append(right + ")");
        return str.toString(); 
    }
}