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
	
	/**
	 * @return true iff o is a StringToken with same value
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof StringToken)) {
			return false;
		}
		StringToken other = (StringToken) o;
		return myValue.equals(other.value());
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

	@Override
	public boolean isPushedToPostfix(){
		return true;
	}

}
