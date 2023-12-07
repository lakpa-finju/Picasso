package picasso.parser.language.expressions;

import picasso.parser.IdentifierAnalyzer;
import picasso.parser.language.ExpressionTreeNode;

public class Assignment extends ExpressionTreeNode{
	Variable idVariable;
	ExpressionTreeNode rightExpression;

	public Assignment(Variable idVariable, ExpressionTreeNode rightExpression) {
		this.idVariable = idVariable;
		this.rightExpression = rightExpression;
		IdentifierAnalyzer.variableToExpression(idVariable.getName(), rightExpression);
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		return new RGBColor(-1,-1,-1);
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Assignment)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Assignment other = (Assignment) o;
		if (!other.idVariable.equals(this.idVariable)) {
			return false;
		}

		if (!other.rightExpression.equals(this.rightExpression)) {
			return false;
		}

		return true;

	}
	@Override
    public String toString(){
        StringBuilder str = new StringBuilder(""); 
        str.append(idVariable); 
        str.append(" = "); 
        str.append(rightExpression);
        return str.toString(); 
    }
	
	
}