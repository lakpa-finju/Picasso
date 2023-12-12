package picasso.parser.language.math;

public class ComplexNumber {
    
    //-------INSTANCE VARIABLES-------
    //Represents the real and imaginary parts of the complex number
    private double real;
    private double imaginary;

    public ComplexNumber(double r, double i) {
        this.real = r;
        this.imaginary = i;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    /**
     * Adds two complex numbers and returns
     * the result.
     * 
     * @param b, a complex number
     * @return the sum 
     */
    public ComplexNumber plus(ComplexNumber b){
        ComplexNumber a = this;
        //Add real portions
        double r = a.getReal() + b.getReal();
        //Add imaginary portions
        double i = a.getImaginary() + b.getImaginary();

        return new ComplexNumber(r, i);

    }

    /**
     * Subtracts two complex numbers and returns
     * the result.
     * 
     * @param b, a complex number
     * @return the result
     */
    public ComplexNumber minus(ComplexNumber b){
        ComplexNumber a = this;
        //Sub real portions
        double r = a.getReal() - b.getReal();
        //Sub imaginary portions
        double i = a.getImaginary() - b.getImaginary();

        return new ComplexNumber(r, i);

    }

    /**
     * Multiplies two complex numbers and returns
     * the result.
     * 
     * @param b, a complex number
     * @return the result
     */
    public ComplexNumber times(ComplexNumber b){
        ComplexNumber a = this;
        double re = (a.getReal() * b.getReal()) - (a.getImaginary() * b.getImaginary());
        double im = (a.getReal() * b.getImaginary()) + (a.getImaginary() * b.getReal());
        return new ComplexNumber(re, im);
    }

    /**
     * Squares this complex number and returns
     * the result.
     * 
     * @return the result
     */
    public ComplexNumber squared(){
        ComplexNumber a = this;
        return a.times(a);
    }

    /**
     * Returns the absolute value of this 
     * 
     * @return the result
     */
    public double abs() {
        return Math.hypot(real, imaginary);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(""); 
        str.append(real); 
        str.append(" + "); 
        str.append(imaginary);
        str.append("i");
        return str.toString();
    }
}
