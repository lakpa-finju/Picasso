package picasso.parser;

import java.util.Stack;
import java.util.EmptyStackException;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.language.expressions.Variable;
import picasso.parser.tokens.*;

/**
 * Handles parsing the plus or "addition function".
 * 
 * @author Linh Nguyen
 * 
 */
public class AssignmentAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the assignment token
		// left side of the assignment token is the expression
		ExpressionTreeNode rightExpression = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		try {
			if (tokens.peek() instanceof IdentifierToken) {
				//System.out.println((tokens.peek()).getClass());
				//if (tokens.peek() is the variable x or y){
				//throw new ParseException("Cannot assign values to x or y")
				// }
				IdentifierToken idToken = (IdentifierToken) tokens.pop();
				String nameidToken = idToken.getName();
				Variable idVariable = new Variable(nameidToken);
				return new Assignment(idVariable, rightExpression);
			} else {
				throw new ParseException("Unable to perform assignment on the lefthand expression");
			}
		} catch (EmptyStackException e) {
			throw new ParseException("Incomplete expression");
		}

	}
}
