package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the multiplication token
 * 
 * @author Liz Kent
 */
public class TimesToken extends CharToken implements OperationInterface {
	public TimesToken() {
		super(CharConstants.STAR);
	}

	@Override
	public int orderOfOperation() {
		return 3;
	}
}