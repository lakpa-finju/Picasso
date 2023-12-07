package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the minus token
 * 
 * @author Liz Kent
 */
public class MinusToken extends CharToken implements OperationInterface {
	public MinusToken() {
		super(CharConstants.MINUS);
	}

	@Override
	public int orderOfOperation() {
		return 2;
	}
}
