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

    /**
     * Adds two complex numbers and returns
     * the result.
     * 
     * @param a, a complex number
     * @param b, a complex number
     * @return the sum 
     */
    public ComplexNumber add(ComplexNumber a, ComplexNumber b){
        //Add real portions
        double r = a.real + b.real;
        //Add imaginary portions
        double i = a.imaginary + b.imaginary;

        return new ComplexNumber(r, i);

    }

    /**
     * Subtracts two complex numbers and returns
     * the result.
     * 
     * @param a, a complex number
     * @param b, a complex number
     * @return the result
     */
    public ComplexNumber subtract(ComplexNumber a, ComplexNumber b){
        //Sub real portions
        double r = a.real - b.real;
        //Sub imaginary portions
        double i = a.imaginary - b.imaginary;

        return new ComplexNumber(r, i);

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
