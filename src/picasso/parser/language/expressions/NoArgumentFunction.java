package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that takes no arguments.
 * 
 * @author Liz Kent
 *
 */
public abstract class NoArgumentFunction extends ExpressionTreeNode{
    
    public NoArgumentFunction(){

    }

    /**
	 * Returns the string representation of the function in the format "<ClassName>"
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String classname = this.getClass().getName();
		return classname.substring(classname.lastIndexOf(".") + 1);
	}

    @Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof NoArgumentFunction)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		return true;
	}

}

