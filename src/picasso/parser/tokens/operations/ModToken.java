package picasso.parser.tokens.operations;

import picasso.parser.language.CharConstants;
import picasso.parser.tokens.chars.CharToken;

/**
 * Represents the modulo token
 * 
 * @author Liz Kent
 */
public class ModToken extends CharToken implements OperationInterface {
	public ModToken() {
		super(CharConstants.MOD);
	}

	@Override
	public int orderOfOperation() {
		return 3;
	}
}
