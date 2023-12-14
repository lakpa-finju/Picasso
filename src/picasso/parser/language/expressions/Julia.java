package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.StringToken;
import picasso.parser.language.math.ComplexNumber;

/**
 * This class represents the Julia set function.
 * 
 * @author Liz Kent
 */
public class Julia extends ExpressionTreeNode {
	
    private ComplexNumber z;
    protected static final int MAX_ITER = 100;

	/**
	 * Constructs a Julia set with the given values as the constant
     * 
	 * @param real - StringToken that represents the real part of the Julia constant
     * @param imaginary - StringToken that represents the imaginary part of the Julia constant
	 */
	public Julia(StringToken real, StringToken imaginary) {
        //Set the inputted real and imaginary values for c to 
        //a complex number
        //Converts to double from StringToken
		z = new ComplexNumber(Double.parseDouble(real.value()), Double.parseDouble(imaginary.value()));
        //System.out.println(c);
	}

	/**
	 * Gets the value of z
	 * 
	 * @return z
	 */
	public ComplexNumber getZ(){
		return this.z;
	}

	/**
	 * Determines whether z is in the 
	 * this Julia set.
	 * 
	 * @param z, a complex number
	 * @return the number of iterations over z^2 + c
	 */
    public int julia(ComplexNumber z){
		int i = 0;
		while(((z.abs()) <= 2.0) && (i < MAX_ITER) ){
			z = z.squared().plus(this.z);
			i++;
		}
        //System.out.println(i);
        return i;
		
	}
	/**
	 * Evaluates the expression at the given (x,y) by mapping
     * that pixel to a coordinate. If the coordinate is in the set, it is colored black.
     * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return the evaluated RGBColor
	 */
	public RGBColor evaluate(double x, double y) {
		ComplexNumber zNot = new ComplexNumber(x, y);
        double result = julia(zNot);
		//If result = MAX_ITER, it is in the set. Color it black 
        if (result == MAX_ITER){
			return new RGBColor(-1, -1, -1);
		}
		//Otherwise, generate a different color based on the number of iterations needed
		else if(result < MAX_ITER / 30){
			return new RGBColor(.4, -.28, .9);
		}
		else if (result < MAX_ITER / 25){
			return new RGBColor(.78, .78, 1);
		}
		else if (result < MAX_ITER / 20){
			return new RGBColor(-.6, .3, .2);
		}
		else if (result < MAX_ITER / 15){
			return new RGBColor(.6, -.8, 1);
		}
		else if (result < MAX_ITER / 10){
			return new RGBColor(1, .5, 0);
		}
		else if(result < MAX_ITER / 5){
			return new RGBColor(-1, .6, .6);
		}
		else if(result < MAX_ITER / 3){
			return new RGBColor(.4, -.28, .9);
		}
		else if(result < MAX_ITER / 2){
			return new RGBColor(.8, -.8, .4);
		}
		else {
			return new RGBColor(.4, -.4, .4);
		}

	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Julia)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		Julia j = (Julia) o;

		if(j.getZ().getReal() == this.getZ().getReal() && j.getZ().getImaginary() == this.getZ().getImaginary()){
			return true;
		}

		return false;

	}
}