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
    
    public RandomColor(){
    }

    /**
	 * Evaluates the expression by generating a random color.
     * 
	 * 
	 * @return the random RGBColor
	 */
    @Override
	public RGBColor evaluate(double x, double y) {
        Random rand = new Random();

		double red = -1.0 + 2 * rand.nextDouble();
		double green = -1.0 + 2 * rand.nextDouble();
		double blue = -1.0 + 2 * rand.nextDouble();

		return new RGBColor(red, green, blue);
	}
}
