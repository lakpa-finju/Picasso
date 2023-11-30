package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

public class Addition extends ExpressionTreeNode{
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	public Addition(ExpressionTreeNode left, ExpressionTreeNode right ) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the addition of
	 * the function's parameter.
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

}
