package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a variable
 * 
 * @author Sara Sprenkle
 *
 */
public class Variable extends ExpressionTreeNode {

	private String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		// Should be set to some value using assignment.
		return null;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Variable)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Variable other = (Variable) o;
		if (!other.name.equals(this.name)) {
			return false;
		}

		return true;

	}
}
