/**
 * 
 */
package picasso.parser.tokens;

/**
 * 
 */
public class StringToken extends Token{

	private final String myValue;
	/**
	 * 
	 */
	public StringToken(String val) {
		super("String Token");
		this.myValue = val;
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
				return false;
	}
	
	/**
	 * @return the value of this token
	 */
	public String value() {
		return myValue;
	}

	public String toString() {
		return super.toString() + ": " + myValue;
	}


}
