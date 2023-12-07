/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Image;
import picasso.parser.tokens.StringToken;
import picasso.parser.tokens.Token;

/**
 * Semantic analyzer for String token
 * @author lakpafinjusherpa
 */
public class StringAnalyzer implements SemanticAnalyzerInterface {
	
	private static StringAnalyzer singleton;
	
	public static StringAnalyzer getInstance() {
		if (singleton == null) {
			singleton = new StringAnalyzer();
		}
		return singleton;
	}
	/**
	 * @param tokens
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		StringToken token = (StringToken) tokens.pop();

		// Check that the number is a valid number
		java.lang.String value = token.value();

		// Would violate the preconditions of the constant
		if (value == null) {
			throw new ParseException("String is null");
		}

		return new Image(value);
	}

}
