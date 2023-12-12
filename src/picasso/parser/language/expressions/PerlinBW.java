package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

public class PerlinBW extends ExpressionTreeNode {
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	public PerlinBW(ExpressionTreeNode left, ExpressionTreeNode right) {
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

		double grey = ImprovedNoise.noise(leftResult.getRed() + rightResult.getRed(), leftResult.getGreen() + rightResult.getGreen(),
				leftResult.getBlue() + rightResult.getBlue());
		return new RGBColor(grey, grey, grey);
		
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof PerlinBW)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		PerlinBW other = (PerlinBW) o;
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
        StringBuilder str = new StringBuilder("perlinBW("); 
        str.append(left); 
        str.append(","); 
        str.append(right + ")");
        return str.toString(); 
    }
}