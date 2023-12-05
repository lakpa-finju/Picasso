/**
 * 
 */
package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.language.expressions.RGBColor;
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
		// TODO Auto-generated method stub
		String fileName = tokens.pop().toString(); //pop the name of the image
		ExpressionTreeNode leftETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		ExpressionTreeNode rightETN = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		return new ImageWrap(fileName,leftETN,rightETN);
	}

}
