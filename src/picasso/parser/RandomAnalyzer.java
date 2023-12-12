package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.RandomColor;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the Random function.
 * 
 * @author Liz Kent
 * 
 */
public class RandomAnalyzer extends UnaryFunctionAnalyzer {

	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the random token.
		// // The parameter is the next token(s) on the stack.
		// // But, it needs to be processed
		return new RandomColor();
	}
}

