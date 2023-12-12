package picasso.parser.language.math;

public class Wrapper {
    
    /**
	 * A wrapper function for doubles - static version
	 * Specs: takes any number not within upperBound and
	 * lowerBound, removes the amount over the 
	 * limit in either direction and returns a number within range.
	 * Ex. For range -1.0 to 1.0, input of -1.25 should return .75. 1.5 should 
	 * return -.5.
	 * 
	 * @param n number to be wrapped
	 * @param lowerBound lower bound of range, inclusive
	 * @param upperBound upper bound of range, inclusive
	 */
	public static double wrap(double n, double lowerBound, double upperBound){
        if ((n- lowerBound) < 0){
            double num = Math.abs((n - lowerBound)) % (upperBound - lowerBound) + lowerBound;
            return num * -1;
        }
        else{ 
		    return (n - lowerBound) % (upperBound - lowerBound) + lowerBound;
        }
	}
}


