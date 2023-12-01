package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Division;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the "division function".
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * @author Liz Kent
 */
public class DivideAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the slash token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
        //Order matters here!
		ExpressionTreeNode rightETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode leftETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		return new Division(leftETN, rightETN);
	}

}
