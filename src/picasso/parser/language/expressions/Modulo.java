package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents the modulo operation.
 * 
 * @author Liz Kent
 */
public class Modulo extends ExpressionTreeNode {
	ExpressionTreeNode left;
	ExpressionTreeNode right;

	public Modulo(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Evaluates this expression at the given x,y point by producing a color
     * based on the function's parameter. Handles modulo by 0 as well
	 * 
	 * @return the color from evaluating the modulo of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor leftResult = left.evaluate(x, y);
		RGBColor rightResult = right.evaluate(x, y);
        double red = 0.0;
        double green = 0.0;
        double blue = 0.0;

        if (rightResult.getRed() != 0.0){
            red = leftResult.getRed() % rightResult.getRed();
        }
        if (rightResult.getGreen() != 0.0){
             green = leftResult.getGreen() % rightResult.getGreen();
        }
        if (rightResult.getBlue() != 0.0){
             blue = leftResult.getBlue() % rightResult.getBlue();
        }

		return new RGBColor(red, green, blue);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Modulo)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Modulo other = (Modulo) o;
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
        str.append(" % "); 
        str.append(right);
        return str.toString(); 
    }

}
