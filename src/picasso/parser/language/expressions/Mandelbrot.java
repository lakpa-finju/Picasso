package picasso.parser.language.expressions;

import picasso.parser.language.math.ComplexNumber;

/**
 * This class represents the Mandelbrot function.
 * Generates the Mandelbrot set, takes no parameters.
 * 
 * @author Liz Kent
 */
public class Mandelbrot extends NoArgumentFunction{
	
	protected static final int MAX_ITER = 500;
	/**
	 * Constructs the Mandelbrot function
	 */
	public Mandelbrot() {
	}

	/**
	 * Determines whether c is in the 
	 * Mandelbrot set.
	 * 
	 * @param c, a complex number 
	 * @return the number of iterations
	 */
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
	 * Evaluates the expression at the given (x,y) by mapping
	 * the given x,y pixel to a complex number and then
	 * iterating over zero.
     * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		
		ComplexNumber c =  new ComplexNumber(x, y);
		int result = mandelbrot(c);
		//If result is equal to MAX_ITER, it is in the set. Color it black 
		if (result == MAX_ITER){
			return new RGBColor(-1, -1, -1);
		}
		//Otherwise, generate a different color based on the number of iterations needed
		else if (result < MAX_ITER / 250){
			return new RGBColor(.78, .78, 1);
		}
		else if (result < MAX_ITER / 200){
			return new RGBColor(-.6, .3, .2);
		}
		else if (result < MAX_ITER / 150){
			return new RGBColor(.6, -.8, 1);
		}
		else if (result < MAX_ITER / 100){
			return new RGBColor(1, .5, 0);
		}
		else if(result < MAX_ITER / 50){
			return new RGBColor(-1, .6, .6);
		}
		else if(result < MAX_ITER / 30){
			return new RGBColor(.4, -.28, .9);
		}
		else {
			return new RGBColor(.4, -.4, .4);
		}

	}

}