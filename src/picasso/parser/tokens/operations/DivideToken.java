package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the division/slash token
 * 
 */
public class DivideToken extends CharToken implements OperationInterface {
	public DivideToken() {
		super(CharConstants.SLASH);
	}

	@Override
	//TODO: change from hardcoded number
	public int orderOfOperation() {
		return 3;
	}
}
