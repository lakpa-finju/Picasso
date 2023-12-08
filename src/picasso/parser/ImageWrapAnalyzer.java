/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.language.expressions.RGBColor;
import picasso.parser.tokens.StringToken;
import picasso.parser.tokens.Token;

/**
 * Handles the parsing the ImageWrap function
 * @author lakpafinjusherpa
 */
public class ImageWrapAnalyzer implements SemanticAnalyzerInterface {



	/**
	 * @param tokens in postFix order
	 * @return
	 * @see picasso.parser.SemanticAnalyzerInterface#generateExpressionTree(java.util.Stack)
	 */
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); //pop imageWrap
		//ImageWrap("image.jpg",x+x,y
		//imageWrap y + x x image.jpg -- is the stack 
		ExpressionTreeNode yCoordinateETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode xCoordinateETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		if(!(tokens.peek() instanceof StringToken)) {
			throw new ParseException("Input for image is not valid");
		}
		StringToken image =(StringToken)tokens.pop();
		return new ImageWrap(image,xCoordinateETN,yCoordinateETN);
	}

}
