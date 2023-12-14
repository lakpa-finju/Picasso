package picasso.parser.language.expressions;
import java.util.Random;

/**
 * Represents a function that generates a random color, 
 * no matter what parameters are used to evaluate it.
 * 
 * @author Liz Kent
 *
 */
public class RandomColor extends NoArgumentFunction{
    
	private double red, green, blue;
    public RandomColor(){
		Random rand = new Random();
		red = -1.0 + 2 * rand.nextDouble();
		green = -1.0 + 2 * rand.nextDouble();
		blue = -1.0 + 2 * rand.nextDouble();
    }

    /**
	 * Evaluates the expression by generating a random color.
     * 
	 * 
	 * @return the random RGBColor
	 */
    @Override 
	public RGBColor evaluate(double x, double y) {
		return new RGBColor(this.red, this.green, this.blue);
	}
}
