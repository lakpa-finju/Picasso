package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the modulo token
 * 
 * @author Liz Kent
 */
public class NegateToken extends CharToken implements OperationInterface {
	public NegateToken() {
		super(CharConstants.BANG);
	}

	@Override
	public int orderOfOperation() {
		return 5;
	}
}
