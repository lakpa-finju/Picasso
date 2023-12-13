package picasso.parser.language.expressions;

import picasso.parser.language.math.ComplexNumber;
import picasso.parser.language.math.Wrapper;

/**
 * This class represents the sine function for Picasso evaluations.
 * 
 * @author Liz Kent
 */
public class Mandelbrot extends NoArgumentFunction{
	
	protected static final int MAX_ITER = 120;
	/**
	 * Constructs the mandelbrot function
	 */
	public Mandelbrot() {
	}

	public static int mandelbrot(ComplexNumber c){
		ComplexNumber z = new ComplexNumber(0, 0);
		int i = 0;
		while(((z.abs()) <= 2.0) && (i < MAX_ITER) ){
			z = z.squared().plus(c);
			i++;
		}
		return i;
	}
	
	/**
	 * Evaluates the expression at the given (x,y) by taking the sine of
     * the parameter.
     * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		
		ComplexNumber c =  new ComplexNumber(x, y);
		int result = mandelbrot(c);
		if (result == MAX_ITER){
			return new RGBColor(-1, -1, -1);
		}
		else{
			double color = Wrapper.wrap(result, -1, 1);
			return new RGBColor(color, color, color);
		}


	}

}