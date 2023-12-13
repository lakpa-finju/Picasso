package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Mandelbrot;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the Mandelbrot function.
 * 
 * @author Liz Kent
 * 
 */
public class MandelbrotAnalyzer extends UnaryFunctionAnalyzer {

	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the mandelbrot token.
        //No parameter so return this token
		return new Mandelbrot();
	}
}