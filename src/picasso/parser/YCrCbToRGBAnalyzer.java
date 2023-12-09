package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Abs;
import picasso.parser.language.expressions.rgbToYCrCb;
import picasso.parser.language.expressions.yCrCbToRGB;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the floor function.
 * 
 * @author Linh Nguyen
 * 
 */
public class YCrCbToRGBAnalyzer extends UnaryFunctionAnalyzer {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the yCrCbToRGBAnalyzer token
		// the parameter is the next token(s) on the stack.
		// But, it needs to be processed
		ExpressionTreeNode paramETN = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new yCrCbToRGB(paramETN);
	}

}


