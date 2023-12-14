/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Julia;
import picasso.parser.tokens.StringToken;
import picasso.parser.tokens.Token;

/**
 * Handles the parsing the Julia function
 * @author Liz Kent
 */
public class JuliaAnalyzer implements SemanticAnalyzerInterface {



	/**
	 * @param tokens in postFix order
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); //pop julia token
		//ImageWrap("image.jpg",x+x,y
		//imageWrap y + x x image.jpg -- is the stack 
		// ExpressionTreeNode yCoordinateETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		// ExpressionTreeNode xCoordinateETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		if(!(tokens.peek() instanceof StringToken)) {
			throw new ParseException("Input for imaginary number is not valid");
		}
		StringToken imaginary =(StringToken)tokens.pop();
        if(!(tokens.peek() instanceof StringToken)) {
			throw new ParseException("Input for real number is not valid");
		}
		StringToken real =(StringToken)tokens.pop();
		return new Julia(real, imaginary);
	}

}
