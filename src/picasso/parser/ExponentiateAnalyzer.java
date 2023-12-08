package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exponentiation;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the caret or "exponent function".
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * @author Liz Kent
 */
public class ExponentiateAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Remove the caret token
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		ExpressionTreeNode rightETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode leftETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		return new Exponentiation(leftETN, rightETN);
	}

}

